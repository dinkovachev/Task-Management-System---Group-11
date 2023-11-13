package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;

import java.util.List;

public class CreateNewBugCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;

    public CreateNewBugCommand(TaskManagementSystemRepositoryImpl taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    protected String execute(List<String> parameters) {

    }


}

//    @Override
//    public Bug createBug(String title, String description, String stepsToReproduce, Priority priority, Severity severity, Members members) {
//        return new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity, members);
//    }