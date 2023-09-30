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
    String User_ID = FirebaseAuth.getInstance().getCurrentUser().getUid();//get the userUID on the firestore to be used as an user ID
    DocumentReference SignUpRef_DB = FirebaseFirestore.getInstance().document("Uses_ACCS_Information/" + User_ID);
    private static final String KEYLoan_BALANCE = "Mem_Loan_Outstanding_Balance", KEYSavings_Balance = "Mem_Savings_Balance";
    private ListenerRegistration listener;



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

        //start of commodity loan button
        HBinder.CommodityLoanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go_Forms.putExtra("PassLoanTermLimit", 36L);//number of months
                Go_Forms.putExtra("PassLoanType", "Commodity Loan"); //pass the values to the Calculator
                Go_Forms.putExtra("PassButton", "Apply Commodity Loan");//passing the label for button
                startActivity(Go_Forms);

            }
        });//end for the button ofcommodity loan

        //start of short term loan button
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
        });//end of the advanced mid year loan button

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
                    HBinder.ViewLoanBalance.setText(String.valueOf(LoanBalance));
                    HBinder.ViewSavingsBalance.setText(String.valueOf(SavingsBalance));
                    HBinder.LoanType.setText(LoanType);

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
        HBinder = null;
    }
}