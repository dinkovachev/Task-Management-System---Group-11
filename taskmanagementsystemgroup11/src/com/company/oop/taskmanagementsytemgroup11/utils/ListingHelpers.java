package com.company.oop.taskmanagementsytemgroup11.utils;

import com.company.oop.taskmanagementsytemgroup11.commands.CommandConstants;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {
    public static String peopleToString(List<Members> members) {
        List<String> result = new ArrayList<>();
        for (Members member : members) {
            result.add(member.getAsString());
        }

        return String.join(CommandConstants.JOIN_DELIMITER + System.lineSeparator(), result).trim();
    }

    public static String teamsToString(List<Team> teams) {
        List<String> result = new ArrayList<>();
        for (Team team : teams) {
            result.add(team.getAsString());
        }

        return String.join(CommandConstants.JOIN_DELIMITER + System.lineSeparator(), result).trim();
    }

    public static String boardToString(List<Board> boards) {
        List<String> result = new ArrayList<>();
        for (Board board : boards) {
            result.add(board.getAsString());
        }

        return String.join(CommandConstants.JOIN_DELIMITER + System.lineSeparator(), result).trim();
    }
    public static String teamMembersToString(List<Board> boards)  {//todo
        List<String> result = new ArrayList<>();
        for (Board board : boards) {   //todo
            result.add(board.getAsString());
        }

        return String.join(CommandConstants.JOIN_DELIMITER + System.lineSeparator(), result).trim();
    }
}
}

