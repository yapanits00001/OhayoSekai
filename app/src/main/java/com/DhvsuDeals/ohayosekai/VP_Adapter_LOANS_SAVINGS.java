package com.DhvsuDeals.ohayosekai;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VP_Adapter_LOANS_SAVINGS extends FragmentStateAdapter {

    public VP_Adapter_LOANS_SAVINGS(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0: return new MemberHomeFragment();
            case 1: return new Loans_ViewFragment();
            default: return new MemberHomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
