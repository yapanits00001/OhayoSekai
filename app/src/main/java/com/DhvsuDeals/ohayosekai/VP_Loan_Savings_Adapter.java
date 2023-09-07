package com.DhvsuDeals.ohayosekai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VP_Loan_Savings_Adapter extends RecyclerView.Adapter<VP_Loan_Savings_Adapter.ViewHolder> {

    Context context;
    ArrayList<ViewPagerItem_Loan_Savings> viewPagerItem_loan_savingsArrayList;

    public VP_Loan_Savings_Adapter(Context context, ArrayList<ViewPagerItem_Loan_Savings> viewPagerItem_loan_savingsArrayList) {
        this.context = context;
        this.viewPagerItem_loan_savingsArrayList = viewPagerItem_loan_savingsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.viewpager_items_loans_savings,parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewPagerItem_Loan_Savings viewPagerItem_loan_savings = viewPagerItem_loan_savingsArrayList.get(position);

        holder.OutBalance.setText(String.valueOf(viewPagerItem_loan_savings.balance));

    }

    @Override
    public int getItemCount() {
        return viewPagerItem_loan_savingsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView OutBalance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            OutBalance = itemView.findViewById(R.id.View_Balance);
        }
    }
}
