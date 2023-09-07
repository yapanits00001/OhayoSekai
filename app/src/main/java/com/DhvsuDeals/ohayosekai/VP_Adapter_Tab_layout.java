package com.DhvsuDeals.ohayosekai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class VP_Adapter_Tab_layout extends FragmentStateAdapter {


    public VP_Adapter_Tab_layout(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0: return new InboxFragment();
            case 1: return new ReadFragment();
            default: return new InboxFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
