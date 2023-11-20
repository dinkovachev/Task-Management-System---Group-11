package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class AddMemberCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String MEMBER_ADDED_SUCCESSFULLY = "%s added to team %s";
    private static final String MEMBER_ALREADY_EXISTS = "Member with username %s already exists in team %s";

    public AddMemberCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String usernameOfMemberToAdd = parameters.get(0);
        String teamNameToAdd = parameters.get(1);
        Team team = getTaskManagementSystemRepository().getTeamByName(teamNameToAdd);
        Members member = getTaskManagementSystemRepository().getMemberByUsername(usernameOfMemberToAdd);
        if (getTaskManagementSystemRepository().memberExistsInTeam(member, team)) {
            throw new IllegalArgumentException(String.format(MEMBER_ALREADY_EXISTS, member.getUsername(), team.getName()));
        }
        return addMember(usernameOfMemberToAdd, teamNameToAdd);
    }

    private String addMember(String usernameOfMemberToAdd, String teamToAdd) {
        Members member = getTaskManagementSystemRepository().getMemberByUsername(usernameOfMemberToAdd);
        Team team = getTaskManagementSystemRepository().getTeamByName(teamToAdd);
        member.addToTeam(team);

        return String.format(MEMBER_ADDED_SUCCESSFULLY, member.getUsername(), team.getName());
    }
}
