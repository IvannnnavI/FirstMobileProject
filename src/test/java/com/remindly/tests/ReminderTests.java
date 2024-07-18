package com.remindly.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ReminderTests extends TestBase {
    @Test
    public void arrReminderTitlePositiveTest(){
        app.getMainScreen().tapOnAddReminder();
        app.getReminder().enterReminderTitle("Holiday");
        app.getReminder().tapOnSaveReminder();
        Assert.assertTrue(app.getMainScreen().isReminderTitlePresent().contains("Holiday"));
    }

    @Test
    public void addReminderWithDatePositiveTest(){
        app.getMainScreen().tapOnAddReminder();
        app.getReminder().enterReminderTitle("Holiday");
        app.getReminder().tapOnDateField();
        app.getReminder().swipeToMonth("future","SEP",2);
        app.getReminder().selectDate(0);
        app.getReminder().tapOnYear();
        app.getReminder().swipeToYear("future","2025");
        app.getReminder().tapOnOk();
        app.getReminder().tapOnSaveReminder();
        Assert.assertTrue(app.getMainScreen().isReminderDateTimePresent().contains("1/9/2025"));

    }

    @Test
    public void addReminderWithTimeTest(){
        app.getMainScreen().tapOnAddReminder();
        app.getReminder().enterReminderTitle("Holiday");
        app.getReminder().tapTimeField();
        app.getReminder().selectTime("am",539,1195,539,656);
        app.getReminder().tapOnOk();
        app.getReminder().tapOnSaveReminder();
        Assert.assertTrue(app.getMainScreen().isReminderDateTimePresent().contains("6:00"));

    }

    //Repeat off, Repetition interval и Type of Repetition
    @Test
    public void addReminderWithoutRepeat(){
        app.getMainScreen().tapOnAddReminder();
        app.getReminder().enterReminderTitle("Holiday");
        app.getReminder().tapTimeField();
        app.getReminder().selectTime("am",539,1195,539,656);
        app.getReminder().tapOnOk();
        app.getReminder().tapOnRepeatButton();
        app.getReminder().tapOnSaveReminder();
        Assert.assertTrue(app.getMainScreen().isReminderInfoOffRepeatPresent().contains("Repeat Off"));
    }

    @Test
    public void addRepetitionInterval(){
        app.getMainScreen().tapOnAddReminder();
        app.getReminder().enterReminderTitle("Holiday");
        app.getReminder().tapOnRepetitionInterval();
        app.getReminder().addNumber("24");
        app.getReminder().tapOnOkRepetitioninterval();
        app.getReminder().tapOnSaveReminder();
        Assert.assertTrue(app.getMainScreen().isReminderInfoOffRepeatPresent().contains("Every 24 Hour(s)"));

    }

    @Test
    public void addTypeOfRepetition(){
        app.getMainScreen().tapOnAddReminder();
        app.getReminder().enterReminderTitle("Holiday");

        app.getReminder().tapOnTypeOfRepetition();
        app.getReminder().tapOnType("Week");

        app.getReminder().tapOnSaveReminder();
        Assert.assertTrue(app.getMainScreen().isReminderInfoOffRepeatPresent().contains("Every 1 Week(s)"));

        }



}
