package com.company.oop.taskmanagementsytemgroup11.core;

import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.commands.enums.CommandType;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.CommandFactory;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString,
                                                TaskManagementSystemRepository vehicleDealershipRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class,
                String.format(INVALID_COMMAND, commandTypeAsString));

        switch (commandType) {
            //ToDo finish all commands
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
        }
    }
}
