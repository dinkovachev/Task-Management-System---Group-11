package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

import java.util.List;

public interface Task extends Commentable, Identifiable, Printable {
    List<Task> getAllTasks();

    public Status getStatus();

    void addComment(Comment comment);

    void addTask(Task task);

    void assignTask(Members member);

    void unassignTask(Members member);

    String getTitle();

    String getDescription();

    List<Comment> getCommentList();

    List<ActivityLog> getActivityLogList();

    void revertStatus();

    void advanceStatus();

    TaskType getType();

    int getTaskIndex();

}