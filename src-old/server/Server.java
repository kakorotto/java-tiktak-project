package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import req.Request;
import req.RequestType;

public class Server {

    ObjectOutputStream sendingStream;
    ArrayList<ObjectOutputStream> clients = new ArrayList<>();
    ServerSocket gameServer;
    Socket playerSocket;
    public ServerSession connection;
    Thread thread;
    public static List<OnlinePlayer> onlinePlayers = new ArrayList<>();
//    public static ArrayList<OnlinePlayer> onlineBusyPlayers=new ArrayList<>();

    public void startServer() {
        try {
            gameServer = new ServerSocket(5000);
            thread = new Thread(() -> {
                while (true) {
                    try {
                        playerSocket = gameServer.accept();
                        sendingStream = new ObjectOutputStream(playerSocket.getOutputStream());
                        clients.add(sendingStream);
                        connection = new ServerSession(playerSocket, sendingStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
                        thread.setDaemon(true);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
//            Logger.getLogger(ServerFunctoins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void stopServer() {
        try {
            thread.stop();
            for (int i = 0; i < clients.size(); i++) {
                Request request = new Request(RequestType.SERVER_DISCONNECTED);
                clients.get(i).writeObject(request);
            }
            clients.clear();
            gameServer.close();
            if(!Server.onlinePlayers.isEmpty())
            {
            	connection.disconnectServer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
