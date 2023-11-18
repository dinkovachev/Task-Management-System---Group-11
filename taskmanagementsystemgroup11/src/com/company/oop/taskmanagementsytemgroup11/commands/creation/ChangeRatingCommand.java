package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangeRatingCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_INPUT_MSG = "Invalid input. Expected a number.";
    private static final String INVALID_TASK_MSG = "You cannot change the rating of %s task type.";

    public ChangeRatingCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    // Only FEEDBACK has RATING // [1, 10]
    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        validateTaskType(type);
        int rating = ParsingHelpers.tryParseInteger(parameters.get(1), INVALID_INPUT_MSG);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG) - 1;

        return changeRating(rating, taskIndex);
    }

    private String changeRating(int rating, int taskIndex) {
        Feedback feedback = getTaskManagementSystemRepository().findFeedbackByTaskIndex(taskIndex);

        if (rating == (feedback.getRating())) {
            throw new IllegalArgumentException(format("Rating is already set to %d", feedback.getRating()));
        } else {
            feedback.changeRating(rating);
            return format("Rating changed to %d", feedback.getRating());
        }
    }

    private void validateTaskType(TaskType type) {
        if (!type.equals(TaskType.FEEDBACK)) {
            throw new IllegalArgumentException(format(INVALID_TASK_MSG, type));
        }
    }
}