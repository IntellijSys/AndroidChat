package my.intellij.androidchat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class Chats extends Fragment implements OnClickListener {

    private EditText msg_edittext;
    private ImageButton sendButton;
    private String user1 = "Papa", user2 = "Mama";
    private Random random;
    public static ArrayList<ChatMessage> chatlist;
    public static ChatAdapter chatAdapter;
    public ListView msgListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_layout, container, false);
        random = new Random();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Chat");
        msg_edittext = (EditText) view.findViewById(R.id.messageEditText);
        msgListView = (ListView) view.findViewById(R.id.msgListView);
        sendButton = (ImageButton) view.findViewById(R.id.sendMessageButton);

        sendButton.setOnClickListener(this);

        msgListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msgListView.setStackFromBottom(true);

        chatlist = new ArrayList<ChatMessage>();
        chatAdapter = new ChatAdapter(getActivity(), chatlist);
        msgListView.setAdapter(chatAdapter);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){

    }

    public void sendTextMessage(View v){
        String message = msg_edittext.getEditableText().toString();

        if(!message.equalsIgnoreCase("")){
            final ChatMessage chatMessage = new ChatMessage(user1, user2, message,
                    "" + random.nextInt(1000), true);
            chatMessage.setMsgID();
            chatMessage._messageString = message;
            chatMessage._date = CommonMethods.getCurrentDate();
            chatMessage._time = CommonMethods.getCurrentTime();
            msg_edittext.setText("");
            chatAdapter.add(chatMessage);
            chatAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.sendMessageButton:
                sendTextMessage(v);
        }
    }
}