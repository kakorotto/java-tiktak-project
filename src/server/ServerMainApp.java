package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Application;

import static javafx.application.Application.launch;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import client.clientui.SignUpUI;
import db.DatabaseConnection;
import db.Player;
import db.UserAccount;
//gui of the server 
public class ServerMainApp extends Application {
    
    Server server;
    @Override
    public void start(Stage primaryStage) throws Exception {
        server = new Server();
        TableView<UserAccount> table = new TableView<>();
        table.setId("table");
        // Create column UserName (Data type of String).
        TableColumn<UserAccount, String> userNameCol = new TableColumn<>("UserName");
        // Active Column
        TableColumn<UserAccount, Boolean> activeCol  = new TableColumn<>("Score");
   
        // Defines how to fill data for each cell.
        // Get value from property of UserAccount. .
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userNameCol.setId("UserCol");
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
        // Set Sort type for userName column
        userNameCol.setSortType(TableColumn.SortType.DESCENDING);
        // Display row data
        ObservableList<UserAccount> list = getUserList();
        table.setItems(list);
        table.getColumns().addAll(userNameCol, activeCol);
        GridPane root = new GridPane();
        root.setPadding(new Insets(5));

        //table.setMaxWidth(Double.MAX_VALUE);

        root.add(table , 0 , 1 );
        
        Button startBtn = new Button();
        startBtn.setText("Start Server");
        startBtn.setId("start-btn");
        Button stopBtn = new Button();
        stopBtn.setText("Stop Server");
        stopBtn.setId("stop-btn");

        // add actions on buttons
        startBtn.setOnAction(event -> {
            server.startServer();
            root.getChildren().remove(startBtn);
            root.add(stopBtn, 0, 4);
        });
        stopBtn.setOnAction(event -> {
            server.stopServer();
            root.getChildren().remove(stopBtn);
            root.add(startBtn, 0, 4);
        });
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(0, 10, 0, 10));
        root.add(startBtn, 0, 4 );
        root.setAlignment(Pos.CENTER);
        startBtn.setMaxWidth(Double.MAX_VALUE);
        stopBtn.setMaxWidth(Double.MAX_VALUE);
        Scene scene = new Scene(root,500, 650);
        scene.getStylesheets().add(SignUpUI.class.getResource("/res/serverui.css").toExternalForm());
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    private ObservableList<UserAccount> getUserList() throws SQLException {
        ArrayList<Player> players = new DatabaseConnection().getAll();
        ObservableList<UserAccount> list = FXCollections.observableArrayList();
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            UserAccount user1 = new UserAccount(i + 1L, p.username, p.score);
            list.add(user1);
        }
        return list;
    }

    @Override
    public void stop() {
        try {
            server.stopServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Platform.exit();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
