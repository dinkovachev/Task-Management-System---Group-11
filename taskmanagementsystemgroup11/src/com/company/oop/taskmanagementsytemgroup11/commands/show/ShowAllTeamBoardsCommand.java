package com.company.oop.taskmanagementsytemgroup11.commands.show;


import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamBoardsCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String SHOW_ALL_TEAM_NO_BOARDS_MESSAGE = "There no boards in %s team.";
    private static final String SHOW_TEAM_BOARDS_MESSAGE = "Team: %s\nBoards:\n%s";


    public ShowAllTeamBoardsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);

        Team team = getTaskManagementSystemRepository().getTeamByName(teamName);
        List<Board> boards = team.getTeamBoards();

        if (boards.isEmpty()) {
            return String.format(SHOW_ALL_TEAM_NO_BOARDS_MESSAGE, team.getName());
        }
        StringBuilder boardsHistory = new StringBuilder();
        for (Board board : team.getTeamBoards()) {
            boardsHistory.append(board.getAsString()).append(board.displayActivityLogHistory()).append("\n");
        }
        return String.format(SHOW_TEAM_BOARDS_MESSAGE, team.getName(), boardsHistory);
    }
}