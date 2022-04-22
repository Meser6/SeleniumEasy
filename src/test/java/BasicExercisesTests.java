import Tests.Helpers.ExercisesDifficulty;
import Tests.PageObjects.BasicExercises.CheckBoxDemo;
import Tests.PageObjects.BasicExercises.RadioButtonsDemo;
import Tests.PageObjects.BasicExercises.SelectDropdownList;
import Tests.PageObjects.BasicExercises.SimpleFormDemo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BasicExercisesTests extends BaseTest {

    @Tag("input")
    @Test
    void singleInputField() {
        //given
        String messageToSend = "Test01#";
        String expectedMessage = "Your Message: " + messageToSend;
        String receivedMessage;
        SimpleFormDemo exercisesSite = new SimpleFormDemo(driver);
        //when
        exercisesSite.goToExercise(ExercisesDifficulty.BASIC, 0);
        receivedMessage = exercisesSite.sendMessage(messageToSend).clickOnShowMessageButton().showReceivedMessage();
        //then
        assertThat(receivedMessage, equalTo(expectedMessage));
    }

    @Tag("input")
    @ParameterizedTest
    @CsvSource({"5, 10, 15", "x, 10, NaN", "x, y, NaN"})
    void twoInputFields(String a, String b, String sum) {
        //given
        String expectedMessage = "Total a + b = " + sum;
        String receivedMessage;
        SimpleFormDemo exercisesSite = new SimpleFormDemo(driver);
        //when
        exercisesSite.goToExercise(ExercisesDifficulty.BASIC, 0);
        receivedMessage = exercisesSite.sendValues(a, b).clickOnGetTotalButton().showTotalSum();
        //then
        assertThat(receivedMessage, equalTo(expectedMessage));
    }

    @Tag("input")
    @Test
    void singleCheckboxDemo() {
        //given
        String expectedMessage = "Success - Check box is checked";
        String receivedMessage;
        CheckBoxDemo exercisesSite = new CheckBoxDemo(driver);
        //when
        exercisesSite.goToExercise(ExercisesDifficulty.BASIC, 1);
        receivedMessage = exercisesSite.checkSingleCheckbox().getMessage();
        //then
        assertThat(receivedMessage, equalTo(expectedMessage));
    }

    @Tag("input")
    @Test
    void multipleCheckboxDemo() {
        //given
        String expectedMessage = "Uncheck All";
        String receivedMessage;
        int amountOFCheckedOption;
        CheckBoxDemo exercisesSite = new CheckBoxDemo(driver);
        //when
        exercisesSite.goToExercise(ExercisesDifficulty.BASIC, 1);
        receivedMessage = exercisesSite.checkAllCheckbox().getButtonText();
        amountOFCheckedOption = exercisesSite.getAmountOfCheckedOption();
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
        String receivedMessage;
        int amountOFCheckedOption;
        CheckBoxDemo exercisesSite = new CheckBoxDemo(driver);
        //when
        exercisesSite.goToExercise(ExercisesDifficulty.BASIC, 1);
        ;
        receivedMessage = exercisesSite.checkAllCheckbox().clickOption(3).getButtonText();
        amountOFCheckedOption = exercisesSite.getAmountOfCheckedOption();
        //then
        assertAll(
                () -> assertThat(receivedMessage, equalTo(expectedMessage)),
                () -> assertThat(3, equalTo(amountOFCheckedOption))
        );
    }

    @Tag("iput")
    @ParameterizedTest
    @CsvSource({"Male", "Female"})
    void radioButtonDemo(String gender) {
        //given
        String expectedMessage = "Radio button '" + gender + "' is checked";
        String receivedMessage;
        RadioButtonsDemo exercisesSite = new RadioButtonsDemo(driver);
        //when
        exercisesSite.goToExercise(ExercisesDifficulty.BASIC, 2);
        receivedMessage = exercisesSite.radioSelectGender(gender).getCheckedValue().getRadioMessage();
        //when
        assertThat(receivedMessage, equalTo(expectedMessage));
    }

    @Tag("input")
    @ParameterizedTest
    @CsvSource({"Male, 0 - 5", "Male, 5 - 15", "Male, 15 - 50", "Female, 0 - 5", "Female, 5 - 15", "Female, 15 - 50"})
    void groupRadioButtonsDemo(String gender, String age) {
        //given
        String expectedMessage = "Sex : " + gender + "\nAge group: " + age;
        String receivedMessage;

        RadioButtonsDemo exerciseSite = new RadioButtonsDemo(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 2);
        receivedMessage = exerciseSite.groupRadioSelectGender(gender).groupRadioSelectAge(age).getValues().getGroupRadioMessage();
        //when
        assertThat(receivedMessage, equalTo(expectedMessage));
    }

    @Tag("input")
    @Test
    void selectListDemo() {
        //given
        String selectedDay = "Wednesday";
        String expectedMessage = "Day selected :- " + selectedDay;
        String receivedMessage;
        SelectDropdownList exerciseSite = new SelectDropdownList(driver);
        //when
        exerciseSite.goToExercise(ExercisesDifficulty.BASIC, 3);
        receivedMessage = exerciseSite.singleSelectDay(selectedDay).getSingleMessage();
        //when
        assertThat(receivedMessage, equalTo(expectedMessage));
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
        exerciseSite.selectTwoOptions(firstSelectedOption, secondSelectedOption);
        //then
        assertAll(
                () -> {
                    String firstSelectReceivedMessage = exerciseSite.getFirstSelectedMessage();
                    assertThat(firstSelectReceivedMessage, equalTo(firstSelectExpectedMessage));
                },
                () -> {
                    String allSelectReceivedMessage = exerciseSite.getAllSelectedMessage();
                    assertThat(allSelectReceivedMessage, equalTo(allSelectExpectedMessage));
                }
        );
    }

}
