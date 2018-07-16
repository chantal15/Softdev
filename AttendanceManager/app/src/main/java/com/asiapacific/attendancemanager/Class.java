package com.asiapacific.attendancemanager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chakely on 12/07/2018.
 */

public class Class implements Serializable {

    private String accessCode, className, startDate, endDate, startTime, endTime;
    private List<String> weekdays;

    public Class(String accessCode, String className, String startDate, String endDate, String startTime, String endTime, List<String> weekdays) {
        this.accessCode = accessCode;
        this.className = className;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekdays = weekdays;
    }

    public Class(){}

    public String getAccessCode() {
        return accessCode;
    }

    public String getClassName() {
        return className;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public List<String> getWeekdays() {
        return weekdays;
    }

    
    }
}
