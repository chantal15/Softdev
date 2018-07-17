package com.asiapacific.attendancemanager;

/**
 * Created by Chakely on 13/07/2018.
 */

public class Student {

    String displayName,  email, mobilePhone, notes;

    public Student(String displayName, String email, String mobilePhone, String notes) {
        this.displayName = displayName;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.notes = notes;
    }

    public Student() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
