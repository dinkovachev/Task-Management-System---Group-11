package com.company.oop.taskmanagementsytemgroup11.core.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

import java.util.List;

public interface TaskManagementSystemRepository {

    List<Members> getAllMembers();

    List<Team> getAllTeams();

    List<Board> getAllTeamsBoards();

    Members getMemberById(int id);

    Members getMemberByUsername(String username);

    Team getTeamByName(String name);

    Board getBoardByName(String name);

    Task findTaskByID(int id);

    Members createMember(String firstName, String lastName);

    Team createTeam(String name);

    Board createBoard(String name);

    Comment createComment(String content, String author);

    // ToDo stepsToReproduce maybe need to be a List if we need to invoke it

    // ToDo Status status double check if we need to give Status to the bugs
    Bug createBug(TaskType type, String title, String description, String stepsToReproduce,
                  Priority priority, Severity severity, String assignee); // TODO Georgi take a look!

    //3. ToDo Status status double check if we need to give Status to the Stories
    Bug findBugByIndex(int bugIndex);


    Story createStory(TaskType type, String title, String description, Priority priority, Size size, String assignee);
    //4. ToDo Status status double check if we need to give Status to the Stories

    Story findStoryByIndex(int storyIndex);

    Feedback createFeedback(TaskType type, String title, String description, int rating);

    Feedback findFeedbackByIndex(int taskIndex);
    // 5. ToDo maybe need to add function to find user by Username

    boolean memberExistsInTeam(Members member, Team team);

    boolean teamExist(String teamName);  //todo

    boolean boardExist(String name);  //todo

    List<ActivityLog> getAllActivities();
}
