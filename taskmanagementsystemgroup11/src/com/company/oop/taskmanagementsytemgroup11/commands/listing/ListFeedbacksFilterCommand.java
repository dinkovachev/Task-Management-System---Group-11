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
//public class ListFeedbacksFilterCommand extends BaseCommand {
//    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
//
//    private static final String FILTER_LIST_BUGS_BY_TITLE = "Sorted feedbacks by title \n%s";
//    private static final String FILTER_LIST_BUGS_BY_ASSIGNEE = "Sorted feedbacks by assignee \n%s";
//
//
//    public ListFeedbacksFilterCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
//        super(taskManagementSystemRepository);
//    }
//
//    @Override
//    protected String executeCommand(List<String> parameters) {
//        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
//        ListingEnums type = ParsingHelpers.tryParseEnum(parameters.get(0), ListingEnums.class);
//        String value = parameters.get(1);
//
//
//        return listing(type, value);
//    }
//
//    public String listing(ListingEnums type, String parameter) {
//        StringBuilder stringBuilder = new StringBuilder();
//
//        switch (type) {
//
//            case TITLE:
//                List<Bug> listBugsByTitle = getTaskManagementSystemRepository().getSortedListOfBugsByTitle();
//                for (Bug bug : listBugsByTitle) {
//                    stringBuilder.append(bug.getAsString());
//                }
//                return String.format(FILTER_LIST_BUGS_BY_TITLE, stringBuilder);
//            case ASSIGNEE:
//                List<Bug> listBugsByAssignee = getTaskManagementSystemRepository()
//                        .getFilteredListOfBugsByAssignee(parameter);
//                for (Bug bug : listBugsByAssignee) {
//                    stringBuilder.append(bug.getAsString());
//                }
//                return String.format(FILTER_LIST_BUGS_BY_ASSIGNEE, stringBuilder);
//            default:
//                throw new IllegalArgumentException("Command must be title or assignee");
//        }
//    }
//}