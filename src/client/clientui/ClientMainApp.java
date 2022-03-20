package client.clientui;

import client.network.ClientSession;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;


public class ClientMainApp extends Application {
    public static Stage mainStage;
    public static Socket serverSocket;
    public static ClientSession clientSession;
    public static MultiUI multiUI;
    public static ChooseUI choice;
    public static Game game;
    GridPane grid = new GridPane();
    public static String gameArr[];
    public ClientMainApp() {
        try {
            serverSocket = new Socket("localhost", 5000);
            clientSession = new ClientSession(serverSocket);
        } catch (IOException ex) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Connection problem");
            alert.setHeaderText(null);
            alert.setContentText("Connection lost");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Platform.exit();
            }
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn_log = new Button();
        btn_log.setText("Login");
        btn_log.setId("loginbtn");
        Button btn_signup = new Button();
        btn_signup.setText("Signup");
        btn_signup.setId("loginbtn");
        // add actions on buttons
        btn_log.setOnAction(event -> signin());
        btn_signup.setOnAction(event -> signup());
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        grid.add(btn_log , 0, 3);
        grid.add(btn_signup , 0 , 5);

        grid.setAlignment(Pos.CENTER);
        btn_log.setMaxWidth(Double.MAX_VALUE);
        btn_signup.setMaxWidth(Double.MAX_VALUE);
        Scene scene = new Scene(grid, 400, 350);
        scene.getStylesheets().add(SignUpUI.class.getResource("/res/clientmain.css").toExternalForm());
        primaryStage.setTitle("Register");
        primaryStage.setResizable(false);
        mainStage = primaryStage;
        mainStage.setScene(scene);
        mainStage.show();
        grid.requestFocus();
    }
   
    public static void signin() {
        SignInUI signIn = new SignInUI();
        try {
            signIn.start(mainStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void signup() {
        SignUpUI signUp = new SignUpUI();
        try {
            signUp.start(mainStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void changeStageSize(Window stage, int width, int height) {
        stage.setWidth(width);
        stage.setHeight(height);
    }
    @Override
    public void stop() throws IOException {
        try {
            game.disconnectGame(); //if the player is in a game with another player if it's not it will continue
        } catch (Exception e) {
            e.printStackTrace();
        }
        clientSession.endConnection();
        Platform.exit();
    }
    
    //Alert functions to show msgs to users
    public static void alertLoser() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lose");
        alert.setHeaderText(null);
        alert.setContentText("try another time");
        Optional<ButtonType> result = alert.showAndWait();
        System.out.println(result.get());
        if (result.get() == ButtonType.OK) {
            ClientMainApp.multiUI.start(ClientMainApp.mainStage);
            ClientMainApp.clientSession.startMultiGame();
        }
    }
    public static void connectionError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Connection problem");
        alert.setHeaderText(null);
        alert.setContentText("OnlinePlayer Has disconnected, game has been saved");
        Optional<ButtonType> result = alert.showAndWait();
        System.out.println(result.get());
        if (result.get() == ButtonType.OK) {
            try {
                ClientMainApp.multiUI.start(ClientMainApp.mainStage);
                ClientMainApp.clientSession.startMultiGame(); // if other player disconnected
            } catch (IOException ex) {
                disconnectServer();
            }
        }
    }
    public static void disconnectServer() { //WHEN server disconnect 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Connection problem");
        alert.setHeaderText(null);
        alert.setContentText("Disconnect from server");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        };
    }
    public static void removeBusyPlayer(String p) { // Remove player from list when he logout or start playing
        if (multiUI.acceptInvitationObserveList.contains(p)) {
            multiUI.acceptInvitationObserveList.remove(p);
        }
        if (multiUI.sendIvitationObservableList.contains(p)) {
            multiUI.sendIvitationObservableList.remove(p);
            System.out.println((String) p);
        }
    }
    public static void draw()
    {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Draw");
        alert.setHeaderText(null);
        alert.setContentText("No winner!!! Try again!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        	ClientMainApp.multiUI.start(ClientMainApp.mainStage);
        }
    }
    public static void repeated(String msg, String mode) { //WHEN server disconnect 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        switch (mode) {
            case "signin":
                ClientMainApp.mainStage.setScene(SignInUI.scene);
                ClientMainApp.signin();
                break;
            case "signup":
                ClientMainApp.signup();
                break;
        }
        alert.showAndWait();
    }
    public static void showSavedGameExist() { //WHEN server disconnect 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("There is a saved SavedGame");
        alert.setHeaderText(null);
        alert.setContentText("You will continue the past game");
        alert.showAndWait();
        
    }
    
    public static void showNotification(String player){
         Notifications.create()
              .text(player + " is online")
              .showInformation();
    }
       
    //starting the stage
    public static void main(String[] args) {
        launch(args);
    }
}
