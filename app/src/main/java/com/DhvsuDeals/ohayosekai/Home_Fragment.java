package com.DhvsuDeals.ohayosekai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.DhvsuDeals.ohayosekai.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;


public class Home_Fragment extends Fragment {

    private FragmentHomeBinding HBinder;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HBinder = FragmentHomeBinding.inflate(inflater, container, false);
        return HBinder.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent Go_Forms = new Intent(getActivity(), LoanCostCalculatorActivity.class);
/*
        vp_adapter_loans_savings = new VP_Adapter_LOANS_SAVINGS(getActivity());
        HBinder.VPLoansSavings.setAdapter(vp_adapter_loans_savings);

        HBinder.VPLoansSavings.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateIndicator(position);
            }
        });

        HBinder.VPLoansSavings.setPageTransformer(new PageTransformer());

*/
     /*   //start of commodity loan button
        HBinder.CommodityLoanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go_Forms.putExtra("PassLoanTermLimit", 36L);//number of months
                Go_Forms.putExtra("PassLoanType", "Commodity Loan"); //pass the values to the Calculator
                Go_Forms.putExtra("PassButton", "Apply Commodity Loan");//passing the label for button
                startActivity(Go_Forms);

            }
        });//end for the button ofcommodity loan */

      /*  //start of short term loan button
        HBinder.ShortTermLoanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go_Forms.putExtra("PassLoanTermLimit", 9L);//number of months
                Go_Forms.putExtra("PassLoanType", "Short Term Loan"); //pass the values to the Calculator
                Go_Forms.putExtra("PassButton", "Apply Short Term Loan");//passing the label for button
                startActivity(Go_Forms);


            }
        });//end of short term loan button

        //start of the christmas loan button
        HBinder.ChristmasLoanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go_Forms.putExtra("PassLoanTermLimit", 12L);//number of months
                Go_Forms.putExtra("PassLoanType", "Christmas Loan"); //pass the values to the Calculator
                Go_Forms.putExtra("PassButton", "Apply Christmas Loan");//passing the label for button
                startActivity(Go_Forms);

            }
        });//end of the christmas loan button

        //start of the advanced mid year loan
        HBinder.AdvanceMidyearLoanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go_Forms.putExtra("PassLoanTermLimit", 12L);//number of months
                Go_Forms.putExtra("PassLoanType", "Advance Mid-Year Loan"); //pass the values to the Calculator
                Go_Forms.putExtra("PassButton", "Apply Advance Mid-Year Loan");//passing the label for button
                startActivity(Go_Forms);
            }
        });//end of the advanced mid year loan button  */

            }

/*
    private void updateIndicator(int position){

        HBinder.indicatorVP.removeAllViews();
        for (int i = 0; i < vp_adapter_loans_savings.getItemCount(); i++){
            ImageView indicator = new ImageView(getActivity());
            indicator.setImageResource(
                    i == position ? R.drawable.selected_indicator : R.drawable.idle_selector_indicator
            );
            HBinder.indicatorVP.addView(indicator);
        }
    }*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Nullify the binding object to avoid memory leaks
        HBinder = null;
    }
}