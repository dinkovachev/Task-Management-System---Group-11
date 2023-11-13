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
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class,
                String.format(INVALID_COMMAND, commandTypeAsString));

        switch (commandType) {
            case CREATEMEMBER:
                return new AddComment(taskManagementSystemRepository);
            case SHOWPEOPLE:
                return new ShowPeople(taskManagementSystemRepository);
            case SHOWACTIVITY:
                return new ShowPersonActivity(taskManagementSystemRepository);
            case CREATETEAM:
                return new CreateTeam(taskManagementSystemRepository);
            case SHOWTEAMS:
                return new ShowTeams(taskManagementSystemRepository);
            case SHOWPERSONACTIVITY:
                return new ShowPersonActivity(taskManagementSystemRepository);
            case SOHWTEAMACTIVITY:
                return new ShowTeamActivity(taskManagementSystemRepository;
            case ADDMEMBER:
                return new AddMember(taskManagementSystemRepository);
            case SHOWTEAMEMBERS:
                return new ShowPeople(taskManagementSystemRepository);
            case CREATEBOARD:
                return new CreateBoard(taskManagementSystemRepository);
            case SHOWALLBOARDS:
                return new ShowAllBoards(taskManagementSystemRepository);
            case SHOWBOARD:
                return new ShowBoard(taskManagementSystemRepository);
            case CREATINGNEWBUG:
                return new CreateNewBug(taskManagementSystemRepository);
            case CREATENEWSTORY:
                return new CreateNewStory(taskManagementSystemRepository);
            case CREATENEWFEEDBACK:
                return new CreateNewFeedback(taskManagementSystemRepository);
            case CHANGEBUGPRIORITY:
                return new ChangeBugPriority(taskManagementSystemRepository);
            case CHANGEBUGSEVERITY:
                return new ChangeBugSeverity(taskManagementSystemRepository);
            case CHANGEBUGSTATUS:
                return new ChangeBugStatus(taskManagementSystemRepository);
            case CHANGESTORYPRIORITY:
                return new ChangeStoryPriority(taskManagementSystemRepository);
            case CHANGESTORYSIZE:
                return new ChangeStorySize(taskManagementSystemRepository);
            case CHANGESTORYSTATUS:
                return new ChangeStoryStatus(taskManagementSystemRepository);
            case CHANGEFEEDBACKRATING:
                return new ChangeFeedbackRating(taskManagementSystemRepository);
            case CHANGEFEEDBACKSTATUS:
                return new ChangeStoryStatus(taskManagementSystemRepository);
            case ASSIGNTASK:
                return new AssignTask(taskManagementSystemRepository);
            case UNASSIGNTASK:
                return new UnassignedTask(taskManagementSystemRepository);
            case ADDCOMMENT:
                return new AddComment(taskManagementSystemRepository);

            //ToDo finish all commands
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
        }
    }
}
