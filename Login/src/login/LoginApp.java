
package login;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static login.SignUpController.toHexString;


public class LoginApp extends Application {
    byte [] hashPasswowrd;
    String stringHashPassword;
    @Override
    public void start(Stage primaryStage) throws Exception {
      //  Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        LoginBase loginroot = new LoginBase(primaryStage)  ;
        Scene loginscene = new Scene(loginroot);
        primaryStage.setTitle("Login");
        Image image = new Image("login/xo-icon.png");
        primaryStage.getIcons().add(image);
        /////////////////////////////
        SignUp signuproot =new SignUp(primaryStage)  ;
        Scene signupScene = new Scene(signuproot);
        primaryStage.setTitle("SignUp!");
        loginroot.signup.setOnAction(e -> primaryStage.setScene(signupScene));  
        primaryStage.setScene(loginscene);
        primaryStage.show();
        /////////////////////////////
        signuproot.sginUp.setOnAction(e -> {
            login.ConnctionToDatabase connection = new  login.ConnctionToDatabase();
            connection.makeConection();
            try {
               hashPasswowrd=SignUpController.getSHA(signuproot.password.getText());
               stringHashPassword=SignUpController.toHexString(hashPasswowrd);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        connection.insertPlayerData(signuproot.userName.getText().toString(),stringHashPassword,signuproot.phone.getText().toString());
            primaryStage.setScene(loginscene);
            primaryStage.setTitle("Login");
                });
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
