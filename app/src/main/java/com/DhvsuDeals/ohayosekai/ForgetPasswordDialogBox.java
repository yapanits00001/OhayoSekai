package com.DhvsuDeals.ohayosekai;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ForgetPasswordDialogBox extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Password Reset Link Sent!!")
                .setMessage("Reset Password link has been sent to your Registered Email!!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Go_Login();
                    }

                });
        return builder.create();
    }
    public void Go_Login(){
        Intent Go_LogIn = new Intent(getActivity(), LogInActivity.class);
        startActivity(Go_LogIn);
    }
}
