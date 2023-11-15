package com.company.oop.taskmanagementsytemgroup11.models.enums;

public enum TaskType {
    BUG,
    STORY,
    FEEDBACK;

    @Override
    public String toString() {
        switch (this) {
            case BUG:
                return "Small";
            case STORY:
                return "Medium";
            case FEEDBACK:
                return "Large";
            default:
                throw new UnsupportedOperationException("Can't convert task type.");
        }
    }
}
