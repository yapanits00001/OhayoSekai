package com.DhvsuDeals.ohayosekai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.DhvsuDeals.ohayosekai.databinding.ActivityMainBinding;

import com.DhvsuDeals.ohayosekai.databinding.ViewpagerItemBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth Auth;
    private FirebaseUser User;
    private static final String KEY_LOANSBAL = "Mem-LoanBalance", KEY_NAME = "Mem-Name", KEY_SAVINGSBAL = "Mem-SavingsBalance";
    ActivityMainBinding Homebinding;
    private String LoanBalance, SavingsBalance;
    private ArrayList<ViewPagerItem> viewPagerItemArrayList;
    private String User_ID = Auth.getCurrentUser().getUid();//get the userUID on the firestore to be used as an user ID
    private DocumentReference SigninRef_DB = FirebaseFirestore.getInstance().collection("Uses_ACCS_Information").document(User_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Homebinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(Homebinding.getRoot());
        replaceFragment(new Home_Fragment());


        Auth = FirebaseAuth.getInstance();
        /*btnLogOut = findViewById(R.id.btnLogOut);
        btnFragment_Crud = findViewById(R.id.SwtchFragmentLYT);
        View_Account = findViewById(R.id.ViewAccount);*/
        User = Auth.getCurrentUser();


        int[] TittleLabel = {R.string.tittle_BalSavings, R.string.title_BalLoan};
        String[] balances = {LoanBalance, SavingsBalance};

        viewPagerItemArrayList = new ArrayList<>();

        for (int i = 0; i<TittleLabel.length; i++){
            ViewPagerItem ViewPagerItem = new ViewPagerItem(balances[i], TittleLabel[i]);

        }
        VPAdapter vpAdapter = new VPAdapter(viewPagerItemArrayList);

        Homebinding.crslPager.setAdapter(vpAdapter);
        Homebinding.crslPager.setClipToPadding(false);
        Homebinding.crslPager.setClipChildren(false);
        Homebinding.crslPager.setOffscreenPageLimit(2);
        Homebinding.crslPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);



        if (User==null){
            Intent Go_LogIn = new Intent(getApplicationContext(), LogInActivity.class);
            startActivity(Go_LogIn);
            finish();
        } else {
            Homebinding.ViewAccount.setText(User.getEmail());
        }

        Homebinding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent Go_LogIn = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(Go_LogIn);
                finish();
            }
        });

        Homebinding.SwtchFragmentLYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_Crud = new Intent(getApplicationContext(), SampleFragmentedLayoutMain.class);
                startActivity(Go_Crud);
                finish();
            }
        });

        Homebinding.NavigationButtons.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.HomeNav){
                replaceFragment(new Home_Fragment());
            } else if (item.getItemId() == R.id.CreateNav) {
                replaceFragment(new CreateFragment());
            } else if (item.getItemId() == R.id.ReadNav) {
                replaceFragment(new ReadFragment());
            } else if (item.getItemId() == R.id.UpdateNav) {
                replaceFragment(new UpdateFragment());
            } else if (item.getItemId() == R.id.DeleteNav) {
                replaceFragment(new DeleteFragment());
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

    @Override
    public void onStart(){
        super.onStart();
        SigninRef_DB.addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot , FirebaseFirestoreException error) {

                if (error != null){
                    Toast.makeText(MainActivity.this , "Error while loading!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (documentSnapshot.exists()){
                     LoanBalance = documentSnapshot.getString(KEY_LOANSBAL);
                     SavingsBalance = documentSnapshot.getString(KEY_SAVINGSBAL);


                    //String txtBalance = String.valueOf(LoanBalance);//convert the intbalance into string

                }
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Frame_Home,fragment);
        fragmentTransaction.commit();
    }
}