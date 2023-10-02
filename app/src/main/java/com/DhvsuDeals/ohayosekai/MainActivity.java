package com.DhvsuDeals.ohayosekai;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.DhvsuDeals.ohayosekai.databinding.ActivityMainBinding;

import com.google.android.material.badge.BadgeDrawable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth Auth;
    private FirebaseUser User;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ActivityMainBinding Homebinding;

    int BadgeCounter;

    public void onStart() { //check if the user is logged in
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        User = Auth.getCurrentUser();
        if(User == null){
            Intent Go_Login = new Intent(getApplicationContext(), LogInActivity.class);
            startActivity(Go_Login);
            finish();
        }

/*
        db.collection("SampleInbox").addSnapshotListener((value, error) -> {
            if (error != null){
                Toast.makeText(this, "Error Fetching Data!!", Toast.LENGTH_SHORT).show();
            }
            if (!value.isEmpty()){
                BadgeCounter = value.size();
            }

        });*/
//TODO:will try snapshot listener




    }//check if the user is logged in - end function

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Homebinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(Homebinding.getRoot());



        replaceFragment(new Home_Fragment());

        Auth = FirebaseAuth.getInstance();
        User = Auth.getCurrentUser();

        //start of notif badge statement

        db.collection("SampleInbox").whereEqualTo("Status", false).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    // Handle errors
                    System.err.println("Listen failed: " + error);
                }
                BadgeCounter = value.size();
                BadgeDrawable badge = Homebinding.NavigationButtons.getOrCreateBadge(R.id.InboxNav);
                badge.setBackgroundColor(Color.RED);
                badge.setBadgeTextColor(Color.WHITE);
                badge.setMaxCharacterCount(5);
                badge.setNumber(BadgeCounter);
                badge.setVisible(true);

                //end notif badge statement

                //code for the navigation buttons of fragments
                Homebinding.NavigationButtons.setOnItemSelectedListener(item -> {
                    if (item.getItemId() == R.id.HomeNav){
                        replaceFragment(new Home_Fragment());
                    } else if (item.getItemId() == R.id.TransNav) {
                        replaceFragment(new TransactFragment());
                    } else if (item.getItemId() == R.id.InboxNav) {
                        replaceFragment(new InboxFragment());
                        if (BadgeCounter == 0){
                            badge.setVisible(false);
                        } else {
                            badge.setVisible(true);
                            badge.setNumber(BadgeCounter);
                        }
                    } else if (item.getItemId() == R.id.ProfileNav) {
                        replaceFragment(new ProfileFragment());
                    }
                    return true;
                });
                //code for the navigation buttons of fragments - end statement
            }
        });


    }


    private void replaceFragment(Fragment fragment){ //code for switching different fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MainFrame,fragment);
        fragmentTransaction.commit();
    }
    //code for switching different fragments - end statement
}