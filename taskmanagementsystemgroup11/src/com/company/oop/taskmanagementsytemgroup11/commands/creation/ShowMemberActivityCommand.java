package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ShowMemberActivityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_PARAMETERS = 1;
    private static final String SHOW_MEMBER_ACTIVITY_MESSAGE = "Member activity for %s%n";

    public ShowMemberActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        String username = parameters.get(0);
        Members member = getTaskManagementSystemRepository().getMemberByUsername(username);
        System.out.printf(SHOW_MEMBER_ACTIVITY_MESSAGE, username);
        return String.format(member.displayActivityLogHistory());
    }
}
