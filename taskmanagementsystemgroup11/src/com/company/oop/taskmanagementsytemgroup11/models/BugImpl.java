package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BugImpl extends TaskImpl implements Bug {

    private static final String BUG_STATUS_SET_TO_ACTIVE_MESSAGE = "Bug status set to Active.";
    private static final String BUG_STATUS_ALREADY_ACTIVE_MESSAGE = "Current bug status is already Active.";
    private static final String NEW_BUG_CREATED_MESSAGE = "New bug with title %s created";
    private static final String BUG_STATUS_SET_TO_DONE_MESSAGE = "Bug status set to Done.";
    private static final String CURRENT_BUG_STATUS_IS_ALREADY_DONE_MESSAGE = "Current bug status is already Done.";
    private static final String PRIORITY_OF_BUG_ID_CHANGED_TO_MESSAGE = "Priority of Bug id %s changed to %s";
    private static final String SEVERITY_OF_BUG_ID_CHANGED_TO_MESSAGE = "Severity of Bug id %s changed to %s";
    private Priority priority;
    private Severity severity;
    private String assignee;
    private Status status;

    public BugImpl(int id, String title, String description, String stepsToReproduce, Priority priority,
                   Severity severity, String assignee, String teamName, String board) {
        super(id, title, description);
        setStepsToReproduce(stepsToReproduce);
        setPriority(priority);
        setSeverity(severity);
        setAssignee(assignee);
        this.status = Status.ACTIVE;

        addEventToActivityLogHistory(String.format(NEW_BUG_CREATED_MESSAGE, title));
    }

    @Override
    public void changePriority(int taskIndex, Priority priority) {
        setPriority(priority);
        addEventToActivityLogHistory(String.format(PRIORITY_OF_BUG_ID_CHANGED_TO_MESSAGE, taskIndex, priority));
    }

    @Override
    public void changeSeverity(int taskIndex, Severity severity) {
        setSeverity(severity);
        addEventToActivityLogHistory(String.format(SEVERITY_OF_BUG_ID_CHANGED_TO_MESSAGE, taskIndex, severity));
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    private void setStepsToReproduce(String stepsToReproduce) {
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    private void setSeverity(Severity severity) {
        this.severity = severity;
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public void changeAssignee(String assignee){
         setAssignee(assignee);
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public String advanceStatus() {
        if (getStatus() != Status.DONE) {
            setStatus(Status.DONE);
            addEventToActivityLogHistory(BUG_STATUS_SET_TO_DONE_MESSAGE);
            return (BUG_STATUS_SET_TO_DONE_MESSAGE);
        } else {
            addEventToActivityLogHistory(CURRENT_BUG_STATUS_IS_ALREADY_DONE_MESSAGE);
            return (CURRENT_BUG_STATUS_IS_ALREADY_DONE_MESSAGE);
        }
    }

    public String revertStatus() {
        if (getStatus() != Status.ACTIVE) {
            setStatus(Status.ACTIVE);
            addEventToActivityLogHistory(BUG_STATUS_SET_TO_ACTIVE_MESSAGE);
            return (BUG_STATUS_SET_TO_ACTIVE_MESSAGE);
        } else {
            addEventToActivityLogHistory(BUG_STATUS_ALREADY_ACTIVE_MESSAGE);
            return (BUG_STATUS_ALREADY_ACTIVE_MESSAGE);
        }
    }

    @Override
    public TaskType getType() {
        return TaskType.BUG;
    }

    @Override
    public String getAsString() {
        return """
                Type: %s Title: %s with Id: %d""".formatted(getType(), getTitle(), getId());
    }
}