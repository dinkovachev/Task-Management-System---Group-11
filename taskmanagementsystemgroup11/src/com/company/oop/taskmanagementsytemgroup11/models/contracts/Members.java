package com.company.oop.taskmanagementsytemgroup11.models.contracts;

public interface Members extends Printable{

    String getUsername();
    void addComment(Comment commentToAdd, Task taskToAddComment);

    int getPersonId();




}
