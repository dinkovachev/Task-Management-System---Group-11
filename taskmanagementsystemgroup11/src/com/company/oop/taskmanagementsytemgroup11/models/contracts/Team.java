package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import java.util.List;

public interface Team extends Printable {

    String getName();

    void addBoard(Board board);

    List<Members> getTeamMembers();

    List<Board> getTeamBoards();

    void addMember(Members member);

    void addEventToActivityLogHistory(String event);

    String displayActivityLogHistory();
}