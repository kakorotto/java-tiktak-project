/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.Serializable;

//game that will be send across the server
public class SavedGame implements Serializable{

    public String player1;
    public String player2;
    public String pos11;
    public String pos12;
    public String pos13;
    public String pos21;
    public String pos22;
    public String pos23;
    public String pos31;
    public String pos32;
    public String pos33;
    public SavedGame(String player1, String player2, String pos11, String pos12, String pos13, String pos21, String pos22, String pos23, String pos31, String pos32, String pos33) {
        this.player1 = player1;
        this.player2 = player2;
        this.pos11 = pos11;
        this.pos12 = pos12;
        this.pos13 = pos13;
        this.pos21 = pos21;
        this.pos22 = pos22;
        this.pos23 = pos23;
        this.pos31 = pos31;
        this.pos32 = pos32;
        this.pos33 = pos33;
    }
}
