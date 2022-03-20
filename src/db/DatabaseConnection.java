package db;

import com.mysql.jdbc.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import server.SavedGame;

public class DatabaseConnection {

    int affectedRow;
    PreparedStatement preparedStatement;
    Connection connection;
    ArrayList<Player> players;
    public DatabaseConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TicTacToe","root", "root");
            players = new ArrayList<>();
        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
    //return all players
    public ArrayList<Player> getAll() throws SQLException {
        Statement connectionStatement = connection.createStatement();
        String queryString = "select * from player";
        ResultSet resultSet = connectionStatement.executeQuery(queryString);
        while (resultSet.next()) {
            players.add(new Player(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
        }
        return players;
    }
    //update the player score
    public void update(String username) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE player SET score = score + 10 WHERE username = ?;",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setString(1, username);
        affectedRow = preparedStatement.executeUpdate();
    }
    //insert new player
    public void insert(String username, String pass, String status, int score) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO player (username, pass, status, score)"
                + " VALUES (? , ? ,? , ?);",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, pass);
        preparedStatement.setString(3, status);
        preparedStatement.setInt(4, score);
        preparedStatement.executeUpdate();
    }
    //insert saved game
    public void insertNewGame(String player1, String player2, String pos11, String pos12, String pos13,
            String pos21, String pos22, String pos23, String pos31, String pos32, String pos33) throws SQLException {
        preparedStatement =
                connection.prepareStatement("INSERT into game (player1,player2,pos11,pos12,pos13,pos21,pos22,pos23,pos31,pos32,pos33) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?);",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setString(1, player1);
        preparedStatement.setString(2, player2);
        preparedStatement.setString(3, pos11);
        preparedStatement.setString(4, pos12);
        preparedStatement.setString(5, pos13);
        preparedStatement.setString(6, pos21);
        preparedStatement.setString(7, pos22);
        preparedStatement.setString(8, pos23);
        preparedStatement.setString(9, pos31);
        preparedStatement.setString(10, pos32);
        preparedStatement.setString(11, pos33);
        preparedStatement.executeUpdate();
    }
    //get all games to search for a saved game
    public List<SavedGame> getGames() throws SQLException {
        ArrayList<SavedGame> savedGames = new ArrayList<>();
        Statement connectionStatement = connection.createStatement();
        String queryString = "select player1,player2,pos11,pos12,pos13,pos21,pos22,pos23,pos31,pos32,pos33 from game";
        ResultSet resultSet = connectionStatement.executeQuery(queryString);
        while (resultSet.next()) {
//            System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3)
//                    + resultSet.getString(4) + resultSet.getString(5) + resultSet.getString(6) + resultSet.getString(7)
//                    + resultSet.getString(8) + resultSet.getString(9) + resultSet.getString(10) + resultSet.getString(11));
            savedGames.add(new SavedGame(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                     resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11)));
        }
        return savedGames;
    }
    //Remove saved SavedGame After loading it 
    public void removeSavedGame(String p1, String p2) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM game WHERE (player1 = ? and player2 = ? ) or (player1=? and player2=?);",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setString(1, p1);
        preparedStatement.setString(2, p2);
        preparedStatement.setString(3, p2);
        preparedStatement.setString(4, p1);

        affectedRow = preparedStatement.executeUpdate();
    }
}
