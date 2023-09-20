package com.DhvsuDeals.ohayosekai;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.DhvsuDeals.ohayosekai.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.FirebaseFirestore;


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

        HBinder.VPLoansSavings.setPageTransformer(new PageTransformer());

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Nullify the binding object to avoid memory leaks
        HBinder = null;
    }
}