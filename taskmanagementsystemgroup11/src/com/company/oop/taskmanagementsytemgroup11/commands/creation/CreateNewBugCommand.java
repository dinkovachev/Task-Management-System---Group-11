package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateNewBugCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    public static final String INVALID_INPUT_MSG = String.format("Invalid input. Expected a number.");

    public CreateNewBugCommand(TaskManagementSystemRepositoryImpl taskManagementSystemRepository) {

        super(taskManagementSystemRepository);
    }

    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.tryParseInteger(parameters.get(0), INVALID_INPUT_MSG) - 1;
        String title = parameters.get(1);
        String description = parameters.get(2);
        String stepsToReproduce = parameters.get(3);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(4), Priority.class);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(5), Severity.class);
        String assignee = parameters.get(6);

        return createNewBug(bugId, title, description, stepsToReproduce, priority, severity, assignee);
    }

    //TODO Should be Assignee that is a username from Team members;
    private String createNewBug(int bugId, String title, String description, String stepsToReproduce,
                                Priority priority, Severity severity, String assignee) {
        Bug bug = getTaskManagementSystemRepository().createBug(bugId, title, description, stepsToReproduce, priority, severity, assignee);

        return String.format("New bug created.", bug.getId());
    }

}
