package com.asiapacific.attendancemanager;

/**
 * Created by Chakely 
 */

public class Professor {
    String name;
    String email;

    public Professor(String name, String email) {

        this.name = name;
        this.email = email;
    }

    public Professor() {}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
