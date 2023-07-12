package com.DhvsuDeals.ohayosekai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
     private TextInputEditText editTextEmail, editTextPassword;
     private Button btnReg;
     private ProgressBar ProgBar;
     private TextView View_LogIn;

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
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_register);
        editTextEmail = findViewById(R.id.txtEmail);
        editTextPassword = findViewById(R.id.txtPassword);
        btnReg = findViewById(R.id.btnRegister);
        ProgBar = findViewById(R.id.progressBar);
        View_LogIn = findViewById(R.id.LogInNow);

        View_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_LogIn = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(Go_LogIn);
                finish();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgBar.setVisibility(View.VISIBLE);
                String Click_email, Click_password;
                Click_email = String.valueOf(editTextEmail.getText());
                Click_password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(Click_email) || TextUtils.isEmpty(Click_password)){
                    Toast.makeText(RegisterActivity.this, "Missing some Credentials", Toast.LENGTH_SHORT).show();
                    ProgBar.setVisibility(View.GONE);
                    return;
                }

                /*mAuth.signInWithEmailAndPassword(Click_email, Click_password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                ProgBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Account Already Exist!!!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });*/

                mAuth.createUserWithEmailAndPassword(Click_email, Click_password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                ProgBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Authentication Success!.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent Go_LogIn = new Intent(getApplicationContext(), LogInActivity.class);
                                    startActivity(Go_LogIn);
                                    finish();
                                } else {

                                    Toast.makeText(RegisterActivity.this, "Authentication Failed!.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}