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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MemberAuthActivity extends AppCompatActivity {

    private FirebaseAuth MemAuth;
    FirebaseUser CurUser;
    private CollectionReference MemCheckCollection;
    private ActivityMemberAuthBinding MemBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemBinder = ActivityMemberAuthBinding.inflate(getLayoutInflater());
        setContentView(MemBinder.getRoot());
        MemAuth = FirebaseAuth.getInstance();
        CurUser = MemAuth.getCurrentUser();
        MemCheckCollection = FirebaseFirestore.getInstance().collection("Member_Check_SignUp");


        MemBinder.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchRec = String.valueOf(MemBinder.txtVID.getText()).trim();
                if (!TextUtils.isEmpty(searchRec)){
                        SearchMember(searchRec);
                } else {
                    MemBinder.txtVID.setError("Please Fill out the empty Field!!");
                    MemBinder.txtVID.requestFocus();
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
                            String compAcc_Status = docSnapshot.getString("Account_Status");
                            if (compAcc_Status == "Registered"){
                                Toast.makeText(MemberAuthActivity.this, "Account Already Registered!!", Toast.LENGTH_SHORT).show();
                            } else{
                                String welcomeName = docSnapshot.getString("Mem_Name");
                                Toast.makeText(MemberAuthActivity.this, "Welcome our dear Member " + welcomeName, Toast.LENGTH_SHORT).show();
                                Intent Go_Register = new Intent(getApplicationContext(), RegisterActivity.class);
                                Go_Register.putExtra("PassMemID", searchRec);
                                Go_Register.putExtra("PassMemName", welcomeName); //pass the values to the register
                                startActivity(Go_Register);
                                finish();
                            }

                        } else {
                            Toast.makeText(MemberAuthActivity.this, "Member doesn't exist!!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MemberAuthActivity.this, "Error Fetching data!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }
    @Override
    public void onBackPressed(){
        Intent Go_LogIn = new Intent(getApplicationContext(), LogInActivity.class);
        startActivity(Go_LogIn);
        finish();
    }
}