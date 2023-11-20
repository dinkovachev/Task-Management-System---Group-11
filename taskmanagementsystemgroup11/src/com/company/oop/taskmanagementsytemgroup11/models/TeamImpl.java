package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.ActivityLog;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {

    private static final int MINIMUM_SYMBOLS = 5;
    private static final int MAXIMUM_SYMBOLS = 15;
    private static final String TEAM_NAME_ERR_MSG = String.format(
            "The Team name's length cannot be less than %d or more than %d symbols long.",
            MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS);
    private static final String NEW_TEAM_ADDED = "Team %s was created";
    private static final String NEW_BOARD_ADDED_TO_TEAM = "New %s was added to %s";
    private static final String NEW_MEMBER_ADDED_TO_TEAM = "New member %s was added to team %s";
    private final List<ActivityLog> teamActivityHistory = new ArrayList<>();
    private String name;
    private final List<Members> teamMembers;
    private final List<Board> boards;

    public TeamImpl(String name) {
        setName(name);
        this.teamMembers = new ArrayList<>();
        this.boards = new ArrayList<>();
        addEventToActivityLogHistory(String.format(NEW_TEAM_ADDED, this.getName()));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addBoard(Board board) {
        boards.add(board);
        addEventToActivityLogHistory(String.format(NEW_BOARD_ADDED_TO_TEAM, board.getName(), this.getName()));
    }

    @Override
    public List<Members> getTeamMembers() {
        return new ArrayList<>(teamMembers);
    }

    @Override
    public List<Board> getTeamBoards() {
        return new ArrayList<>(boards);
    }

    private void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, TEAM_NAME_ERR_MSG);
        this.name = name;
    }

    @Override
    public void addMember(Members member) {
        teamMembers.add(member);
        addEventToActivityLogHistory(String.format(NEW_MEMBER_ADDED_TO_TEAM, member.getUsername(), this.getName()));
    }

    @Override
    public void addEventToActivityLogHistory(String event) {
        teamActivityHistory.add(new ActivityLogImpl(event));
    }

    @Override
    public String displayActivityLogHistory() {
        StringBuilder result = new StringBuilder();
        for (ActivityLog activityLog : teamActivityHistory) {
            result.append(activityLog.displayInfo()).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public String getAsString() {
        return """
                Team name: %s
                """.formatted(name);
    }
}