package com.DhvsuDeals.ohayosekai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.DhvsuDeals.ohayosekai.databinding.ActivityForgotPasswordBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    FirebaseAuth FBAuth;
    ActivityForgotPasswordBinding FPBinding;
    String resetPasswordEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FPBinding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(FPBinding.getRoot());

        FBAuth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();


        FPBinding.btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPBinding.resetProgBar.setVisibility(View.VISIBLE);
                resetPasswordEmail = String.valueOf(FPBinding.txtForgotEmail.getText()).trim();

                if (!TextUtils.isEmpty(resetPasswordEmail)){
                    ResetPassword();

                } else {

                    FPBinding.txtForgotEmail.setError("Email cannot be empty");
                    FPBinding.txtForgotEmail.requestFocus();
                    FPBinding.resetProgBar.setVisibility(View.GONE);
                }
            }
        });


    }

    private void ResetPassword() {
        FPBinding.resetProgBar.setVisibility(View.VISIBLE);
        FPBinding.btnResetPassword.setVisibility(View.INVISIBLE);

        FBAuth.sendPasswordResetEmail(resetPasswordEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                openDialogResetPass();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ForgotPasswordActivity.this, "Error Fetching Data..", Toast.LENGTH_SHORT).show();
                FPBinding.resetProgBar.setVisibility(View.GONE);
                FPBinding.btnResetPassword.setVisibility(View.VISIBLE);
            }
        });
    }

    public void openDialogResetPass(){
        ForgetPasswordDialogBox forgetPasswordDialogBox = new ForgetPasswordDialogBox();
        forgetPasswordDialogBox.show(getSupportFragmentManager(), "Password Reset");

    }


}