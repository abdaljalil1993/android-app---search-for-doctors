package com.fadidebow.finddoctors;

import android.app.Application;

public class global extends Application{
    String username;
    int id;

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }
}
