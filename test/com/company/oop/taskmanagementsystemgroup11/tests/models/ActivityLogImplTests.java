package com.company.oop.taskmanagementsystemgroup11.tests.models;

import com.company.oop.taskmanagementsytemgroup11.models.ActivityLogImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.ActivityLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActivityLogImplTests {

public static String VALID_ACTIVITY_LOG = "something happened";
public static String INVALID_ACTIVITY_LOG = "";

    @Test
    public void activityImpl_Should_ImplementCommentInterface() {
        // Arrange
        ActivityLog activityLog = initializeActivityLog();
        // Assert, Act
        Assertions.assertTrue(activityLog instanceof ActivityLogImpl);
}
    @Test
    public void constructor_Should_Create_ActivityLog_When_ParametersAreCorrect(){
        // Arrange
        ActivityLog activityLog = initializeActivityLog();
        //Assert, Act
        Assertions.assertEquals(VALID_ACTIVITY_LOG, activityLog.getActivityLog());
    }
    @Test
    public void constructor_Should_Throw_Exception_When_ActivityLogIsEmpty(){
         //Arrange, Assert, Act
        Assertions.assertThrows(IllegalArgumentException.class,() ->
                new ActivityLogImpl(INVALID_ACTIVITY_LOG));
    }
public static ActivityLogImpl initializeActivityLog() {
        return new ActivityLogImpl(
                VALID_ACTIVITY_LOG);
    }

}