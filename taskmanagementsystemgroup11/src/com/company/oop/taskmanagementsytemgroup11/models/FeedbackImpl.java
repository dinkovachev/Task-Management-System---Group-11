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
    private static final String CHANGED_FEEDBACK_STATUS_TO_SCHEDULED_MESSAGE = "Feedback status set to Scheduled.";
    private static final String FEEDBACK_STATUS_SET_TO_UNSCHEDULED_MESSAGE = "Feedback status set to Unscheduled.";
    private static final String FEEDBACK_STATUS_SET_TO_NEW = "Feedback status set to New.";
    private static final String FEEDBACK_STATUS_IS_ALREADY_NEW_MESSAGE = "Current feedback status is already New.";
    private static final String FEEDBACK_STATUS_SET_TO_DONE_MESSAGE = "Feedback status set to Done.";
    private static final String FEEDBACK_STATUS_IS_ALREADY_DONE = "Current feedback status is already Done.";
    private static final String NEW_FEEDBACK_CREATED_MESSAGE = "Feedback with title %s is created";
    private int rating;
    private int taskIndex;
    private Status status;
    private final List<Feedback> feedbacks;
    //private final List<ActivityLog> feedbackActivityLog = new ArrayList<>();

    public FeedbackImpl(
            int id, String title, String description, int rating, int taskIndex, String teamname, String board) {
        super(id, title, description);
        setRating(rating);
        this.status = Status.NEW;
        this.feedbacks = new ArrayList<>();
        addEventToActivityLogHistory(String.format(NEW_FEEDBACK_CREATED_MESSAGE, title));
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
        if (rating <= 1 || rating >= 10) {
            throw new IllegalArgumentException("Rating should be between 1 and 10.");
        }
    }

    // TODO Georgi Q: set to private
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

//    public void addEventToActivityLogHistory(String event) {
//        feedbackActivityLog.add(new ActivityLogImpl(event));
//    }

//    public String displayActivityLogHistory() {
//        StringBuilder result = new StringBuilder();
//        for (ActivityLog activityLog : feedbackActivityLog) {
//            result.append(activityLog.displayInfo()).append(System.lineSeparator());
//        }
//        return result.toString();
//    }

    @Override
    public void changeRating(int rating) {
        setRating(rating);
    }

    @Override
    public String getAsString() {
        return null;
    }
}
