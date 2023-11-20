package com.company.oop.taskmanagementsytemgroup11.commands.creation;


import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.ActivityLog;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ShowBoardActivityCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String SHOW_BOARD_ACTIVITY_MESSAGE = "Team name: " +
            "%s%nBoardName: %s%n";

    public ShowBoardActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        String boardName = parameters.get(1);

        return showBoardActivity(teamName, boardName);
    }

    private String showBoardActivity(String teamName, String boardName) {
        Team team = getTaskManagementSystemRepository().getTeamByName(teamName);
        Board board = getTaskManagementSystemRepository().getBoardByName(boardName);
        System.out.printf(SHOW_BOARD_ACTIVITY_MESSAGE, team.getName(), board.getName());
        return String.format(board.displayActivityLogHistory());

    }
}