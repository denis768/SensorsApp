package com.school.sensorsapp.data.api;

import com.school.sensorsapp.data.repository.LocalDateTimeJsonAdapter;
import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitService {
    private static final String BASE_URL = "https://cloud.itx.ru:444/Sensor-server-0.0.1-SNAPSHOT/";

    private static Retrofit retrofit;

    private static Retrofit create() {
        return new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().add(new LocalDateTimeJsonAdapter()).build()))
                .baseUrl(BASE_URL)
                .build();
    }

    public static Retrofit getInstance() {
        if (retrofit == null) retrofit = create();
        return retrofit;
    }
}
