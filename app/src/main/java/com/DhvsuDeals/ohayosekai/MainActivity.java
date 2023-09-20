package com.DhvsuDeals.ohayosekai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.DhvsuDeals.ohayosekai.databinding.ActivityMainBinding;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth Auth;
    private FirebaseUser User;
    ActivityMainBinding Homebinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Homebinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(Homebinding.getRoot());
        replaceFragment(new Home_Fragment());
        getSupportActionBar().hide();



        Auth = FirebaseAuth.getInstance();
        /*btnLogOut = findViewById(R.id.btnLogOut);
        btnFragment_Crud = findViewById(R.id.SwtchFragmentLYT);
        View_Account = findViewById(R.id.ViewAccount);*/
        User = Auth.getCurrentUser();


        if (User==null){
            Intent Go_LogIn = new Intent(getApplicationContext(), LogInActivity.class);
            startActivity(Go_LogIn);
            finish();
        }




        Homebinding.NavigationButtons.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.HomeNav){
                replaceFragment(new Home_Fragment());
            } else if (item.getItemId() == R.id.TransNav) {
                replaceFragment(new TransactFragment());
            } else if (item.getItemId() == R.id.InboxNav) {
                replaceFragment(new InboxFragment());
            } else if (item.getItemId() == R.id.ProfileNav) {
                replaceFragment(new ProfileFragment());
            }


            /*switch (item.getItemId()){

                case R.id.HomeNav:
                    replaceFragment(new Home_Fragment());
                    break;
                case R.id.CreateNav:
                    replaceFragment(new CreateFragment());
                    break;
                case R.id.ReadNav:
                    replaceFragment(new ReadFragment());
                    break;
                case R.id.UpdateNav:
                    replaceFragment(new UpdateFragment());
                    break;
                case R.id.DeleteNav:
                    replaceFragment(new DeleteFragment());
                    break;
            }*/
            return true;
        });

    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MainFrame,fragment);
        fragmentTransaction.commit();
    }
}