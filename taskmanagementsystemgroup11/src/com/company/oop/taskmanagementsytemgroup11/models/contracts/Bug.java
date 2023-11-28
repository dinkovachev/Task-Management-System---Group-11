package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;

import java.util.List;

public interface Bug extends Task{
    Priority getPriority();

    Severity getSeverity();

    void changePriority(int taskIndex, Priority priority);

    void changeSeverity(int taskIndex, Severity severity);
}