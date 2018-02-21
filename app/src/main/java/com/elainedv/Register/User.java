package com.elainedv.Register;

import io.realm.RealmObject;

/**
 * Created by Elaine on 18/2/13.
 */

public class User extends RealmObject {
    private String user,password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }
}
