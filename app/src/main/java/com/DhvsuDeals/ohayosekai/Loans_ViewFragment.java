package com.DhvsuDeals.ohayosekai;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.DhvsuDeals.ohayosekai.databinding.FragmentHomeBinding;
import com.DhvsuDeals.ohayosekai.databinding.FragmentLoansViewBinding;
import com.google.firebase.firestore.FirebaseFirestore;


public class Loans_ViewFragment extends Fragment {

    private FragmentLoansViewBinding loansViewBinding;
    FirebaseFirestore db;

    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loansViewBinding = FragmentLoansViewBinding.inflate(inflater, container, false);
        return loansViewBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Nullify the binding object to avoid memory leaks
        loansViewBinding = null;
    }

}