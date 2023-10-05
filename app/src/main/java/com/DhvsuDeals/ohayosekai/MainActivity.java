package com.DhvsuDeals.ohayosekai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.DhvsuDeals.ohayosekai.databinding.ActivityMainBinding;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.navigation.NavigationBarView;
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
    VP_Adapter_LOANS_SAVINGS vp_adapter_loans_savings;
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

    }//check if the user is logged in - end function

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Homebinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(Homebinding.getRoot());



        replaceFragment(new Home_Fragment());

        Auth = FirebaseAuth.getInstance();
        User = Auth.getCurrentUser();


        vp_adapter_loans_savings = new VP_Adapter_LOANS_SAVINGS(MainActivity.this);
        Homebinding.VPLoansSavings.setAdapter(vp_adapter_loans_savings);

        Homebinding.VPLoansSavings.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateIndicator(position);
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
                        if (BadgeCounter == 0){
                            badge.setVisible(false);
                        } else {
                            badge.setVisible(true);
                            badge.setNumber(BadgeCounter);
                        }

                        //end notif badge statement

                        //code for the navigation buttons swipable
                        switch (position){
                            case 0:
                                Homebinding.NavigationButtons.setSelectedItemId(R.id.HomeNav);
                                break;
                            case 1:
                                Homebinding.NavigationButtons.setSelectedItemId(R.id.InboxNav);
                                badge.setVisible(false);
                                break;
                        }

                        //code for the navigation buttons of fragments - end statement
                    }
                });
            }
        });

        Homebinding.VPLoansSavings.setPageTransformer(new PageTransformer());

        //pressing the home buttons
        Homebinding.NavigationButtons.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.HomeNav){
                    replaceFragment(new Home_Fragment());
                    Homebinding.VPLoansSavings.setCurrentItem(0);
                } else if (item.getItemId() == R.id.InboxNav) {
                    replaceFragment(new InboxFragment());
                    Homebinding.VPLoansSavings.setCurrentItem(1);
                }
                return true;
            }
        });


    }
    private void updateIndicator(int position){

        Homebinding.indicatorVP.removeAllViews();
        for (int i = 0; i < vp_adapter_loans_savings.getItemCount(); i++){
            ImageView indicator = new ImageView(MainActivity.this   );
            indicator.setImageResource(
                    i == position ? R.drawable.selected_indicator : R.drawable.idle_selector_indicator
            );
            Homebinding.indicatorVP.addView(indicator);
        }
        switch (position) {
            case 0:
                replaceFragment(new MemberHomeFragment());
                break;
            case 1:
                replaceFragment(new InboxFragment());
                break;
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }

    }

    private void replaceFragment(Fragment fragment){ //code for switching different fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MainFrame,fragment);
        fragmentTransaction.commit();
    }
    //code for switching different fragments - end statement
}