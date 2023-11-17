package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

import java.util.List;

public interface Feedback extends Task {

    public int getRating();

    public int getTaskIndex();

    void changeRating(int rating);

    List<Feedback> getFeedbacks();
}