
package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class LoginApp extends Application {
    
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
            connection.insertPlayerData(signuproot.userName.getText().toString(),signuproot.password.getText().toString(),signuproot.phone.getText().toString());
            primaryStage.setScene(loginscene);
            primaryStage.setTitle("Login");
                });
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
