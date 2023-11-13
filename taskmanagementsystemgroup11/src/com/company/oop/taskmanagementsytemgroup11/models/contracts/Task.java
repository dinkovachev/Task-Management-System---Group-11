package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import java.util.List;

public interface Task extends Commentable, Identifiable {

    String getTitle();

    String getDescription();

    List<Comment> getCommentList();

    List<ActivityLog> getActivityLogList();

    void revertStatus();

    void advanceStatus();
}