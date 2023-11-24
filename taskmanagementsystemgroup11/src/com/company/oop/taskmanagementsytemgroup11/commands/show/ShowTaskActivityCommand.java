package com.company.oop.taskmanagementsytemgroup11.commands.show;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ShowTaskActivityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String INVALID_INPUT_MSG = "Invalid input. Expected a number.";

    public ShowTaskActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(0), INVALID_INPUT_MSG);
        return showTaskActivity(taskIndex);
    }

    private String showTaskActivity(int taskIndex) {
        Task task = getTaskManagementSystemRepository().findTaskByID(taskIndex);
        return String.format(task.displayActivityLogHistory());
    }
}
