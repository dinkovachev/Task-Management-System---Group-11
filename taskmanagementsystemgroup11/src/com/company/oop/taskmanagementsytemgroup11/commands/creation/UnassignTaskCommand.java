package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class UnassignTaskCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_ID = "Invalid value for id. Should be a number.";
    private static final String TASK_ASSIGNED_SUCCESSFULLY = "%s assigned successfully to %s";

    protected UnassignTaskCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }


    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        //ToDo - Dinko
        // Double check if type(bug,story,feedback) is needed if we are going to add the task by finding it with the ID
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        String title = parameters.get(1);
        int id = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_ID);
        String username = parameters.get(3);
        return assignTask(type, title, id, username);
    }

    //ToDo - Dinko
    // 1. used the findTaskByID & getMemberByUsername to find the task that need to be added to the username
    // 2. created method void unassignTask(Members memberToAssignTask, Task taskToBeAssigned) in Members Interface so that
    // a member from the team to be able to assign tasks
    // 3. created method void unassignTask(Task task); in Task interface so that task can have the functionality to be assigned

    private String assignTask(TaskType type, String title, int id, String username) {
        Task task = getTaskManagementSystemRepository().findTaskByID(id);
        Members member = getTaskManagementSystemRepository().getMemberByUsername(username);
        getTaskManagementSystemRepository().getMemberByUsername(username).unassignTask(member, task);
        return String.format(TASK_ASSIGNED_SUCCESSFULLY, title, member);
    }
}
