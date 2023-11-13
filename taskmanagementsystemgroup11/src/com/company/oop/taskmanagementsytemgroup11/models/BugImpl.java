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
    private Members members; // TODO Georgi Q: Is it a name, name from a list or username?
    private Status status;

    // TODO Georgi Q: Assignee to be added.
    public BugImpl(int id, String title, String description,
                   String stepsToReproduce, Priority priority, Severity severity, Members members) {
        super(id, title, description);
        setStepsToReproduce(stepsToReproduce);
        setPriority(priority);
        setSeverity(severity);
        setMembers(members);
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
    public Members getMembers() {
        return members;
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

    private void setMembers(Members members) {
        this.members = members;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public void advanceStatus() {
        // TODO Georgi Q: Are we going to record this?
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
