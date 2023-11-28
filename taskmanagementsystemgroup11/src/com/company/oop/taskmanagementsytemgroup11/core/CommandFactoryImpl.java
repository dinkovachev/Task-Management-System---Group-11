package com.company.oop.taskmanagementsytemgroup11.core;

import com.company.oop.taskmanagementsytemgroup11.commands.add.AddCommentCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.add.AddMemberCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.assign.AssignTaskCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.change.*;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.commands.creation.*;
import com.company.oop.taskmanagementsytemgroup11.commands.enums.CommandType;
import com.company.oop.taskmanagementsytemgroup11.commands.listing.ListStoriesSortBySizeCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.listing.*;
import com.company.oop.taskmanagementsytemgroup11.commands.listing.ListTasksWithAssigneeSortCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.show.*;
import com.company.oop.taskmanagementsytemgroup11.commands.unassign.UnassignTaskCommand;
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
            case SHOWALLPEOPLE:
                return new ShowAllPeopleCommand(taskManagementSystemRepository);
            case CREATETEAM:
                return new CreateTeamCommand(taskManagementSystemRepository);
            case SHOWALLTEAMS:
                return new ShowAllTeamsCommand(taskManagementSystemRepository);
            case SHOWMEMBERACTIVITY:
                return new ShowMemberActivityCommand(taskManagementSystemRepository);
            case SHOWTEAMACTIVITY:
                return new ShowTeamActivityCommand(taskManagementSystemRepository);
            case ADDMEMBER:
                return new AddMemberCommand(taskManagementSystemRepository);
            case SHOWTEAMMEMBERS:
                return new ShowAllTeamMembersCommand(taskManagementSystemRepository);
            case CREATEBOARD:
                return new CreateBoardCommand(taskManagementSystemRepository);
            case SHOWALLTEAMBOARDS:
                return new ShowAllTeamBoardsCommand(taskManagementSystemRepository);
            case CREATESTORY:
                return new CreateStoryCommand(taskManagementSystemRepository);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivityCommand(taskManagementSystemRepository);
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
            case CREATEBUG:
                return new CreateBugCommand(taskManagementSystemRepository);
            case CHANGERATING:
                return new ChangeRatingCommand(taskManagementSystemRepository);
            case CHANGESTATUS:
                return new ChangeStatusCommand(taskManagementSystemRepository);
            case SHOWTASKACTIVITY:
                return new ShowTaskActivityCommand(taskManagementSystemRepository);
            case CREATEFEEDBACK:
                return new CreateFeedbackCommand(taskManagementSystemRepository);
//            case LISTBUGSFILTER:
//                return new ListBugsFilterCommand(taskManagementSystemRepository);
//            case LISTBUGSSORT:
 //               return new ListBugsSortCommand(taskManagementSystemRepository);
            case LISTTASKSBYTITLEFILTER:
                return new ListFilterTasksByTitleCommand(taskManagementSystemRepository);
            case LISTTASKWITHASSIGNEE:
                return new ListTasksWithAssigneeSortCommand(taskManagementSystemRepository);
//            case LISTFEEDBACKSSORT:
//                return new ListFeedbacksSortCommand(taskManagementSystemRepository);
//            case LISTFEEDBACKSFILTER:
//                return new ListFeedbacksFilterCommand(taskManagementSystemRepository);
//            case LISTSTORIES:
//                return new ListSortByPriorityCommand(taskManagementSystemRepository);
            case LISTSORTBYTITLE:
                return new ListSortByTitleCommand(taskManagementSystemRepository);
            case LISTBUGSSORTBYPRIORITY:
                return new ListBugsSortByPriorityCommand(taskManagementSystemRepository);
            case LISTBUGSSORTBYSEVERITY:
                return new ListBugsSortBySeverityCommand(taskManagementSystemRepository);
            case LISTSTORIESSORTBYPRIORITY:
                return new ListStoriesSortByPriorityCommand(taskManagementSystemRepository);
            case LISTSTORIESSORTBYSIZE:
                return new ListStoriesSortBySizeCommand(taskManagementSystemRepository);
            case LISTFEEDBACKSSORTBYRATING:
                return new ListSortFeedbacksByRatingCommand(taskManagementSystemRepository);
            case LISTBUGSFILTERBYSTATUS:
                return new ListBugFilterByStatusCommand(taskManagementSystemRepository);
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
        }
    }
}
