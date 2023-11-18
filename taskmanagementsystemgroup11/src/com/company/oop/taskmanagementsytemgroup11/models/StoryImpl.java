package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class StoryImpl extends TaskImpl implements Story {
    private Priority priority;
    private Size size;
    private Status status;
    private String assignee;
    private int taskIndex;
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();

    public StoryImpl(int id, String title, String description, Priority priority, Size size, String assignee,
                     int taskIndex) {
        super(id, title, description);
        setPriority(priority);
        setSize(size);
        setAssignee(assignee);
        this.status = Status.NOT_DONE;
//        this.stories = new ArrayList<>();
//        this.feedbacks = new ArrayList<>();
    }

    @Override
    public int getTaskIndex() {
        return taskIndex;
    }

    @Override
    public TaskType getType() {
        return TaskType.STORY;
    }

    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getMembers() {
        return assignee;
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    private void setSize(Size size) {
        this.size = size;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String advanceStatus() {
        if (getStatus() == Status.NOT_DONE) {
            setStatus(Status.IN_PROGRESS);
            return "Story status set to InProgress.";
        } else if (getStatus() == Status.IN_PROGRESS) {
            setStatus(Status.DONE);
            return "Story status set to Done.";
        } else {
            return "Current story status is already Done.";
        }
    }
    //ToDo
    // Finish the override methods from the interfaces

    //ToDo
    // Finish the override methods from the interfaces
    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public void addTask(Task task) {

    }

    @Override
    public void assignTask(Members member) {

    }

    @Override
    public void unassignTask(Members member) {

    }

    public String revertStatus() {
        if (getStatus() == Status.DONE) {
            setStatus(Status.IN_PROGRESS);
            return "Story status set to InProgress.";
        } else if (getStatus() == Status.IN_PROGRESS) {
            setStatus(Status.NOT_DONE);
            return "Story status set to Not Done.";
        } else {
            return "Current story status is already Not Done.";
        }
    }

    @Override
    public void changePriority(Priority priority) {
        setPriority(priority);
    }

    @Override
    public void changeSize(Size size) {
        setSize(size);
    }

    @Override
    public String getAsString() {
        return null;
    }
}