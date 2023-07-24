package com.DhvsuDeals.ohayosekai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth Auth;
    private Button btnLogOut, btnFragment_Crud;
    private ProgressBar ProgBar;
    private TextView View_Account;
    private FirebaseUser User;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Auth = FirebaseAuth.getInstance();
        btnLogOut = findViewById(R.id.btnLogOut);
        btnFragment_Crud = findViewById(R.id.SwtchFragmentLYT);
        View_Account = findViewById(R.id.ViewAccount);
        User = Auth.getCurrentUser();

        if (User==null){
            Intent Go_LogIn = new Intent(getApplicationContext(), LogInActivity.class);
            startActivity(Go_LogIn);
            finish();
        } else {
            View_Account.setText(User.getEmail());
        }

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent Go_LogIn = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(Go_LogIn);
                finish();
            }
        });

        btnFragment_Crud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_Crud = new Intent(getApplicationContext(), SampleFragmentedLayoutMain.class);
                startActivity(Go_Crud);
                finish();
            }
        });

    }
}