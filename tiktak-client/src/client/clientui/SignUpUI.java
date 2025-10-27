
package client.clientui;

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

public class SignUpUI {

    GridPane grid = new GridPane();

    public void start(Stage primaryStage) {
    	//Login btn
        Button btn = new Button();
        btn.setText("Register");
        btn.setId("loginbtn");
        
        Button back = new Button();
        back.setText("Back");
        back.setId("loginbtn");

        // text field
        TextField usernameField = new TextField();
        usernameField.setId("UssText");
        usernameField.setPromptText("Enter your Name");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setId("PasssText");
        passwordField.setPromptText("Enter your Password");

        btn.setOnAction(event -> {
            if (!(usernameField.getText().equals("") || passwordField.getText().equals(""))) {
                try {
                    ClientMainApp.clientSession.signup(usernameField.getText(), passwordField.getText());
                    Thread.sleep(1000);
                    if ("success".equals(ClientMainApp.clientSession.return_response())) {
                        SignInUI signInUI = new SignInUI();
                        showAlert("Registration Succeeded");
                        try {
                            signInUI.start(ClientMainApp.mainStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        showAlert("Registration Failed");
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            } else {
                showAlert("Username Or PassWord can`t be empty");
            }
        });
        
        back.setOnAction(event -> {
            try {
                ClientMainApp clientMainApp = new ClientMainApp();
                clientMainApp.start(ClientMainApp.mainStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // Textfield
        grid.add(usernameField, 2 , 3);
        grid.add(passwordField,2  , 4);
        grid.add(btn , 2, 5);
        grid.add(back , 2 , 6);
        
        btn.setMaxWidth(Double.MAX_VALUE);
        back.setMaxWidth(Double.MAX_VALUE);
        
        Scene scene = new Scene(grid, 350, 350);
        scene.getStylesheets().add(SignUpUI.class.getResource("/res/signup.css").toExternalForm());

        primaryStage.setTitle("Registration");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        grid.requestFocus();

    }

    private void showAlert(String alertMessage) {
        Alert alert = new Alert(AlertType.INFORMATION, alertMessage, ButtonType.OK);
        alert.setTitle("Succeeded");
        alert.setHeaderText(null);
        alert.setContentText(alertMessage);
        alert.show();
    }
}
