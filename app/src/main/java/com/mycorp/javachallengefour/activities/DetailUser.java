package com.mycorp.javachallengefour.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luqman.dev.helloworld.R;

public class DetailUser extends AppCompatActivity {

    ImageView profileImage;
    TextView userName, userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        userName = findViewById(R.id.users_names);
        userEmail = findViewById(R.id.user_email);
        profileImage = findViewById(R.id.profile_image);

        Bundle extras = getIntent().getExtras();
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(extras.getString("username").toUpperCase());
        userName.setText(extras.getString("username"));
        userEmail.setText(extras.getString("email"));
        Glide.with(getApplicationContext()).load(extras.getString("image")).apply(RequestOptions.centerCropTransform()).into(profileImage);

    }
}