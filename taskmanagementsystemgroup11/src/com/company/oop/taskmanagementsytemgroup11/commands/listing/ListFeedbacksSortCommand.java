//package com.company.oop.taskmanagementsytemgroup11.commands.listing;
//
//import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
//import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
//import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
//import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
//import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;
//
//import java.util.List;
//
//public class ListFeedbacksSortCommand extends BaseCommand {
//    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
//
//    private static final String SORT_LIST_BUGS_BY_PRIORITY = "Sorted bugs by title \n%s";
//    private static final String SORT_LIST_BUGS_BY_SEVERITY = "Sorted bugs by assignee \n%s";
//
//
//    public ListFeedbacksSortCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
//        super(taskManagementSystemRepository);
//    }
//
//    @Override
//    protected String executeCommand(List<String> parameters) {
//        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
//        ListingEnums type = ParsingHelpers.tryParseEnum(parameters.get(0), ListingEnums.class);
//
//        return listing(type);
//    }
//    public String listing(ListingEnums type) {
//        StringBuilder stringBuilder = new StringBuilder();
//
//        switch (type) {
//
//            case PRIORITY:
//                List<Bug> sortedListOfBugsByPriority = getTaskManagementSystemRepository().getSortedListOfBugsByPriority();
//                for (Bug bug : sortedListOfBugsByPriority) {
//                    stringBuilder.append(bug.getAsString());
//                }
//                return String.format(SORT_LIST_BUGS_BY_PRIORITY, stringBuilder);
//            case SEVERITY:
//                List<Bug> sortedListOfBugsBySeverity = getTaskManagementSystemRepository().getSortedListOfBugsBySeverity();
//                for (Bug bug : sortedListOfBugsBySeverity) {
//                    stringBuilder.append(bug.getAsString());
//                }
//                return String.format(SORT_LIST_BUGS_BY_SEVERITY, stringBuilder);
//            default:
//                throw new IllegalArgumentException("Command must be priority or severity");
//        }
//    }
//}
