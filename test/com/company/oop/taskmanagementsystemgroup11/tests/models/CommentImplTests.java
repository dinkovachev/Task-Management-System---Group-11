package com.company.oop.taskmanagementsystemgroup11.tests.models;

import com.company.oop.taskmanagementsytemgroup11.models.CommentImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentImplTests {

    public static String VALID_CONTENT = "comment";
    public static String VALID_AUTHOR = "Dinko_Kovachev1";


    @Test
    public void commentImpl_Should_ImplementCommentInterface() {
        // Arrange, Act
        Comment comment = initializeTestComment();
        // Assert
        Assertions.assertTrue(comment instanceof Comment);
    }

    @Test
    public void constructor_Should_CreateNewComment_When_ValidContent(){
        // Arrange, Act
        Comment comment = initializeTestComment();
        // Assert
        Assertions.assertEquals(VALID_CONTENT, comment.getContent());
    }
     @Test
    public void constructor_Should_CreateNewComment_When_ValidAuthor(){
        // Arrange, Act
        Comment comment = initializeTestComment();
        // Assert
        Assertions.assertEquals(VALID_AUTHOR, comment.getAuthor());
    }

    public static CommentImpl initializeTestComment() {
        return new CommentImpl(
                VALID_CONTENT,
                VALID_AUTHOR);
    }
}
