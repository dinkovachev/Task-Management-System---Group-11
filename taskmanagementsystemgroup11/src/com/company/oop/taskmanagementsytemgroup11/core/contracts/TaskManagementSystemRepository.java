package com.company.oop.taskmanagementsytemgroup11.core.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

import java.util.List;

public interface TaskManagementSystemRepository {

    List<Task> getAllTasks();

    List<Story> getAllStories();

    List<Bug> getAllBugs();

    List<Feedback> getAllFeedbacks();

    List<Members> getAllMembers();

    List<Team> getAllTeams();

    List<Board> getAllTeamsBoards();

    Members getMemberByUsername(String username);

    Team getTeamByName(String name);

    Board getBoardByName(String name);

    Task findTaskByID(int id);

    Members createMember(String firstName, String lastName);

    Team createTeam(String name);

    Board createBoard(String name);

    Comment createComment(String content, String author);

    Bug createBug(String title, String description, String stepsToReproduce, Priority priority,
                  Severity severity, String assignee, int taskIndexBug, String teamNameBug, String boardBug);

//    Bug createBug(TaskType type, String title, String description, String stepsToReproduce, Priority priority,
//                  Severity severity, String assignee, int taskIndexBug, String teamNameBug, String board);

    Story createStory(String title, String description, Priority priority, Size size, String assignee,
                      String teamNameStory, String boardStory);

    Feedback createFeedback(String title, String description, int rating, int index, String teamName, String boardName);

    boolean memberExistsInTeam(Members member, Team team);

    boolean memberExist(String memberName);

    boolean teamExist(String teamName);

    boolean boardExist(String name);

    public int getLastId();

    Story findStoryByTaskIndex(int taskIndex);

    Bug findBugByTaskIndex(int taskIndex);

    Feedback findFeedbackByTaskIndex(int taskIndex);

    void validateType(TaskType type, int index);

    List<Bug> getSortedListOfBugsByTitle();

    List<Task> getFilteredListOfTasksByTitle(String title);

    List<Task> getSortedListOfTasksByTitle();
}