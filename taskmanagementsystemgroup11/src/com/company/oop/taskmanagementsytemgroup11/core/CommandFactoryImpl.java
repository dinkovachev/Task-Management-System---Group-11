package com.company.oop.taskmanagementsytemgroup11.core;

import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.commands.creation.*;
import com.company.oop.taskmanagementsytemgroup11.commands.enums.CommandType;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.CommandFactory;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString,
                                                TaskManagementSystemRepository taskManagementSystemRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);

        switch (commandType) {
            case CREATEMEMBER:
                return new CreateMemberCommand(taskManagementSystemRepository);
            case SHOWPEOPLE:
                return new ShowAllPeopleCommand(taskManagementSystemRepository);
            case CREATETEAM:
                return new CreateTeamCommand(taskManagementSystemRepository);
            case SHOWTEAMS:
                return new ShowAllTeamsCommand(taskManagementSystemRepository);
            case SHOWPERSONACTIVITY:
                return new ShowMemberActivityCommand(taskManagementSystemRepository);
            case SOHWTEAMACTIVITY:
                return new ShowTeamActivityCommand(taskManagementSystemRepository);
            case ADDMEMBER:
                return new AddMemberCommand(taskManagementSystemRepository);
            case SHOWTEAMEMBERS:
                return new ShowAllTeamMembersCommand(taskManagementSystemRepository);
            case CREATEBOARD:
                return new CreateBoardCommand(taskManagementSystemRepository);
            case SHOWALLBOARDS:
                return new ShowAllTeamsBoardsCommand(taskManagementSystemRepository);
            case SHOWBOARD:
                return new ShowBoardsActivityCommand(taskManagementSystemRepository);
            case ASSIGNTASK:
                return new AssignTaskCommand(taskManagementSystemRepository);
            case UNASSIGNTASK:
                return new UnassignTaskCommand(taskManagementSystemRepository);
            case ADDCOMMENT:
                return new AddCommentCommand(taskManagementSystemRepository);
            case CHANGESEVERITY:
                return new ChangeSeverityCommand(taskManagementSystemRepository);
            case CHANGESIZE:
                return new ChangeSizeCommand(taskManagementSystemRepository);
            case CHANGEPRIORITY:
                return new ChangePriorityCommand(taskManagementSystemRepository);
            case CREATE:
                return new CreateNewTaskCommand(taskManagementSystemRepository);
            case CHANGERATING:
                return new ChangeRatingCommand(taskManagementSystemRepository);
            case CHANGESTATUS:
                return new ChangeStatusCommand(taskManagementSystemRepository);
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
        }
    }
}
