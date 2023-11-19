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

    private String stepsToReproduce;
    private Priority priority;
    private Severity severity;
    String assignee;
    private Status status;
    private int taskIndex;
    private final List<Bug> bugs;

    public BugImpl(int id, String title, String description,
                   String stepsToReproduce, Priority priority, Severity severity, String assignee, int taskIndex) {
        super(id, title, description);
        setStepsToReproduce(stepsToReproduce);
        setPriority(priority);
        setSeverity(severity);
        setAssignee(assignee);
        this.status = Status.ACTIVE;
        this.bugs = new ArrayList<>();

    }

    @Override
    public int getTaskIndex() {
        return taskIndex;
    }

    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public void changePriority(Priority priority) {
        setPriority(priority);
    }

    @Override
    public void changeSeverity(Severity severity) {
        setSeverity(severity);
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

    @Override
    public TaskType getType() {
        return TaskType.BUG;
    }

    @Override
    public String getAsString() {
        return null;
    }

//    @Override
//    public void changePriority(Priority newPriority) {
//        setPriority(newPriority);
//    }
//
//    @Override
//    public void changeSeverity(Severity newSeverity) {
//        setSeverity(newSeverity);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugImpl bug = (BugImpl) o;
        return taskIndex == bug.taskIndex && Objects.equals(stepsToReproduce, bug.stepsToReproduce) && priority == bug.priority && severity == bug.severity && Objects.equals(assignee, bug.assignee) && status == bug.status && Objects.equals(bugs, bug.bugs);
    }
}
