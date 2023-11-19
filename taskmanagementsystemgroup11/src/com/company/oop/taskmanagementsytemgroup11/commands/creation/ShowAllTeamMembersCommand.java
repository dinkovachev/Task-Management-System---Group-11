package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ListingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamMembersCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_PARAMETERS = 1;

    //ToDo ShowAllTeam members must display the name of each member

//    private final List<Members> teamMembers;  //todo ???

    public ShowAllTeamMembersCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
          String teamName = parameters.get(0);
        return showAllTeamMembers(teamName);
    }

    private String showAllTeamMembers(String teamName) {
        Team team = getTaskManagementSystemRepository().getTeamByName(teamName);
        List<Members> teamMembers = team.getTeamMembers();
        if (teamMembers.isEmpty()) {
            return "There are no registered  team members.";
        }
        System.out.printf("Team: %s%n",teamName);
        return ListingHelpers.teamMembersToString(teamMembers);
    }
}
