package client.clientui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.application.Application.launch;
import static javafx.scene.layout.GridPane.setHalignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//this is used for the gui of multiplayer choosing
public class MultiUI {

    String current;
    String currentInvitation;
    public ObservableList<String> sendIvitationObservableList;
    public ObservableList<String> offlinePeople;
    ListView<String> invitePeopleListView;
    public ObservableList<String> acceptInvitationObserveList;
    public void start(Stage primaryStage) {
        //Send to online players a hint that i am online 
        //create and set the grid
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(20);
        grid.setPadding(new Insets(30, 20, 30, 20));
        //create the nodes
        Label invitePeople = new Label("People To Invite");
        invitePeople.setId("Label_Invite");
        Label invitedYou = new Label("People Who Invited You");
        invitedYou.setId("Label_Invite");
        Label offlinePeople = new Label("Offline");
        offlinePeople.setId("Label_Invite");

        
//        grid.setGridLinesVisible(true);
        
        Button inviteBtn = new Button("Invite");
        inviteBtn.setId("InviteBtn");
        Button acceptBtn = new Button("Accept");
        acceptBtn.setId("AcceptBtn");
        Button backBtn = new Button("Back");
        backBtn.setId("AcceptBtn");

        sendIvitationObservableList = FXCollections.observableArrayList();
        invitePeopleListView = new ListView<>(sendIvitationObservableList);
        invitePeopleListView.setId("listinvited");
        invitePeopleListView.setPrefSize(300, 300);
        invitePeopleListView.setOrientation(Orientation.VERTICAL);
        MultipleSelectionModel<String> sendInvitationModule = invitePeopleListView.getSelectionModel();
        sendInvitationModule.selectedItemProperty().addListener((ov, old_val, new_val) -> current = new_val);
        this.offlinePeople = FXCollections.observableArrayList();
        ListView<String> Off_players = new ListView<>(this.offlinePeople);
        Off_players.setId("listinvited");
        Off_players.setPrefSize(300, 300);
        Off_players.setOrientation(Orientation.VERTICAL);
        MultipleSelectionModel<String> lvModule = Off_players.getSelectionModel();
        lvModule.selectedItemProperty().addListener((ov, old_val, new_val) -> currentInvitation = new_val);
        acceptInvitationObserveList = FXCollections.observableArrayList();
        ListView<String> acceptInvitationListView = new ListView<>(acceptInvitationObserveList);
        acceptInvitationListView.setId("listinvited");
        acceptInvitationListView.setPrefSize(300, 300);
        acceptInvitationListView.setOrientation(Orientation.VERTICAL);
        MultipleSelectionModel<String> lv1Module = acceptInvitationListView.getSelectionModel();
        lv1Module.selectedItemProperty().addListener((ov, old_val, new_val) -> currentInvitation = new_val);

        inviteBtn.setOnAction(event -> {
            try {
                if (current != null) {
                    ClientMainApp.clientSession.sendInvitation(current);
                }
                current = null;
            } catch (IOException ex) {
                Logger.getLogger(MultiUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            showAlert("invitation sent successfully we will inform you if what is response",ButtonType.OK);
        });
        acceptBtn.setOnAction(event -> {
            if (currentInvitation != null) {
                try {
                    ClientMainApp.clientSession.sendReply(currentInvitation, "accept");
                    currentInvitation = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        backBtn.setOnAction(event -> {
            try {
                ChooseUI ca = new ChooseUI();
                ca.start(ClientMainApp.mainStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //added nodes of grid
        grid.add(invitePeople, 0, 0);
        grid.add(invitePeopleListView, 0, 1);
        grid.add(inviteBtn, 0, 2);
        grid.add(invitedYou, 4, 0);
        grid.add(offlinePeople, 0, 3);
        grid.add(acceptInvitationListView, 4, 1);
        grid.add(acceptBtn, 4, 2);
        backBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(backBtn, 4, 8 );
        grid.add(Off_players, 0, 4 , 1 , 5);
        
        //set alignment
        setHalignment(invitePeople, HPos.CENTER);
        setHalignment(invitedYou, HPos.CENTER);
        setHalignment(inviteBtn, HPos.CENTER);
        setHalignment(acceptBtn, HPos.CENTER);
        //setting the stage & scene
        Scene scene = new Scene(grid, 600, 600);
        scene.getStylesheets().add(SignUpUI.class.getResource("/res/multiui.css").toExternalForm());
        primaryStage.setTitle("Invitation");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
    public void showAlert(String mess,ButtonType buttonType) {
        Alert alert = new Alert(AlertType.INFORMATION, mess, buttonType);
        alert.setTitle("Invitation");
        alert.setHeaderText(null);
        alert.setContentText(mess);
        alert.show();
    }
}
