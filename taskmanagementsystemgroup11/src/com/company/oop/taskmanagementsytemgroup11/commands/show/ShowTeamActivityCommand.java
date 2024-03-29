package com.company.oop.taskmanagementsytemgroup11.commands.show;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamActivityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String SHOW_TEAM_ACTIVITY_MESSAGE = "%s team activity %s ";
    public static final String THERE_ARE_NO_TEAM_ACTIVITY_IN_TEAM_MESSAGE = "There are no team activity in team %s.";


    public ShowTeamActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        Team team = getTaskManagementSystemRepository().getTeamByName(teamName);
        if (teamName.length() == 1) {
            return String.format(THERE_ARE_NO_TEAM_ACTIVITY_IN_TEAM_MESSAGE, teamName);
        }
        return String.format(SHOW_TEAM_ACTIVITY_MESSAGE, teamName, team.displayActivityLogHistory());
    }
}
