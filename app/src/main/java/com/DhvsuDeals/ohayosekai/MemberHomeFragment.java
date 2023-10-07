package com.DhvsuDeals.ohayosekai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.DhvsuDeals.ohayosekai.databinding.FragmentMemberHomeBinding;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class MemberHomeFragment extends Fragment {
    FragmentMemberHomeBinding memberHomeBinding;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String User_ID = FirebaseAuth.getInstance().getCurrentUser().getUid();//get the userUID on the firestore to be used as an user ID
        DocumentReference Get_UserInfo = FirebaseFirestore.getInstance().document("Members_Collections/" + User_ID);
    private ListenerRegistration listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        memberHomeBinding = FragmentMemberHomeBinding.inflate(inflater, container, false);
        return memberHomeBinding.getRoot();
    }


    @Override
    public void onStart(){
        super.onStart();
        listener = Get_UserInfo.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot , FirebaseFirestoreException error) {

                if (error != null){
                    Toast.makeText(getActivity(), "Error while loading!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (documentSnapshot.exists()){
                    Account_Info_Note note = documentSnapshot.toObject(Account_Info_Note.class);

                    String EMP_ID = note.getMem_EMP_ID();
                    String memID = note.getMem_ID();


                    //String txtBalance = String.valueOf(LoanBalance);convert the intbalance into string
                    memberHomeBinding.ViewEmployeeID.setText((EMP_ID));
                    memberHomeBinding.ViewMemID.setText(memID);

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
        memberHomeBinding = null;
    }
}