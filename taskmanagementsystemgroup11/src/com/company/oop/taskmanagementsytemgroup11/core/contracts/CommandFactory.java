package com.company.oop.taskmanagementsytemgroup11.core.contracts;

import com.company.oop.taskmanagementsytemgroup11.commands.creation.ChangeCommand;

public interface CommandFactory {

    ChangeCommand createCommandFromCommandName(String commandTypeAsString, TaskManagementSystemRepository vehicleDealershipRepository);

}

