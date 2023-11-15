package com.company.oop.taskmanagementsytemgroup11.models.contracts;

public interface Members extends Printable {

    String getUsername();

    void addComment(Comment commentToAdd, Task taskToAddComment);

    void addMemberToTeam(Members memberToAdd, Team teamToAddMember);

    void assignTask(Members memberToAssignTask, Task taskToBeAssigned);

    void unassignTask(Members memberToUnassignTask, Task taskToBeUnassigned);


    int getPersonId();

    String getFirstName();

    String getLastName();

}
