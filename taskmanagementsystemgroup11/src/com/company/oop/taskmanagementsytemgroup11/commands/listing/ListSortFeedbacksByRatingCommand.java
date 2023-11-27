package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ListSortFeedbacksByRatingCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private static final String SORT_LIST_FEEDBACKS_BY_RATING = "Sorted feedbacks by rating \n%s";

    public ListSortFeedbacksByRatingCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return listing();
    }

    public String listing() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Feedback> sortedListOfFeedbacksByRating = getTaskManagementSystemRepository().getSortedListOfFeedbacksByRating();
        for (Feedback feedback : sortedListOfFeedbacksByRating) {
            stringBuilder.append(feedback.getAsString()).append("\nSeverity: ").append(feedback.getRating()).append("\n\n");
        }
        return String.format(SORT_LIST_FEEDBACKS_BY_RATING, stringBuilder);
    }
}

