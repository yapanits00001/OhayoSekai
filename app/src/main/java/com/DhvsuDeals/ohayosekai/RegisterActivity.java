package com.DhvsuDeals.ohayosekai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.DhvsuDeals.ohayosekai.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
     private String User_ID;

     ActivityRegisterBinding RegBinder;
     ProgressDialog progressDialog;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent Go_Dashboard = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(Go_Dashboard);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RegBinder = ActivityRegisterBinding.inflate(getLayoutInflater());
        mAuth = FirebaseAuth.getInstance();
        setContentView(RegBinder.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data.....");


        String ViewTextLogIn = "Already registered? Log-In Here.";
        SpannableString SpanStr = new SpannableString(ViewTextLogIn);
        SpanStr.setSpan(new ForegroundColorSpan(Color.BLUE), 20,32, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableSpan ClickLogIn = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(RegisterActivity.this, "Redirecting...", Toast.LENGTH_SHORT).show();
                Intent Go_LogIn = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(Go_LogIn);
                finish();
            }
        };
        SpanStr.setSpan(ClickLogIn, 20, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        RegBinder.LogInNow.setText(SpanStr);
        RegBinder.LogInNow.setMovementMethod(LinkMovementMethod.getInstance());//give the function for the colored text to go to LogIn Activity



        RegBinder.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegBinder.progressBar.setVisibility(View.VISIBLE);
                String Click_email, Click_password, Click_ConfirmPass, MemName, MemID;
                Click_email = String.valueOf(RegBinder.txtEmail.getText());
                Click_password = String.valueOf(RegBinder.txtPassword.getText());
                Click_ConfirmPass = String.valueOf(RegBinder.txtConfirmPassword.getText());
                MemName = getIntent().getStringExtra("PassMemName");
                MemID = getIntent().getStringExtra("PassMemID");

                if (TextUtils.isEmpty(Click_email) || TextUtils.isEmpty(Click_password)){
                    Toast.makeText(RegisterActivity.this, "Missing some Credentials", Toast.LENGTH_SHORT).show();
                    RegBinder.progressBar.setVisibility(View.GONE);

                    return;
                }

                //check if the email inputed matches the EMAIL PATTERN Type
                if (Patterns.EMAIL_ADDRESS.matcher(Click_email).matches()){

                    //check if passwords are matched
                    if (Click_password.matches(Click_ConfirmPass)){
                        mAuth.createUserWithEmailAndPassword(Click_email, Click_password)//User will be registered in the firebase Authentication
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        RegBinder.progressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {

                                            mAuth.getCurrentUser().sendEmailVerification().
                                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){

                                                        User_ID = mAuth.getCurrentUser().getUid();//get the userUID on the firestore to be used as an user ID
                                                        DocumentReference SignUpRef_DB = FirebaseFirestore.getInstance().collection("Uses_ACCS_Information").document(User_ID);
                                                        Map<String, Object> SaveUser = new HashMap<>();
                                                        SaveUser.put("Mem-ID", MemID);
                                                        SaveUser.put("Mem-Name", MemName);
                                                        SaveUser.put("Mem-Email", Click_email);
                                                        SaveUser.put("Mem-Password", Click_password);

                                                        SignUpRef_DB.set(SaveUser);
                                                        Toast.makeText(RegisterActivity.this, "Registered Successfully!!.", Toast.LENGTH_SHORT).show();
                                                        openDialog();

                                                    } else {
                                                        Toast.makeText(RegisterActivity.this, "Error Verifying!!!", Toast.LENGTH_SHORT).show();
                                                    }


                                                }
                                            });


                                        } else {

                                            Toast.makeText(RegisterActivity.this, "User Registration Failed!.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }else {
                        RegBinder.txtPassword.setError("Passwords doesn't Match!!");
                        RegBinder.txtPassword.requestFocus();
                        RegBinder.progressBar.setVisibility(View.GONE);
                    }//end else of password matching

                } else {
                    RegBinder.txtEmail.setError("Please Enter a Valid Email");
                    RegBinder.txtEmail.requestFocus();
                    RegBinder.progressBar.setVisibility(View.GONE);
                }//end else of email pattern checking


            }
        });
    }

    public void openDialog(){
        VerifyEmailDialogBox verifyEmailDialogBox = new VerifyEmailDialogBox();
        verifyEmailDialogBox.show(getSupportFragmentManager(), "email notif verifier");

    }
}