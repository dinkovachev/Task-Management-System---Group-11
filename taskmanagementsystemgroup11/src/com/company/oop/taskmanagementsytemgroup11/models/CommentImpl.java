package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;

public class CommentImpl implements Comment {
    private String content;
    private String author;

    public CommentImpl(String content, String author) {
        setContent(content);
        setAuthor(author);
    }

    @Override
    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    private void setAuthor(String author) {
        this.author = author;
    }
}
