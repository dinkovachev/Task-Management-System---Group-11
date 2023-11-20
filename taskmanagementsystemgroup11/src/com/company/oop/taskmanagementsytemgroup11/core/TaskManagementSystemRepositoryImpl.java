package com.company.oop.taskmanagementsytemgroup11.core;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.*;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private static final String NO_SUCH_TEAM = "No such team with name %s.";
    private final static String NO_SUCH_MEMBER = "There is no user with username %s!";
    private static final String NO_SUCH_BOARD = "There is no such board with name %s";
    public static final String INVALID_TASK_INDEX_MSG = "Invalid task index.";
    private int nextId;
    private int nextPersonId;
    private final List<Members> members = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();
    private final List<ActivityLog> activityLogList = new ArrayList<>();


    public TaskManagementSystemRepositoryImpl() {

        nextId = 0;
        nextPersonId = 0;
    }


    //ToDo - Dinko
    // finish the remaining methods

    @Override
    public List<Members> getAllMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Team> getAllTeams() {
        return new ArrayList<>(teams);
    }

    //ToDo double check how to display the teamBoards only not all boards
    @Override
    public List<Board> getAllTeamsBoards() {
        return new ArrayList<>(boards);
    }

    public List<ActivityLog> getAllActivities() {
        return new ArrayList<>(activityLogList);
    }


    @Override
    public Members createMember(String firstName, String lastName) {
        Members member = new MembersImpl(++nextPersonId, firstName, lastName);
        this.members.add(member);
//        addEventToActivityLogHistory(format("New member with name %s %s created", firstName, lastName));
        return member;
    }

    @Override
    public Team createTeam(String name) {
        Team team = new TeamImpl(name);
        this.teams.add(team);
//        addEventToActivityLogHistory(format("New team with name %s created", name));
        return team;
    }


    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        this.boards.add(board);
//        addEventToActivityLogHistory(format("New board with name %s created", name));
        return board;
    }

    @Override
    public Comment createComment(String content, String author) {
        return new CommentImpl(content, author);
    }


    @Override
    public Bug createBug(TaskType type, String title, String description, String stepsToReproduce, Priority priority,
                         Severity severity, String assignee, int taskIndexBug, String teamname, String board) {
        Bug bug = new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity, assignee,
                taskIndexBug, teamname, board);
        this.bugs.add(bug);
        this.feedbacks.add(null);
        this.stories.add(null);
        this.tasks.add(bug);
//        addEventToActivityLogHistory(format("New %s with title %s created",type, title));
        return bug;
    }

    @Override
    public Story createStory(TaskType type, String title, String description, Priority priority, Size size,
                             String assignee, int taskIndex, String teamname, String board) {
        Story story = new StoryImpl(++nextId, title, description, priority, size, assignee, taskIndex, teamname, board);
        this.stories.add(story);
        this.feedbacks.add(null);
        this.bugs.add(null);
        this.tasks.add(story);
//        addEventToActivityLogHistory(format("New %s with title %s created",type, title));
        return story;
    }
//todo това ми го поиска да се имплемнтира ей така от нищото

    // @Override
    // public Story findStoryByIndex(int storyIndex) {
    //    return null;
    //}

    @Override
    public Feedback createFeedback(TaskType type, String title, String description, int rating,
                                   int taskIndexFeedback, String teamname, String board) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating, taskIndexFeedback, teamname, board);
        this.feedbacks.add(feedback);
        this.stories.add(null);
        this.bugs.add(null);
        this.tasks.add(feedback);
