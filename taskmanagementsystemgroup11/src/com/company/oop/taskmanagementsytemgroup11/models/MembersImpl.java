package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MembersImpl implements Members {
    private static final int MINIMUM_SYMBOLS = 5;
    private static final int MAXIMUM_SYMBOLS = 15;
    private static final String MEMBER_NAME_ERR_MSG = String.format(
            "The Member name's length cannot be less than %d or more than %d symbols long.",
            MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS);
    private static final String NEW_MEMBER_CREATED_MESSAGE = "New member created %s%n";
    private static final String NEW_TASK_UNASSIGNED_TO_TEAM_MEMBER_MESSAGE = "New task %s was unassigned to team member %s";
    private static final String NEW_TASK_ASSIGNED_TO_TEAM_MEMBER_MESSAGE = "New task %s was assigned to team member %s";
    private static final String MEMBER_ADDED_TO_TEAM_MESSAGE = "Member %s was added to team %s";
    private static final String COMMENT_ADDED_TO_TASK_MESSAGE = "Comment %s added to task %s";
    private String username;
    private String firstName;
    private String lastName;
    private int personId;
    private List<ActivityLog> activityHistory = new ArrayList<>();
    private List<Members> members;

    public MembersImpl(int personId, String firstName, String lastName) {
        setPersonId(personId);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(generateUsername(personId, firstName, lastName));
        addEventToActivityLogHistory(String.format(NEW_MEMBER_CREATED_MESSAGE, displayInfoForNewCreatedMember()));

    }

    @Override
    public String getUsername() {
        return username;
    }
    private void setUsername(String username) {
        this.username = username;
    }

    private void setFirstName(String firstName) {
        ValidationHelpers.validateIntRange(firstName.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, MEMBER_NAME_ERR_MSG);
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        ValidationHelpers.validateIntRange(lastName.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, MEMBER_NAME_ERR_MSG);
        this.lastName = lastName;
    }

    private void setPersonId(int personId) {
        this.personId = personId;
    }

    private String generateUsername(int personId, String firstName, String lastName) {
        return firstName + "_" + lastName + personId;
    }

    @Override
    public void addComment(Comment commentToAdd, Task taskToAddComment) {
        taskToAddComment.addComment(commentToAdd);
        addEventToActivityLogHistory(String.format(COMMENT_ADDED_TO_TASK_MESSAGE, taskToAddComment.getTitle(),
                commentToAdd.getContent()));
    }

    @Override
    public void addToTeam(Team teamToAddMember) {
        teamToAddMember.addMember(this);
        addEventToActivityLogHistory(String.format(MEMBER_ADDED_TO_TEAM_MESSAGE, this.getUsername(),
                teamToAddMember.getName()));
    }

    @Override
    public void assignTask(Members memberToAssignTask, Task taskToBeAssigned) {
        taskToBeAssigned.assignTask(memberToAssignTask);
        addEventToActivityLogHistory(String.format(NEW_TASK_ASSIGNED_TO_TEAM_MEMBER_MESSAGE, taskToBeAssigned.getTitle(),
                memberToAssignTask.getUsername()));

    }

    @Override
    public void unassignTask(Members memberToUnassignTask, Task taskToBeUnassigned) {
        taskToBeUnassigned.unassignTask(memberToUnassignTask);
        addEventToActivityLogHistory(String.format(NEW_TASK_UNASSIGNED_TO_TEAM_MEMBER_MESSAGE, taskToBeUnassigned.getTitle(),
                memberToUnassignTask.getUsername()));
    }

    public String displayInfoForNewCreatedMember() {
        return String.format("%nPersonId: %d%n" +
                "FirstName: %s%n" +
                "LastName: %s%n" +
                "Username: %s", personId, firstName, lastName, username);
    }

    public void addEventToActivityLogHistory(String event) {
        activityHistory.add(new ActivityLogImpl(event));
    }


    public String displayActivityLogHistory() {
        StringBuilder result = new StringBuilder();
        for (ActivityLog activityLog : activityHistory) {
            result.append(activityLog.displayInfo()).append(System.lineSeparator());
        }
        return result.toString();
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
        MembersImpl members1 = (MembersImpl) o;
        return personId == members1.personId && username.equals(members1.username) &&
                firstName.equals(members1.firstName) && lastName.equals(members1.lastName) &&
                activityHistory.equals(members1.activityHistory) && members.equals(members1.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, personId, activityHistory, members);
    }
}
