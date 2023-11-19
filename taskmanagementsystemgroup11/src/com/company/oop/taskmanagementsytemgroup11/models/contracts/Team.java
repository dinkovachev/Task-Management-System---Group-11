package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import java.util.List;

public interface Team extends Printable {

    String getName();

    List<String> getTeamActivityHistory();

    void addBoard(Board board);

    List<Members> getTeamMembers();

    List<Board> getTeamBoards();

    void addMember(Members member);
}
