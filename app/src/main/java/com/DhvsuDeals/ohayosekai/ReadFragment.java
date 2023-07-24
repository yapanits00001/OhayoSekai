package com.DhvsuDeals.ohayosekai;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class ReadFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView ViewName, ViewBalance;
    private Button btnRead;
    private static final String KEY_BALANCE = "Mem-Balance", KEY_NAME = "Mem-Name";


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_read, container, false);
        ViewName = view.findViewById(R.id.View_ReadName);
        ViewBalance = view.findViewById(R.id.View_ReadBalance);
        btnRead = view.findViewById(R.id.btnReadIt);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_ID = mAuth.getCurrentUser().getUid();//get the userUID on the firestore to be used as an user ID
                DocumentReference SignUpRef_DB = db.collection("Uses_ACCS_Information").document(User_ID);
                SignUpRef_DB.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                        SignUpRef_DB.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()){
                                    DocumentSnapshot ReadDocSnapshot = task.getResult();
                                    if (ReadDocSnapshot.exists()){
                                        Long intBalance = ReadDocSnapshot.getLong(KEY_BALANCE);
                                        String name = ReadDocSnapshot.getString(KEY_NAME);

                                        String txtBalance = String.valueOf(intBalance);//convert the intbalance into string
                                        ViewName.setText(name);
                                        ViewBalance.setText(txtBalance);

                                    } else {
                                        Toast.makeText(getActivity(), "No Document saved on this account", Toast.LENGTH_SHORT).show();

                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Error occurred while fetching data", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

                    }
                });




            }
        });


        return view;
    }
}