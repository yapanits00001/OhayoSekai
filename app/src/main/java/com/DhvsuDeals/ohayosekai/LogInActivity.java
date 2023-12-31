package com.DhvsuDeals.ohayosekai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

import com.DhvsuDeals.ohayosekai.databinding.ActivityLogInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {
     FirebaseAuth mAuth;
     FirebaseUser User;
     
     ActivityLogInBinding LIBinder;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        User = mAuth.getCurrentUser();
        if(User != null){
            Intent Go_Dashboard = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(Go_Dashboard);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LIBinder = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(LIBinder.getRoot());
        mAuth = FirebaseAuth.getInstance();


        LIBinder.btnLogIn.setBackgroundResource(R.drawable.btn_bg);



        //clickable link like for register account
        String viewTextReg = "Don't have an account? Sign Up Here.";
        SpannableString SpanStrReg = new SpannableString(viewTextReg);
        SpanStrReg.setSpan(new ForegroundColorSpan(Color.BLUE), 24,35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableSpan clickReg = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(LogInActivity.this, "Redirecting..", Toast.LENGTH_SHORT).show();
                Intent Go_Verify = new Intent(getApplicationContext(), MemberAuthActivity.class);
                startActivity(Go_Verify);
                finish();
            }
        };
        SpanStrReg.setSpan(clickReg, 23, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        LIBinder.Registration.setText(SpanStrReg);
        LIBinder.Registration.setMovementMethod(LinkMovementMethod.getInstance());

        //clickable link like for forgot password
        String viewTextForgotPass = "Forgot Password? ";
        SpannableString SpanStrForgotPass = new SpannableString(viewTextForgotPass);
        SpanStrForgotPass.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ClickableSpan clickForgotPassword = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(LogInActivity.this, "Redirecting..", Toast.LENGTH_SHORT).show();
                Intent Go_ForgotPass = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(Go_ForgotPass);
                finish();
            }
        };
        SpanStrForgotPass.setSpan(clickForgotPassword, 0, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        LIBinder.ForgotPassword.setText(SpanStrForgotPass);
        LIBinder.ForgotPassword.setMovementMethod(LinkMovementMethod.getInstance());




        LIBinder.btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LIBinder.progressBar.setVisibility(View.VISIBLE);
                String Click_email, Click_password;
                Click_email = String.valueOf(LIBinder.txtEmail.getText()).trim();
                Click_password = String.valueOf(LIBinder.txtPassword.getText()).trim();

                //check if the email and password fields are empty
                if (TextUtils.isEmpty(Click_email)){
                    LIBinder.txtEmail.setError("Email cannot be empty!!");
                    LIBinder.txtEmail.requestFocus();
                    LIBinder.progressBar.setVisibility(View.GONE);
                    return;
                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(Click_email).matches()){
                        LIBinder.txtEmail.setError("Please Enter a Valid Email");
                        LIBinder.txtEmail.requestFocus();
                        LIBinder.progressBar.setVisibility(View.GONE);
                        return;
                    }
                    if (TextUtils.isEmpty(Click_password)){
                        LIBinder.txtPassword.setError("Password cannot be empty!!");
                        LIBinder.txtPassword.requestFocus();
                        LIBinder.progressBar.setVisibility(View.GONE);
                        return;
                    }
                }

                //sign in function
                mAuth.signInWithEmailAndPassword(Click_email, Click_password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                LIBinder.progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    //user will be logged in if he or she is verified and met the requirements
                                    if (mAuth.getCurrentUser().isEmailVerified()){
                                        Toast.makeText(LogInActivity.this, "Log-In Successful", Toast.LENGTH_SHORT).show();
                                        Intent Go_Dashboard = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(Go_Dashboard);
                                        finish();
                                    } else {
                                        //throws error if email is not verified
                                        Toast.makeText(LogInActivity.this, "Email is not Verified!!.",
                                                Toast.LENGTH_SHORT).show();
                                        mAuth.signOut();
                                    }

                                } else {
                                    //shows log in faild if user doesnt met the requirements
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthInvalidUserException e){
                                        LIBinder.txtEmail.setError("User Does not Exist!");
                                        LIBinder.txtEmail.requestFocus();
                                        LIBinder.progressBar.setVisibility(View.GONE);
                                    } catch (FirebaseAuthInvalidCredentialsException e){
                                        LIBinder.txtEmail.setError("Wrong input Credentials!! kindly, check and re-enter.");
                                        LIBinder.txtEmail.requestFocus();
                                        LIBinder.progressBar.setVisibility(View.GONE);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    mAuth.signOut();
                                }
                            }
                        });
            }
        });



    }

}