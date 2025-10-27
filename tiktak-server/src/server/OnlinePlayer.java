package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//this class is used to save streams to be used in server
public class OnlinePlayer {

	public ObjectInputStream inputStream;
	public ObjectOutputStream outputStream;
	public String playerName;
	public String playerStatus;
	public char sign;

	OnlinePlayer(ObjectInputStream inputStream, ObjectOutputStream outputStream, String playerName, String playerStatus) {
		this.playerName = playerName;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.playerStatus = playerStatus;
	}
	//this for setting the status
	public void setSign (char sign){
		this.sign = sign;
	}
	public char getSign(){
		return this.sign;
        }
}
