package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.MembersImpl;

import java.util.List;
import java.util.Objects;

public interface Members extends Printable {

    String getUsername();

    void addComment(Comment commentToAdd, Task taskToAddComment);

    void addToTeam(Team teamToAddMember);

    void assignTask(Members memberToAssignTask, Task taskToBeAssigned);

    void unassignTask(Members memberToUnassignTask, Task taskToBeUnassigned);

    public List<Members> getAllTeamMembers();

    List<String> getActivityHistory();

    int getPersonId();

    String getFirstName();

    String getLastName();

    boolean equals(Object o);

    int hashCode();

}
