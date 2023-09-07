package com.DhvsuDeals.ohayosekai;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.DhvsuDeals.ohayosekai.databinding.FragmentHomeBinding;
import com.DhvsuDeals.ohayosekai.databinding.FragmentInboxBinding;
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
    ArrayList<ViewPagerItem_Loan_Savings> viewPagerItem_loan_savingsArrayList;
    VP_Loan_Savings_Adapter vp_loan_savings_adapter;
    ProgressDialog progressDialog;


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
       /* progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data.....");
        progressDialog.show();


        // Access and interact with views using the 'binding' object

        db = FirebaseFirestore.getInstance();
        viewPagerItem_loan_savingsArrayList = new ArrayList<ViewPagerItem_Loan_Savings>();
        vp_loan_savings_adapter = new VP_Loan_Savings_Adapter(getActivity(), viewPagerItem_loan_savingsArrayList);

        HBinder.ViewPagerLoansSavings.setAdapter(vp_loan_savings_adapter);
        //EventchangeListener(); error in viewpager*/
//remove comments to see the errors in view pager
    }

    private void EventchangeListener() {
        db.collection("SampleViewPager")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error!=null){
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error", error.getMessage());
                            //Toast.makeText(getActivity(), "Firestore Error!!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                viewPagerItem_loan_savingsArrayList.add(dc.getDocument().toObject(ViewPagerItem_Loan_Savings.class));
                            }
                            vp_loan_savings_adapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
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