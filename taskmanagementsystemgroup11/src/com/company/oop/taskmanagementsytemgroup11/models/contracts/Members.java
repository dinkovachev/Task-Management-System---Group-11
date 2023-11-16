package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import java.util.List;

public interface Members extends Printable {

    String getUsername();

    void addComment(Comment commentToAdd, Task taskToAddComment);

    void addMemberToTeam(Members memberToAdd, Team teamToAddMember);

    void assignTask(Members memberToAssignTask, Task taskToBeAssigned);

    void unassignTask(Members memberToUnassignTask, Task taskToBeUnassigned);

    public List<Members> getAllTeamMembers();

    List<String> getActivityHistory();

    int getPersonId();

    String getFirstName();

    String getLastName();

}
