package com.mycorp.javachallengefour.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.luqman.dev.helloworld.R;
import com.mycorp.javachallengefour.adapters.UserAdapter;
import com.mycorp.javachallengefour.models.ResponseModel;
import com.mycorp.javachallengefour.models.UsersModel;
import com.mycorp.javachallengefour.user.UserRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    ProgressBar pbLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        RecyclerView rvuser = findViewById(R.id.rvUsers);
        pbLoading = findViewById(R.id.loadings);

        showLoading(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvuser.setLayoutManager(linearLayoutManager);

        UserRepository userRepository = new UserRepository();

        userRepository.service.getData().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UsersModel> users = response.body().getData();
                    UserAdapter userAdapter = new UserAdapter(getApplicationContext(), users);

                    rvuser.setAdapter(userAdapter);
                    showLoading(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("error", "Failed to fetch data", t);
            }
        });
    }

    private void showLoading(boolean b) {
        if (b) {
            pbLoading.setVisibility(View.VISIBLE);
        }else {
            pbLoading.setVisibility(View.GONE);
        }
    }
}