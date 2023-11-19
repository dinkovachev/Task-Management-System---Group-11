package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private int rating;
    private int taskIndex;
    private Status status;
    private final List<Feedback> feedbacks;

    public FeedbackImpl(int id, String title, String description, int rating, int taskIndex, String board) {
        super(id, title, description);
        setRating(rating);
        this.status = Status.NEW;
        this.feedbacks = new ArrayList<>();
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public TaskType getType() {
        return TaskType.FEEDBACK;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public int getTaskIndex() {
        return taskIndex;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    private void setRating(int rating) {
        validateRating(rating);
        this.rating = rating;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public String advanceStatus() {
        if (getStatus() == Status.NEW) {
            setStatus(Status.UNSCHEDULED);
            return "Feedback status set to Unscheduled.";
        } else if (getStatus() == Status.UNSCHEDULED) {
            setStatus(Status.SCHEDULED);
            return "Feedback status set to Scheduled.";
        } else if (getStatus() == Status.SCHEDULED) {
            setStatus(Status.DONE);
            return "Feedback status set to Done.";
        } else {
            return "Current feedback status is already Done.";
        }
    }

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

    private void validateRating(int rating) {
        if (rating <= 1 || rating >= 10) {
            throw new IllegalArgumentException("Rating should be between 1 and 10.");
        }
    }

    // TODO Georgi Q: set to private
    public String revertStatus() {
        if (getStatus() == Status.DONE) {
            setStatus(Status.SCHEDULED);
            return "Feedback  status set to Scheduled.";
        } else if (getStatus() == Status.SCHEDULED) {
            setStatus(Status.UNSCHEDULED);
            return "Feedback status set to Unscheduled.";
        } else if (getStatus() == Status.UNSCHEDULED) {
            setStatus(Status.NEW);
            return "Feedback status set to New.";
        } else {
            return "Current feedback status is already New.";
        }
    }

    @Override
    public void changeRating(int rating) {
        setRating(rating);
    }

    @Override
    public String getAsString() {
        return null;
    }
}
