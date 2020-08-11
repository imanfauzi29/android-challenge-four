package com.mycorp.javachallengefour.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {

    @SerializedName("page")
    private int page;

    @SerializedName("per_page")
    private int perPage;

    @SerializedName("total")
    private int totalUsers;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("data")
    private List<UsersModel> data;

    public List<UsersModel> getData() {
        return data;
    }



}
