package com.DhvsuDeals.ohayosekai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SampleFragmentedLayoutMain extends AppCompatActivity {
    private Button B_Create, B_Read, B_Update, B_Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_fragmented_layout_main);
        B_Create = findViewById(R.id.btnCreate);
        B_Read = findViewById(R.id.btnRead);
        B_Update = findViewById(R.id.btnUpdate);
        B_Delete = findViewById(R.id.btnDelete);

        B_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Home_Fragment());

            }
        });

        B_Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ReadFragment());
            }
        });

        B_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TransactFragment());
            }
        });

        B_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ProfileFragment());
            }
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Frame_Crud,fragment);
        fragmentTransaction.commit();

    }
}