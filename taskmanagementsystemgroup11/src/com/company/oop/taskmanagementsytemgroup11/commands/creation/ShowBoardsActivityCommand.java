package com.company.oop.taskmanagementsytemgroup11.commands.creation;


import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.ActivityLog;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ShowBoardsActivityCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");

    public ShowBoardsActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return showAllBoardsActivity();
    }

    private String showAllBoardsActivity() {

        for (Board allTeamsBoard : getTaskManagementSystemRepository().getAllTeamsBoards()) {
            StringBuilder sb = new StringBuilder();
            sb.append("--BOARDS--\n");
            sb.append(allTeamsBoard.toString()).append("\n");
//            return sb.toString().trim();

            int counter = 0;

            for (ActivityLog activities : getTaskManagementSystemRepository().getAllActivities()) {
                sb.append(activities).append(System.lineSeparator());
            }

            System.out.println(sb.toString().trim());
        }

        return "-------------------";
    }
}

