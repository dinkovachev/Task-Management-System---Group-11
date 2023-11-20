package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import java.util.List;

public interface Members extends Printable {

    String getUsername();

    void addComment(Comment commentToAdd, Task taskToAddComment);

    void addToTeam(Team teamToAddMember);

    void assignTask(Members memberToAssignTask, Task taskToBeAssigned);

    void unassignTask(Members memberToUnassignTask, Task taskToBeUnassigned);

    public List<Members> getAllTeamMembers();

    void addEventToActivityLogHistory(String event);

    String displayActivityLogHistory();

    int getPersonId();

    String getFirstName();

    String getLastName();

    boolean equals(Object o);

    int hashCode();
}