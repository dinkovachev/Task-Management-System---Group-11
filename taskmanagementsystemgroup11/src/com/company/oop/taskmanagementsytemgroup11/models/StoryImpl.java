package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class StoryImpl extends TaskImpl implements Story {
    private static final String STORY_STATUS_SET_TO_IN_PROGRESS_MESSAGE = "Story status set to InProgress.";
    private static final String STORY_STATUS_SET_TO_NOT_DONE_MESSAGE = "Story status set to Not Done.";
    private static final String CURRENT_STORY_STATUS_IS_ALREADY_NOT_DONE_MESSAGE
            = "Current story status is already Not Done.";
    private static final String STORY_STATUS_SET_TO_DONE_MESSAGE = "Story status set to Done.";
    private static final String CURRENT_STORY_STATUS_IS_ALREADY_DONE_MESSAGE = "Current story status is already Done.";
    private static final String NEW_STORY_WAS_CREATED_MESSAGE = "New story with title %s was created";
    public static final String PRIORITY_FOR_STORY_ID_CHANGED_TO_MESSAGE = "Priority for Story id %s changed to %s";
    public static final String SIZE_FOR_STORY_ID_CHANGED_TO_MESSAGE = "Size for Story id %s changed to %s";
    private Priority priority;
    private Size size;
    private Status status;
    private String assignee;
    private int taskIndex;
    //private final List<Story> stories = new ArrayList<>();
    //private final List<Feedback> feedbacks = new ArrayList<>();

    public StoryImpl(int id, String title, String description, Priority priority, Size size, String assignee,
                     String teamName, String board) {
        super(id, title, description);
        setPriority(priority);
        setSize(size);
        setAssignee(assignee);
        this.status = Status.NOT_DONE;
        addEventToActivityLogHistory(String.format(NEW_STORY_WAS_CREATED_MESSAGE, title));
    }

//    @Override
//    public int getTaskIndex() {
//        return taskIndex;
//    }

    @Override
    public TaskType getType() {
        return TaskType.STORY;
    }

    @Override
    public void changeAssignee(String assignee) {
         setAssignee(assignee);
    }

//    public List<Story> getStories() {
//        return new ArrayList<>(stories);
//    }

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

    @Override
    public String getAssignee() {
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
            addEventToActivityLogHistory(CURRENT_STORY_STATUS_IS_ALREADY_NOT_DONE_MESSAGE);
            return (STORY_STATUS_SET_TO_IN_PROGRESS_MESSAGE);
        } else if (getStatus() == Status.IN_PROGRESS) {
            setStatus(Status.DONE);
            addEventToActivityLogHistory(STORY_STATUS_SET_TO_DONE_MESSAGE);
            return (STORY_STATUS_SET_TO_DONE_MESSAGE);
        } else {
            addEventToActivityLogHistory(CURRENT_STORY_STATUS_IS_ALREADY_DONE_MESSAGE);
            return (CURRENT_STORY_STATUS_IS_ALREADY_DONE_MESSAGE);
        }
    }

    public String revertStatus() {
        if (getStatus() == Status.DONE) {
            setStatus(Status.IN_PROGRESS);
            addEventToActivityLogHistory(STORY_STATUS_SET_TO_IN_PROGRESS_MESSAGE);
            return (STORY_STATUS_SET_TO_IN_PROGRESS_MESSAGE);
        } else if (getStatus() == Status.IN_PROGRESS) {
            setStatus(Status.NOT_DONE);
            addEventToActivityLogHistory(STORY_STATUS_SET_TO_NOT_DONE_MESSAGE);
            return (STORY_STATUS_SET_TO_NOT_DONE_MESSAGE);
        } else {
            addEventToActivityLogHistory(CURRENT_STORY_STATUS_IS_ALREADY_NOT_DONE_MESSAGE);
            return (CURRENT_STORY_STATUS_IS_ALREADY_NOT_DONE_MESSAGE);
        }
    }

    @Override
    public void changePriority(int taskIndex, Priority priority) {
        setPriority(priority);
        addEventToActivityLogHistory(String.format(PRIORITY_FOR_STORY_ID_CHANGED_TO_MESSAGE, taskIndex, priority));
    }

    @Override
    public void changeSize(int taskIndex, Size size) {
        setSize(size);
        addEventToActivityLogHistory(String.format(SIZE_FOR_STORY_ID_CHANGED_TO_MESSAGE, taskIndex, size));

    }

    @Override
    public String getAsString() {
        return """
                Type: %s Title: %s with Id: %d""".formatted(getType(), getTitle(), getId());
    }
}