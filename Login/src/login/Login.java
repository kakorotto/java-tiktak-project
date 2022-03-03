package login;

import javafx.geometry.Insets;
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

public abstract class Login extends GridPane {

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
    protected final TextField textField;
    protected final PasswordField passwordField;
    protected final Label label;
    protected final Button button;
    protected final Separator separator;
    protected final ImageView imageView;
    protected final Button button0;

    public Login() {

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
        textField = new TextField();
        passwordField = new PasswordField();
        label = new Label();
        button = new Button();
        separator = new Separator();
        imageView = new ImageView();
        button0 = new Button();

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
        rowConstraints7.setVgrow(javafx.scene.layout.Priority.SOMETIMES);


        rowConstraints10.setMinHeight(10.0);
        rowConstraints10.setPrefHeight(30.0);
        rowConstraints10.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints12.setMinHeight(50.0);
        rowConstraints12.setPrefHeight(30.0);
        rowConstraints12.setValignment(javafx.geometry.VPos.CENTER);
        rowConstraints12.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        setPadding(new Insets(40.0, 40.0, 30.0, 40.0));

        GridPane.setHalignment(textField, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(textField, 8);
        textField.setMaxWidth(200.0);
        textField.setMinHeight(35.0);
        textField.setPrefHeight(60.0);
        textField.setPromptText("Enter your name");
        textField.setStyle("-fx-background-color: F7F3F2; -fx-background-radius: 10;");

        GridPane.setHalignment(passwordField, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(passwordField, 11);
        passwordField.setMaxWidth(200.0);
        passwordField.setMinHeight(35.0);
        passwordField.setPrefHeight(60.0);
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle("-fx-background-color: F7F3F2; -fx-background-radius: 10;");

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

        GridPane.setHalignment(button, javafx.geometry.HPos.RIGHT);
        GridPane.setRowIndex(button, 13);
        button.setAlignment(javafx.geometry.Pos.CENTER);
        button.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        button.setMaxHeight(30.0);
        button.setMaxWidth(100.0);
        button.setMinWidth(100.0);
        button.setMnemonicParsing(false);
        button.setStyle("-fx-background-radius: 10; -fx-background-color: #335397;");
        button.setText("Sign Up");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font("Segoe UI", 18.0));
        GridPane.setMargin(button, new Insets(10.0, 40.0, 0.0, 0.0));
        button.setPadding(new Insets(8.0));

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
        imageView.setImage(new Image(getClass().getResource(".././xo-logo.png").toExternalForm()));
        GridPane.setMargin(imageView, new Insets(0.0));

        GridPane.setHalignment(button0, javafx.geometry.HPos.LEFT);
        GridPane.setRowIndex(button0, 13);
        button0.setMaxHeight(30.0);
        button0.setMaxWidth(100.0);
        button0.setMinWidth(100.0);
        button0.setMnemonicParsing(false);
        button0.setStyle("-fx-background-radius: 10; -fx-background-color: #5577cf;");
        button0.setText("Login");
        button0.setTextFill(javafx.scene.paint.Color.WHITE);
        GridPane.setMargin(button0, new Insets(10.0, 0.0, 0.0, 40.0));
        button0.setFont(new Font("Segoe UI", 18.0));
        button0.setPadding(new Insets(8.0));

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
        getChildren().add(textField);
        getChildren().add(passwordField);
        getChildren().add(label);
        getChildren().add(button);
        getChildren().add(separator);
        getChildren().add(imageView);
        getChildren().add(button0);

    }
}
