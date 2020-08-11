package com.mycorp.javachallengefour.user;

import com.mycorp.javachallengefour.utils.UserUtil;

public class UserRepository {
    private static final String BASE_URL = "https://reqres.in/api/";

    public UserServices service;

    public UserRepository() {
        service = UserUtil.user(UserServices.class, BASE_URL);
    }
}