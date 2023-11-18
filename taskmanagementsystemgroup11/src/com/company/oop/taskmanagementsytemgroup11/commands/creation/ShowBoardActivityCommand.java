package com.company.oop.taskmanagementsytemgroup11.commands.creation;


import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.ActivityLog;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ShowBoardActivityCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");

    public ShowBoardActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        //ToDo create arguments to search board in a team and display the board activity
        return showAllBoardsActivity();
    }

    private String showAllBoardsActivity() {

        StringBuilder sb = new StringBuilder();
        sb.append("-----BOARDS-----").append(System.lineSeparator());

        for (Board allTeamsBoard : getTaskManagementSystemRepository().getAllTeamsBoards()) {
            sb.append("----------------").append(System.lineSeparator());
            sb.append(allTeamsBoard.toString()).append("\n");

//            int counter = 0;

            for (ActivityLog activities : getTaskManagementSystemRepository().getAllActivities()) {
                sb.append(activities).append(System.lineSeparator());
            }

            System.out.println(sb.toString().trim());
        }

        return "---END-BOARDS---";

        // TODO PROBABLY THE COMMAND SHOULD PRINT TEAM NAMES, TOO ???
    }
}

