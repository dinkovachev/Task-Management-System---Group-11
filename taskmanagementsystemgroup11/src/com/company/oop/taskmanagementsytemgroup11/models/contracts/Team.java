package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import java.util.List;

public interface Team extends Printable {

    String getName();

    void addBoardToTeam(Board board);

    List<Members> getTeamMembers();

    void addMember(Members member);


}
