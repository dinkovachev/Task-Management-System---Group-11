package com.company.oop.taskmanagementsytemgroup11.core.contracts;

import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;

public interface CommandFactory {

    Command createCommandFromCommandName(String commandTypeAsString, TaskManagementSystemRepository vehicleDealershipRepository);

}

