package com.DhvsuDeals.ohayosekai;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.DhvsuDeals.ohayosekai.databinding.FragmentTransactBinding;
import com.google.android.material.tabs.TabLayout;


public class TransactFragment extends Fragment {

    public TransactFragment(){}

    private FragmentTransactBinding binding;
    VP_Adapter_Tab_layout vp_adapter_tab_layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment using View Binding
        binding = FragmentTransactBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Access and interact with views using the 'binding' object

        vp_adapter_tab_layout = new VP_Adapter_Tab_layout(getActivity());
        binding.VPTransactHistory.setAdapter(vp_adapter_tab_layout);
        binding.TabLayoutTransact.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.VPTransactHistory.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.VPTransactHistory.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.TabLayoutTransact.getTabAt(position).select();
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Nullify the binding object to avoid memory leaks
        binding = null;
    }
}