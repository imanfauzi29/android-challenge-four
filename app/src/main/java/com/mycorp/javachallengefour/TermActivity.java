package com.mycorp.javachallengefour;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.luqman.dev.helloworld.R;

public class TermActivity extends AppCompatActivity {

    AlertDialog dialog;
    AlertDialog.Builder alert;
    CheckBox checkBox;
    View cbView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        sharedPreferences = getSharedPreferences(SplashActivity.MY_SHARED, Context.MODE_PRIVATE);

        cbView = View.inflate(this, R.layout.terms_message, null);
        checkBox = cbView.findViewById(R.id.checkbox);

        alert = new AlertDialog.Builder(this);
        alert.setTitle("TERMS AND CONDITIONS")
                .setCancelable(false)
                .setView(cbView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(SplashActivity.SESSION_TERMS,true);
                        editor.apply();
                    }
                });

        dialog = alert.create();

//        checkedBox(dialog, checkBox);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (checkBox.isChecked()) ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                        else ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    }
                });
            }
        });

        dialog.show();
    }

//    private void checkedBox(final AlertDialog dialog, final CheckBox checkBox) {
//        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialogInterface) {
//                ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
//
//                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                        if (checkBox.isChecked()) ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
//                        else ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
//                    }
//                });
//            }
//        });
//
//        dialog.show();
//    }

}