package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ListStoriesSortBySizeCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private static final String SORT_LIST_STORIES_BY_SIZE = "Sorted stories by size \n\n%s";


    public ListStoriesSortBySizeCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return listing();
    }

    public String listing() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Story> sortedListOfStoriesBySize = getTaskManagementSystemRepository().getSortedListOfStoriesBySize();
        for (Story story : sortedListOfStoriesBySize) {
            stringBuilder.append(story.getAsString()).append("\nSeverity: ").append(story.getSize()).append("\n\n");
        }
        return String.format(SORT_LIST_STORIES_BY_SIZE, stringBuilder);
    }
}

