package com.company.oop.taskmanagementsytemgroup11.commands.change;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangeRatingCommand extends BaseCommand {
    public static final String RATING_IS_ALREADY_SET_TO_MESSAGE = "Rating is already set to %d.";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_INPUT_MSG = "Invalid input. Expected a number.";
    private static final String INVALID_TASK_MSG = "You cannot change the rating of %s task type.";
    public static final String RATING_CHANGED_TO_MESSAGE = "Rating changed to %d.";

    public ChangeRatingCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        validateTaskType(type);
        int rating = ParsingHelpers.tryParseInteger(parameters.get(1), INVALID_INPUT_MSG);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG);

        return changeRating(type, rating, taskIndex);
    }

    private String changeRating(TaskType type, int rating, int taskIndex) {
        Feedback feedback = getTaskManagementSystemRepository().findFeedbackByTaskIndex(taskIndex);

        if (rating == (feedback.getRating())) {
            throw new IllegalArgumentException(format(RATING_IS_ALREADY_SET_TO_MESSAGE, feedback.getRating()));

        } else {
            feedback.changeRating(taskIndex, rating);
            return format(RATING_CHANGED_TO_MESSAGE, feedback.getRating());
        }
    }

    private void validateTaskType(TaskType type) {
        if (!type.equals(TaskType.FEEDBACK)) {
            throw new IllegalArgumentException(format(INVALID_TASK_MSG, type));
        }
    }
}