package com.DhvsuDeals.ohayosekai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class NotifsRecycleV_Adapter extends RecyclerView.Adapter<NotifsRecycleV_Adapter.MyViewHolder> {

    Context context;
    ArrayList<Notifications> notificationArrayList;

    public NotifsRecycleV_Adapter(Context context, ArrayList<Notifications> notificationArrayList) {
        this.context = context;
        this.notificationArrayList = notificationArrayList;

    }

    @NonNull
    @Override
    public NotifsRecycleV_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notifications,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifsRecycleV_Adapter.MyViewHolder holder, int position) {
        Notifications notifications = notificationArrayList.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());



        holder.messContent.setText(notifications.Mess_Content);
        holder.messDate.setText(sdf.format(notifications.Date_Received.toDate()));
        holder.messTittle.setText(notifications.Mess_Tittle);
        holder.messSender.setText(notifications.Sender);

    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView messSender, messDate, messTittle, messContent;
        String strDateTime;
        Timestamp tmstmp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            messSender = itemView.findViewById(R.id.txtSender);
            messDate = itemView.findViewById(R.id.txtDate);
            messTittle = itemView.findViewById(R.id.txtMessTittle);
            messContent = itemView.findViewById(R.id.txtMessContent);

        }


    }
}
