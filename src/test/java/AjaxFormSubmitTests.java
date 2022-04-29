import Tests.Helpers.ExercisesDifficulty;
import Tests.PageObjects.IntermediateExercises.AjaxFormSubmit;
import Tests.PageObjects.IntermediateExercises.InputFormValidations;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class AjaxFormSubmitTests extends BaseTest {

    private final String name = "name";
    private final String description = "description";
    private final String successMessage = "Form submited Successfully!";

    @Test
    void itIsPossibleToSendFormWithNameOnly() {
        //given
        AjaxFormSubmit exerciseSite = new AjaxFormSubmit(driver);
        //when
        choseExercise();
        String receivedMessage = exerciseSite.sendTextToNameForm(name).submitForm().getMessage();
        //then
        assertThat(receivedMessage, equalTo(successMessage));
    }

    @Test
    void itIsPossibleToSendFormWithBothText() {
        //given
        AjaxFormSubmit exerciseSite = new AjaxFormSubmit(driver);
        //when
        choseExercise();
        String receivedMessage = exerciseSite.sendTextToNameForm(name).sendTextToDescriptionForm(description).submitForm().getMessage();
        //then
        assertThat(receivedMessage, equalTo(successMessage));
    }

    @Test
    void itIsImpossibleToSendFormWithDescriptionOnly() {
        //given
        AjaxFormSubmit exerciseSite = new AjaxFormSubmit(driver);
        //when
        choseExercise();
        exerciseSite.sendTextToDescriptionForm(description).submitForm();
        //then
        assertAll(
                () -> assertThat(exerciseSite.getNameFormBorderColor(), equalTo("border: 1px solid rgb(255, 0, 0);")),
                () -> assertTrue(exerciseSite.getSubmitButtonElement().isDisplayed())
        );
    }


    private void choseExercise() {
        InputFormValidations exerciseSite = new InputFormValidations(driver);
        exerciseSite.goToExercise(ExercisesDifficulty.INTERMEDIATE, 1);
    }
}
