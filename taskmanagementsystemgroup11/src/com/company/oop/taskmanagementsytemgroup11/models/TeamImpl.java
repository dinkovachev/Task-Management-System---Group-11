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

    private String name;
    private List<Members> teamMembers;


    //ToDo double check how to add a Board to a team
    private List<Board> boards;

    public TeamImpl(String name) {
        setName(name);
        this.teamMembers = new ArrayList<>();
        this.boards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public void addBoardToTeam(Board board) {
    }

    @Override
    public List<Members> getTeamMembers() {
        return new ArrayList<>(teamMembers);
    }

    public List<Board> getBoards() {

        return new ArrayList<>(boards);
    }

    private void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, TEAM_NAME_ERR_MSG);
        this.name = name;
    }

     @Override
    public void addMember(Members member) {
        teamMembers.add(member);
    }

    private void setTeamMembers(List<Members> teamMembers) {

        this.teamMembers = teamMembers;
    }

    private void setBoards(List<Board> boards) {

        this.boards = boards;
    }

    @Override
    public String getAsString() {
        return """
                Team name: %s
                """.formatted(name);
    }
}

