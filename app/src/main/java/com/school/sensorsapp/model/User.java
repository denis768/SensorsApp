package com.school.sensorsapp.model;

import androidx.annotation.NonNull;

public class User {

    private long id;
    private String name;
    private String email;
    private String phone;
    private String photoUrl;

    public User(long id, String name, String email, String phone, String photoUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.photoUrl = photoUrl;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    @NonNull
    public String toString() {
        return name;
    }
}
