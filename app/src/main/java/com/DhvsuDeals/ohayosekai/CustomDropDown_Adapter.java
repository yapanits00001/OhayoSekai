package com.DhvsuDeals.ohayosekai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomDropDown_Adapter extends ArrayAdapter<String> {
    public CustomDropDown_Adapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
