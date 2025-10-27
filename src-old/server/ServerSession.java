package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import db.DatabaseConnection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.stream.Collectors;

import db.Player;
import req.Request;
import req.RequestType;

public class ServerSession extends Thread {

    OnlinePlayer onlinePlayer;
    OnlinePlayer playerTwo;
    Socket playerSocket;
    ObjectInputStream receivingStream;
    ObjectOutputStream sendingStream;
    Request request;
    DatabaseConnection databaseConnection;
    SavedGame loadedSavedGame;
    int countX = 0;
    int countO = 0;
    ArrayList<String> offlinePlayers;
    //In this constructor i create recieveing from player
    public ServerSession(Socket ps, ObjectOutputStream sendingStream) throws IOException {
        playerSocket = ps;
        receivingStream = new ObjectInputStream(ps.getInputStream());
        this.sendingStream = sendingStream;
        databaseConnection = new DatabaseConnection();
        setDaemon(true);
        start();
    }
    public void run() {
        while (true) {
            try {
                request = (Request) receivingStream.readObject();
                requestHandler(request);
            }  catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void requestHandler(Request request) throws IOException, SQLException {
        if (request.getType() == RequestType.SIGNUP) {
            try { signUpHandler(request); } catch (SQLException e) { e.printStackTrace(); }
        } else if (request.getType() == RequestType.BOARD) {
            boardHandler();
        } else if (request.getType() == RequestType.LOGIN) {
            loginHandler(request);
        } else if (request.getType() == RequestType.ONLINE_PLAYERS) {
            getOnlinePlayers();
        } else if (request.getType() == RequestType.SEND_INVITATION) {
            invite(request);
        } else if (request.getType() == RequestType.MULTI_GAME) {
            handleMultiRequest();
        } else if (request.getType() == RequestType.CHAT) {
            chatHandler(request);
        } else if (request.getType() == RequestType.SEND_MOVE) {
            gameHandler(request);
        } else if (request.getType() == RequestType.SEND_REPLY) {
            reply(request);
        } else if (request.getType() == RequestType.ACCEPT_INVITATION) {
            acceptInvitation(request);
        } else if (request.getType() == RequestType.SEND_MSG) {
            chatHandler(request);
        } else if (request.getType() == RequestType.END_SESSION) {
            closeConnection();
        } else if (request.getType() == RequestType.END_GAME) { //End the game while playing
            endGame();
        } else if (request.getType() == RequestType.QUIT_GAME) {
            quitGame();
        } else if (request.getType() == RequestType.WIN) {
            winnerHandler();
        } else if (request.getType() == RequestType.SAVE_GAME) {
            saveGame(request.getGameData("game"));
        } else if (request.getType() == RequestType.CHECK_GAME) { // check for saved game
            loadedSavedGame = checkSavedGame();
            countX = 0;
            countO = 0;
            //Check the one who has the turn
            if (loadedSavedGame != null) {
                checkPlayer(loadedSavedGame.pos11);
                checkPlayer(loadedSavedGame.pos12);
                checkPlayer(loadedSavedGame.pos13);
                checkPlayer(loadedSavedGame.pos21);
                checkPlayer(loadedSavedGame.pos22);
                checkPlayer(loadedSavedGame.pos23);
                checkPlayer(loadedSavedGame.pos31);
                checkPlayer(loadedSavedGame.pos32);
                checkPlayer(loadedSavedGame.pos33);
                //this is used to set players then the game is retrieved
                if (countX == countO) {
                    if (loadedSavedGame.player1.equals(onlinePlayer.playerName)) {
                        request = new Request(RequestType.PLAYER_X);
                        onlinePlayer.outputStream.writeObject(request);
                        request = new Request(RequestType.PLAYER_O);
                        playerTwo.outputStream.writeObject(request);
                    } else {
                        request = new Request(RequestType.PLAYER_O);
                        onlinePlayer.outputStream.writeObject(request);
                        request = new Request(RequestType.PLAYER_X);
                        playerTwo.outputStream.writeObject(request);
                    }
                } else if (countX > countO) {
                    if (loadedSavedGame.player1.equals(onlinePlayer.playerName)) {
                        request = new Request(RequestType.PLAYER_O);
                        onlinePlayer.outputStream.writeObject(request);
                        request = new Request(RequestType.PLAYER_X);
                        playerTwo.outputStream.writeObject(request);
                    } else {
                        request = new Request(RequestType.PLAYER_X);
                        onlinePlayer.outputStream.writeObject(request);
                        request = new Request(RequestType.PLAYER_O);
                        playerTwo.outputStream.writeObject(request);
                    }
                }
                //return the saved game
                request = new Request(RequestType.SAVED_GAME);
                request.setSaveddGame("game", loadedSavedGame);
                sendingStream.writeObject(request);
                playerTwo.outputStream.writeObject(request);
            }
        }
    }
    //check the current player after loading the game
    private void boardHandler() throws IOException {
    	Request tie = new Request(RequestType.BOARD);
        playerTwo.outputStream.writeObject(tie);
    }
    private void checkPlayer(String player) {
        if ("X".equals(player)) {
            countX++;
        } else if ("O".equals(player)) {
            countO++;
        }
    }
    private void winnerHandler() throws IOException {
        try {
            databaseConnection.update(onlinePlayer.playerName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request = new Request(RequestType.LOSE);
        playerTwo.outputStream.writeObject(request);
    }
    //this is used for sign up 
    public void signUpHandler(Request signUpRequest) throws IOException, SQLException {
        String user_name = signUpRequest.getData("username");
        String user_pass = signUpRequest.getData("pass");
        ArrayList<Player> players = databaseConnection.getAll();
        for (Player player : players) {
            if (player.username.equals(user_name)) {
                request = new Request(RequestType.REPEATED_USER);
                sendingStream.writeObject(request);
                return;
            }
        }
        try {
            databaseConnection.insert(user_name, user_pass, "1", 0);
            Request signUpSuccessRequest = new Request(RequestType.SIGN_UP_SUCCESS);
            sendingStream.writeObject(signUpSuccessRequest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //login and send the online players to the users
    public void loginHandler(Request loginRequest) throws IOException {
        String user_name = loginRequest.getData("username");
        String user_pass = loginRequest.getData("pass");
        try {
            ArrayList<Player> players = databaseConnection.getAll();
            boolean flag = false;
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).username.equals(user_name)
                        && players.get(i).pass.equals(user_pass)) {
                    flag = true;
                    break;
                }
            }
            boolean isRepeatedUser = false;
            for (int i = 0; i < Server.onlinePlayers.size(); i++) {
                if (Server.onlinePlayers.get(i).playerName.equals(user_name)) {
                    request = new Request(RequestType.REPEATED_LOGIN);
                    try {
                        isRepeatedUser = true;
                        sendingStream.writeObject(request);
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (!isRepeatedUser) {
                if (flag) {
                    onlinePlayer = new OnlinePlayer(receivingStream, sendingStream, loginRequest.getData("username"), "online");
                    Server.onlinePlayers.add(onlinePlayer);
                    Request loginSuccessRequest = new Request(RequestType.LOGIN_SUCCESS);
                    sendingStream.writeObject(loginSuccessRequest);
                } else {
                    Request loginSuccessRequest = new Request(RequestType.LOGIN_FAILED);
                    sendingStream.writeObject(loginSuccessRequest);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getOnlinePlayers() throws IOException {
        Request onlinePlayerRequest = new Request(RequestType.ONLINE_PLAYERS);
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < Server.onlinePlayers.size(); i++) {
            arr.add(Server.onlinePlayers.get(i).playerName);
        }
        onlinePlayerRequest.set_online_Data("online_players", arr);
        sendingStream.writeObject(onlinePlayerRequest);
    }
    public void invite(Request playerTwoData) { //ClientSendPlayerData //derver will handle it 
        String requestedPlayer = playerTwoData.getData("destination");
        Server.onlinePlayers.forEach(player -> {
            if (requestedPlayer.equals(player.playerName)) {
                try {
                    Request invitation = new Request(RequestType.RECEIVE_INVITATION);
                    invitation.setData("source", playerTwoData.getData("source"));
                    player.outputStream.writeObject(invitation);
                } catch (IOException ex) {
                    Logger.getLogger(ServerSession.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    //handle reply
    public void reply(Request replyData) {
        String destinationName = replyData.getData("destination");
        String replyResult = replyData.getData("reply");
        Server.onlinePlayers.forEach(player -> {
            if (destinationName.equals(player.playerName)) {
                try {
                    Request reply = new Request(RequestType.RECEIVE_REPLY);
                    reply.setData("source", onlinePlayer.playerName);
                    reply.setData("reply", replyResult);
                    player.outputStream.writeObject(reply);
                } catch (IOException ex) {
                    Logger.getLogger(ServerSession.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
    }
    //sending game moves
    private void gameHandler(Request request) {
        String x = request.getData("x");
        String y = request.getData("y");
        String playable = request.getData("current_player");
        Request game = new Request(RequestType.RECEIVE_MOVE);
        game.setData("x", x);
        game.setData("y", y);
        game.setData("current_player", playable);
        try {
            playerTwo.outputStream.writeObject(game);
        } catch (Exception e) {
            request = new Request(RequestType.CONNECTION_LOST);
            try {
                sendingStream.writeObject(request);
            } catch (IOException ex) {
                Logger.getLogger(ServerSession.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void chatHandler(Request request) {
        try {
            String msg = request.getData("msg");
            Request chatMsg = new Request(RequestType.RECEIVE_MSG);
            chatMsg.setData("msg", msg);
            playerTwo.outputStream.writeObject(chatMsg);
        } catch (IOException e) {
            request = new Request(RequestType.CONNECTION_LOST);
            try {
                sendingStream.writeObject(request);
            } catch (IOException ex) {
                Logger.getLogger(ServerSession.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //connect the two players together 
    private void acceptInvitation(Request q) {
        String playerTwoAccepted = q.getData("destination");
        Server.onlinePlayers.forEach(player -> {
            if (playerTwoAccepted.equals(player.playerName)) {
                playerTwo = player;
                try {
                    sendBusyPlayer(player.playerName); //Send to all players that this player is busy
                } catch (IOException ex) {
                    Logger.getLogger(ServerSession.class.getName()).log(Level.SEVERE, null, ex);
                }
                player.setSign('b'); // Set his sign to be busy
            }
        });
    }
    //to send the online and offline players to all people in multiplayer game mode
    private void handleMultiRequest() throws IOException, SQLException {
        onlinePlayer.setSign('d');
        sendNotification();
        sendOnlinePlayers();
        sendOfflinePlayers();
    }
    private void closeConnection() throws IOException, SQLException {
        // if a player close the window
        if (onlinePlayer != null) {
            Server.onlinePlayers.remove(onlinePlayer);
            sendBusyPlayer(onlinePlayer.playerName);
            sendOnlinePlayers();
            sendOfflinePlayers();
        }
        playerSocket.close();
    }
    public void disconnectServer() throws IOException {
        request = new Request(RequestType.SERVER_DISCONNECTED);
        sendingStream.writeObject(request);
    }
    private void endGame() throws IOException { //player disconnected
        request = new Request(RequestType.CONNECTION_LOST);
        playerTwo.outputStream.writeObject(request);
        playerTwo = null;
    }
    private void quitGame() throws IOException { //player quit
        request = new Request(RequestType.QUIT_GAME);
        playerTwo.outputStream.writeObject(request);
        playerTwo = null;
    }
    public void sendBusyPlayer(String name) throws IOException {
        request = new Request(RequestType.BUSY_PLAYER);
        request.setData("busy", name);
        for (int i = 0; i < Server.onlinePlayers.size(); i++) {
            if (Server.onlinePlayers.get(i).getSign() == 'd') {
                Server.onlinePlayers.get(i).outputStream.writeObject(request);
            }
        }
    }
    private void saveGame(String[] gameArr) throws SQLException {
        databaseConnection.insertNewGame(onlinePlayer.playerName, playerTwo.playerName,
                gameArr[0], gameArr[1], gameArr[2],
                gameArr[3], gameArr[4], gameArr[5],
                gameArr[6], gameArr[7], gameArr[8]);
    }
    //check for saved game when two players are connected
    private SavedGame checkSavedGame() throws SQLException {
        List<SavedGame> savedGames = databaseConnection.getGames();
        for (SavedGame savedGame : savedGames) {
            if ((savedGame.player1.equals(onlinePlayer.playerName) && savedGame.player2.equals(playerTwo.playerName))) {
                databaseConnection.removeSavedGame(onlinePlayer.playerName, playerTwo.playerName);
                return savedGame;
            }
        }
        return null;
    }
    //get offline players by getting the differences 
    public List<String> getOffline() throws SQLException {
        ArrayList<String> allPlayers = new ArrayList<>();
        ArrayList<String> onlinePlayers;
        ArrayList<Player> players = new DatabaseConnection().getAll();
        for (Player player : players) {
            allPlayers.add(player.username);
        }
        onlinePlayers = Server.onlinePlayers.stream().map(player -> player.playerName).collect(Collectors.toCollection(ArrayList::new));
        allPlayers.removeAll(onlinePlayers);
        return allPlayers;
    }
    public void sendOfflinePlayers() throws SQLException, IOException {
        offlinePlayers = (ArrayList<String>) getOffline();
        request = new Request(RequestType.OFFLINE_PLAYERS);
        request.set_online_Data("offlinePlayers", offlinePlayers);
        for (int i = 0; i < Server.onlinePlayers.size(); i++) {
            if (Server.onlinePlayers.get(i).getSign() == 'd') {
                Server.onlinePlayers.get(i).outputStream.writeObject(request);
            }
        }
    }
    public void sendOnlinePlayers() throws IOException {
        request = new Request(RequestType.ONLINE_PLAYERS);
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < Server.onlinePlayers.size(); i++) {
            if (Server.onlinePlayers.get(i).getSign() == 'd') {
                arr.add(Server.onlinePlayers.get(i).playerName);
            }
        }
        request.set_online_Data("online_players", arr);
        for (int i = 0; i < Server.onlinePlayers.size(); i++) {
            if (Server.onlinePlayers.get(i).getSign() == 'd') {
                Server.onlinePlayers.get(i).outputStream.writeObject(request);
            }
        }
    }
    private void sendNotification() throws IOException {
        request = new Request(RequestType.NOTIFICATION);
                request.setData("online", onlinePlayer.playerName);
        for (int i = 0; i < Server.onlinePlayers.size(); i++) {
            if (Server.onlinePlayers.get(i).getSign() == 'd'&& !Server.onlinePlayers.get(i).playerName.equals(onlinePlayer.playerName)) {
                Server.onlinePlayers.get(i).outputStream.writeObject(request);
            }
        }
        
        
    }
}
