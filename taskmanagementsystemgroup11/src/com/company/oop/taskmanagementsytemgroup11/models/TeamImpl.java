package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {

    private static final int MINIMUM_SYMBOLS = 5;
    private static final int MAXIMUM_SYMBOLS = 15;
    public static final String TEAM_NAME_ERR_MSG = String.format(
            "The Team name's length cannot be less than %d or more than %d symbols long.",
            MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS);
    private List<String> TeamActivityHistory = new ArrayList<>();
    private String name;
    private List<Members> teamMembers;
    private List<Board> TeamBoards;


    public TeamImpl(String name) {
        setName(name);
        this.teamMembers = new ArrayList<>();
        this.TeamBoards = new ArrayList<>();
        TeamActivityHistory.add(String.format("New team %s was added", this.getName()));

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getTeamActivityHistory() {
        return new ArrayList<>(TeamActivityHistory);
    }

    //    @Override
    //   public void addBoardToTeam(String boardName, String TeamName) {
//        TeamName.addMember(boardName);
//       boards.add(String.format("New board %s was added to team %s", Board.getName(),
//               getName()));
//    }
    @Override
    public void addBoard(Board board) {
        TeamBoards.add(board);
        TeamActivityHistory.add(String.format("New board %s was added to team %s", board.getName(), this.getName()));

    }

    @Override
    public List<Members> getTeamMembers() {
        return new ArrayList<>(teamMembers);
    }

    @Override
    public List<Board> getTeamBoards() {
        return new ArrayList<>(TeamBoards);
    }

    private void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, TEAM_NAME_ERR_MSG);
        this.name = name;
    }

    @Override
    public void addMember(Members member) {
        teamMembers.add(member);
        TeamActivityHistory.add(String.format("New member %s was added to team %s", member.getUsername(), this.getName()));

    }

    private void setTeamMembers(List<Members> teamMembers) {
        this.teamMembers = teamMembers;
    }

    private void setTeamBoards(List<Board> teamBoards) {
        this.TeamBoards = teamBoards;
    }

    @Override
    public String getAsString() {
        return """
                Team name: %s
                """.formatted(name);
    }
}

