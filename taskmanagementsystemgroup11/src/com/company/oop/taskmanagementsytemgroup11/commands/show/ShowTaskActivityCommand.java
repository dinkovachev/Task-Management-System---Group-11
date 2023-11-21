package com.company.oop.taskmanagementsytemgroup11.commands.show;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ShowTaskActivityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_INPUT_MSG = "Invalid input. Expected a number.";

    public ShowTaskActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(1), INVALID_INPUT_MSG) - 1;
        return showTaskActivity(type, taskIndex);
    }

    private String showTaskActivity(TaskType type, int taskIndex) {
        Task task = getTaskManagementSystemRepository().findTaskByID(taskIndex);
        return String.format(task.displayActivityLogHistory());
    }
}
