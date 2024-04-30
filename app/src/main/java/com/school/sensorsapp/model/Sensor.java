package com.school.sensorsapp.model;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Sensor {

    private final long id;
    private final LocalDateTime date;
    private final float pm25;
    private final float pm10;
    private final float airQuality;
    private final float temperature;
    private final float humidity;
    private final float pressure;
    private final float latitude;
    private final float longitude;
    private final float altitude;

    public Sensor(long id, LocalDateTime date, float pm25, float pm10, float airQuality, float temperature, float humidity, float pressure, float latitude, float longitude, float altitude) {
        this.id = id;
        this.date = date;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.airQuality = airQuality;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public float getPm25() {
        return pm25;
    }

    public float getPm10() {
        return pm10;
    }

    public float getAirQuality() {
        return airQuality;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getAltitude() {
        return altitude;
    }

    @Override
    @NonNull
    public String toString() {
        System.out.println(pm10);
        return "{\n" +
                "\tid=" + id + "\n" +
                "\tdate=" + date + "\n" +
                "\tpm25=" + pm25 + "\n" +
                "\tpm10=" + pm10 + "\n" +
                "\tairQuality=" + airQuality + "\n" +
                "\ttemperature=" + temperature + "\n" +
                "\thumidity=" + humidity + "\n" +
                "\tpressure=" + pressure + "\n" +
                "\tlatitude=" + latitude + "\n" +
                "\tlongitude=" + longitude + "\n" +
                "\taltitude=" + altitude + "\n" +
                '}';
    }
}
