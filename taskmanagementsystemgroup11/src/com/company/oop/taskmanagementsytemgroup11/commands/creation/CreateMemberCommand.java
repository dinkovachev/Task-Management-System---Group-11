package com.company.oop.taskmanagementsytemgroup11.commands.creation;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class CreateMemberCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String NAME_ALREADY_EXISTS = "Name %s, already exists";

    private String firstName;
    private String lastName;

    public CreateMemberCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
       super(taskManagementSystemRepository);
        // this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        if (getTaskManagementSystemRepository().memberExist(firstName+lastName)){   //firstname+lastname ?!?
            throw new IllegalArgumentException(String.format(NAME_ALREADY_EXISTS, firstName+lastName));
        }
        return createMember(firstName, lastName);
    }
    private String createMember(String firstName, String lastName) {
        Members member = getTaskManagementSystemRepository().createMember(firstName, lastName);

        return String.format("New member with name %s  created.", member);
    }

    public void parseParameters (List<String> parameters) {
        this.firstName = parameters.get(0);
        this.lastName = parameters.get(1);
    }
}
