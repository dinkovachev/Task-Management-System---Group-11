package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import java.util.List;

public interface Task {

    List<Task> getAllTasks();

    void addComment(Comment comment);

}
