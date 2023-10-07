package com.DhvsuDeals.ohayosekai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.DhvsuDeals.ohayosekai.databinding.ActivityLoanApplicationFormBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class LoanApplicationFormActivity extends AppCompatActivity {
    private ActivityLoanApplicationFormBinding LAFormBinding;
    private String User_ID = FirebaseAuth.getInstance().getCurrentUser().getUid();//get the userUID on the firestore to be used as an user ID
    private DocumentReference SignUpRef_DB = FirebaseFirestore.getInstance().document("Members_Collections/" + User_ID);
    private ListenerRegistration listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LAFormBinding = ActivityLoanApplicationFormBinding.inflate(getLayoutInflater());
        setContentView(LAFormBinding.getRoot());

        LAFormBinding.btnCancelForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_Back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Go_Back);
                finish();
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        String FormLoanType = getIntent().getStringExtra("PassLoanType");
        listener = SignUpRef_DB.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot , FirebaseFirestoreException error) {

                if (error != null){
                    Toast.makeText(getApplicationContext(), "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (documentSnapshot.exists()){
                    Account_Info_Note note = documentSnapshot.toObject(Account_Info_Note.class);

                    String memName = note.getMem_Name();
                    String memSex = note.getMem_Sex();
                    String memDept_Inst = note.getMem_Dept_Inst();
                    String memStatsEmp = note.getMem_Stats_Employment();

                    //String txtBalance = String.valueOf(LoanBalance);convert the intbalance into string



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
    public void onBackPressed(){
        Intent Go_Back = new Intent(getApplicationContext(), LoanCostCalculatorActivity.class);
        startActivity(Go_Back);
        finish();
    }
}