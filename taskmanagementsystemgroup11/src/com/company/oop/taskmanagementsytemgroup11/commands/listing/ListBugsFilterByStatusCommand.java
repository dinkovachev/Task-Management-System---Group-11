//package com.company.oop.taskmanagementsytemgroup11.commands.listing;
//
//import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
//import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
//import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
//import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;
//
//import java.util.List;
//
//public class ListBugsFilterByStatusCommand extends BaseCommand {
//    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
//
//    private static final String FILTER_LIST_BUGS_BY_STATUS = "Sorted bugs by status \n%s";
//
//
//    public ListBugsFilterByStatusCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
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
//
//    public String listing(ListingEnums type) {
//        StringBuilder stringBuilder = new StringBuilder();
//
////        //todo status , not title
////        if (Objects.requireNonNull(type) == ListingEnums.ASSIGNEE) {
////            List<Bug> listBugsByAssignee = getTaskManagementSystemRepository()
////                    .getFilteredListOfBugsByStatus();
////            for (Bug bug : listBugsByAssignee) {
////                stringBuilder.append(bug.getAsString());
////            }
////            return String.format(FILTER_LIST_BUGS_BY_STATUS, stringBuilder);
////        }
////        throw new IllegalArgumentException("Command must be status");
////    }
//}
//
//
