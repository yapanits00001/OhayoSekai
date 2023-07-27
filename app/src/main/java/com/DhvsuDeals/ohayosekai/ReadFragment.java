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

import com.DhvsuDeals.ohayosekai.databinding.FragmentReadBinding;
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
import com.google.firebase.firestore.ListenerRegistration;

import java.util.Objects;
import java.util.concurrent.Executor;


public class ReadFragment extends Fragment {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String User_ID = mAuth.getCurrentUser().getUid();//get the userUID on the firestore to be used as an user ID
    DocumentReference SignUpRef_DB = FirebaseFirestore.getInstance().document("Uses_ACCS_Information/" + User_ID);
    private static final String KEY_BALANCE = "Mem-Balance", KEY_NAME = "Mem-Name";
    private FragmentReadBinding ReadBinder;
    private ListenerRegistration listener;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ReadBinder = FragmentReadBinding.inflate(inflater, container, false);
        return ReadBinder.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Access and interact with views using the 'binding' object

        ReadBinder.btnReadIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        SignUpRef_DB.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()){
                                    DocumentSnapshot ReadDocSnapshot = task.getResult();
                                    if (ReadDocSnapshot.exists()){
                                        Long intBalance = ReadDocSnapshot.getLong(KEY_BALANCE);
                                        String name = ReadDocSnapshot.getString(KEY_NAME);

                                        String txtBalance = String.valueOf(intBalance);//convert the intbalance into string
                                        ReadBinder.ViewReadName.setText(name);
                                        ReadBinder.ViewReadBalance.setText(txtBalance);

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
                                Toast.makeText(getActivity(), "Error with the program", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });



    }

    @Override
    public void onStart(){
        super.onStart();
        listener = SignUpRef_DB.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot , FirebaseFirestoreException error) {

                if (error != null){
                    Toast.makeText(getActivity(), "Error while loading!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (documentSnapshot.exists()){
                    Long intBalance = documentSnapshot.getLong(KEY_BALANCE);
                    String name = documentSnapshot.getString(KEY_NAME);

                    String txtBalance = String.valueOf(intBalance);//convert the intbalance into string
                    ReadBinder.ViewReadName.setText(name);
                    ReadBinder.ViewReadBalance.setText(txtBalance);

                }
            }
        });
    }

    @Override
    public void onStop(){
        super.onStop();
        listener.remove();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Nullify the binding object to avoid memory leaks
        ReadBinder = null;
    }

}