package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

public class MembersImpl implements Members {
    private static final int MINIMUM_SYMBOLS = 5;
    private static final int MAXIMUM_SYMBOLS = 15;
    public static final String MEMBER_NAME_ERR_MSG = String.format(
            "The Team name's length cannot be less than %d or more than %d symbols long.",
            MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS);
    private String username;
    private String firstName;
    private String lastName;
    private int personId;

    public MembersImpl(int personId, String firstName, String lastName) {
        setPersonId(personId);
        setUsername(generateUsername(personId, firstName, lastName));
        setFirstName(firstName);
        setLastName(lastName);
    }
    @Override
    public String getUsername() {
        return username;
       }

    @Override
    public void addComment(Comment commentToAdd, Task taskToAddComment) {

    }

    @Override
    public int getPersonId() {
        return personId;
    }

    private void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setUsername(String username) {
        ValidationHelpers.validateIntRange(username.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, MEMBER_NAME_ERR_MSG);

        this.username = username;
    }

    private void setFirstName(String firstName) {
        //      ValidationHelpers.validateIntRange(firstName.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, MEMBER_NAME_ERR_MSG);
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        //       ValidationHelpers.validateIntRange(lastName.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, MEMBER_NAME_ERR_MSG);
        this.lastName = lastName;
    }

    private String generateUsername(int personId, String firstName, String lastName) {
        return firstName + lastName +personId;
    }

    @Override
    public String getAsString() {
        return  "member"; //todo print;
    }
}
