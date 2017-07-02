package com.example.ecss.medicalmapper.chatbot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.userInterface.adapters.AdvancedSearchAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by samuel on 7/2/2017.
 */

public class ChatBotAdapter  extends RecyclerView.Adapter<ChatBotAdapter.MyViewHolder> {

    private List<MasaageHolder> masaageHolders;
    public ChatBotAdapter ()
    {
        masaageHolders =new ArrayList<MasaageHolder>();
    }

    public void addNewMass (MasaageHolder masaageHolder)
    {
        this.masaageHolders.add(masaageHolder);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Context context = parent.getContext() ;
        int layoutPhotoItem = R.layout.bot_mass;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutPhotoItem ,parent ,shouldAttachToParentImmediately);
        return new MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.send.setText(masaageHolders.get(i).userSend);
        myViewHolder.recive.setText(masaageHolders.get(i).bot);

    }

    @Override
    public int getItemCount() {
        if(masaageHolders== null)
            return 0;
        return masaageHolders.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView send ;
        TextView recive;

        MyViewHolder(View convertView) {
            super(convertView);
            send =(TextView) convertView.findViewById(R.id.text_view_sender_message);
            recive =(TextView) convertView.findViewById(R.id.text_view_ans_message);

        }
    }
}
