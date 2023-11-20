package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateMemberCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private String firstName;
    private String lastName;

    public CreateMemberCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        return createMember(firstName, lastName);
    }

    private String createMember(String firstName, String lastName) {
        Members member = getTaskManagementSystemRepository().createMember(firstName, lastName);

        return String.format("New member with name %s %s and ID %d is created.",
                member.getFirstName(), member.getLastName(), member.getPersonId());
    }

    public void parseParameters(List<String> parameters) {
        this.firstName = parameters.get(0);
        this.lastName = parameters.get(1);
    }
}