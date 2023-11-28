package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ListFeedbacksFilterByStatusCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;


    public ListFeedbacksFilterByStatusCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Status status = ParsingHelpers.tryParseEnum(parameters.get(0), Status.class);

        return FilterFeedbackByStatus(status);
    }

    public String FilterFeedbackByStatus(Status status) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Feedback> filteredFeedbacksListByStatus = getTaskManagementSystemRepository().getFilteredFeedbacksListByStatus(status);

        for (Feedback feedback : filteredFeedbacksListByStatus) {
            stringBuilder.append(feedback.getAsString()).append("\nStatus: ").append(feedback.getStatus()).append("\n\n");
        }

        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.setLength(stringBuilder.length() - 1);

        return format("Filtered feedbacks by status: \n\n%s", stringBuilder);
    }
}
