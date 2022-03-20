package client.clientui;

import java.net.UnknownHostException;

import client.single.HardLevel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import client.single.EasyLevel;
import client.single.MediumLevel;

//This is for gui of levels
public class LevelsUI {

    GridPane grid = new GridPane();



    public void start(Stage primaryStage) {

    	Button Easy = new Button();
        Easy.setText("Easy");
        Easy.setId("easy-btn");
        
        Button Meduim = new Button();
        Meduim.setText("Medium");
        Meduim.setId("medium-btn");
        
        Button Hard = new Button();
        Hard.setText("Hard");
        Hard.setId("hard-btn");
        
        Button Back = new Button();
        Back.setText("Back");
        Back.setId("back-btn");

        // add actions on buttons
        Easy.setOnAction(event -> {
            EasyLevel easyLevel = new EasyLevel();
            try {
                easyLevel.start(ClientMainApp.mainStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        Meduim.setOnAction(event -> {
            MediumLevel mediumLevel = new MediumLevel();
            try {
                mediumLevel.start(ClientMainApp.mainStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        Hard.setOnAction(event -> {
            HardLevel hardLevel = new HardLevel();
            try {
                hardLevel.start(ClientMainApp.mainStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        Back.setOnAction(event -> {
            try {
            ChooseUI chooseUI = new ChooseUI();
            chooseUI.start(ClientMainApp.mainStage);
        } catch (Exception e) {
            e.printStackTrace();
        }});

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.add(Easy , 0, 0);
        grid.add(Meduim , 0 , 1);
        grid.add(Hard , 0 , 2);
        grid.add(Back , 0 , 3);
        grid.setAlignment(Pos.CENTER);
        Easy.setMaxWidth(Double.MAX_VALUE);
        Meduim.setMaxWidth(Double.MAX_VALUE);
        Hard.setMaxWidth(Double.MAX_VALUE);
        Back.setMaxWidth(Double.MAX_VALUE);

        Scene scene = new Scene(grid, 400, 500);
        scene.getStylesheets().add(SignUpUI.class.getResource("/res/levelsui.css").toExternalForm());

        primaryStage.setTitle("SavedGame LevelsUI");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        grid.requestFocus();
    }
}
  
