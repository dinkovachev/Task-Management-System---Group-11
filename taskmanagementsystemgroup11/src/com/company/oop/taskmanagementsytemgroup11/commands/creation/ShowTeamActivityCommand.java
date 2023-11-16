//package com.company.oop.taskmanagementsytemgroup11.commands.creation;
//
//import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
//import com.company.oop.taskmanagementsytemgroup11.models.contracts.ActivityLog;
//import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
//import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
//import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;
//
//import java.util.List;
//
//public class ShowTeamActivityCommand extends BaseCommand {
//
//    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
//    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
//    private static final String SHOW_TEAM_ACTIVITY_MESSAGE = "%s team activity ";
//
//
//    protected ShowTeamActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
//        super(taskManagementSystemRepository);
//    }
//
//    @Override
//    protected String executeCommand(List<String> parameters) {
//        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
//        String team = parameters.get(0);
//        int id = ParsingHelpers.tryParseInteger(parameters.get(1), INVALID_INPUT_MESSAGE);
//        return showTeamActivity(team, id);
//    }
//    private String showTeamActivity(String team, int id){
//        Team team1 = getTaskManagementSystemRepository().getTeamByUsername(team);
//        ActivityLog activityLog = getTaskManagementSystemRepository().ge
//
//        return String.format(SHOW_TEAM_ACTIVITY_MESSAGE,team);
//    }
//}
