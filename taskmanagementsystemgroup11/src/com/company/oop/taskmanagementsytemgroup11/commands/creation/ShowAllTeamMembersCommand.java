package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ListingHelpers;

import java.util.List;

public class ShowAllTeamMembersCommand extends BaseCommand {

    //ToDo ShowAllTeam members must display the name of each member

//    private final List<Members> teamMembers;  //todo ???

    public ShowAllTeamMembersCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        // teamMembers = taskManagementSystemRepository.getAllTeamMembers();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        //todo check params
          String teamName = parameters.get(0);
//        List teamMembers = getTaskManagementSystemRepository().getAllTeamMembers();
        return showAllTeamMembers(teamName);
    }

    private String showAllTeamMembers(String teamName) {
        Team team = getTaskManagementSystemRepository().getTeamByName(teamName);
        List<Members> teamMembers = team.getTeamMembers();
        if (teamMembers.isEmpty()) {
            return "There are no registered  team members.";
        }
//        else {
//            getTaskManagementSystemRepository().getTeamByName(teamName);
//
//
//        }
        return ListingHelpers.teamMembersToString(teamMembers);
    }
}
