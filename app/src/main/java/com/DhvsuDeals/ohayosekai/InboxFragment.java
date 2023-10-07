package com.DhvsuDeals.ohayosekai;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.DhvsuDeals.ohayosekai.databinding.FragmentInboxBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;


public class InboxFragment extends Fragment {

    private FragmentInboxBinding binding;

    ArrayList<Notifications> notificationsArrayList;
    NotifsRecycleV_Adapter notifsRecycleV_adapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    public InboxFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment using View Binding
        binding = FragmentInboxBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Access and interact with views using the 'binding' object



        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data.....");
        progressDialog.show();



       /*  binding.RecycleInbox.setHasFixedSize(true);
        binding.RecycleInbox.setLayoutManager(new LinearLayoutManager(getActivity())); */

        db = FirebaseFirestore.getInstance();
        notificationsArrayList = new ArrayList<Notifications>();
        notifsRecycleV_adapter = new NotifsRecycleV_Adapter(getActivity(), notificationsArrayList);

       /*  binding.RecycleInbox.setAdapter(notifsRecycleV_adapter); */

        EventchangeListener();


    }


    private void EventchangeListener() {// function that reloads if the user changed the data in database
        db.collection("SampleInbox").orderBy("Date_Received", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Firestore error", error.getMessage());
                    Toast.makeText(getActivity(), "Firestore Error!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()){
                    if (dc.getType() == DocumentChange.Type.ADDED){
                        notificationsArrayList.add(dc.getDocument().toObject(Notifications.class));
                    }
                    notifsRecycleV_adapter.notifyDataSetChanged();
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }
        });
    }


}