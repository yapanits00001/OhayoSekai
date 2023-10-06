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

import com.DhvsuDeals.ohayosekai.databinding.FragmentHomeBinding;
import com.DhvsuDeals.ohayosekai.databinding.FragmentLoansViewBinding;
import com.DhvsuDeals.ohayosekai.databinding.FragmentReadBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;


public class Loans_ViewFragment extends Fragment {

    private FragmentLoansViewBinding loansViewBinding;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String User_ID = FirebaseAuth.getInstance().getCurrentUser().getUid();//get the userUID on the firestore to be used as an user ID
    DocumentReference SignUpRef_DB = FirebaseFirestore.getInstance().document("Members_Collections/" + User_ID);
    private ListenerRegistration listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loansViewBinding = FragmentLoansViewBinding.inflate(inflater, container, false);
        return loansViewBinding.getRoot();

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
                    Account_Info_Note note = documentSnapshot.toObject(Account_Info_Note.class);

                    String LoanType = note.getMem_Loan_Type();
                    double LoanBalance = note.getMem_Loan_Outstanding_Balance();
                    double SavingsBalance = note.getMem_Savings_Balance();


                    //String txtBalance = String.valueOf(LoanBalance);convert the intbalance into string
                    loansViewBinding.ViewLoanBalance.setText(String.valueOf(LoanBalance));
                    loansViewBinding.ViewSavingsBalance.setText(String.valueOf(SavingsBalance));
                    loansViewBinding.LoanType.setText(LoanType);

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
        loansViewBinding = null;
    }

}