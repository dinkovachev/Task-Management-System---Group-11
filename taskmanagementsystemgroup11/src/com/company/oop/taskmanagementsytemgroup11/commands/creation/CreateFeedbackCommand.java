package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.exceptions.InvalidUserInputException;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateFeedbackCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 4;
    public static final String TEAM_WITH_NAME_DOES_NOT_EXIST_MESSAGE = "Team with name %s does not exist.";
    private static final String NEW_TASK_CREATED_MSG = "New feedback with title %s and id %s created.";
    public static final String BOARD_WITH_NAME_DOES_NOT_EXIST_MESSAGE = "Board with name %s does not exist";
    private static final String INVALID_INPUT_MSG = "Invalid input. Expected a number.";

    public CreateFeedbackCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        String title = parameters.get(0);
        String description = parameters.get(1);
        int rating = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG);
        String teamName = parameters.get(2);
        validateTeamName(teamName);
        String boardName = parameters.get(3);
        validateBoardName(boardName);
        int index = getTaskManagementSystemRepository().getLastId();
        return createFeedbackCommand(title, description, rating, index, teamName, boardName);
    }

    private String createFeedbackCommand(String title, String description, int rating, int index, String teamName, String boardName) {
        Feedback feedback = getTaskManagementSystemRepository().createFeedback(title, description, rating, index, teamName, boardName);
        Board board = getTaskManagementSystemRepository().getBoardByName(boardName);
        board.addTask(feedback);
        return String.format(NEW_TASK_CREATED_MSG, title, index);
    }

    private void validateTeamName(String teamName) {
        if (!getTaskManagementSystemRepository().teamExist(teamName)) {
            throw new InvalidUserInputException(String.format(TEAM_WITH_NAME_DOES_NOT_EXIST_MESSAGE, teamName));

        }
    }

    private void validateBoardName(String boardName) {
        if (!getTaskManagementSystemRepository().boardExist(boardName)) {
            throw new InvalidUserInputException(String.format(BOARD_WITH_NAME_DOES_NOT_EXIST_MESSAGE, boardName));

        }
    }
}