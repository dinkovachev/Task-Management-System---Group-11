package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.exceptions.InvalidUserInputException;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateStoryCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT_STORY = 7;
    public static final String TEAM_WITH_NAME_DOES_NOT_EXIST_MESSAGE = "Team with name %s does not exist.";
    private static final String NEW_TASK_CREATED_MSG = "New story with title %s and id %s created.";
    public static final String MEMBER_WITH_USERNAME_DOES_NOT_EXIST_MESSAGE = "Member with username %s does not exist.";
    public static final String BOARD_WITH_NAME_DOES_NOT_EXIST_MESSAGE = "Board with name %s does not exist";

    public CreateStoryCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT_STORY);
        String title = parameters.get(0);
        String description = parameters.get(1);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(2), Priority.class);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(3), Size.class);
        String assignee = parameters.get(4);
        validateAssignee(assignee);
        String teamName = parameters.get(5);
        validateTeamName(teamName);
        String boardName = parameters.get(6);
        validateBoardName(boardName);
        int taskIndexStory = getTaskManagementSystemRepository().getLastId();
        return createStoryCommand(title, description, priority, size, assignee, taskIndexStory, teamName, boardName);
    }

    private String createStoryCommand(String title, String description, Priority priority, Size size, String assignee,
                                      int taskIndexStory, String teamName, String boardName) {
        Story story = getTaskManagementSystemRepository().createStory(title, description, priority, size, assignee,
                teamName, boardName);
        Board board = getTaskManagementSystemRepository().getBoardByName(boardName);
        board.addTask(story);
        return String.format(NEW_TASK_CREATED_MSG, title,taskIndexStory);
    }

    private void validateAssignee(String assignee) {
        if (!getTaskManagementSystemRepository().memberExist(assignee)) {
            throw new InvalidUserInputException(String.format(MEMBER_WITH_USERNAME_DOES_NOT_EXIST_MESSAGE, assignee));
        }
    }

    private void validateTeamName(String teamName) {
        if (!getTaskManagementSystemRepository().teamExist(teamName)) {
            throw new InvalidUserInputException(String.format(TEAM_WITH_NAME_DOES_NOT_EXIST_MESSAGE, teamName));

        }
    }

    private void validateBoardName(String boardName) {
        if (!getTaskManagementSystemRepository().boardExist(boardName)) {
            throw new InvalidUserInputException(String.format(BOARD_WITH_NAME_DOES_NOT_EXIST_MESSAGE, boardName));

        }
    }

}
