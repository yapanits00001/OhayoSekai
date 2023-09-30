package com.DhvsuDeals.ohayosekai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.DhvsuDeals.ohayosekai.databinding.ActivityMemberAuthBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class MemberAuthActivity extends AppCompatActivity {
     Long timeoutOTP = 60L;
     FirebaseAuth MemAuth = FirebaseAuth.getInstance();
    private CollectionReference MemCheckCollection;
    private ActivityMemberAuthBinding MemBinder;
    private String verificationCode;
    private PhoneAuthProvider.ForceResendingToken resendingToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemBinder = ActivityMemberAuthBinding.inflate(getLayoutInflater());
        setContentView(MemBinder.getRoot());
        MemCheckCollection = FirebaseFirestore.getInstance().collection("Coop Members");



        MemBinder.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemBinder.progressBar.setVisibility(View.VISIBLE);
                MemBinder.btnVerify.setVisibility(View.GONE);
                String searchRec = String.valueOf(MemBinder.txtVID.getText()).trim();
                if (!TextUtils.isEmpty(searchRec)){
                        SearchMember(searchRec);
                } else {
                    MemBinder.txtVID.setError("Please Fill out the empty Field!!");
                    MemBinder.txtVID.requestFocus();
                    MemBinder.progressBar.setVisibility(View.GONE);
                    MemBinder.btnVerify.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void SearchMember(String searchRec){
        //check if the user is authenticated
            DocumentReference searchDocRef = MemCheckCollection.document(searchRec);
            searchDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot docSnapshot = task.getResult();
                        if (docSnapshot.exists()){
                            Boolean compAcc_Status = docSnapshot.getBoolean("Account_Registered");
                            if (compAcc_Status == true){
                                Toast.makeText(MemberAuthActivity.this, "Account Already Registered!!", Toast.LENGTH_SHORT).show();
                                MemBinder.progressBar.setVisibility(View.GONE);
                                MemBinder.btnVerify.setVisibility(View.VISIBLE);
                            } else{
                                String welcomeName = docSnapshot.getString("Mem_Name");
                                Double memLoanBal = docSnapshot.getDouble("Mem_Outstanding_Balance");
                                Double memSavingsBal = docSnapshot.getDouble("Mem_Savings");
                                String memPhoneNum = docSnapshot.getString("Phone_Number");


                                Intent Go_Register = new Intent(getApplicationContext(), RegisterActivity.class);
                                Toast.makeText(MemberAuthActivity.this, "Welcome our dear Member " + welcomeName, Toast.LENGTH_SHORT).show();

                                Go_Register.putExtra("PassMemID", searchRec);
                                Go_Register.putExtra("PassMemName", welcomeName); //pass the values to the register
                                Go_Register.putExtra("PassLoanBalance", memLoanBal);
                                Go_Register.putExtra("PassSavingsBalance", memSavingsBal);
                                Go_Register.putExtra("PassSavingsBalance", memSavingsBal);
                                Go_Register.putExtra("PassPhoneNumber", memPhoneNum);

                                startActivity(Go_Register);
                                finish();



                            }

                        } else {
                            Toast.makeText(MemberAuthActivity.this, "Member doesn't exist!!", Toast.LENGTH_SHORT).show();
                            MemBinder.progressBar.setVisibility(View.GONE);
                            MemBinder.btnVerify.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(MemberAuthActivity.this, "Error Fetching data!!", Toast.LENGTH_SHORT).show();
                        MemBinder.progressBar.setVisibility(View.GONE);
                        MemBinder.btnVerify.setVisibility(View.VISIBLE);
                    }
                }
            });

    }

/*
    public void sendOTP(String PhoneNumber, boolean isResend){
        MemBinder.progressBar.setVisibility(View.VISIBLE);
        MemBinder.btnVerify.setVisibility(View.GONE);

        PhoneAuthOptions.Builder builder = PhoneAuthOptions.newBuilder(MemAuth).setPhoneNumber(PhoneNumber)
                .setTimeout(timeoutOTP, TimeUnit.SECONDS)
                .setActivity(this).setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Intent Go_Register = new Intent(getApplicationContext(), RegisterActivity.class);
                        startActivity(Go_Register);
                        finish();
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(MemberAuthActivity.this, "Error Verifying your Phone Number", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCode = s;
                        resendingToken = forceResendingToken;
                    }
                });
        if (isResend){
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
        } else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }
    }
    */
    @Override
    public void onBackPressed(){
        Intent Go_LogIn = new Intent(getApplicationContext(), LogInActivity.class);
        startActivity(Go_LogIn);
        finish();
    }
}