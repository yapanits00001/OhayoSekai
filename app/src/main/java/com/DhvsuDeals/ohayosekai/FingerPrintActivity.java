package com.DhvsuDeals.ohayosekai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class FingerPrintActivity extends AppCompatActivity {

    private ImageView FingerPrintIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        FingerPrintIMG = findViewById(R.id.IMG_FPrint);
    //TODO Maybe next time na fingerprint

    }
}