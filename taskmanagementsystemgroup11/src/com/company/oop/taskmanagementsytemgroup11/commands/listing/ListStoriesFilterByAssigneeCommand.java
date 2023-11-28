package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.exceptions.InvalidUserInputException;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ListStoriesFilterByAssigneeCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String MEMBER_WITH_USERNAME_DOES_NOT_EXIST_MESSAGE = "Member with username %s does not exist.";

    public ListStoriesFilterByAssigneeCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String assignee = parameters.get(0);
        validateAssignee(assignee);

        return FilterStoryByStatus(assignee);
    }

    public String FilterStoryByStatus(String assignee) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Story> filteredStoryListByStatus = getTaskManagementSystemRepository().getFilteredStoriesListByAssignee(assignee);

        for (Story story : filteredStoryListByStatus) {
            stringBuilder.append(story.getAsString()).append("\nAssignee: ").append(story.getAssignee()).append("\n\n");
        }

        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.setLength(stringBuilder.length() - 1);

        return format("Filtered stories by assignee: \n\n%s", stringBuilder);
    }

    private void validateAssignee(String assignee) {
        if (!getTaskManagementSystemRepository().memberExist(assignee)) {
            throw new InvalidUserInputException(String.format(MEMBER_WITH_USERNAME_DOES_NOT_EXIST_MESSAGE, assignee));
        }
    }

}
