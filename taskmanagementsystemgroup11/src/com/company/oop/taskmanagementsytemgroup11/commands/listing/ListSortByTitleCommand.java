package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ListSortByTitleCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String SORT_LIST_TASK_BY_TITLE = "Sorted tasks by title \n%s";
    private static final String SORT_LIST_BUGS_BY_TITLE = "Sorted bugs by title \n%s";
    private static final String SORT_LIST_STORIES_BY_TITLE = "Sorted bugs by title \n%s";
    private static final String SORT_LIST_FEEDBACKS_BY_TITLE = "Sorted bugs by title \n%s";


    public ListSortByTitleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        ListingEnums command = ParsingHelpers.tryParseEnum(parameters.get(0), ListingEnums.class);

        return listing(command);
    }
    public String listing(ListingEnums command) {
        StringBuilder stringBuilder = new StringBuilder();

        switch (command) {
            case TASKS:
                List<Task> sortedTaskListByTitle = getTaskManagementSystemRepository().getSortedListOfTasksByTitle();
                for (Task task : sortedTaskListByTitle) {
                    stringBuilder.append(task.getAsString());
                }
                return String.format(SORT_LIST_TASK_BY_TITLE, stringBuilder);
            case STORIES:
                List<Story> sortedStoryListByTitle = getTaskManagementSystemRepository().getSortedListOfStoriesByTitle();
                for (Story story : sortedStoryListByTitle) {
                    stringBuilder.append(story.getAsString());
                }
                return String.format(SORT_LIST_STORIES_BY_TITLE, stringBuilder);

            case FEEDBACKS:
                List<Feedback> sortedFeedbackListByTitle = getTaskManagementSystemRepository().getSortedListOfFeedbackByTitle();
                for (Feedback feedback : sortedFeedbackListByTitle) {
                    stringBuilder.append(feedback.getAsString());
                }
                return String.format(SORT_LIST_FEEDBACKS_BY_TITLE, stringBuilder);

            case BUGS:
                List<Bug> sortedListOfBugsByTitle = getTaskManagementSystemRepository().getSortedListOfBugsByTitle();
                for (Bug bug : sortedListOfBugsByTitle) {
                    stringBuilder.append(bug.getAsString());
                }
                return String.format(SORT_LIST_BUGS_BY_TITLE, stringBuilder);
            default:
                throw new IllegalArgumentException("Command must be tasks, stories, bugs or feedbacks");
        }
    }
}
