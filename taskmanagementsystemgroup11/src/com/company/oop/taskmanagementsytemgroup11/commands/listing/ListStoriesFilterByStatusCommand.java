package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ListStoriesFilterByStatusCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;


    public ListStoriesFilterByStatusCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Status status = ParsingHelpers.tryParseEnum(parameters.get(0), Status.class);

        return FilterStoryByStatus(status);
    }

    public String FilterStoryByStatus(Status status) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Story> filteredStoryListByStatus = getTaskManagementSystemRepository().getFilteredStoriesListByStatus(status);

        for (Story story : filteredStoryListByStatus) {
            stringBuilder.append(story.getAsString()).append("\nStatus: ").append(story.getStatus()).append("\n\n");
        }

        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.setLength(stringBuilder.length() - 1);

        return format("Filtered stories by status: \n\n%s", stringBuilder);
    }
}
