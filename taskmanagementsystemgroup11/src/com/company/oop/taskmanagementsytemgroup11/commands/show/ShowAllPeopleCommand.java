package com.company.oop.taskmanagementsytemgroup11.commands.show;


import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.utils.ListingHelpers;

import java.util.List;

public class ShowAllPeopleCommand extends BaseCommand {

    private final List<Members> members;

    public ShowAllPeopleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        members = taskManagementSystemRepository.getAllMembers();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (members.isEmpty()) {
            return "There are no registered members.";
        }
        return ListingHelpers.peopleToString(members);
    }
}