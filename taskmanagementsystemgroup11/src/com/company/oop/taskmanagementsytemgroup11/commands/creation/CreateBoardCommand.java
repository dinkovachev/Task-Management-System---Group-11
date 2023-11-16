package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateBoardCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String BOARD_ALREADY_EXISTS = "Board %s, already exists";

//todo second parameter teamname and check for duplication in boards names
    public CreateBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
       super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        if (getTaskManagementSystemRepository().boardExist(name)){   //todo all boards or team boards
            throw new IllegalArgumentException(String.format(BOARD_ALREADY_EXISTS, name));
        }
        return createBoard(name);
    }

    private String createBoard(String name) {
            Board board = getTaskManagementSystemRepository().createBoard(name);
            return String.format("New board with name %s  created.", board.getName());

    }
}
