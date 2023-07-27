package com.DhvsuDeals.ohayosekai;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VPAdapter extends RecyclerView.Adapter<VPAdapter.ViewHolder>{

    ArrayList<ViewPagerItem> viewPagerItemArrayList;

    public VPAdapter(ArrayList<ViewPagerItem> viewPagerItemArrayList) {
        this.viewPagerItemArrayList = viewPagerItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ViewPagerItem viewPagerItem = viewPagerItemArrayList.get(position);

        holder.currBal.setText(viewPagerItem.ShowBal);
        holder.labels.setText(viewPagerItem.ItemName);

    }

    @Override
    public int getItemCount() {
        return viewPagerItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView currBal, labels;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            labels = itemView.findViewById(R.id.Item_Name);
            currBal = itemView.findViewById(R.id.View_Balance);

        }
    }
}
