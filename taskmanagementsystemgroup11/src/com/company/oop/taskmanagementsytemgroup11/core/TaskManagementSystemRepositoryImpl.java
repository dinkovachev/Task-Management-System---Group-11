package com.company.oop.taskmanagementsytemgroup11.core;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.*;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.enums.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private static final String NO_SUCH_TEAM = "No such team with name %s.";
    private final static String NO_SUCH_MEMBER = "There is no user with username %s!";
    private static final String NO_SUCH_BOARD = "There is no such board with name %s";
    public static final String INVALID_TASK_INDEX_MSG = "Invalid task index.";
    private int lastId;
    private int lastMemberId;
    private final List<Members> members = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();
    private final List<Task> tasksWithAssignee = new ArrayList<>();

    public TaskManagementSystemRepositoryImpl() {
        lastId = 1;
        lastMemberId = 0;
    }

    @Override
    public List<Members> getAllMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Team> getAllTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Board> getAllTeamsBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public Members createMember(String firstName, String lastName) {
        int nextMemberId = lastMemberId + 1;
        Members member = new MembersImpl(nextMemberId, firstName, lastName);
        this.members.add(member);
        lastMemberId = nextMemberId;

        return member;
    }

    @Override
    public Team createTeam(String name) {
        Team team = new TeamImpl(name);
        this.teams.add(team);

        return team;
    }

    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        this.boards.add(board);

        return board;
    }

    @Override
    public Comment createComment(String content, String author) {
        return new CommentImpl(content, author);
    }

    @Override
    public Bug createBug(String title, String description, String stepsToReproduce, Priority priority, Severity severity, String assignee, int taskIndexBug, String teamNameBug, String boardBug) {
        Bug bug = new BugImpl(lastId, title, description, stepsToReproduce, priority, severity, assignee,
                teamNameBug, boardBug);
        ++lastId;
        this.bugs.add(bug);
        this.tasks.add(bug);
        this.tasksWithAssignee.add(bug);
        return bug;
    }


    @Override
    public Story createStory(String title, String description, Priority priority, Size size,
                             String assignee, String teamNameStory, String board) {
        Story story = new StoryImpl(lastId, title, description, priority, size, assignee, teamNameStory,
                board);
        ++lastId;
        this.stories.add(story);
        this.tasks.add(story);
        this.tasksWithAssignee.add(story);


        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating, int taskIndex, String teamName, String boardName) {
        Feedback feedback = new FeedbackImpl(lastId, title, description, rating, teamName, boardName);
        ++lastId;
        this.feedbacks.add(feedback);
        this.tasks.add(feedback);
        this.tasksWithAssignee.add(feedback);


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
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Task> getAllTasksWithAssignee() {
        return new ArrayList<>(tasksWithAssignee);
    }

    @Override
    public List<Story> getAllStories() {
        return new ArrayList<>(stories);
    }

    @Override
    public List<Bug> getAllBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    public void unnasignAssigneeFromTask(int id, String assignee) {
        for (Task task : tasks) {
            if (task.getId() == id && task.getAssignee().equals(assignee)) {
                task.changeAssignee("unassigned");
                break;
            }
        }
        for (Task task : tasksWithAssignee) {
            if (task.getId() == id && task.getAssignee().equals("unassigned")) {
                tasksWithAssignee.remove(task);
                break;
            }
        }
    }

    @Override
    public void assignAssigneToTask(int id, String assignee) {
        for (Task task : tasks) {
            if (task.getId() == id && task.getAssignee().equals("unassigned")) {
                task.changeAssignee(assignee);
                tasksWithAssignee.add(task);
                break;
            }
        }

    }

    @Override
    public Task findTaskByID(int taskIndex) {
        for (Task task : tasks) {
            if (task.getId() == taskIndex) {
                return task;
            }
        }
        throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
    }

    @Override
    public Story findStoryByTaskIndex(int taskIndex) {
        for (Story story : stories) {
            if (story.getId() == taskIndex) {
                return story;
            }
        }
        throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
    }

//    @Override
//    public TaskType validateTaskTypeEqualsInputType(TaskType type, int taskIndex) {
//        if (!type.equals(tasks.get(taskIndex).getType())) {
//            throw new IllegalArgumentException(format("%s is invalid task type.", type));
//        }
//        return type;
//    }

    @Override
    public Bug findBugByTaskIndex(int taskIndex) {

        for (Bug bug : bugs) {
            if (bug.getId() == taskIndex) {
                return bug;
            }
        }
        throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
    }

    @Override
    public Feedback findFeedbackByTaskIndex(int taskIndex) {
        for (Feedback feedback : feedbacks) {
            if (feedback.getId() == taskIndex) {
                return feedback;
            }
        }
        throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
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
    public int getLastId() {
        return this.lastId;
    }

    public void validateType(TaskType type, int index) {
        if (!tasks.get(index).getType().equals(type)) {
            throw new IllegalArgumentException("No such task type.");
        }
//        for (Task task : tasks) {
//            if (task.getId() == index) {
//                if (!task.getType().equals(type)) {
//        throw new IllegalArgumentException("No such task type.");
//                }
//            }
//        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskManagementSystemRepositoryImpl that = (TaskManagementSystemRepositoryImpl) o;
        return lastId == that.lastId
                && lastMemberId == that.lastMemberId
                && Objects.equals(members, that.members)
                && Objects.equals(teams, that.teams)
                && Objects.equals(boards, that.boards)
                && Objects.equals(bugs, that.bugs)
                && Objects.equals(stories, that.stories)
                && Objects.equals(feedbacks, that.feedbacks)
                && Objects.equals(tasks, that.tasks);

    }

    @Override
    public List<Bug> getSortedListOfBugsByTitle() {

        return bugs.stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .toList();
    }

    @Override
    public List<Story> getSortedListOfStoriesByTitle() {
        return stories.stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .toList();
    }

    @Override
    public List<Feedback> getSortedListOfFeedbackByTitle() {
        return feedbacks.stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .toList();
    }

    @Override
    public List<Task> getSortedListOfTasksByTitle() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .toList();
    }

    @Override
    public List<Task> getSortedListOfTasksWithAssigneeByTitle() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .toList();
    }

    public List<Bug> getFilteredBugListByStatus(Status status) {
        return bugs.stream()
                .filter(bug -> bug.getStatus().equals(status))
                .toList();
    }

    @Override
    public List<Task> getFilteredListOfTasksByTitle(String title) {

        return tasks.stream()
                .filter(task -> task.getTitle().contains(title))
                .toList();
    }

