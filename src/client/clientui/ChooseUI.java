package client.clientui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//This class is for choosing between single and multiPlayer
public class ChooseUI {

    GridPane grid = new GridPane();
    
    public void start(Stage primaryStage) {

    	Button signInBtn = new Button();
        signInBtn.setText("Single Mode");
        signInBtn.setId("loginbtn");
        
        Button signUpBtn = new Button();
        signUpBtn.setText("Multi Mode");
        signUpBtn.setId("loginbtn");
        
        // add actions on buttons
        signInBtn.setOnAction(event -> {
            LevelsUI levelsUI = new LevelsUI();
            try {
                levelsUI.start(ClientMainApp.mainStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        signUpBtn.setOnAction(event -> {
            ClientMainApp.multiUI = new MultiUI();
            try {
                ClientMainApp.clientSession.startMultiGame();
                ClientMainApp.multiUI.start(ClientMainApp.mainStage);
            } catch (Exception e) {
            }
        });

        grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 10, 0, 10));
	    grid.add(signInBtn, 0, 0);
	    grid.add(signUpBtn, 0 , 1);
	    grid.setAlignment(Pos.CENTER);
	    signInBtn.setMaxWidth(Double.MAX_VALUE);
	    signUpBtn.setMaxWidth(Double.MAX_VALUE);

        Scene scene = new Scene(grid, 400, 350);
        scene.getStylesheets().add(SignUpUI.class.getResource("/res/chooseui.css").toExternalForm());

        primaryStage.setTitle("SavedGame modes");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        grid.requestFocus();
    }
}

