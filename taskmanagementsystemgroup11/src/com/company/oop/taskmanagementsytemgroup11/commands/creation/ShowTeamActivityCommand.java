package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamActivityCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
    private static final String SHOW_TEAM_ACTIVITY_MESSAGE = "%s team activity %s ";


    public ShowTeamActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        Team team = getTaskManagementSystemRepository().getTeamByName(teamName);
        if (teamName.length() == 1) {
            return String.format("There are no team activity in team %s.", teamName);
        }
        return String.format(SHOW_TEAM_ACTIVITY_MESSAGE, teamName, team.displayActivityLogHistory());
    }
}
