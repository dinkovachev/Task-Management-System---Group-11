package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.exceptions.InvalidUserInputException;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ListTasksWithAssigneeFilterByStatusAndAssigneeCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String MEMBER_WITH_USERNAME_DOES_NOT_EXIST_MESSAGE = "Member with username %s does not exist.";

    public ListTasksWithAssigneeFilterByStatusAndAssigneeCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Status status = ParsingHelpers.tryParseEnum(parameters.get(0), Status.class);
        String assignee = parameters.get(1);
        validateAssignee(assignee);

        return listAssignedTasksFilteredByStatusAndAssignee(status, assignee);
    }

    private String listAssignedTasksFilteredByStatusAndAssignee(Status status, String assignee) {
        StringBuilder result = new StringBuilder();
        List<Task> assignedTasks = getTaskManagementSystemRepository().
                getFilteredListOfAssignedTasksByStatusAndAssignee(status, assignee);
        result.append("Assigned Tasks Filtered by Status and Assignee:").append(System.lineSeparator());
        for (Task assignedTask : assignedTasks) {
            result.append("Task title: ").append(assignedTask.getTitle()).append(System.lineSeparator()).
                    append("Task status: ").append(assignedTask.getStatus()).append(System.lineSeparator())
                    .append("Task assignee: ").append(assignedTask.getAssignee()).append(System.lineSeparator())
                    .append(System.lineSeparator());
        }
        return result.toString();
    }

    private void validateAssignee(String assignee) {
        if (!getTaskManagementSystemRepository().memberExist(assignee)) {
            throw new InvalidUserInputException(String.format(MEMBER_WITH_USERNAME_DOES_NOT_EXIST_MESSAGE, assignee));
        }
    }
}
