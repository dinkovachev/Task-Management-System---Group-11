package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateBoardCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String BOARD_ALREADY_EXISTS = "Board %s, already exists";
    public static final String NEW_BOARD_WITH_NAME_S_CREATED_IN_TEAM_MESSAGE = "New board with name %s created in team %s.";

    public CreateBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String boardName = parameters.get(0);
        String teamName = parameters.get(1);
        if (getTaskManagementSystemRepository().boardExist(boardName)) {
            throw new IllegalArgumentException(String.format(BOARD_ALREADY_EXISTS, boardName));
        }
        return createBoard(boardName, teamName);
    }

    private String createBoard(String name, String teamName) {
        Board board = getTaskManagementSystemRepository().createBoard(name);
        Team team = getTaskManagementSystemRepository().getTeamByName(teamName);
        team.addBoard(board);

        return String.format(NEW_BOARD_WITH_NAME_S_CREATED_IN_TEAM_MESSAGE, board.getName(), team.getName());
    }
}
