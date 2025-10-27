package req;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import server.SavedGame;

public class Request implements Serializable {
    RequestType requestType;
    public HashMap<String, String> requestData;
    public HashMap<String, ArrayList<String>> onlineData;
    public HashMap<String, String[]> gameData;
    public HashMap<String, SavedGame> savedGame;
    
    public Request(RequestType requestType) {
        this.requestType = requestType;
        requestData = new HashMap<>();
        onlineData = new HashMap<>();
        gameData = new HashMap<>();
        savedGame = new HashMap<>();
    }
    public void setType(RequestType requestType) {
        this.requestType = requestType;
    }
    public RequestType getType() {
        return requestType;
    }
    public void setData(String key, String value) {
        requestData.put(key, value);
    }
    public String getData(String key) {
        return requestData.getOrDefault(key, null);
    }
    public void set_online_Data(String key, ArrayList<String> value) {
        onlineData.put(key, value);
    }
    public ArrayList<String> get_online_Data(String key) {
        return onlineData.getOrDefault(key, null);
    }
    public void setGameData(String key, String[] value) {
        gameData.put(key, value);
    }
    public String[] getGameData(String key) {
        return gameData.getOrDefault(key, null);
    }
    public void setSaveddGame(String key, SavedGame value) {
        savedGame.put(key, value);
    }
    public SavedGame getSavedGame(String key) {
        return savedGame.getOrDefault(key, null);
    }
}
