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
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.DhvsuDeals.ohayosekai.databinding.ActivityLogInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

     ActivityLogInBinding LIBinder;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            if (currentUser.isEmailVerified()){
                Toast.makeText(LogInActivity.this, "Log-In Successful", Toast.LENGTH_SHORT).show();
                Intent Go_Dashboard = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Go_Dashboard);
                finish();
            } else {
                Toast.makeText(LogInActivity.this, "Email is not Verified!!.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LIBinder = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(LIBinder.getRoot());
        mAuth = FirebaseAuth.getInstance();

        String viewTextReg = "Don't have an account? Sign Up Here. ";
        SpannableString SpanStr = new SpannableString(viewTextReg);
        SpanStr.setSpan(new ForegroundColorSpan(Color.BLUE), 24,36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableSpan clickRegister = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(LogInActivity.this, "Redirecting..", Toast.LENGTH_SHORT).show();
                Intent Go_Verify = new Intent(getApplicationContext(), MemberAuthActivity.class);
                startActivity(Go_Verify);
                finish();
            }
        };
        SpanStr.setSpan(clickRegister, 23, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        LIBinder.Registration.setText(SpanStr);
        LIBinder.Registration.setMovementMethod(LinkMovementMethod.getInstance());






       /* LIBinder.Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_Register = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(Go_Register);
                finish();
            }

        });*/


        LIBinder.btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LIBinder.progressBar.setVisibility(View.VISIBLE);
                String Click_email, Click_password;
                Click_email = String.valueOf(LIBinder.txtEmail.getText());
                Click_password = String.valueOf(LIBinder.txtPassword.getText());

                if (TextUtils.isEmpty(Click_email) || TextUtils.isEmpty(Click_password)){
                    Toast.makeText(LogInActivity.this, "Missing some Credentials", Toast.LENGTH_SHORT).show();
                    LIBinder.progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.signInWithEmailAndPassword(Click_email, Click_password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                LIBinder.progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    if (mAuth.getCurrentUser().isEmailVerified()){
                                        Toast.makeText(LogInActivity.this, "Log-In Successful", Toast.LENGTH_SHORT).show();
                                        Intent Go_Dashboard = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(Go_Dashboard);
                                        finish();
                                    } else {
                                        Toast.makeText(LogInActivity.this, "Email is not Verified!!.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(LogInActivity.this, "Log-In Failed.",
                                            Toast.LENGTH_SHORT).show();
                                    ;
                                }
                            }
                        });
            }
        });
    }

}