package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public abstract class BaseCommand implements Command {

    //ToDo - Dinko
    // Double check if it is ok

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    protected BaseCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    protected TaskManagementSystemRepository getTaskManagementSystemRepository() {

        return taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        return executeCommand(parameters);
    }
    protected abstract String executeCommand(List<String> parameters);
}
