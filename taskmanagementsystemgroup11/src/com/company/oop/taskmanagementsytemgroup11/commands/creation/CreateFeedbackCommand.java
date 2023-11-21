package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class CreateFeedbackCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    private static final String INVALID_INPUT_MSG = "Invalid input. Expected a number.";
    private static final String NEW_TASK_CREATED_MSG = "New feedback with id %s created.";

    public CreateFeedbackCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int index = ParsingHelpers.tryParseInteger(parameters.get(0), INVALID_INPUT_MSG)
        String title = parameters.get(1);
        String description = parameters.get(2);
        int rating = ParsingHelpers.tryParseInteger(parameters.get(3), INVALID_INPUT_MSG);
        String teamName = parameters.get(4);
        String boardName = parameters.get(5);

        validateTeamName(teamName);
        validateBoardName(boardName);

        return createFeedback(index, title, description, rating, teamName, boardName);
    }

    private String createFeedback(
            int index, String title, String description, int rating, String teamName, String boardName) {
        Feedback feedback = getTaskManagementSystemRepository().createFeedback(
                index, title, description, rating, teamName, boardName);

        Board board = getTaskManagementSystemRepository().getBoardByName(boardName);
        board.addTask(feedback);

        return format(NEW_TASK_CREATED_MSG, feedback.getId());
    }

    private void validateTeamName(String teamName) {
        if (!getTaskManagementSystemRepository().teamExist(teamName)) {
            throw new IllegalArgumentException(format("Team with name %s does not exist.", teamName));
        }
    }

    private void validateBoardName(String boardName) {
        if (!getTaskManagementSystemRepository().boardExist(boardName)) {
            throw new IllegalArgumentException(format("Board with name %s does not exist.", boardName));
        }
    }
}
