package com.company.oop.taskmanagementsytemgroup11.models.contracts;

public interface Board extends Printable {

    void addTask(Task task);

    String getName();

    String displayActivityLogHistory();
}