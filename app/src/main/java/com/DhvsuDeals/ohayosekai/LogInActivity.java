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

public class LogInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputEditText editTextEmail, editTextPassword;
    private Button btnLogIn;
    private ProgressBar ProgBar;
    private TextView View_Register;

    @Override
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
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.txtEmail);
        editTextPassword = findViewById(R.id.txtPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        ProgBar = findViewById(R.id.progressBar);
        View_Register = findViewById(R.id.Registration);

        View_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_Register = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(Go_Register);
                finish();
            }

        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgBar.setVisibility(View.VISIBLE);
                String Click_email, Click_password;
                Click_email = String.valueOf(editTextEmail.getText());
                Click_password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(Click_email) || TextUtils.isEmpty(Click_password)){
                    Toast.makeText(LogInActivity.this, "Missing some Credentials", Toast.LENGTH_SHORT).show();
                    ProgBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.signInWithEmailAndPassword(Click_email, Click_password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                ProgBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(LogInActivity.this, "Log-In Successful", Toast.LENGTH_SHORT).show();
                                    Intent Go_Dashboard = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(Go_Dashboard);
                                    finish();
                                } else {
                                    Toast.makeText(LogInActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    ;
                                }
                            }
                        });
            }
        });
    }
}