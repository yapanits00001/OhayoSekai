package com.DhvsuDeals.ohayosekai;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.DhvsuDeals.ohayosekai.databinding.FragmentHomeBinding;
import com.DhvsuDeals.ohayosekai.databinding.FragmentInboxBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Home_Fragment extends Fragment {

    private FragmentHomeBinding HBinder;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    VP_Adapter_LOANS_SAVINGS vp_adapter_loans_savings;


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
        vp_adapter_loans_savings = new VP_Adapter_LOANS_SAVINGS(getActivity());
        HBinder.VPLoansSavings.setAdapter(vp_adapter_loans_savings);

        HBinder.LoansSavingsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                HBinder.VPLoansSavings.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        HBinder.VPLoansSavings.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                HBinder.LoansSavingsTab.getTabAt(position).select();
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Nullify the binding object to avoid memory leaks
        HBinder = null;
    }
}