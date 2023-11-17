package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MembersImpl implements Members {
    private static final int MINIMUM_SYMBOLS = 5;
    private static final int MAXIMUM_SYMBOLS = 15;
    public static final String MEMBER_NAME_ERR_MSG = String.format(
            "The Member name's length cannot be less than %d or more than %d symbols long.",
            MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS);
    private String username;
    private String firstName;
    private String lastName;
    private int personId;
    private List<String> activityHistory = new ArrayList<>();
    private List<Members> members;

    public MembersImpl(int personId, String firstName, String lastName) {
        setPersonId(personId);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(generateUsername(personId, firstName, lastName));
  //      this.activityHistory = new ArrayList<>();
    }

    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void addComment(Comment commentToAdd, Task taskToAddComment) {
        taskToAddComment.addComment(commentToAdd);
        activityHistory.add(String.format("New comment %s was added to the task %s", commentToAdd.getContent(),
                taskToAddComment.getTitle()));
    }

    @Override
    public void addToTeam(Team teamToAddMember) {
        teamToAddMember.addMember(this);
        activityHistory.add(String.format("Member %s was added to team %s", this.getUsername(),
                teamToAddMember.getName()));
    }

    @Override
    public void assignTask(Members memberToAssignTask, Task taskToBeAssigned) {
        taskToBeAssigned.assignTask(memberToAssignTask);
        activityHistory.add(String.format("New task %s was assigned to team member %s", taskToBeAssigned.getTitle(),
                memberToAssignTask.getUsername()));

    }

    @Override
    public void unassignTask(Members memberToUnassignTask, Task taskToBeUnassigned) {
        taskToBeUnassigned.unassignTask(memberToUnassignTask);
        activityHistory.add(String.format("New task %s was assigned to team member %s", taskToBeUnassigned.getTitle(),
                memberToUnassignTask.getUsername()));
    }

    @Override
    public int getPersonId() {
        return personId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

      @Override
    public List<Members> getAllTeamMembers() {
        return new ArrayList<>(members);
    }

    private void setUsername(String username) {
        this.username = username;
        activityHistory.add(String.format("New member with username %s was created", username));
    }

    private void setFirstName(String firstName) {
        ValidationHelpers.validateIntRange(firstName.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, MEMBER_NAME_ERR_MSG);
        this.firstName = firstName;
 //       activityHistory.add(String.format("New member with first name %s was created", firstName));
    }

    private void setLastName(String lastName) {
        ValidationHelpers.validateIntRange(lastName.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, MEMBER_NAME_ERR_MSG);
        this.lastName = lastName;
  //      activityHistory.add(String.format("New member with last name %s was created", lastName));
    }

    private void setPersonId(int personId) {
        this.personId = personId;
 //       activityHistory.add(String.format("New member with id %d was created", personId));
    }

    private String generateUsername(int personId, String firstName, String lastName) {
        return firstName + "_" + lastName + personId;
    }

    @Override
    public String getAsString() {
        return """
                Name: %s %s
                Username: %s
                """.formatted(firstName, lastName, username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembersImpl members = (MembersImpl) o;
        return personId == members.personId
                && Objects.equals(username, members.username)
                && Objects.equals(firstName, members.firstName)
                && Objects.equals(lastName, members.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, personId);
    }
}
