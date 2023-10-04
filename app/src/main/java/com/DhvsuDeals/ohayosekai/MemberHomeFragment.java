package com.DhvsuDeals.ohayosekai;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.DhvsuDeals.ohayosekai.databinding.FragmentHomeBinding;
import com.DhvsuDeals.ohayosekai.databinding.FragmentMemberHomeBinding;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class MemberHomeFragment extends Fragment {
    FragmentMemberHomeBinding memberHomeBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        memberHomeBinding = FragmentMemberHomeBinding.inflate(inflater, container, false);
        return memberHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }
}