package com.company.oop.taskmanagementsytemgroup11.commands.creation;


import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class CreateNewFeedbackCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    public static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");
    private static final String TASK_DOES_NOT_EXIST = "Task doesn't exist";


    public CreateNewFeedbackCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int feedbackId = ParsingHelpers.tryParseInteger(parameters.get(0), INVALID_INPUT_MSG) - 1;
        String title = parameters.get(1);
        String description = parameters.get(2);
        int rating = ParsingHelpers.tryParseInteger(parameters.get(3), INVALID_INPUT_MSG) - 1;

        return createNewFeedback(feedbackId, title, description, rating);
    }

    private String createNewFeedback(int feedbackId, String title, String description, int rating) {
        Feedback feedback = getTaskManagementSystemRepository().createFeedback(feedbackId, title, description, rating);
        return format("New feedback created.", feedback.getId());
    }
}