//        addEventToActivityLogHistory(format("New %s with title %s created",type, title));
        return feedback;
    }

    @Override
    public Members getMemberByUsername(String username) {
        Members member = members.stream().filter(m -> m.getUsername().equalsIgnoreCase(username)).findFirst().
                orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_MEMBER, username)));
        return member;
    }

    @Override
    public Team getTeamByName(String name) {
        Team team = teams.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_TEAM, name)));
        return team;
    }

    @Override
    public Board getBoardByName(String name) {
        Board board = boards.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().
                orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_BOARD, name)));
        return board;
    }


    @Override
    public Members getMemberById(int id) {
        Members member = members.stream()
                .filter(b -> b.getAllTeamMembers().get(id).equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No such bug with index %d", id)));
        return member;
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    //    @Override
//    public Task findTaskByID(int id) {
//        Task task = tasks
//                .stream()
//                .filter(b -> b.getAllTasks().get(id).equals(id))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(String.format("No such bug with index %d", id)));
//        return task;
//    }

    @Override
    public Task findTaskByID(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
        } else {
            return tasks.get(taskIndex);
        }
    }


//    @Override
//    public Bug findBugByIndex(int bugIndex) {
//        Bug bug = bugs
//                .stream()
//                .filter(b -> b.getBugs().get(bugIndex).equals(bugIndex))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(String.format("No such bug with index %d", bugIndex)));
//        return bug;
//    }

//    @Override
//    public Bug findBugByIndex(int bugIndex) {
//        if (bugIndex < 0 || bugIndex >= tasks.size()) {
//            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
//        }
//        return tasks.get(tasks.size() - bugIndex - 1);
//    }
    // find team by name - teamMembers

    @Override
    public Story findStoryByTaskIndex(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
        }
        return stories.get(taskIndex);
    }

    @Override
    public TaskType validateTaskTypeEqualsInputType(TaskType type, int taskIndex) {
        if (!type.equals(tasks.get(taskIndex).getType())) {
            throw new IllegalArgumentException(format("%s is invalid task type.", type));
        }
        return type;
    }

    @Override
    public Bug findBugByTaskIndex(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
        }
        return bugs.get(taskIndex);
    }

    @Override
    public Feedback findFeedbackByTaskIndex(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
        }
        return feedbacks.get(taskIndex);
    }

//    @Override
//    public Story findStoryByIndex(int storyIndex) {
//        Story story = stories
//                .stream()
//                .filter(s -> s.getStories().get(storyIndex).equals(storyIndex))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(String.format("No such story with index %d", storyIndex)));
//        return story;
//    }

    @Override
    public Feedback findFeedbackByIndex(int feedbackIndex) {
        Feedback feedback = feedbacks
                .stream()
                .filter((f -> f.getFeedbacks().get(feedbackIndex).equals(feedbackIndex)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No such feedback with index %d", feedbackIndex)));

        return feedback;
    }

    @Override
    public boolean memberExistsInTeam(Members member, Team team) {
        return false;
    }

    @Override
    public boolean memberExist(String memberName) {
        boolean exists = false;
        for (Members members : getAllMembers()) {
            if (members.getUsername().equalsIgnoreCase(memberName)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public boolean teamExist(String name) {
        boolean exists = false;
        for (Team teams : getAllTeams()) {
            if (teams.getName().equalsIgnoreCase(name)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public boolean boardExist(String name) {
        boolean exists = false;
        for (Board boards : getAllTeamsBoards()) {
            if (boards.getName().equalsIgnoreCase(name)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public int getNextId() {
        return this.nextId;
    }

//    public void addEventToActivityLogHistory(String event) {
//        activityLogList.add(new ActivityLogImpl(event));
//    }


//    public String displayActivityLogHistory() {
//        StringBuilder result = new StringBuilder();
//        for (ActivityLog activityLog : activityLogList) {
//            result.append(activityLog.displayInfo()).append(System.lineSeparator());
//        }
//        return result.toString();
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskManagementSystemRepositoryImpl that = (TaskManagementSystemRepositoryImpl) o;
        return nextId == that.nextId && nextPersonId == that.nextPersonId && Objects.equals(members, that.members) && Objects.equals(teams, that.teams) && Objects.equals(boards, that.boards) && Objects.equals(bugs, that.bugs) && Objects.equals(stories, that.stories) && Objects.equals(feedbacks, that.feedbacks) && Objects.equals(tasks, that.tasks) && Objects.equals(activityLogList, that.activityLogList);
    }
}
