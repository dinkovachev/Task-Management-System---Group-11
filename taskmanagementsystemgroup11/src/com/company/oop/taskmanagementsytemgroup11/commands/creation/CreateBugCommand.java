package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.exceptions.InvalidUserInputException;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateBugCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT_STORY = 8;
    public static final String TEAM_WITH_NAME_DOES_NOT_EXIST_MESSAGE = "Team with name %s does not exist.";

    private static final String NEW_BUG_CREATED_MSG = "New bug with title %s and id %s created.";
    public static final String MEMBER_WITH_USERNAME_DOES_NOT_EXIST_MESSAGE = "Member with username %s does not exist.";
    public static final String BOARD_WITH_NAME_DOES_NOT_EXIST_MESSAGE = "Board with name %s does not exist";


    public CreateBugCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT_STORY);
        String title = parameters.get(0);
        String description = parameters.get(1);
        String stepsToReproduce = parameters.get(2);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(4), Severity.class);
        String assignee = parameters.get(5);
        validateAssignee(assignee);
        String teamName = parameters.get(6);
        validateTeamName(teamName);
        String boardName = parameters.get(7);
        validateBoardName(boardName);
        int taskIndexBug = getTaskManagementSystemRepository().getLastId();
        return createBugCommand(title, description, stepsToReproduce, priority, severity, assignee, taskIndexBug,
                teamName, boardName);

    }

    public String createBugCommand(String title, String description, String stepsToReproduce,
                                   Priority priority, Severity severity, String assignee, int taskIndexBug,
                                   String teamName, String boardName) {
        Bug bug = getTaskManagementSystemRepository().createBug(title, description, stepsToReproduce, priority,
                severity, assignee,
                taskIndexBug, teamName, boardName);
        Board board = getTaskManagementSystemRepository().getBoardByName(boardName);
        board.addTask(bug);
        return String.format(NEW_BUG_CREATED_MSG, title, taskIndexBug);
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
