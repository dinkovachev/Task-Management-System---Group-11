package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

public interface Feedback extends Task {
    public Status getStatus();

    public int getRating();


}
