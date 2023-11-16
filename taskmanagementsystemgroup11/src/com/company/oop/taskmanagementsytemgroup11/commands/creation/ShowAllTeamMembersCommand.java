package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.utils.ListingHelpers;

import java.util.List;

public class ShowAllTeamMembersCommand extends BaseCommand {

    private final List<Members> teamMembers;

    public ShowAllTeamMembersCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
       // teamMembers = taskManagementSystemRepository.getAllTeamMembers();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (teamMembers.isEmpty()) {
            return "There are no registered  team members.";

        }
        return ListingHelpers.teamMembersToString(teamMembers);
    }
}
