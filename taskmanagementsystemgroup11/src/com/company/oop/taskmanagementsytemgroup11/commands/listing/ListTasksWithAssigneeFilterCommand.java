package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ListTasksWithAssigneeFilterCommand extends BaseCommand {

    private static final int EXPECTED_ARGUMENTS_COUNT = 1;


    public ListTasksWithAssigneeFilterCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_ARGUMENTS_COUNT);
        String assignee = parameters.get(0);
        return listAssignedTasksFilterByAssignee(assignee);
    }

    private String listAssignedTasksFilterByAssignee(String assignee) {
        StringBuilder result = new StringBuilder();
        List<Task> assignedTasks = getTaskManagementSystemRepository().getFilteredListOfAssignedTasksByAssignee(assignee);
        result.append("Assigned Tasks Filtered by Assignee:").append(System.lineSeparator());
        for (Task assignedTask : assignedTasks) {
             result.append("Task title: ").append(assignedTask.getTitle()).append(System.lineSeparator()).
                    append("Task status: ").append(assignedTask.getStatus()).append(System.lineSeparator())
                    .append("Task assignee: ").append(assignedTask.getAssignee()).append(System.lineSeparator())
            .append(System.lineSeparator());
        }
    return result.toString();
    }

}
