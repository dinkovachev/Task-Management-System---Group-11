package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

public interface Bug extends Task {
    public String getStepsToReproduce();

    public Priority getPriority();

    public Severity getSeverity();

    public Status getStatus();

    public String getAssignee();
}
