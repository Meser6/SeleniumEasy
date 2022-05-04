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

    private final String errorColor = "rgba(169, 68, 66, 1)";
    private final String successColor = "rgba(60, 118, 61, 1)";
    private final String defaultColor = "rgba(51, 51, 51, 1)";

    @Tag("input")
    @ParameterizedTest
    @CsvFileSource(resources = "/SuccessValidationsFormValues.csv")
    void successFormsShouldHaveProperColorAndMessageAfterSendingText(int formIndex, String textToSend) {
        //given
        InputFormValidations inputFormValidations = new InputFormValidations(driver);
        //when
        choseExercise();
        inputFormValidations
                .chooseForm(formIndex)
                .sendTextToValue(textToSend);
        //then
        assertThat(inputFormValidations.getFormNameColor(), equalTo(successColor));

    }

    @Tag("input")
    @ParameterizedTest
    @CsvFileSource(resources = "/FailedValidationsFormValues.csv")
    void filedFormsShouldHaveProperColorAndMessageAfterSendingText(int formIndex, String textToSend,
                                                                   String errorMessage) {
        //given
        InputFormValidations inputFormValidations = new InputFormValidations(driver);
        //when
        choseExercise();
        if (textToSend.equals("null")) {
            inputFormValidations
                    .chooseForm(formIndex)
                    .sendEmptyText();
        } else {
            inputFormValidations
                    .chooseForm(formIndex)
                    .sendTextToValue(textToSend);
        }
        //then
        assertAll(
                () -> assertThat(inputFormValidations.getErrorMessage(), equalTo(errorMessage)),
                () -> assertThat(inputFormValidations.getErrorMessageColor(), equalTo(errorColor)),
                () -> assertThat(inputFormValidations.getFormNameColor(), equalTo(errorColor))
        );
    }


    @Tag("input")
    @Test
    void listShouldHaveProperColorAfterChoosingOption() {
        //given
        InputFormValidations inputFormValidations = new InputFormValidations(driver);
        //when
        choseExercise();
        inputFormValidations
                .selectState("Hawaii");
        //then
        assertThat(inputFormValidations.getFormNameColor(6), equalTo(successColor));
    }

    @Tag("input")
    @Test
    void sendButtonShouldBeDisabledIfFormIsEmpty() {
        //given
        InputFormValidations inputFormValidations = new InputFormValidations(driver);
        //when
        choseExercise();
        //then
        assertTrue(inputFormValidations.sendButtonIsDisabled());
    }

    @Tag("input")
    @Test
    void afterSendingCorrectFormThereShouldBeDefaultFormNameColor() {
        //given
        int amountOfFormNameWithCorrectColor = 0;
        InputFormValidations inputFormValidations = new InputFormValidations(driver);
        //when
        choseExercise();
        for (Integer map : properValuesToForm().keySet()) {
            inputFormValidations
                    .chooseForm(map)
                    .sendTextToValue(properValuesToForm()
                            .get(map));
        }
        inputFormValidations
                .selectState("Hawaii")
                .selectHosting("yes")
                .sendForm();
        for (int i = 0; i < 11; i++) {
            if (inputFormValidations.getFormNameColor(i).equals(defaultColor)) {
                amountOfFormNameWithCorrectColor++;
            }
        }
        //then
        assertThat(amountOfFormNameWithCorrectColor, is(11));
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
        InputFormValidations inputFormValidations = new InputFormValidations(driver);
        inputFormValidations.goToExercise(ExercisesDifficulty.INTERMEDIATE, 0);
    }
}


