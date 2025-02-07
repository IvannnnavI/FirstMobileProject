package com.remindly.fw;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsActions;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ReminderHelper extends BaseHelper {
    public ReminderHelper(AppiumDriver driver) {
        super(driver);
    }

    public void enterReminderTitle(String title) {
        type(By.id("reminder_title"), title);
    }

    public void tapOnSaveReminder() {
        tap(By.id("save_reminder"));
    }

    public void tapOnDateField() {
        tap(By.id("date"));
    }

    public void swipeToMonth(String period, String month, int number) {
        pause(500);

        if (!getSelectedMonth().equals(month)) {
            for (int i = 0; i < number; i++) {
                if (period.equals("future")) {
                    swipe(0.8, 0.5);
                } else if (period.equals("past")) {
                    swipe(0.5, 0.8);

                }
            }
        }
    }

    private String getSelectedMonth() {
        return isTextPresent(By.id("date_picker_month"));
    }

    public void selectDate(int index) {
        List<WebElement> days = driver.findElements(By.className("android.view.View"));
        days.get(index).click();
    }

    public void tapOnYear() {
        tap(By.id("date_picker_year"));
    }

    public void swipeToYear(String period, String year) {
        pause(500);

        if (!getSelectedYear().equals(year)) {
            if (period.equals("future")) {
                swipeUntillNeededYear(year, 0.6, 0.5);
            } else if (period.equals("past")) {
                swipeUntillNeededYear(year, 0.5, 0.6);
            }

        }
        tap(By.id("month_text_view"));
    }

    private void swipeUntillNeededYear(String year, double startPoint, double stopPoint) {
        while (!getYear().equals(year)) {
            swipelnElement(By.className("android.widget.ListView"), startPoint, stopPoint);
            getYear();
        }
    }

    public void swipelnElement(By locator, double startPoint, double stopPoint) {
        Dimension size = driver.manage().window().getSize();

        int startY = (int) (size.height * startPoint);
        int stopY = (int) (size.height * stopPoint);

        WebElement element = driver.findElement(locator);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int middleX = (leftX + rightX) / 2;

        new TouchAction((PerformsTouchActions) driver).longPress(PointOption.point(middleX, startY))
                .moveTo(PointOption.point(middleX, stopY))
                .release().perform();
    }

    public String getYear() {
        return isTextPresent(By.id("month_text_view"));
    }

    private String getSelectedYear() {
        return isTextPresent(By.id("date_picker_year"));
    }

    public void tapOnOk() {
        tap(By.id("ok"));
    }

    public void tapTimeField() {
        tap(By.id("time"));
    }

    public void selectTime(String timeOfDay, int xHour, int yHour, int xMin, int yMin) {
        pause(500);
        if (timeOfDay.equals("am")) {
            tapWithCoordinates(277, 1332);
        } else if (timeOfDay.equals("pm")) {
            tapWithCoordinates(789, 1332);
        }

        tapWithCoordinates(xHour, yHour);
        tapWithCoordinates(xMin, yMin);


    }

    public void tapWithCoordinates(int x, int y) {
        new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(x, y))
                .release().perform();
    }

    public void tapOnRepeatButton() {
        tap(By.id("repeat_switch"));
    }

    public void tapOnRepetitionInterval() {
        tap(By.id("RepeatNo"));
    }

    public void addNumber(String number) {

        tap(By.className("android.widget.EditText"));
        pause(500);

        tapWithCoordinates(405, 1272);
        tapWithCoordinates(135, 1404);
    }

    public void tapOnOkRepetitioninterval() {
        tapWithCoordinates(878, 755);
    }

    public void tapOnTypeOfRepetition() {
        pause(500);
        swipe(0.8, 0.4);
        tap(By.id("RepeatType"));

    }

    public void tapOnType(String type) {
        pause(500);
        switch (type) {
            case "Minute":
                tapWithCoordinates(208, 709);
                break;
            case "Hour":
                tapWithCoordinates(205, 847);
                break;
            case "Day":
                tapWithCoordinates(205, 994);
                break;
            case "Week":
                tapWithCoordinates(205, 1140);
                break;
            case "Month":
                tapWithCoordinates(205, 1286);
                break;
            default:
                System.out.println("Unknown type: " + type);
        }
    }

}
