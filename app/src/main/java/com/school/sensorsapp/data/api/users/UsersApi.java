package com.school.sensorsapp.data.api.users;

import android.telecom.Call;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsersApi {

    @GET("user")
    Call getUsers();

    @GET("user/{id}")
    android.telecom.Call getUser(
            @Path("id")
            long id
    );
}
