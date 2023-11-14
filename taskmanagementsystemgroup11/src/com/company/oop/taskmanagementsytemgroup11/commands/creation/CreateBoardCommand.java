package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateBoardCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String BOARD_ALREADY_EXISTS = "Board %s, already exists";


//    private final TaskManagementSystemRepository taskManagementSystemRepository;


    public CreateBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
       super(taskManagementSystemRepository);
 //       this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

//    @Override
//    public String execute(List<String> parameters) {
//        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
//        String name = parameters.get(0);
//        if (getTaskManagementSystemRepository().memberExist(name)){
//            throw new IllegalArgumentException(String.format(BOARD_ALREADY_EXISTS, name));
//        }
//        return createBoard(name);
//    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        if (getTaskManagementSystemRepository().memberExist(name)){   //todo check board exist not member
            throw new IllegalArgumentException(String.format(BOARD_ALREADY_EXISTS, name));
        }
        return createBoard(name);
    }

    private String createBoard(String name) {
            Board board = getTaskManagementSystemRepository().createBoard(name);

            return String.format("New board with name %s  created.", board);

//        Board createdBoard = taskManagementSystemRepository.createBoard(name);
//        return String.format(CommandConstants.BOARD_CREATED_MESSAGE, createdBoard);
    }
}
