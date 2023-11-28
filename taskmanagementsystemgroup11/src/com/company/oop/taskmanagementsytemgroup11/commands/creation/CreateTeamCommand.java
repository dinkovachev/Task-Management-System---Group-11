package com.company.oop.taskmanagementsytemgroup11.commands.creation;


import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateTeamCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String TEAM_ALREADY_EXISTS = "Team %s, already exists";
    public static final String NEW_TEAM_WITH_NAME_CREATED_MESSAGE = "New team with name %s created.";

    private String name;

    public CreateTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        if (getTaskManagementSystemRepository().teamExist(name)) {
            throw new IllegalArgumentException(String.format(TEAM_ALREADY_EXISTS, name));
        }
        return createTeam(name);
    }

    private String createTeam(String name) {
        Team team = getTaskManagementSystemRepository().createTeam(name);
        return String.format(NEW_TEAM_WITH_NAME_CREATED_MESSAGE, team.getName());
    }

    public void parseParameters(List<String> parameters) {
        this.name = parameters.get(0);
    }
}
