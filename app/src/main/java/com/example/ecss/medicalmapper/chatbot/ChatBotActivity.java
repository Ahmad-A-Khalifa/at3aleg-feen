package com.example.ecss.medicalmapper.chatbot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.chattingsystem.adapter.MessageChatAdapter;
import com.example.ecss.medicalmapper.chattingsystem.model.ChatMessage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ChatBotActivity extends AppCompatActivity {

    RecyclerView mChatRecyclerView ;
    ChatBotAdapter chatBotAdapter ;
    public static void startActivity(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, ChatBotActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        mChatRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_chatbot) ;
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatBotAdapter = new ChatBotAdapter();
        mChatRecyclerView.setAdapter(chatBotAdapter);
       // final TextView text = (TextView) findViewById(R.id.textview);
        final EditText edit = (EditText) findViewById(R.id.textSend);
        Button button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edit.getText().length() > 0) {
                    Text text1 = new Text();
                    text1.text = edit.getText().toString();
                    text1.text = text1.text.replaceAll("(\\r|\\n)", " ");
                    Log.d("string", text1.text);
                    Input input = new Input();
                    input.input = text1;

                    Gson gson = new Gson();
                    String json = gson.toJson(input);

                    String url = "https://gateway.watsonplatform.net/conversation/api/v1/workspaces/46113d30-bbf6-4e58-8e5e-6c5e40abc9eb/message?version=2017-05-26";
                    connection con = new connection();
                    try {
                        String s = con.execute(url, json).get();
                        Gson gson1 = new Gson();
                        Output out = gson1.fromJson(s, Output.class);
                        Log.d("Rest Api", s);
                       // text.setText(out.output.text.get(0));
                        String botans = out.output.text.get(0);
                        String seend =edit.getText().toString() ;
                        MasaageHolder masaageHolder = new MasaageHolder( seend,botans);
                        chatBotAdapter.addNewMass(masaageHolder);
                        edit.setText("");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
