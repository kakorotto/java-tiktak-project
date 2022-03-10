package login;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public  class LoginBase extends GridPane {

    protected final ColumnConstraints columnConstraints;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final RowConstraints rowConstraints3;
    protected final RowConstraints rowConstraints4;
    protected final RowConstraints rowConstraints5;
    protected final RowConstraints rowConstraints6;
    protected final RowConstraints rowConstraints7;
    protected final RowConstraints rowConstraints8;
    protected final RowConstraints rowConstraints9;
    protected final RowConstraints rowConstraints10;
    protected final RowConstraints rowConstraints11;
    protected final RowConstraints rowConstraints12;
    protected final RowConstraints rowConstraints13;
    protected final RowConstraints rowConstraints14;
    protected final TextField username;
    protected final PasswordField password;
    protected final Label label;
    protected final Button signup;
    protected final Separator separator;
    protected final ImageView imageView;
    protected final Button login;
    protected final Label validationLabel;
   
    public LoginBase(Stage loginStage) {

        columnConstraints = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        rowConstraints4 = new RowConstraints();
        rowConstraints5 = new RowConstraints();
        rowConstraints6 = new RowConstraints();
        rowConstraints7 = new RowConstraints();
        rowConstraints8 = new RowConstraints();
        rowConstraints9 = new RowConstraints();
        rowConstraints10 = new RowConstraints();
        rowConstraints11 = new RowConstraints();
        rowConstraints12 = new RowConstraints();
        rowConstraints13 = new RowConstraints();
        rowConstraints14 = new RowConstraints();
        username = new TextField();
        password = new PasswordField();
        label = new Label();
        signup = new Button();
        separator = new Separator();
        imageView = new ImageView();
        login = new Button();
        validationLabel = new Label();

        setAlignment(javafx.geometry.Pos.CENTER);
        setHgap(10.0);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(400.0);
        setMinHeight(USE_PREF_SIZE);
        setPrefHeight(500.0);
        setPrefWidth(600.0);
        setStyle("-fx-border-color: blue; -fx-background-color: white;");
        setVgap(10.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.ALWAYS);
        columnConstraints.setMaxWidth(Double.MAX_VALUE);
        columnConstraints.setMinWidth(200.0);
        columnConstraints.setPrefWidth(200.0);

        rowConstraints.setMaxHeight(48.80001220703126);
        rowConstraints.setMinHeight(31.999993896484384);
        rowConstraints.setPrefHeight(31.999993896484384);

        rowConstraints0.setMaxHeight(21.600006103515625);
        rowConstraints0.setMinHeight(4.79998779296875);
        rowConstraints0.setPrefHeight(4.799999999999997);

        rowConstraints1.setMaxHeight(0.0);
        rowConstraints1.setMinHeight(0.0);
        rowConstraints1.setPrefHeight(0.0);




        rowConstraints8.setMinHeight(10.0);
        rowConstraints8.setPrefHeight(30.0);
        rowConstraints8.setVgrow(javafx.scene.layout.Priority.SOMETIMES);


        rowConstraints11.setMinHeight(10.0);
        rowConstraints11.setPrefHeight(30.0);
        rowConstraints11.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints13.setMinHeight(50.0);
        rowConstraints13.setPrefHeight(30.0);
        rowConstraints13.setValignment(javafx.geometry.VPos.CENTER);
        rowConstraints13.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        setPadding(new Insets(40.0, 40.0, 30.0, 40.0));

        GridPane.setHalignment(username, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(username, 9);
        username.setMaxWidth(200.0);
        username.setMinHeight(35.0);
        username.setPrefHeight(60.0);
        username.setPromptText("Enter your name");
        username.setStyle("-fx-background-color: F7F3F2; -fx-background-radius: 10;");

        GridPane.setHalignment(password, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(password, 12);
        password.setMaxWidth(200.0);
        password.setMinHeight(35.0);
        password.setPrefHeight(60.0);
        password.setPromptText("Enter your password");
        password.setStyle("-fx-background-color: F7F3F2; -fx-background-radius: 10;");

        GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setText("Login");
        label.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
        label.setTextFill(javafx.scene.paint.Color.valueOf("#4262a1"));
        label.setFont(new Font("Arial Rounded MT Bold", 28.0));
        label.setPadding(new Insets(10.0, 0.0, 10.0, 0.0));
        label.setOpaqueInsets(new Insets(0.0));
        GridPane.setMargin(label, new Insets(0.0));

        GridPane.setHalignment(signup, javafx.geometry.HPos.RIGHT);
        GridPane.setRowIndex(signup, 14);
        signup.setAlignment(javafx.geometry.Pos.CENTER);
        signup.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        signup.setMaxHeight(30.0);
        signup.setMaxWidth(100.0);
        signup.setMinWidth(100.0);
        signup.setMnemonicParsing(false);
        signup.setStyle("-fx-background-radius: 10; -fx-background-color: #335397;");
        signup.setText("Sign Up");
        signup.setTextFill(javafx.scene.paint.Color.WHITE);
        signup.setFont(new Font("Segoe UI", 18.0));
        GridPane.setMargin(signup, new Insets(10.0, 40.0, 0.0, 0.0));
        signup.setPadding(new Insets(8.0));
        signup.setCursor(Cursor.HAND);

        GridPane.setRowIndex(separator, 3);
        separator.setPrefWidth(318.0);
        GridPane.setMargin(separator, new Insets(-40.0, 0.0, 0.0, 0.0));

        GridPane.setHalignment(imageView, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(imageView, 4);
        imageView.setCache(true);
        imageView.setFitHeight(189.0);
        imageView.setFitWidth(257.0);
        imageView.setMouseTransparent(true);
        imageView.setNodeOrientation(javafx.geometry.NodeOrientation.INHERIT);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("xo-icon.png").toExternalForm()));
        GridPane.setMargin(imageView, new Insets(0.0));

        GridPane.setHalignment(login, javafx.geometry.HPos.LEFT);
        GridPane.setRowIndex(login, 14);
        login.setMaxHeight(30.0);
        login.setMaxWidth(100.0);
        login.setMinWidth(100.0);
        login.setMnemonicParsing(false);
        login.setStyle("-fx-background-radius: 10; -fx-background-color: #5577cf;");
        login.setText("Login");
        login.setTextFill(javafx.scene.paint.Color.WHITE);
        GridPane.setMargin(login, new Insets(10.0, 0.0, 0.0, 40.0));
        login.setFont(new Font("Segoe UI", 18.0));
        login.setPadding(new Insets(8.0));
        login.setCursor(Cursor.HAND);

        GridPane.setRowIndex(validationLabel, 6);
        validationLabel.setText("username or password is not true ");
        validationLabel.setTextFill(javafx.scene.paint.Color.valueOf("#f80a0a"));
        validationLabel.setVisible(false);

        getColumnConstraints().add(columnConstraints);
        getRowConstraints().add(rowConstraints);
        getRowConstraints().add(rowConstraints0);
        getRowConstraints().add(rowConstraints1);
        getRowConstraints().add(rowConstraints2);
        getRowConstraints().add(rowConstraints3);
        getRowConstraints().add(rowConstraints4);
        getRowConstraints().add(rowConstraints5);
        getRowConstraints().add(rowConstraints6);
        getRowConstraints().add(rowConstraints7);
        getRowConstraints().add(rowConstraints8);
        getRowConstraints().add(rowConstraints9);
        getRowConstraints().add(rowConstraints10);
        getRowConstraints().add(rowConstraints11);
        getRowConstraints().add(rowConstraints12);
        getRowConstraints().add(rowConstraints13);
        getRowConstraints().add(rowConstraints14);
        getChildren().add(username);
        getChildren().add(password);
        getChildren().add(label);
        getChildren().add(signup);
        getChildren().add(separator);
        getChildren().add(imageView);
        getChildren().add(login);
        getChildren().add(validationLabel);
        login.setOnAction((ActionEvent event) -> {
             byte [] hashLoginPasswowrd;
             String stringLoginHashPassword = null;
            login.ConnctionToDatabase connection = new  login.ConnctionToDatabase();
            connection.makeConection();
            try {
                hashLoginPasswowrd=SignUpController.getSHA(password.getText());
                stringLoginHashPassword=SignUpController.toHexString(hashLoginPasswowrd);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginBase.class.getName()).log(Level.SEVERE, null, ex);
            }   
          if(  connection.checkLogin(username.getText(),stringLoginHashPassword)==true){
              System.out.println("true data");
          }
          else{
              validationLabel.setVisible(true);
          }
        });
    }
}
