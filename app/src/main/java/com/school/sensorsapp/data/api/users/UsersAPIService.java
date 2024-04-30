package com.school.sensorsapp.data.api.users;

import com.school.sensorsapp.data.api.RetrofitService;

public class UsersAPIService {
    private static UsersApi usersApi;

    private static UsersApi create() {
        return RetrofitService.getInstance().create(UsersApi.class);
    }

    public static UsersApi getInstance() {
        if (usersApi == null) usersApi = create();
        return usersApi;
    }
}
