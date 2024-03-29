package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private static final String CHANGED_FEEDBACK_STATUS_TO_SCHEDULED_MESSAGE = "Feedback status set to Scheduled.";
    private static final String FEEDBACK_STATUS_SET_TO_UNSCHEDULED_MESSAGE = "Feedback status set to Unscheduled.";
    private static final String FEEDBACK_STATUS_SET_TO_NEW = "Feedback status set to New.";
    private static final String FEEDBACK_STATUS_IS_ALREADY_NEW_MESSAGE = "Current feedback status is already New.";
    private static final String FEEDBACK_STATUS_SET_TO_DONE_MESSAGE = "Feedback status set to Done.";
    private static final String FEEDBACK_STATUS_IS_ALREADY_DONE = "Current feedback status is already Done.";
    private static final String NEW_FEEDBACK_CREATED_MESSAGE = "Feedback with title %s is created";
    private int rating;
    private Status status;

    public FeedbackImpl(int id, String title, String description, int rating, String teamName, String board) {
        super(id, title, description);
        setRating(rating);
        this.status = Status.NEW;

        addEventToActivityLogHistory(String.format(NEW_FEEDBACK_CREATED_MESSAGE, title));
    }
    @Override
    public TaskType getType() {
        return TaskType.FEEDBACK;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getAssignee() {
        throw new UnsupportedOperationException("Feedback doesn't have assignee");
    }

    @Override
    public void changeAssignee(String assignee) {
        throw new UnsupportedOperationException("Feedback doesn't have assignee");
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
            addEventToActivityLogHistory(FEEDBACK_STATUS_SET_TO_UNSCHEDULED_MESSAGE);
            return (FEEDBACK_STATUS_SET_TO_UNSCHEDULED_MESSAGE);
        } else if (getStatus() == Status.UNSCHEDULED) {
            setStatus(Status.SCHEDULED);
            addEventToActivityLogHistory(CHANGED_FEEDBACK_STATUS_TO_SCHEDULED_MESSAGE);
            return (CHANGED_FEEDBACK_STATUS_TO_SCHEDULED_MESSAGE);
        } else if (getStatus() == Status.SCHEDULED) {
            setStatus(Status.DONE);
            addEventToActivityLogHistory(FEEDBACK_STATUS_SET_TO_DONE_MESSAGE);
            return (FEEDBACK_STATUS_SET_TO_DONE_MESSAGE);
        } else {
            addEventToActivityLogHistory(FEEDBACK_STATUS_IS_ALREADY_DONE);
            return (FEEDBACK_STATUS_IS_ALREADY_DONE);
        }
    }

    private void validateRating(int rating) {
        if (rating < 1 || rating > 10) {
            throw new IllegalArgumentException("Rating should be between 1 and 10.");
        }
    }

    public String revertStatus() {
        if (getStatus() == Status.DONE) {
            setStatus(Status.SCHEDULED);
            addEventToActivityLogHistory(CHANGED_FEEDBACK_STATUS_TO_SCHEDULED_MESSAGE);
            return (CHANGED_FEEDBACK_STATUS_TO_SCHEDULED_MESSAGE);
        } else if (getStatus() == Status.SCHEDULED) {
            setStatus(Status.UNSCHEDULED);
            addEventToActivityLogHistory(FEEDBACK_STATUS_SET_TO_UNSCHEDULED_MESSAGE);
            return (FEEDBACK_STATUS_SET_TO_UNSCHEDULED_MESSAGE);
        } else if (getStatus() == Status.UNSCHEDULED) {
            setStatus(Status.NEW);
            addEventToActivityLogHistory(FEEDBACK_STATUS_SET_TO_NEW);
            return (FEEDBACK_STATUS_SET_TO_NEW);
        } else {
            addEventToActivityLogHistory(FEEDBACK_STATUS_IS_ALREADY_NEW_MESSAGE);
            return (FEEDBACK_STATUS_IS_ALREADY_NEW_MESSAGE);
        }
    }

    @Override
    public void changeRating(int taskIndex, int rating) {

        setRating(rating);
        addEventToActivityLogHistory(String.format("Rating of Feedback id %s changed to %s", taskIndex, rating));
    }

    @Override
    public String getAsString() {
        return """
                Type: %s Title: %s with Id: %d""".formatted(getType().toString(), getTitle(), getId());
    }
}
