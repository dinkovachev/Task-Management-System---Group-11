package com.company.oop.taskmanagementsytemgroup11.commands.creation;


import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangeStoryPriorityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");

    public ChangeStoryPriorityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(0), Priority.class);
        int storyIndex = ParsingHelpers.tryParseInteger(parameters.get(1), INVALID_INPUT_MSG) - 1;

        return changeStoryPriority(priority, storyIndex);
    }

    private String changeStoryPriority(Priority newPriority, int storyIndex) {
        Story story = getTaskManagementSystemRepository().findStoryByIndex(storyIndex);

        if (newPriority.equals(story.getPriority())) {
            throw new IllegalArgumentException(format("Story priority is already set to %s", story.getPriority()));
        } else {
            story.changePriority(newPriority);
            return format("Story priority changed to %s", story.getPriority().toString());
        }
    }
}
