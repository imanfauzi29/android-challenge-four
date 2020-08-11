package com.mycorp.javachallengefour.user;

import com.mycorp.javachallengefour.models.ResponseModel;
import com.mycorp.javachallengefour.models.UsersModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserServices {

    @GET("users")
    Call<ResponseModel> getData();
}
