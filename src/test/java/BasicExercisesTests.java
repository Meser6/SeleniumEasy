import Tests.Helpers.ExercisesDifficulty;
import Tests.PageObjects.BasicExercises.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class BasicExercisesTests extends BaseTest {

    @Tag("input")
    @Test
    void singleInputField() {
        //given
        String messageToSend = "Test01#";
        SimpleFormDemo exerciseSite = new SimpleFormDemo(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 0);
        String receivedMessage = exerciseSite
                .sendMessage(messageToSend)
                .clickOnShowMessageButton()
                .showReceivedMessage();
        //then
        assertThat(receivedMessage, containsString(messageToSend));
    }

    @Tag("input")
    @ParameterizedTest
    @CsvSource({"5, 10, 15", "x, 10, NaN", "x, y, NaN"})
    void twoInputFields(String a, String b, String sum) {
        //given
        SimpleFormDemo exerciseSite = new SimpleFormDemo(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 0);
        String receivedMessage = exerciseSite
                .sendValues(a, b)
                .clickOnGetTotalButton()
                .showTotalSum();
        //then
        assertThat(receivedMessage, containsString(sum));
    }

    @Tag("input")
    @Test
    void singleCheckboxDemo() {
        //given
        String expectedMessage = "Check box is checked";
        CheckBoxDemo exerciseSite = new CheckBoxDemo(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 1);
        String receivedMessage = exerciseSite
                .checkSingleCheckbox()
                .getMessage();
        //then
        assertThat(receivedMessage, containsString(expectedMessage));
    }

    @Tag("input")
    @Test
    void multipleCheckboxDemo() {
        //given
        String expectedMessage = "Uncheck All";
        CheckBoxDemo exerciseSite = new CheckBoxDemo(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 1);
        String receivedMessage = exerciseSite
                .checkAllCheckbox()
                .getButtonText();
        int amountOFCheckedOption = exerciseSite
                .getAmountOfCheckedOption();
        //then
        assertAll(
                () -> assertThat(receivedMessage, equalTo(expectedMessage)),
                () -> assertThat(4, equalTo(amountOFCheckedOption))
        );
    }

    @Tag("input")
    @Test
    void multipleCheckboxDemo2() {
        //given
        String expectedMessage = "Check All";
        CheckBoxDemo exerciseSite = new CheckBoxDemo(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 1);
        String receivedMessage = exerciseSite
                .checkAllCheckbox()
                .clickOption(3)
                .getButtonText();
        int amountOFCheckedOption = exerciseSite
                .getAmountOfCheckedOption();
        //then
        assertAll(
                () -> assertThat(receivedMessage, equalTo(expectedMessage)),
                () -> assertThat(3, equalTo(amountOFCheckedOption))
        );
    }

    @Tag("input")
    @ParameterizedTest
    @CsvSource({"Male", "Female"})
    void radioButtonDemo(String gender) {
        //given
        String expectedMessage = gender + "' is checked";
        RadioButtonsDemo exerciseSite = new RadioButtonsDemo(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 2);
        String receivedMessage = exerciseSite
                .radioSelectGender(gender)
                .getCheckedValue()
                .getRadioMessage();
        //when
        assertThat(receivedMessage, containsString(expectedMessage));
    }

    @Tag("input")
    @ParameterizedTest
    @CsvSource({"Male, 0 - 5", "Male, 5 - 15", "Male, 15 - 50", "Female, 0 - 5", "Female, 5 - 15", "Female, 15 - 50"})
    void groupRadioButtonsDemo(String gender, String age) {
        //given
        String expectedMessage = "Sex : " + gender + "\nAge group: " + age;
        RadioButtonsDemo exerciseSite = new RadioButtonsDemo(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 2);
        String receivedMessage = exerciseSite
                .groupRadioSelectGender(gender)
                .groupRadioSelectAge(age)
                .getValues()
                .getGroupRadioMessage();
        //when
        assertThat(receivedMessage, equalTo(expectedMessage));
    }

    @Tag("input")
    @Test
    void selectListDemo() {
        //given
        String selectedDay = "Wednesday";
        SelectDropdownList exerciseSite = new SelectDropdownList(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 3);
        String receivedMessage = exerciseSite
                .singleSelectDay(selectedDay)
                .getSingleMessage();
        //when
        assertThat(receivedMessage, containsString(selectedDay));
    }

    @Tag("input")
    @Test
    void multiSelectListDemo() {
        //given
        String firstSelectedOption = "California";
        String secondSelectedOption = "Ohio";
        String firstSelectExpectedMessage = "First selected option is :" + firstSelectedOption;
        String allSelectExpectedMessage = "Options selected are : " + firstSelectedOption + "," + secondSelectedOption;
        SelectDropdownList exerciseSite = new SelectDropdownList(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 3);
        exerciseSite
                .selectTwoOptions(firstSelectedOption, secondSelectedOption);
        //then
        assertAll(
                () -> assertThat(exerciseSite.getFirstSelectedMessage(), equalTo(firstSelectExpectedMessage)),
                () -> assertThat(exerciseSite.getAllSelectedMessage(), equalTo(allSelectExpectedMessage))
        );
    }

    @Tag("alerts")
    @Test
    void javaScriptAlertBox() {
        //given
        String expectedMessage = "I am an alert box!";
        JavascriptAlerts exerciseSite = new JavascriptAlerts(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 4);
        String receivedMessage = exerciseSite
                .initAlertBox()
                .getAlertText();
        //when
        assertThat(receivedMessage, equalTo(expectedMessage));
    }

    @Tag("alerts")
    @ParameterizedTest
    @CsvSource({"OK", "Cancel"})
    void javaScriptConfirmBox(String alert) {
        //given
        String expectedMessage = "You pressed " + alert;
        JavascriptAlerts exerciseSite = new JavascriptAlerts(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 4);
        String receivedMessage = exerciseSite
                .initConfirmBox()
                .acceptOrDismiss(alert)
                .getConfirmReceivedMessage();
        //then
        assertThat(receivedMessage, containsString(expectedMessage));
    }

    @Tag("alerts")
    @Test
    void javaScriptAlertBox2() {
        //given
        String messageToSend = "Test01#";
        JavascriptAlerts exerciseSite = new JavascriptAlerts(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 4);
        String receivedMessage = exerciseSite
                .initPromptBox()
                .sendKeysToAlert(messageToSend)
                .acceptOrDismiss("OK")
                .getPromptReceivedMessage();
        //then
        assertThat(receivedMessage, containsString(messageToSend));
    }

    @Tag("alerts")
    @ParameterizedTest
    @CsvSource({"twitter, https://twitter.com/intent/follow?screen_name=seleniumeasy",
            "facebook, https://www.facebook.com/seleniumeasy"})
    void singleWindowPopup(String button, String expectedUrl) {
        //given
        WindowPopupModal exerciseSite = new WindowPopupModal(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 5);
        String receivedUrl = exerciseSite
                .clickSingleWindowPopupButton(button)
                .getSecondCardURL();
        //then
        assertThat(receivedUrl, equalTo(expectedUrl));
    }

    @Tag("alerts")
    @ParameterizedTest
    @ValueSource(ints = {3, 4})
    void multipleWindowModal(int expectedPageAmount) {
        //given
        WindowPopupModal exerciseSite = new WindowPopupModal(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 5);
        int receivedPageAmount = exerciseSite
                .clickMultiWindowPopupButton(expectedPageAmount)
                .getPageAmount();
        //then
        assertThat(receivedPageAmount, equalTo(expectedPageAmount));
    }

    @Tag("alerts")
    @ParameterizedTest
    @CsvFileSource(resources = "/AlertsParameters.csv")
    void bootstrapAlertMessages(String alertCategory, String expectedFontColor, String expectedBackgroundColor,
                                String expectedBorderColor) {
        //given
        String expectedAlertMessage = "normal " + alertCategory + " message.";
        BootstrapAlerts exerciseSite = new BootstrapAlerts(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 6);
        exerciseSite
                .clickAndGetBoxParameters(false, alertCategory);
        //then
        assertAll(
                () -> assertThat(exerciseSite.boxAlertMessage, containsString(expectedAlertMessage)),
                () -> assertThat(exerciseSite.boxFontColor, equalTo(expectedFontColor)),
                () -> assertThat(exerciseSite.boxBackgroundColor, equalTo(expectedBackgroundColor)),
                () -> assertThat(exerciseSite.boxBorderColor, equalTo(expectedBorderColor)),
                () -> assertTrue(exerciseSite.closeAlarmBoxIsDisplayed())
        );
    }

    @Tag("alerts")
    @ParameterizedTest
    @CsvFileSource(resources = "/AlertsParameters.csv")
    void bootstrapAlertMessages2(String alertCategory, String expectedFontColor, String expectedBackgroundColor,
                                 String expectedBorderColor, int displayedTime) {
        //given
        String expectedAlertTypeMessage = "autocloseable " + alertCategory + " message.";
        String expectedAlertTimeMessage = "hide in " + displayedTime + " seconds.";
        BootstrapAlerts exerciseSite = new BootstrapAlerts(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 6);
        exerciseSite
                .clickAndGetBoxParameters(true, alertCategory);
        //then
        assertAll(
                () -> assertFalse(exerciseSite.buttonIsEnabled(true, alertCategory)),
                () -> assertThat(exerciseSite.boxAlertMessage, containsString(expectedAlertTypeMessage)),
                () -> assertThat(exerciseSite.boxAlertMessage, containsString(expectedAlertTimeMessage)),
                () -> assertThat(exerciseSite.boxFontColor, equalTo(expectedFontColor)),
                () -> assertThat(exerciseSite.boxBackgroundColor, equalTo(expectedBackgroundColor)),
                () -> assertThat(exerciseSite.boxBorderColor, equalTo(expectedBorderColor)),
                () -> assertTrue(exerciseSite.boxIsInvisibility(true, alertCategory, displayedTime)),
                () -> assertTrue(exerciseSite.buttonIsEnabled(true, alertCategory))
        );
    }

    @Tag("alerts")
    @Test
    void singleModalExample() {
        //given
        BootstrapModals exerciseSite = new BootstrapModals(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 7);
        //then
        Assertions.assertDoesNotThrow(
                () -> exerciseSite.lunchSingleModal().saveChanges(0).checkRefreshing());
    }

    @Tag("alerts")
    @Test
    void multipleModalExample() {
        //given
        BootstrapModals exerciseSite = new BootstrapModals(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 7);
        //then
        Assertions.assertDoesNotThrow(
                () -> exerciseSite.lunchMultiModal().saveChanges(1).checkRefreshing());
    }

    @Tag("alerts")
    @Test
    void multipleModalExample2() {
        //given
        BootstrapModals exerciseSite = new BootstrapModals(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 7);
        //then
        Assertions.assertDoesNotThrow(
                () -> exerciseSite.lunchMultiModal().lunchSecondModal().saveChanges(2).checkRefreshing());
    }
}
