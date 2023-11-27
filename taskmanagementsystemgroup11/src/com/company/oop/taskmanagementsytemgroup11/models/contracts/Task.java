package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

import java.util.List;

public interface Task extends Commentable, Identifiable, Printable {
    Status getStatus();

    void addEventToActivityLogHistory(String event);

    void addComment(Comment comment);

    String getAssignee();

    void changeAssignee(String assignee);

    String getTitle();

    String getDescription();

    String displayActivityLogHistory();

    String revertStatus();

    String advanceStatus();

    TaskType getType();

    String getAsString();
}