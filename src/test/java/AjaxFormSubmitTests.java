import Tests.Helpers.ExercisesDifficulty;
import Tests.PageObjects.IntermediateExercises.AjaxFormSubmit;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AjaxFormSubmitTests extends BaseTest {

    private final String name = "name";
    private final String description = "description";
    private final String successMessage = "Form submited Successfully!";

    @Test
    void itIsPossibleToSendFormWithNameOnly() {
        //given
        AjaxFormSubmit ajaxFormSubmit = new AjaxFormSubmit(driver);
        //when
        choseExercise();
        String receivedMessage = ajaxFormSubmit
                .sendTextToNameForm(name)
                .submitForm()
                .getMessage();
        //then
        assertThat(receivedMessage, equalTo(successMessage));
    }

    @Test
    void itIsPossibleToSendFormWithBothText() {
        //given
        AjaxFormSubmit ajaxFormSubmit = new AjaxFormSubmit(driver);
        //when
        choseExercise();
        String receivedMessage = ajaxFormSubmit
                .sendTextToNameForm(name)
                .sendTextToDescriptionForm(description)
                .submitForm()
                .getMessage();
        //then
        assertThat(receivedMessage, equalTo(successMessage));
    }

    @Test
    void itIsImpossibleToSendFormWithDescriptionOnly() {
        //given
        AjaxFormSubmit ajaxFormSubmit = new AjaxFormSubmit(driver);
        //when
        choseExercise();
        ajaxFormSubmit
                .sendTextToDescriptionForm(description)
                .submitForm();
        //then
        assertAll(
                () -> assertThat(ajaxFormSubmit.getNameFormBorderColor(),
                        equalTo("border: 1px solid rgb(255, 0, 0);")),
                () -> assertTrue(ajaxFormSubmit.getSubmitButtonElement().isDisplayed())
        );
    }


    private void choseExercise() {
        AjaxFormSubmit ajaxFormSubmit = new AjaxFormSubmit(driver);
        ajaxFormSubmit.goToExercise(ExercisesDifficulty.INTERMEDIATE, 1);
    }
}
