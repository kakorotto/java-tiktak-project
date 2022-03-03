package signup;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUp extends GridPane {

       ColumnConstraints columnConstraints;
       RowConstraints rowConstraints;
       RowConstraints rowConstraints0;
       RowConstraints rowConstraints1;
       RowConstraints rowConstraints2;
       RowConstraints rowConstraints3;
       RowConstraints rowConstraints4;
       RowConstraints rowConstraints5;
       RowConstraints rowConstraints6;
       RowConstraints rowConstraints7;
       RowConstraints rowConstraints8;
       RowConstraints rowConstraints9;
       RowConstraints rowConstraints10;
       RowConstraints rowConstraints11;
       RowConstraints rowConstraints12;
       RowConstraints rowConstraints13;
       RowConstraints rowConstraints14;
       TextField textField;
       PasswordField passwordField;
       Label label;
       Button button;
       Separator separator;
       PasswordField passwordField0;
       TextField textField0;

    public SignUp(Stage stage) {

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
        textField = new TextField();
        passwordField = new PasswordField();
        label = new Label();
        button = new Button();
        separator = new Separator();
        passwordField0 = new PasswordField();
        textField0 = new TextField();
        
        
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



        rowConstraints7.setMinHeight(10.0);
        rowConstraints7.setPrefHeight(30.0);

        rowConstraints9.setMinHeight(10.0);
        rowConstraints9.setPrefHeight(30.0);

        rowConstraints11.setMinHeight(10.0);
        rowConstraints11.setPrefHeight(30.0);

        rowConstraints13.setMinHeight(10.0);
        rowConstraints13.setPrefHeight(30.0);

        rowConstraints14.setMinHeight(50.0);
        rowConstraints14.setPrefHeight(30.0);
        rowConstraints14.setValignment(javafx.geometry.VPos.CENTER);
        rowConstraints14.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        setPadding(new Insets(40.0, 40.0, 30.0, 40.0));

        GridPane.setHalignment(textField, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(textField, 8);
        textField.setMaxWidth(400.0);
        textField.setMinHeight(35.0);
        textField.setPrefHeight(60.0);
        textField.setPromptText("Enter your name");
        textField.setStyle("-fx-background-color: F7F3F2; -fx-background-radius: 10;");

        GridPane.setHalignment(passwordField, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(passwordField, 10);
        passwordField.setMaxWidth(400.0);
        passwordField.setMinHeight(35.0);
        passwordField.setPrefHeight(60.0);
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle("-fx-background-color: F7F3F2; -fx-background-radius: 10;");

        GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setText("SignUp");
        label.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
        label.setTextFill(javafx.scene.paint.Color.valueOf("#4262a1"));
        label.setFont(new Font("Arial Rounded MT Bold", 28.0));
        label.setPadding(new Insets(10.0, 0.0, 10.0, 0.0));
        label.setOpaqueInsets(new Insets(0.0));
        GridPane.setMargin(label, new Insets(0.0));

        GridPane.setHalignment(button, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(button, 15);
        GridPane.setValignment(button, javafx.geometry.VPos.CENTER);
        button.setAlignment(javafx.geometry.Pos.CENTER);
        button.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        button.setMaxHeight(30.0);
        button.setMaxWidth(300.0);
        button.setMinWidth(100.0);
        button.setMnemonicParsing(false);
        button.setStyle("-fx-background-radius: 10; -fx-background-color: #335397;");
        button.setText("Sign Up");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font("Segoe UI", 18.0));
        GridPane.setMargin(button, new Insets(0.0));
        button.setPadding(new Insets(8.0));

        GridPane.setRowIndex(separator, 3);
        separator.setPrefWidth(318.0);
        GridPane.setMargin(separator, new Insets(-40.0, 0.0, 0.0, 0.0));

        GridPane.setHalignment(passwordField0, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(passwordField0, 12);
        passwordField0.setMaxWidth(400.0);
        passwordField0.setMinHeight(35.0);
        passwordField0.setPrefHeight(60.0);
        passwordField0.setPromptText("Confirm your password");
        passwordField0.setStyle("-fx-background-color: F7F3F2; -fx-background-radius: 10;");

        GridPane.setHalignment(textField0, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(textField0, 14);
        textField0.setMaxWidth(400.0);
        textField0.setMinHeight(35.0);
        textField0.setPrefHeight(60.0);
        textField0.setPromptText("Enter your phone");
        textField0.setStyle("-fx-background-radius: 10; -fx-background-color: F7F3F2;");

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
        getChildren().add(textField);
        getChildren().add(passwordField);
        getChildren().add(label);
        getChildren().add(button);
        getChildren().add(separator);
        getChildren().add(passwordField0);
        getChildren().add(textField0);

    }
}
