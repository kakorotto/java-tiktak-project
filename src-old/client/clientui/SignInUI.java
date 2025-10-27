package client.clientui;

import static javafx.application.Application.launch;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SignInUI {

    GridPane grid = new GridPane();

    public static Scene scene;

    public void start(Stage primaryStage) {
        //Login loginBtn
        Button loginBtn = new Button();
        loginBtn.setText("Login");
        loginBtn.setId("loginbtn");
        Button backBtn = new Button();
        backBtn.setText("Back");
        backBtn.setId("loginbtn");
        // text field
        TextField textField = new TextField();
        textField.setId("textField");
        textField.setPromptText("Enter your Name");
        PasswordField passwordField = new PasswordField();
        passwordField.setId("passwordField");
        passwordField.setPromptText("Enter your Password");
        loginBtn.setOnAction(event -> {
            if (!(textField.getText().equals("") || passwordField.getText().equals(""))) {
                try {
                    ClientMainApp.clientSession.login(textField.getText(), passwordField.getText());
                    Thread.sleep(1000); // it's used to wait for a reply from server
                    if ("success".equals(ClientMainApp.clientSession.return_response())) {
                        ClientMainApp.choice = new ChooseUI();
                        try {
                            ClientMainApp.choice.start(ClientMainApp.mainStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if ("failed".equals(ClientMainApp.clientSession.return_response())) {
                        showAlert();
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                showAlert();
            }
        });
        backBtn.setOnAction(event -> {
            try {
                ClientMainApp ca = new ClientMainApp();
                ca.start(ClientMainApp.mainStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.add(textField, 2, 3);
        grid.add(passwordField, 2, 4);
        grid.add(loginBtn, 2, 5);
        grid.add(backBtn, 2, 6);
        backBtn.setMaxWidth(Double.MAX_VALUE);
        loginBtn.setMaxWidth(Double.MAX_VALUE);

        scene = new Scene(grid, 350, 350);

        scene.getStylesheets().add(SignUpUI.class.getResource("/res/signin.css").toExternalForm());
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        grid.requestFocus();
    }
    private void showAlert() {
        Alert alert = new Alert(AlertType.WARNING, "Invalid Username Or Password", ButtonType.OK);
        alert.setTitle("Failed");
        alert.setHeaderText(null);
        alert.setContentText("Invalid Username Or Password");
        alert.show();
    }
    
}
