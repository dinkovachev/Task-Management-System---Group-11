package com.company.oop.taskmanagementsytemgroup11.core.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

import java.util.List;

public interface TaskManagementSystemRepository {

    List<Members> getAllMembers();

    List<Team> getAllTeamMembers();

    Members createMember(String username, String firstName, String lastName);

    Team createTeam(String name);

    Board createBoard(String name);

    Comment createComment(String content, String author);

    //1. ToDo stepsToReproduce maybe need to be a List if we need to invoke it

    //2. ToDo Status status double check if we need to give Status to the bugs
    Bug createBug(int id, String title, String description, String stepsToReproduce,
                  Priority priority, Severity severity, Members members);
    //3. ToDo Status status double check if we need to give Status to the Stories
    Story createStory(int id, String title, String description, Priority priority, Size size, Members members);

    //4. ToDo Status status double check if we need to give Status to the Stories
    Feedback createFeedback(int id, String title, String description, int rating);

    // 5. ToDo maybe need to add function to find user by Username






}
