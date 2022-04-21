import Tests.Helpers.ExercisesDifficulty;
import Tests.PageObjects.BasicExercises.CheckBoxDemo;
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
        //when
        SimpleFormDemo exercisesSite = new SimpleFormDemo(driver);
        exercisesSite.goToMainSite().closeAdd().choseCategory(ExercisesDifficulty.BASIC).choseExercises(0);
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
        //when
        SimpleFormDemo exercisesSite = new SimpleFormDemo(driver);
        exercisesSite.goToMainSite().closeAdd().choseCategory(ExercisesDifficulty.BASIC).choseExercises(0);
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
        //when
        CheckBoxDemo exercisesSite = new CheckBoxDemo(driver);
        exercisesSite.goToMainSite().closeAdd().choseCategory(ExercisesDifficulty.BASIC).choseExercises(1);
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
        //when
        CheckBoxDemo exercisesSite = new CheckBoxDemo(driver);
        exercisesSite.goToMainSite().closeAdd().choseCategory(ExercisesDifficulty.BASIC).choseExercises(1);
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
        //when
        CheckBoxDemo exercisesSite = new CheckBoxDemo(driver);
        exercisesSite.goToMainSite().closeAdd().choseCategory(ExercisesDifficulty.BASIC).choseExercises(1);
        receivedMessage = exercisesSite.checkAllCheckbox().clickOption(3).getButtonText();
        amountOFCheckedOption = exercisesSite.getAmountOfCheckedOption();
        //then
        assertAll(
                () -> assertThat(receivedMessage, equalTo(expectedMessage)),
                () -> assertThat(3, equalTo(amountOFCheckedOption))
        );
    }
}
