package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

import java.util.List;

public interface Task extends Commentable, Identifiable, Printable {
    List<Task> getAllTasks();

    void addComment(Comment comment);

    String getTitle();

    String getDescription();

    List<Comment> getCommentList();

    List<ActivityLog> getActivityLogList();

    void revertStatus();

    void advanceStatus();

    TaskType getType();
}