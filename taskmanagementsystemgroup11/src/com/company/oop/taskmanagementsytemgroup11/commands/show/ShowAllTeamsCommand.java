package com.company.oop.taskmanagementsytemgroup11.commands.show;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ListingHelpers;

import java.util.List;

public class ShowAllTeamsCommand extends BaseCommand {
    public static final String THERE_ARE_NO_REGISTERED_TEAMS_MESSAGE = "There are no registered teams.";
    private final List<Team> teams;

    public ShowAllTeamsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        teams = taskManagementSystemRepository.getAllTeams();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (teams.isEmpty()) {
            return THERE_ARE_NO_REGISTERED_TEAMS_MESSAGE;
        }
        return ListingHelpers.teamsToString(teams);
    }
}
