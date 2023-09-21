package com.DhvsuDeals.ohayosekai;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.DhvsuDeals.ohayosekai.databinding.FragmentLoansViewBinding;
import com.DhvsuDeals.ohayosekai.databinding.FragmentSavingsViewBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class Savings_ViewFragment extends Fragment {
    private FragmentSavingsViewBinding savingsViewBinding;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String User_ID = mAuth.getCurrentUser().getUid();//get the userUID on the firestore to be used as an user ID
    DocumentReference SignUpRef_DB = FirebaseFirestore.getInstance().document("Uses_ACCS_Information/" + User_ID);
    private static final String KEY_BALANCE = "Mem-Savings_Balance";
    private ListenerRegistration listener;

    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        savingsViewBinding = FragmentSavingsViewBinding.inflate(inflater, container, false);
        return savingsViewBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart(){
        super.onStart();
        listener = SignUpRef_DB.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot , FirebaseFirestoreException error) {

                if (error != null){
                    Toast.makeText(getActivity(), "Error while loading!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (documentSnapshot.exists()){
                    Double NumBalance = documentSnapshot.getDouble(KEY_BALANCE);
                    //String txtBalance = String.valueOf(NumBalance);//convert the intbalance into string
                    savingsViewBinding.ViewBalance.setText(String.valueOf(NumBalance));

                }
            }
        });
    }

    @Override
    public void onStop(){
        super.onStop();
        listener.remove();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Nullify the binding object to avoid memory leaks
        savingsViewBinding = null;
    }

}