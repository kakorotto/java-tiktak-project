

package client.single;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import client.clientui.LevelsUI;
import client.clientui.ClientMainApp;
import client.clientui.SignUpUI;


public class HardLevel {
    
	GridPane grid = new GridPane();
    
	private Boolean playable;

    private Board[][] gui_board;
    private char[][] back_end_board;
    char winner;

    public HardLevel() {
        playable = true;
        gui_board = new Board[3][3];
        back_end_board = new char[3][3];
        winner = '-';
    }

    public void start(Stage primaryStage) {
    	
    	Button logout = new Button();
        logout.setText("Back");
        logout.setId("logout");
       
        logout.setMaxWidth(Double.MAX_VALUE);
        grid.add(logout, 2,4 , 1, 16);
        
        logout.setOnAction(event -> {
            try {
                LevelsUI levels = new LevelsUI();
                levels.start(ClientMainApp.mainStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//        Label status = new Label("OnlinePlayer Turn");
//        status.setId("status");
//        status.setAlignment(Pos.CENTER);
//        grid.add(status, 2,2 , 1, 40);
                  
        grid.add(new StackPane(new Text("")), 10, 20);
    	
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        
        Scene scene = new Scene(createContent(),880, 550);
        scene.getStylesheets().add(SignUpUI.class.getResource("/res/level.css").toExternalForm());

        primaryStage.setTitle("Tic Tac - Single SavedGame");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        grid.requestFocus();
    }
    
    private class Board extends StackPane {
        private Text text;
        private Rectangle rect;
        private int row, col;

        public Board(int row, int col) {
        	this.row = row;
            this.col = col;
            text = new Text();
            rect = new Rectangle(165, 165);
            rect.setId("rect");
            
            rect.setArcHeight(45.0d); 
            rect.setArcWidth(45.0d); 
                
            rect.setFill(Color.rgb(10, 10, 10 , 0.5));
//            rect.setStroke(Color.rgb(131,159,14 ));
            text.setFont(Font.font(60));
            getChildren().addAll(rect, text);

            setOnMouseClicked(event -> {
                if (!playable) {
                    return;
                }
                if (event.getButton() == MouseButton.PRIMARY) {   //make left click on mouse
                    drawX();
                    checkWin();
                    if (!playable) {
                        return;
                    }
                    try {
						computerPlay();
					} catch (Exception e) {
						e.printStackTrace();
					}
                    checkWin();
                }
            });
        }

        public void computerPlay() {

        	//Choose winning move if available
            for (int column = 0; column <= 2; column++) {
                if (back_end_board[row][column] == '-') {
                    back_end_board[row][column] = 'o';
                    if (checkRows() || checkCols() || checkDs()) {
                        gui_board[row][column].drawO();
                        return;
                    } else {
                        back_end_board[row][column] = '-';
                    }
                }
            }
            
          //Choose blocking move if available
            for (int row = 0; row <= 2; row++) {
                for (int column = 0; column <= 2; column++) {
                    if (back_end_board[row][column] == '-') {
                        back_end_board[row][column] = 'x';
                        if (checkRows() || checkCols() || checkDs()) {
                            back_end_board[row][column] = 'o';
                            gui_board[row][column].drawO();
                            return;
                        } else {
                            back_end_board[row][column] = '-';
                        }
                    }
                }
            }
        	
            //Choose center if available
            if (back_end_board[1][1] == '-') {
                back_end_board[1][1] = 'o';
                gui_board[1][1].drawO();
                return;
            }

            //Choose a corner if available 
            if (back_end_board[0][0] == '-') {
                back_end_board[0][0] = 'o';
                gui_board[0][0].drawO();
                return;
            }

            if (back_end_board[0][2] == '-') {
                back_end_board[0][2] = 'o';
                gui_board[0][2].drawO();
                return;
            }

            if (back_end_board[2][0] == '-') {
                back_end_board[2][0] = 'o';
                gui_board[2][0].drawO();
                return;
            }

            if (back_end_board[2][2] == '-') {
                back_end_board[2][2] = 'o';
                gui_board[2][2].drawO();
                return;
            }

            //Choose a random move
            for (int row = 0; row <= 2; row++) {
                for (int column = 0; column <= 2; column++) {
                    if (back_end_board[row][column] == '-') {
                        back_end_board[row][column] = 'o';
                        gui_board[row][column].drawO();
                        return;
                    }
                }
            }
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tie");
            alert.setHeaderText(null);
            alert.setContentText("good game no winner try again");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
            	LevelsUI levels = new LevelsUI();
				levels.start(ClientMainApp.mainStage);
            }
        }

        private void drawX() {
            text.setText("x");
            back_end_board[row][col] = 'x';
        }

        private void drawO() {
            text.setText("o");
        }
    }
    
    private Parent createContent() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Board board = new Board(i, j);
                grid.add(board,6+j , 2+i);
                gui_board[i][j] = board;
                back_end_board[i][j] = '-';
            }
        }
        return grid;
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (back_end_board[i][0] == back_end_board[i][1]
                    && back_end_board[i][0] == back_end_board[i][2]
                    && back_end_board[i][0] != '-') {
                if (back_end_board[i][0] == 'x') {
                    winner = 'x';
                } else {
                    winner = 'o';
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkCols() {
        for (int i = 0; i < 3; i++) {
            if (back_end_board[0][i] == back_end_board[1][i]
                    && back_end_board[0][i] == back_end_board[2][i]
                    && back_end_board[0][i] != '-') {
                if (back_end_board[0][i] == 'x') {
                    winner = 'x';
                } else {
                    winner = 'o';
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkDs() {
        if (back_end_board[0][0] == back_end_board[1][1]
                && back_end_board[0][0] == back_end_board[2][2]
                && back_end_board[0][0] != '-') {
            if (back_end_board[0][0] == 'x') {
                winner = 'x';
            } else {
                winner = 'o';
            }
            return true;
        }
        if (back_end_board[0][2] == back_end_board[1][1]
                && back_end_board[0][2] == back_end_board[2][0]
                && back_end_board[0][2] != '-') {
            if (back_end_board[2][0] == 'x') {
                winner = 'x';
            } else {
                winner = 'o';
            }
            return true;
        }
        return false;
    }

    private void checkWin() {
        //check state of the game
        if (checkRows() || checkCols() || checkDs()) {
            playable = false;
            if(winner == 'x')
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Win");
                alert.setHeaderText(null);
                alert.setContentText("Congratulaion you win");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    LevelsUI levels = new LevelsUI();
                    levels.start(ClientMainApp.mainStage);
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Lose");
                alert.setHeaderText(null);
                alert.setContentText("try another time");
                Optional<ButtonType> result = alert.showAndWait();
                System.out.println(result.get());
                if (result.get() == ButtonType.OK) {
                    LevelsUI levels = new LevelsUI();
                    levels.start(ClientMainApp.mainStage);
                }
            }
        }
    }
}
