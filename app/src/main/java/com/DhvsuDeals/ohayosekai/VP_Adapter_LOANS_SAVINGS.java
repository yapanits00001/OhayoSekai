package com.DhvsuDeals.ohayosekai;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VP_Adapter_LOANS_SAVINGS extends FragmentStateAdapter {

    public VP_Adapter_LOANS_SAVINGS(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0: return new Loans_ViewFragment();
            case 1: return new Savings_ViewFragment();
            default: return new Loans_ViewFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}