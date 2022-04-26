import Tests.Helpers.ExercisesDifficulty;
import Tests.PageObjects.IntermediateExercises.InputFormValidations;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputFormValidationsTests extends BaseTest {

    protected final String errorColor = "rgba(169, 68, 66, 1)";
    protected final String successColor = "rgba(60, 118, 61, 1)";
    protected final String defaultColor = "rgba(51, 51, 51, 1)";

    @Tag("input")
    @ParameterizedTest
    @CsvFileSource(resources = "/ValidationsFormValues.csv")
    void formsShouldHaveProperColorAndMessageAfterSendingText(int formIndex, String textToSend, boolean success, String errorMessage) {
        //given
        InputFormValidations exerciseSite = new InputFormValidations(driver);
        //when
        choseExercise();
        if (textToSend.equals("null")) {
            exerciseSite.chooseForm(formIndex).sendEmptyText();
        } else
            exerciseSite.chooseForm(formIndex).sendTextToValue(textToSend);
        //then
        if (success) {
            assertThat(exerciseSite.getFormNameColor(), equalTo(successColor));
        } else {
            assertAll(
                    () -> assertThat(exerciseSite.getErrorMessage(), equalTo(errorMessage)),
                    () -> assertThat(exerciseSite.getErrorMessageColor(), equalTo(errorColor)),
                    () -> assertThat(exerciseSite.getFormNameColor(), equalTo(errorColor))
            );
        }
    }

    @Tag("input")
    @Test
    void listShouldHaveProperColorAfterChoosingOption() {
        //given
        InputFormValidations exerciseSite = new InputFormValidations(driver);
        //when
        choseExercise();
        exerciseSite.selectState("Hawaii");
        //then
        assertThat(exerciseSite.getFormNameColor(6), equalTo(successColor));
    }

    @Tag("input")
    @Test
    void sendButtonShouldBeDisabledIfFormIsEmpty() {
        //given
        InputFormValidations exerciseSite = new InputFormValidations(driver);
        //when
        choseExercise();
        //then
        assertTrue(exerciseSite.sendButtonIsDisabled());
    }

    @Tag("input")
    @Test
    void afterSendingCorrectFormThereShouldBeDefaultFormNameColor() {
        //given
        int formNameWithCorrectColor = 0;
        InputFormValidations exerciseSite = new InputFormValidations(driver);
        //when
        choseExercise();
        for (Integer map : properValuesToForm().keySet()) {
            exerciseSite.chooseForm(map).sendTextToValue(properValuesToForm().get(map));
        }
        exerciseSite.selectState("Hawaii").selectHosting("yes").sendForm();
        for (int i = 0; i < 11; i++) {
            if (exerciseSite.getFormNameColor(i).equals(defaultColor)) formNameWithCorrectColor++;
        }
        //then
        assertThat(formNameWithCorrectColor, is(11));
    }

    private static HashMap<Integer, String> properValuesToForm() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(0, "name");
        hashMap.put(1, "surname");
        hashMap.put(2, "email@email.pl");
        hashMap.put(3, "1511111111");
        hashMap.put(4, "addressAddress");
        hashMap.put(5, "city");
        hashMap.put(7, "1111");
        hashMap.put(8, "website");
        hashMap.put(9, "description");
        return hashMap;
    }

    private void choseExercise() {
        InputFormValidations exerciseSite = new InputFormValidations(driver);
        exerciseSite.goToExercise(ExercisesDifficulty.INTERMEDIATE, 0);
    }
}


