package com.DhvsuDeals.ohayosekai;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFragment extends Fragment {

    private TextInputEditText edittxt_Name, edittxt_Money;
    private Button btnSave;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        edittxt_Name = view.findViewById(R.id.txtName);
        edittxt_Money = view.findViewById(R.id.txtCMoney);
        btnSave = view.findViewById(R.id.btnCreate);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_ID = mAuth.getCurrentUser().getUid();//get the userUID on the firestore to be used as an user ID
                DocumentReference SignUpRef_DB = FirebaseFirestore.getInstance().collection("Uses_ACCS_Information").document(User_ID);
                String txtname = String.valueOf(edittxt_Name.getText());
                String txtmoney = String.valueOf(edittxt_Money.getText());
                if (txtname.isEmpty() || txtmoney.isEmpty()){
                    Toast.makeText(getActivity(), "Fill out all of the boxes please!!", Toast.LENGTH_SHORT).show();
                } else {
                    Double nummoney = Double.parseDouble(txtmoney);
                    Map<String, Object> SaveUser = new HashMap<>();
                    SaveUser.put("Mem-ID", User_ID);
                    SaveUser.put("Mem-Name", txtname);
                    SaveUser.put("Mem-Balance", nummoney);

                    SignUpRef_DB.set(SaveUser)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    Toast.makeText(getActivity(), "The Data has been Saved!!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "Error Saving!!", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, e.toString());
                                }
                            });
                }

            }
        });
        return view;
    }

}