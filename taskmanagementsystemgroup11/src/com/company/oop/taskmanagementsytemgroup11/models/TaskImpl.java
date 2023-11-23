package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.ActivityLog;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

public abstract class TaskImpl implements Task {
    private static final int TITLE_LEN_MIN = 10;
    private static final int TITLE_LEN_MAX = 100;
    private static final String TITLE_LEN_ERR = format(
            "Title must be between %s and %s characters long!",
            TITLE_LEN_MIN,
            TITLE_LEN_MAX);
    private static final int DESCRIPTION_LEN_MIN = 10;
    private static final int DESCRIPTION_LEN_MAX = 500;
    private static final String DESCRIPTION_LEN_ERR = format(
            "Description must be between %s and %s characters long!",
            DESCRIPTION_LEN_MIN,
            DESCRIPTION_LEN_MAX);
    private static final String NEW_TASK_CREATED_MESSAGE = "Task created: %s%n";
    private static final String COMMENT_S_ADDED_TO_THE_TASK_MESSAGE = "Comment %s added to the task %s";
    private static final String MEMBER_ASSIGNED_TO_TASK_MESSAGE = "Member %s assigned to task %s";
    private static final String MEMBER_UNASSIGNED_FROM_TASK_MESSAGE = "Member %s unassigned from task %s";

    private int id;
    private String title;
    private String description;
    private List<Comment> commentList = new ArrayList<>();
    private List<ActivityLog> activityHistory = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private List<String> assignedMembersToTasks = new ArrayList<>();

    public TaskImpl(int id, String title, String description) {
        setId(id);
        setTitle(title);
        setDescription(description);
        addEventToActivityLogHistory(String.format(NEW_TASK_CREATED_MESSAGE, displayInfoForNewCreatedTask()));
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

//    @Override
//    public List<Task> getAllTasks() {
//        return new ArrayList<>(tasks);
//    }

    @Override
    public List<Comment> getCommentList() {

        return new ArrayList<>(commentList);
    }


    private void setId(int id) {

        this.id = id;
    }

    private void setTitle(String title) {
        ValidationHelpers.validateIntRange(
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
        addEventToActivityLogHistory(String.format(COMMENT_S_ADDED_TO_THE_TASK_MESSAGE, comment.getContent(),
                this.getTitle()));
    }

//    @Override
//    public void addTask(Task task) {
//        tasks.add(task);
//        addEventToActivityLogHistory(String.format("Task %s added", task));
//    }

    @Override
    public void assignTask(Members member) {
        assignedMembersToTasks.add(String.valueOf(member));
        addEventToActivityLogHistory(String.format(MEMBER_ASSIGNED_TO_TASK_MESSAGE, member.getUsername(),
                this.getTitle()));
    }

    @Override
    public void unassignTask(Members member) {

        assignedMembersToTasks.add(String.valueOf(member));
        addEventToActivityLogHistory(String.format(MEMBER_UNASSIGNED_FROM_TASK_MESSAGE, member.getUsername(),
                this.getTitle()));
    }

    public void addEventToActivityLogHistory(String event) {

        activityHistory.add(new ActivityLogImpl(event));
    }

    public String displayActivityLogHistory() {
        StringBuilder result = new StringBuilder();
        for (ActivityLog activityLog : activityHistory) {
            result.append(activityLog.displayInfo()).append(System.lineSeparator());
        }
        return result.toString();
    }

    public String displayInfoForNewCreatedTask() {
        return String.format("%nTitle: %s%n" +
                        "Description: %s"
                , title, description);
    }

    @Override
    public abstract String revertStatus();

    @Override
    public abstract String advanceStatus();

    @Override
    public abstract TaskType getType();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TaskImpl task = (TaskImpl) o;
//        return id == task.id && title.equals(task.title) && description.equals(task.description) &&
//                commentList.equals(task.commentList) && activityHistory.equals(task.activityHistory) &&
//                tasks.equals(task.tasks) && assignedMembersToTasks.equals(task.assignedMembersToTasks);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, title, description, commentList, activityHistory, tasks, assignedMembersToTasks);
//    }

    @Override
    public abstract int getTaskIndex();
}