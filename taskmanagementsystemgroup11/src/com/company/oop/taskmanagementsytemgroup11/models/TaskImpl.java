package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.ActivityLog;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Printable;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.enums.*;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public abstract class TaskImpl implements Task {
    public static final int TITLE_LEN_MIN = 10;
    public static final int TITLE_LEN_MAX = 100;
    public static final String TITLE_LEN_ERR = format(
            "Title must be between %s and %s characters long!",
            TITLE_LEN_MIN,
            TITLE_LEN_MAX);
    public static final int DESCRIPTION_LEN_MIN = 10;
    public static final int DESCRIPTION_LEN_MAX = 500;
    public static final String DESCRIPTION_LEN_ERR = format(
            "Description must be between %s and %s characters long!",
            DESCRIPTION_LEN_MIN,
            DESCRIPTION_LEN_MAX);

    private int id;
    private String title;
    private String description;
    private List<Comment> commentList = new ArrayList<>();
    private List<ActivityLog> activityLogList = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private List<String> assignedMembersToTasks = new ArrayList<>();

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public List<String> getAssignedMembersToTasks() {
        return new ArrayList<>(assignedMembersToTasks);
    }

    private Status status;

    public TaskImpl(int id, String title, String description) {
        setId(id);
        setTitle(title);
        setDescription(description);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }


    public abstract Status getStatus();

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Comment> getCommentList() {
        return new ArrayList<>(commentList);
    }

    @Override
    public List<ActivityLog> getActivityLogList() {
        return new ArrayList<>(activityLogList);
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setTitle(String title) {
        ValidationHelpers.validateDecimalRange(
                title.length(),
                TITLE_LEN_MIN,
                TITLE_LEN_MAX,
                TITLE_LEN_ERR);
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateDecimalRange(
                description.length(),
                DESCRIPTION_LEN_MIN,
                DESCRIPTION_LEN_MAX,
                DESCRIPTION_LEN_ERR);
        this.description = description;
    }

    @Override
    public void addComment(Comment comment) {
        commentList.add(comment);
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void assignTask(Members member) {
    }

    @Override
    public void unassignTask(Members member) {
    }

    @Override
    public abstract void revertStatus();

    @Override
    public abstract void advanceStatus();

    @Override
    public abstract TaskType getType();

    @Override
    public abstract int getTaskIndex();
}