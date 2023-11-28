package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ListTasksWithAssigneeFilterByStatusCommand extends BaseCommand {

    private static final int EXPECTED_PARAMETERS_COUNT = 1;
    public ListTasksWithAssigneeFilterByStatusCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_PARAMETERS_COUNT);
        Status status = ParsingHelpers.tryParseEnum(parameters.get(0), Status.class);
        return listAssignedTasksFilteredByStatus(status);
    }
    private String listAssignedTasksFilteredByStatus(Status status){
        StringBuilder result = new StringBuilder();
        List<Task> assignedTasks = getTaskManagementSystemRepository().
                getFilteredListOfTasksWithAssigneeByStatus(status);
        result.append("Assigned Tasks Filtered by Status:").append(System.lineSeparator());
        for (Task assignedTask : assignedTasks) {
            result.append("Task title: ").append(assignedTask.getTitle()).append(System.lineSeparator()).
                    append("Task status: ").append(assignedTask.getStatus()).append(System.lineSeparator())
                    .append("Task assignee: ").append(assignedTask.getAssignee()).append(System.lineSeparator())
            .append(System.lineSeparator());
        }
        return result.toString();
    }
}
