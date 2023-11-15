package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangeRatingCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");

    public ChangeRatingCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        int rating = ParsingHelpers.tryParseInteger(parameters.get(1), INVALID_INPUT_MSG) - 1;
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG) - 1;

        return changeRating(rating, taskIndex);
    }

    private String changeRating(int rating, int taskIndex) {
        Feedback feedback = getTaskManagementSystemRepository().findFeedbackByIndex(taskIndex);

        if (rating == (feedback.getRating())) {
            throw new IllegalArgumentException(format("Rating is already set to %d", feedback.getRating()));
        } else {
            feedback.changeRating(rating);
            return format("Rating changed to %d", feedback.getRating());
        }
    }
}