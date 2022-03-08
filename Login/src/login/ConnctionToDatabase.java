package login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnctionToDatabase {
     Connection con = null;
     PreparedStatement stmt;
    public  Connection makeConection(){
       
        try{

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
             con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaProject","root","nayra");
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public  int insertPlayerData(String playerName,String playerPassword ,String playerPhone ){
        int i =0;
         try {  
            stmt=con.prepareStatement("INSERT INTO player (player_name, player_password, player_Phone) VALUES (?,?,?);");
            stmt.setString(1,playerName);
            stmt.setString(2,playerPassword);  
            stmt.setString(3,playerPhone);
            i=stmt.executeUpdate();  
            System.out.println(i+" records inserted");  
            con.close();  
         } catch (SQLException ex) {
             Logger.getLogger(ConnctionToDatabase.class.getName()).log(Level.SEVERE, null, ex);
         }
       return i;
       
    }
    public  boolean checkLogin(String playerName,String playerPassword  ){
        boolean flageIsfound = false;
         try {  
            stmt=con.prepareStatement("select player_name,player_password from player where player_name=? and player_password=?  ;");
            stmt.setString(1,playerName);
            stmt.setString(2,playerPassword); 
             ResultSet resultSet=stmt.executeQuery();
           if(resultSet.next()){
                flageIsfound=true;
                con.close();
           }
           else{
            flageIsfound=false;
            con.close();
           }
         } catch (SQLException ex) {
             Logger.getLogger(ConnctionToDatabase.class.getName()).log(Level.SEVERE, null, ex);
         }

       return flageIsfound ;
    }
    
}
