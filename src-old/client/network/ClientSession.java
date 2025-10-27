package client.network;

import req.Request;
import req.RequestType;
import client.clientui.ClientMainApp;
import client.clientui.Game;
import javafx.application.Platform;
import server.SavedGame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSession extends Thread {

    //CreateObject of gui
    Socket serverSocket;
    public ObjectInputStream recievingStream;
    public ObjectOutputStream sendingStream;
    Request request;
    String response;
    ArrayList<String> online_players;
    ArrayList<String> players_invite_me;
    String source;
    String myName;
    String emptyArr[] = {"", "", "", "", "", "", "", "", ""}; // this is used to empty saved game arr by cloning this
    public ClientSession(Socket playerSocket) throws IOException {
        response = "";
        this.serverSocket = playerSocket;
        sendingStream = new ObjectOutputStream(playerSocket.getOutputStream());
        recievingStream = new ObjectInputStream(playerSocket.getInputStream());
        setDaemon(true);
        start();
        players_invite_me = new ArrayList<>();
    }
    public void run() {
        while (true) {
            try {
                request = (Request) recievingStream.readObject();
                requestHandler(request);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void requestHandler(Request request) throws IOException {
        //requests that can be sent from the server
        //connection lost with the player
        if (request.getType() == RequestType.SIGN_UP_SUCCESS || request.getType() == RequestType.LOGIN_SUCCESS) {
            response = "success";
        } else if (request.getType() == RequestType.LOGIN_FAILED) {
            response = "failed";
        } else if (request.getType() == RequestType.ONLINE_PLAYERS) {
            handleOnlinePlayers(request);
        } else if (request.getType() == RequestType.OFFLINE_PLAYERS) {
            handleOfflinePlayers(request);
        } else if (request.getType() == RequestType.RECEIVE_INVITATION) {
            handleInvitation(request);
        } else if (request.getType() == RequestType.LOSE) {
            LoseHandler();
        } else if (request.getType() == RequestType.RECEIVE_REPLY) {
            handleReply(request);
        } else if (request.getType() == RequestType.RECEIVE_MOVE) {
            handleMove(request);
        } else if (request.getType() == RequestType.RECEIVE_MSG) {
            handleMsg(request);
        } else if (request.getType() == RequestType.CONNECTION_LOST || request.getType() == RequestType.QUIT_GAME) { // when a player press back button
            saveGameToServer();
            Platform.runLater(ClientMainApp::connectionError);
        } else if (request.getType() == RequestType.SERVER_DISCONNECTED) {//disconnect from server 
            saveGameToServer();
            Platform.runLater(ClientMainApp::disconnectServer);
        } else if (request.getType() == RequestType.BUSY_PLAYER) { //remove the busy player from the lists
            Platform.runLater(() -> ClientMainApp.removeBusyPlayer(request.getData("busy")));
        } else if (request.getType() == RequestType.REPEATED_LOGIN) {
            Platform.runLater(() -> ClientMainApp.repeated("You are already logged in", "signin"));
        } else if (request.getType() == RequestType.REPEATED_USER) {
            Platform.runLater(() -> ClientMainApp.repeated("You are already registered", "signup"));
        } else if (request.getType() == RequestType.SAVED_GAME) { //sending saved game from the server
            Platform.runLater(() -> {
                ClientMainApp.showSavedGameExist();
                ClientSession.this.loadGame(request);
            });
        } else if (request.getType() == RequestType.PLAYER_X) { // choosing playerX for saved SavedGame
            ClientMainApp.game.isPlayer = true;
            ClientMainApp.game.isOpponent = true;
        } else if (request.getType() == RequestType.PLAYER_O) {
            ClientMainApp.game.isOpponent = true;
            ClientMainApp.game.isPlayer = false;
        } else if (request.getType() == RequestType.BOARD) {
            hundle_tie();
        } else if (request.getType() == RequestType.NOTIFICATION) {
            Platform.runLater(() -> ClientMainApp.showNotification(request.getData("online")));
        }
    }
    private void hundle_tie() {
    	Platform.runLater(ClientMainApp::draw);
	}
	public void sendTie() throws IOException  {
    	Request tie = new Request(RequestType.BOARD);
        sendingStream.writeObject(tie);
    }
    public void LoseHandler() {
        Platform.runLater(() -> {
            try {
                ClientMainApp.alertLoser();
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        });
    }
    // send sign up Request
    public void signup(String loginName, String Pass) throws IOException {
        Request signUpRequest = new Request(RequestType.SIGNUP);
        signUpRequest.setData("username", loginName);
        signUpRequest.setData("pass", Pass);
        sendingStream.writeObject(signUpRequest);
    }
    public String return_response() {
        return response;
    }
    public void login(String loginName, String Pass) throws IOException {
        source = loginName;
        Request loginRequest = new Request(RequestType.LOGIN);
        loginRequest.setData("username", loginName);
        loginRequest.setData("pass", Pass);
        sendingStream.writeObject(loginRequest);
    }
    //get online players and show them on gui
    public void handleOnlinePlayers(Request online_request) {
        online_players = online_request.get_online_Data("online_players");
        Platform.runLater(() -> {
            ClientMainApp.multiUI.sendIvitationObservableList.clear();
            for (String online_player : online_players) {
                if (!online_player.equals(source)) {
                    ClientMainApp.multiUI.sendIvitationObservableList.add(online_player);
                }
            }
        });
    }
    private void handleOfflinePlayers(Request request) {
        ArrayList<String> players = request.get_online_Data("offlinePlayers");
        Platform.runLater(() -> {
            ClientMainApp.multiUI.offlinePeople.clear();
            ClientMainApp.multiUI.offlinePeople.addAll(players);
        });
    }
    public void sendInvitation(String name) throws IOException {
        Request inviteRequest = new Request(RequestType.SEND_INVITATION);
        inviteRequest.setData("source", source);
        inviteRequest.setData("destination", name);
        sendingStream.writeObject(inviteRequest);
    }
    public void handleInvitation(Request request) {
        String sourceOfInv = request.getData("source");
        Platform.runLater(() -> {
            ClientMainApp.multiUI.acceptInvitationObserveList.clear();
            ClientMainApp.multiUI.acceptInvitationObserveList.add(sourceOfInv);
        });
    }
    public void sendReply(String playerName, String replyResult) throws IOException {
        Request reply = new Request(RequestType.SEND_REPLY);
        if ("accept".equals(replyResult)) {
            acceptInvitation(playerName, true, true);
        }
        reply.setData("reply", replyResult);
        reply.setData("destination", playerName);
        sendingStream.writeObject(reply);
    }
    public void handleReply(Request request) {
        String result = request.getData("reply");
        String player = request.getData("source");
        if ("accept".equals(result)) {
            acceptInvitation(player, false, true);
        }
    }
    //this function send the request to the server to connect the two players
    public void acceptInvitation(String player, Boolean x, Boolean y) {
        ClientMainApp.gameArr = emptyArr.clone();
        Request r = new Request(RequestType.ACCEPT_INVITATION);
        r.setData("destination", player);
        Platform.runLater(() -> {
            ClientMainApp.game = new Game(x, y);
            try {
                ClientMainApp.game.start(ClientMainApp.mainStage);
                sendingStream.writeObject(r);
                request = new Request(RequestType.CHECK_GAME);
                ClientMainApp.clientSession.sendingStream.writeObject(request);
            } catch (IOException ex) {
                Logger.getLogger(ClientSession.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void endConnection() throws IOException {
        request = new Request(RequestType.END_SESSION);
        sendingStream.writeObject(request);
    }
    //Handling gaming
    public void sendMove(int x, int y, String playable) throws IOException {
        Request move = new Request(RequestType.SEND_MOVE);
        move.setData("x", Integer.toString(x));
        move.setData("y", Integer.toString(y));
        move.setData("current_player", playable);
        saveIntoArr(x, y, playable);
        sendingStream.writeObject(move);
    }
    private void handleMove(Request request) {
        int x = Integer.parseInt(request.getData("x"));
        int y = Integer.parseInt(request.getData("y"));
        String playable = request.getData("current_player");
        saveIntoArr(x, y, playable);
        Platform.runLater(() -> ClientMainApp.game.setMove(x, y, playable));
    }
    //Handle Chat
    public void sendMsg(String msg) throws IOException {
        Request chatMsg = new Request(RequestType.SEND_MSG);
        chatMsg.setData("msg", msg);
        sendingStream.writeObject(chatMsg);
    }
    private void handleMsg(Request request) {
        String msg = request.getData("msg");
        Platform.runLater(() -> ClientMainApp.game.setMsg(msg));
    }
    public void quitGame() throws IOException {
        request = new Request(RequestType.QUIT_GAME);
        sendingStream.writeObject(request);
    }
    public void sendWin() throws IOException {
        request = new Request(RequestType.WIN);
        sendingStream.writeObject(request);
    }
    //this is used to send the server request to start the multi game to check if there is saved game
    //or to remove it from the online player list
    public void startMultiGame() throws IOException {
        request = new Request(RequestType.MULTI_GAME);
        sendingStream.writeObject(request);
    }
    //convert the 2 dimention arr to 1 dimention arr that will be saved
    private void saveIntoArr(int x, int y, String player) {
        int pos = 0;
        if (x == 0) {
            if (y == 0) {
                pos = 0;
            } else if (y == 1) {
                pos = 1;
            } else if (y == 2) {
                pos = 2;
            }
        } else if (x == 1) {
            if (y == 0) {
                pos = 3;
            } else if (y == 1) {
                pos = 4;
            } else if (y == 2) {
                pos = 5;
            }
        } else if (x == 2) {
            if (y == 0) {
                pos = 6;
            } else if (y == 1) {
                pos = 7;
            } else if (y == 2) {
                pos = 8;
            }
        }
        ClientMainApp.gameArr[pos] = player;
    }
    public void saveGameToServer() throws IOException {
        request = new Request(RequestType.SAVE_GAME);
        request.setGameData("game", ClientMainApp.gameArr);
        sendingStream.writeObject(request);
    }
    //convert 1d array to be showed on the scene
    private void loadGame(Request request) {
        SavedGame loadedSavedGame = request.getSavedGame("game");
        String returnedArr[] = {"", "", "", "", "", "", "", "", ""};
        returnedArr[0] = loadedSavedGame.pos11;
        returnedArr[1] = loadedSavedGame.pos12;
        returnedArr[2] = loadedSavedGame.pos13;
        returnedArr[3] = loadedSavedGame.pos21;
        returnedArr[4] = loadedSavedGame.pos22;
        returnedArr[5] = loadedSavedGame.pos23;
        returnedArr[6] = loadedSavedGame.pos31;
        returnedArr[7] = loadedSavedGame.pos32;
        returnedArr[8] = loadedSavedGame.pos33;
        ClientMainApp.gameArr = returnedArr.clone();
        if (!"".equals(loadedSavedGame.pos11)) {
            Platform.runLater(() -> ClientMainApp.game.setMove(0, 0, loadedSavedGame.pos11));
        }
        if (!"".equals(loadedSavedGame.pos12)) {
            Platform.runLater(() -> ClientMainApp.game.setMove(0, 1, loadedSavedGame.pos12));
        }
        if (!"".equals(loadedSavedGame.pos13)) {
            Platform.runLater(() -> ClientMainApp.game.setMove(0, 2, loadedSavedGame.pos13));
        }
        if (!"".equals(loadedSavedGame.pos21)) {
            Platform.runLater(() -> ClientMainApp.game.setMove(1, 0, loadedSavedGame.pos21));
        }
        if (!"".equals(loadedSavedGame.pos22)) {
            Platform.runLater(() -> ClientMainApp.game.setMove(1, 1, loadedSavedGame.pos22));
        }
        if (!"".equals(loadedSavedGame.pos23)) {
            Platform.runLater(() -> ClientMainApp.game.setMove(1, 2, loadedSavedGame.pos23));
        }
        if (!"".equals(loadedSavedGame.pos31)) {
            Platform.runLater(() -> ClientMainApp.game.setMove(2, 0, loadedSavedGame.pos31));
        }
        if (!"".equals(loadedSavedGame.pos32)) {
            Platform.runLater(() -> ClientMainApp.game.setMove(2, 1, loadedSavedGame.pos32));
        }
        if (!"".equals(loadedSavedGame.pos33)) {
            Platform.runLater(() -> ClientMainApp.game.setMove(2, 2, loadedSavedGame.pos33));
        }
    }

}
