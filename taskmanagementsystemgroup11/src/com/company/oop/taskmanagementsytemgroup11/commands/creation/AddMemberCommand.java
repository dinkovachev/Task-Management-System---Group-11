package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class AddMemberCommand extends BaseCommand {


    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String MEMBER_ADDED_SUCCESSFULLY = "%s added to team %s";
    private static final String INVALID_MEMBER_MESSAGE = "Invalid member. Expected an username";
    private static final String MEMBER_DOES_NOT_EXIST = "The member doesn't exist";
    private static final String INVALID_TEAM_MESSAGE = "Invalid team. Expected a team name";
    private static final String TEAM_DOES_NOT_EXIST = "The team %s doesn't exist";
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
        if (getTaskManagementSystemRepository().memberExistsInTeam(member, team)){   //todo all boards or team boards
            throw new IllegalArgumentException(String.format(MEMBER_ALREADY_EXISTS, member.getUsername(), team.getName()));
        }
        return addMember(usernameOfMemberToAdd, teamNameToAdd);
    }

    //ToDo(Done) - Dinko
    // 1. Added method void addMemberToTeam(Members memberToAdd, Team teamToAddMember) in interface Members
    // so that members from a team can add members to the team
    // 2. Override of method public void addMemberToTeam(Members memberToAdd, Team teamToAddMember) in MembersImpl
    // 3. Added method addMember void addMember(Members member) in Team interface so that you can add Member to the team
    // 4. Override of Method void addMember(Members member) in TeamImpl
    // 5. Added method getTeamByUsername in TaskManagementSystemRepository Interface to be able to create a team
    // 6. Added method getMemberByUsername in TaskManagementSystemRepository Interface to be able to create a member
    //

    //ToDo - Dinko
    // Double check if this is ok
    private String addMember(String usernameOfMemberToAdd, String teamToAdd) {
        Members member = getTaskManagementSystemRepository().getMemberByUsername(usernameOfMemberToAdd);
        //ToDo double check if the Member exist
//        ValidationHelpers.validateIntRange(getTaskManagementSystemRepository().getMemberById(), 0,
//                getTaskManagementSystemRepository().getAllMembers().size() - 1, MEMBER_DOES_NOT_EXIST);
        Team team = getTaskManagementSystemRepository().getTeamByName(teamToAdd);
        //ToDo double check if the Team exist
//        ValidationHelpers.validateIntRange(getTaskManagementSystemRepository().getMemberById(), 0,
//                getTaskManagementSystemRepository().getAllMembers().size() - 1, MEMBER_DOES_NOT_EXIST);
        member.addToTeam(team);
        return String.format(MEMBER_ADDED_SUCCESSFULLY, member.getUsername(),team.getName());

    }

//    private Members createMember(String firstName, String lastName){
//        return getTaskManagementSystemRepository().createMember(firstName,lastName);
//    }
//    private Team createTeam(String name){
//        return getTaskManagementSystemRepository().createTeam(name);
//    }
}
