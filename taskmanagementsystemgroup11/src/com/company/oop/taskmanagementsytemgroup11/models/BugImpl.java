package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

public class BugImpl extends TaskImpl implements Bug {

    private String stepsToReproduce;
    private Priority priority;
    private Severity severity;
    String assignee;
    private Status status;

    // TODO Georgi Q: Assignee to be added.
    public BugImpl(int id, String title, String description,
                   String stepsToReproduce, Priority priority, Severity severity, String assignee) {
        super(id, title, description);
        setStepsToReproduce(stepsToReproduce);
        setPriority(priority);
        setSeverity(severity);
        setAssignee(assignee);
        this.status = Status.ACTIVE;
    }

    @Override
    public String getStepsToReproduce() {
        return stepsToReproduce;
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
        this.stepsToReproduce = stepsToReproduce;
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

    private void setStatus(Status status) {
        this.status = status;
    }

    public void advanceStatus() {
        // TODO Georgi Q: Should be added in a ActivityLog.
        if (getStatus() != Status.DONE) {
            setStatus(Status.DONE);
            System.out.println("Bug status set to Done.");
        } else {
            System.out.println("Current bug status is already Done.");
        }
    }

    public void revertStatus() {
        if (getStatus() != Status.ACTIVE) {
            setStatus(Status.ACTIVE);
            System.out.println("Bug status set to Active.");
        } else {
            System.out.println("Current bug status is already Active.");
        }
    }
}
