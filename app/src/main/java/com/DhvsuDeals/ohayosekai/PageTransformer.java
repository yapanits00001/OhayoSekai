package com.DhvsuDeals.ohayosekai;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;

public class PageTransformer implements ViewPager2.PageTransformer {
    private static final float MIN_SCALE = 0.85f; // Adjust this value for the amount of peeking

    @Override
    public void transformPage(View page, float position) {
        if (position >= -1 && position <= 1) {
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
    }
}
