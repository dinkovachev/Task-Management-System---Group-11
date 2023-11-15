package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateNewStoryCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    public static final String INVALID_INPUT_MSG = "Invalid input. Expected a number.";

    public CreateNewStoryCommand(TaskManagementSystemRepository taskManagementSystemRepository) {

        super(taskManagementSystemRepository);
    }


    private String createNewStory(int storyId, String title, String description,
                                  Priority priority, Size size, String assignee) {
        Story story = getTaskManagementSystemRepository().createStory
                (storyId, title, description, priority, size, assignee);

        return String.format("New story created.", story.getId());
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int storyId = ParsingHelpers.tryParseInteger(parameters.get(0), INVALID_INPUT_MSG) - 1;
        String title = parameters.get(1);
        String description = parameters.get(2);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(4), Size.class);
        String assignee = parameters.get(5);

        return createNewStory(storyId, title, description, priority, size, assignee);
    }
}
