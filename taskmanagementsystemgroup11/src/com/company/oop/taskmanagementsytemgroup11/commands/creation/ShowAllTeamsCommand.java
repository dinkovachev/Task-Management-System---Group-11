package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ListingHelpers;

import java.util.List;

public class ShowAllTeamsCommand extends BaseCommand {
    private final List<Team> teams;

    public ShowAllTeamsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        teams = taskManagementSystemRepository.getAllTeams();
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        if (teams.isEmpty()) {
            return "There are no registered teams.";
        }
        return ListingHelpers.teamsToString(teams);
    }
}
