package com.school.sensorsapp.data.api.sensors;


import com.school.sensorsapp.data.api.RetrofitService;

public class SensorsAPIService {
    private static SensorsApi sensorsApi;

    private static SensorsApi create() {
        return RetrofitService.getInstance().create(SensorsApi.class);
    }

    public static SensorsApi getInstance() {
        if (sensorsApi == null) sensorsApi = create();
        return sensorsApi;
    }
}
