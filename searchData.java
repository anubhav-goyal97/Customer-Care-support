package com.anubhav.lenovo.dell;

import com.anubhav.lenovo.dell.Client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Lenovo on 29-08-2018.
 */

public interface searchData {
    @POST("client")
    Call<Client> createSearchData(@Body Client user);


}
