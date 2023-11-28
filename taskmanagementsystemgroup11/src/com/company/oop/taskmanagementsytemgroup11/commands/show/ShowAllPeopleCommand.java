package com.company.oop.taskmanagementsytemgroup11.commands.show;


import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.utils.ListingHelpers;

import java.util.List;

public class ShowAllPeopleCommand extends BaseCommand {

    public static final String THERE_ARE_NO_REGISTERED_MEMBERS_MESSAGE = "There are no registered members.";
    private final List<Members> members;

    public ShowAllPeopleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        members = taskManagementSystemRepository.getAllMembers();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (members.isEmpty()) {
            return THERE_ARE_NO_REGISTERED_MEMBERS_MESSAGE;
        }
        return ListingHelpers.peopleToString(members);
    }
}