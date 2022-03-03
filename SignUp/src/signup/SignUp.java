package signup;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public abstract class SignUp extends GridPane {

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
    protected final RowConstraints rowConstraints15;
    protected final TextField textField;
    protected final PasswordField passwordField;
    protected final Label label;
    protected final Button button;
    protected final Separator separator;
    protected final PasswordField passwordField0;
    protected final TextField textField0;

    public SignUp() {

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
        rowConstraints15 = new RowConstraints();
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

        rowConstraints0.setMaxHeight(48.80001220703126);
        rowConstraints0.setMinHeight(31.999993896484384);
        rowConstraints0.setPrefHeight(31.999993896484384);

        rowConstraints1.setMaxHeight(21.600006103515625);
        rowConstraints1.setMinHeight(4.79998779296875);
        rowConstraints1.setPrefHeight(4.799999999999997);

        rowConstraints2.setMaxHeight(0.0);
        rowConstraints2.setMinHeight(0.0);
        rowConstraints2.setPrefHeight(0.0);



        rowConstraints8.setMinHeight(10.0);
        rowConstraints8.setPrefHeight(30.0);

        rowConstraints10.setMinHeight(10.0);
        rowConstraints10.setPrefHeight(30.0);

        rowConstraints12.setMinHeight(10.0);
        rowConstraints12.setPrefHeight(30.0);

        rowConstraints14.setMinHeight(10.0);
        rowConstraints14.setPrefHeight(30.0);

        rowConstraints15.setMinHeight(50.0);
        rowConstraints15.setPrefHeight(30.0);
        rowConstraints15.setValignment(javafx.geometry.VPos.CENTER);
        rowConstraints15.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        setPadding(new Insets(40.0, 40.0, 30.0, 40.0));

        GridPane.setHalignment(textField, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(textField, 9);
        textField.setMaxWidth(400.0);
        textField.setMinHeight(35.0);
        textField.setPrefHeight(60.0);
        textField.setPromptText("Enter your name");
        textField.setStyle("-fx-background-color: F7F3F2; -fx-background-radius: 10;");

        GridPane.setHalignment(passwordField, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(passwordField, 11);
        passwordField.setMaxWidth(400.0);
        passwordField.setMinHeight(35.0);
        passwordField.setPrefHeight(60.0);
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle("-fx-background-color: F7F3F2; -fx-background-radius: 10;");

        GridPane.setHalignment(label, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(label, 1);
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
        GridPane.setRowIndex(button, 16);
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
        button.setCursor(Cursor.HAND);

        GridPane.setRowIndex(separator, 4);
        separator.setPrefWidth(318.0);
        GridPane.setMargin(separator, new Insets(-40.0, 0.0, 0.0, 0.0));

        GridPane.setHalignment(passwordField0, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(passwordField0, 13);
        passwordField0.setMaxWidth(400.0);
        passwordField0.setMinHeight(35.0);
        passwordField0.setPrefHeight(60.0);
        passwordField0.setPromptText("Confirm your password");
        passwordField0.setStyle("-fx-background-color: F7F3F2; -fx-background-radius: 10;");

        GridPane.setHalignment(textField0, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(textField0, 15);
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
        getRowConstraints().add(rowConstraints15);
        getChildren().add(textField);
        getChildren().add(passwordField);
        getChildren().add(label);
        getChildren().add(button);
        getChildren().add(separator);
        getChildren().add(passwordField0);
        getChildren().add(textField0);

    }
}
