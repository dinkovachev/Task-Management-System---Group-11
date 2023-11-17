package com.company.oop.taskmanagementsytemgroup11.exceptions;

import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

public class InvalidUserInputException extends RuntimeException {


    public InvalidUserInputException(String no, TaskType type) {
    }

    public InvalidUserInputException(String message) {
        super(message);
    }

}