//    @Override
//    public List<Bug> getFilteredListOfBugsByAssignee(String assignee) {
//        return bugs.stream()
//                .filter(bug -> bug.getAssignee))
//                .toList();
//    }

//    @Override
//    public List<Bug> getFilteredListOfBugsByStatus(String status) {
//        return bugs.stream()
//                .filter(bug -> Boolean.parseBoolean(status))
//                .collect(Collectors.toList());
//                .toList();
//    }

    @Override
    public List<Bug> getSortedListOfBugsByPriority() {
        return bugs.stream()
                .sorted(Comparator.comparing(Bug::getPriority))
                .toList();
    }

    @Override
    public List<Story> getSortedListOfStoriesByPriority() {
        return stories.stream()
                .sorted(Comparator.comparing(Story::getPriority))
                .toList();
    }

    @Override
    public List<Feedback> getSortedListOfFeedbacksByRating() {
        return feedbacks.stream()
                .sorted(Comparator.comparing(Feedback::getRating))
                .toList();
    }


    @Override
    public List<Story> getSortedListOfStoriesBySize() {
        return stories.stream()
                .sorted(Comparator.comparing(Story::getSize))
                .toList();
    }

    @Override
    public List<Bug> getSortedListOfBugsBySeverity() {
        return bugs.stream()
                .sorted(Comparator.comparing(Bug::getPriority))
                .toList();
    }
}