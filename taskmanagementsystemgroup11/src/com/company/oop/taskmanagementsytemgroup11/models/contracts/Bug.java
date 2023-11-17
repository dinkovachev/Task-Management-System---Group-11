package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

import java.util.List;

public interface Bug extends Task {
    public String getStepsToReproduce();

    public Priority getPriority();

    public Severity getSeverity();

    public String getAssignee();

    public List<Bug> getBugs();

    public int getTaskIndex();

//    void changePriority(Priority priority);
//
//    void changeSeverity(Severity severity);
}
