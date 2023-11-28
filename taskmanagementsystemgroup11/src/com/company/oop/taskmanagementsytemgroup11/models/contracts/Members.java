package com.company.oop.taskmanagementsytemgroup11.models.contracts;

public interface Members extends Printable {

    String getUsername();

    void addComment(Comment commentToAdd, Task taskToAddComment);

    void addToTeam(Team teamToAddMember);

    void addEventToActivityLogHistory(String event);

    String displayActivityLogHistory();

    int getPersonId();

    String getFirstName();

    String getLastName();
}