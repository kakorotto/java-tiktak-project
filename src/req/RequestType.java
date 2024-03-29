package req;

/**
 * @author Pola Attya 
 * @see java.lang.Enum 
 */
public enum RequestType {
    LOGIN,
    LOGIN_SUCCESS,
    LOGIN_FAILED,
    SIGNUP,
    SIGN_UP_SUCCESS,
    SIGN_UP_FAILED,
    SEND_INVITATION,
    RECEIVE_INVITATION,
    ACCEPT_INVITATION,
    REJECT_INVITATION,
    SEND_REPLY,
    RECEIVE_REPLY,
    SEND_MSG,
    RECEIVE_MSG,
    SEND_MOVE,
    RECEIVE_MOVE,
    ONLINE_PLAYERS,
    OFFLINE_PLAYERS,
    END_GAME,
    END_SESSION,
    MULTI_GAME,
    CONNECTION_LOST,
    SERVER_DISCONNECTED,
    BUSY_PLAYER,
    QUIT_GAME,
    REPEATED_LOGIN,
    REPEATED_USER,
    WIN,
    LOSE,
    SAVE_GAME,
    CHECK_GAME,
    SAVED_GAME,
    PLAYER_X,
    PLAYER_O,
    CHAT,
    BOARD,
    NOTIFICATION,
}
