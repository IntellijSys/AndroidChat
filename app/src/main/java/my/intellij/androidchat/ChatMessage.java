package my.intellij.androidchat;

import java.util.Random;

/**
 * Created by ZERO on 18/05/2017.
 */

public class ChatMessage {

    public String _messageString, _sender, _receiver;
    public String _date, _time;
    public String _msgid;
    public boolean _isMine;

    public ChatMessage(String sender,String receiver, String messageString, String id, boolean isMine){
        _messageString = messageString;
        _isMine = isMine;
        _sender = sender;
        _msgid = id;
        _receiver = receiver;
    }

    public void setMsgID() {
        _msgid += "-" + String.format("0%02d", new Random().nextInt(100));
    }
}
