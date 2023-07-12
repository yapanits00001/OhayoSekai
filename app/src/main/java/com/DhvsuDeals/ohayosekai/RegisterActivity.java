package com.DhvsuDeals.ohayosekai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
     private TextInputEditText editTextEmail, editTextPassword, editTextConfirmPass;
     private Button btnReg;
     private ProgressBar ProgBar;
     private TextView View_LogIn;
     private String User_ID;


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
        editTextConfirmPass = findViewById(R.id.txtConfirm_Password);
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
                String Click_email, Click_password, Click_ConfirmPass;
                Click_email = String.valueOf(editTextEmail.getText());
                Click_password = String.valueOf(editTextPassword.getText());
                Click_ConfirmPass = String.valueOf(editTextConfirmPass.getText());

                if (TextUtils.isEmpty(Click_email) || TextUtils.isEmpty(Click_password)){
                    Toast.makeText(RegisterActivity.this, "Missing some Credentials", Toast.LENGTH_SHORT).show();
                    ProgBar.setVisibility(View.GONE);
                    return;
                }

                //check if the email inputed matches the EMAIL PATTERN Type
                if (Patterns.EMAIL_ADDRESS.matcher(Click_email).matches()){

                    //check if passwords are matched
                    if (Click_password.matches(Click_ConfirmPass)){
                        mAuth.createUserWithEmailAndPassword(Click_email, Click_password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        ProgBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            User_ID = mAuth.getCurrentUser().getUid();//get the userUID on the firestore to be used as an user ID
                                            DocumentReference SignUpRef_DB = FirebaseFirestore.getInstance().collection("Uses_ACCS_Information").document(User_ID);
                                            Map<String, Object> SaveUser = new HashMap<>();
                                            SaveUser.put("Mem-ID", User_ID);
                                            SaveUser.put("Mem-Email", Click_email);
                                            SaveUser.put("Mem-Password", Click_password);

                                            SignUpRef_DB.set(SaveUser);

                                            Toast.makeText(RegisterActivity.this, "Authentication Success!.", Toast.LENGTH_SHORT).show();
                                            Intent Go_LogIn = new Intent(getApplicationContext(), LogInActivity.class);
                                            startActivity(Go_LogIn);
                                            finish();
                                        } else {

                                            Toast.makeText(RegisterActivity.this, "Authentication Failed!.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }else {
                        editTextPassword.setError("Passwords doesn't Match!!");
                        editTextPassword.requestFocus();
                        ProgBar.setVisibility(View.GONE);
                    }//end else of password matching

                } else {
                    editTextEmail.setError("Please Enter a Valid Email");
                    editTextEmail.requestFocus();
                    ProgBar.setVisibility(View.GONE);
                }//end else of email pattern checking


            }
        });
    }
}