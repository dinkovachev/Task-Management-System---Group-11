package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;

import java.util.List;

public class ListBugsCommand extends BaseCommand {

    private static final String SORT_LIST_BUGS_BY_TITLE = "Sorted bugs by title \n%s";


    public ListBugsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
 //       String priority = parameters.get(0);
        List<Bug> listBugsByTitle = getTaskManagementSystemRepository().getSortedListOfBugsByTitle();
        StringBuilder stringBuilder = new StringBuilder();
        for (Bug bug:listBugsByTitle) {
            stringBuilder.append(bug.getAsString());
//                            Title())
//                    .append(bug.getId()).append(System.lineSeparator());
        }
        return String.format(SORT_LIST_BUGS_BY_TITLE, stringBuilder);
    }
}


