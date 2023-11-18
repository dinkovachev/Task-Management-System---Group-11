package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

import java.util.List;

public interface Bug extends Task {
    String getStepsToReproduce();

    Priority getPriority();

    Severity getSeverity();

    String getAssignee();

    List<Bug> getBugs();

    void changePriority(Priority priority);

    void changeSeverity(Severity severity);
}
