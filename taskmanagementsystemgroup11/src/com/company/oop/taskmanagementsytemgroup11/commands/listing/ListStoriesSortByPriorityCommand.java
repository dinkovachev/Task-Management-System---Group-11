package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ListStoriesSortByPriorityCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private static final String SORT_LIST_STORIES_BY_PRIORITY = "Sorted stories by priority \n%s";

    public ListStoriesSortByPriorityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);


        return listing();
    }

    public String listing() {
        StringBuilder stringBuilder = new StringBuilder();

            List<Story> sortedStoryListByPriority = getTaskManagementSystemRepository().getSortedListOfStoriesByPriority();
            for (Story story : sortedStoryListByPriority) {
                stringBuilder.append(story.getAsString()).append("\nSeverity: ").append(story.getPriority()).append("\n\n");
            }
            return String.format(SORT_LIST_STORIES_BY_PRIORITY, stringBuilder);
        }
}
