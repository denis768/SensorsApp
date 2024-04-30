package com.school.sensorsapp.data.api.sensors;

import com.school.sensorsapp.model.Sensor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SensorsApi {

    @GET("sensor")
    Call<List<Sensor>> getSensors();

    @GET("last-data")
    Call<Sensor> getLast();

    @GET("sensor/{id}")
    Call<Sensor> getSensor(
            @Path("id")
            long id
    );
}
