package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;

public class ActivityLogImpl implements ActivityLog {
    public static final String EMPTY_DESCRIPTION_ERROR_MESSAGE = "Description cannot be empty";
    private String activityLog;

    public ActivityLogImpl(String activityLog) {
        setActivityLog(activityLog);
    }

    private void setActivityLog(String activityLog) {
        if (activityLog.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
        } else {
            this.activityLog = activityLog;
        }
    }

    public String displayInfo() {
        return String.format("Activity History - %s", activityLog);
    }


    //ToDo - Dinko
    // Finish the remaining methods from the interface


}
