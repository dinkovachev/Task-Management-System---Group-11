package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateBoardCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String BOARD_ALREADY_EXISTS = "Board %s, already exists";

//todo second parameter teamname and check for duplication in boards names
    public CreateBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
       super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String boardName = parameters.get(0);
        String teamName = parameters.get(1);
        if (getTaskManagementSystemRepository().boardExist(boardName)){   //todo all boards or team boards
            throw new IllegalArgumentException(String.format(BOARD_ALREADY_EXISTS, boardName));
        }
        return createBoard(boardName, teamName);
    }

    private String createBoard(String name, String teamName) {
            Board board = getTaskManagementSystemRepository().createBoard(name);
            Team team = getTaskManagementSystemRepository().createTeam(teamName);
            return String.format("New board with name %s  created in team %s.", board.getName(), team.getName());
    }
}
