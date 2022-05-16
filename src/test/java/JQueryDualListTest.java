import Tests.Helpers.ExercisesDifficulty;
import Tests.Helpers.JQueryDualListOptions;
import Tests.PageObjects.IntermediateExercises.AjaxFormSubmit;
import Tests.PageObjects.IntermediateExercises.JQueryDualList;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JQueryDualListTest extends BaseTest {


    @Tag("list")
    @Test
    void bothListsShouldBeMultiple() {
        //given
        //when
        choseExercise();
        JQueryDualList exerciseSite = new JQueryDualList(driver);
        //then
        assertTrue(exerciseSite.bothListAreMultiple());
    }

    @Tag("list")
    @Test
    void selectingOneOptionAndClickingOnAddButtonShouldMovingThisOptionToRightList() {
        //given
        //when
        choseExercise();
        JQueryDualList exerciseSite = new JQueryDualList(driver);
        exerciseSite
                .selectOptionOnLeftList(JQueryDualListOptions.Isis)
                .clickAddButton();
        //then
        assertAll(
                () -> assertThat(exerciseSite.getAmountOfOptionOnLeftList(), equalTo(14)),
                () -> assertThat(exerciseSite.getAmountOfOptionOnRightList(), equalTo(1)));
    }

    @Tag("list")
    @Test
    void selectingAFewOptionAndClickingOnAddButtonShouldMovingThisOptionToRightList() {
        //given
        //when
        choseExercise();
        JQueryDualList exerciseSite = new JQueryDualList(driver);
        exerciseSite
                .selectOptionOnLeftList(JQueryDualListOptions.Giovanna)
                .selectOptionOnLeftList(JQueryDualListOptions.MariaEduarda)
                .selectOptionOnLeftList(JQueryDualListOptions.Valentina)
                .clickAddButton();
        //then
        assertAll(
                () -> assertThat(exerciseSite.getAmountOfOptionOnLeftList(), equalTo(12)),
                () -> assertThat(exerciseSite.getAmountOfOptionOnRightList(), equalTo(3)));
    }

    @Tag("list")
    @Test
    void clickingAddButtonWithoutSelectedAnyOptionShouldNotMovingAnyOptionToRightList() {
        //given
        //when
        choseExercise();
        JQueryDualList exerciseSite = new JQueryDualList(driver);
        exerciseSite
                .clickAddButton();
        //then
        assertAll(
                () -> assertThat(exerciseSite.getAmountOfOptionOnLeftList(), equalTo(15)),
                () -> assertThat(exerciseSite.getAmountOfOptionOnRightList(), equalTo(0)));
    }

    @Tag("list")
    @Test
    void selectingAFewOptionAndClickingAddAllShouldMovingAllOptionToRightList() {
        //given
        //when
        choseExercise();
        JQueryDualList exerciseSite = new JQueryDualList(driver);
        exerciseSite
                .selectOptionOnLeftList(JQueryDualListOptions.Giovanna)
                .selectOptionOnLeftList(JQueryDualListOptions.MariaEduarda)
                .selectOptionOnLeftList(JQueryDualListOptions.Valentina)
                .clickAddAllButton();
        //then
        assertAll(
                () -> assertThat(exerciseSite.getAmountOfOptionOnLeftList(), equalTo(0)),
                () -> assertThat(exerciseSite.getAmountOfOptionOnRightList(), equalTo(15)));
    }

    @Tag("list")
    @Test
    void clickingAddAllButtonWithoutSelectedAnyOptionShouldMoveAllOptionsToRightList() {
        //given
        //when
        choseExercise();
        JQueryDualList exerciseSite = new JQueryDualList(driver);
        exerciseSite
                .clickAddAllButton();
        //then
        assertAll(
                () -> assertThat(exerciseSite.getAmountOfOptionOnLeftList(), equalTo(0)),
                () -> assertThat(exerciseSite.getAmountOfOptionOnRightList(), equalTo(15)));
    }

    @Tag("list")
    @Test
    void selectingOneOptionAndClickingOnRemoveButtonShouldMovingThisOptionToLeftList() {
        //given
        //when
        choseExercise();
        JQueryDualList exerciseSite = new JQueryDualList(driver);
        exerciseSite
                .clickAddAllButton()
                .selectOptionOnRightList(JQueryDualListOptions.Beatriz)
                .clickRemoveButton();
        //then
        assertAll(
                () -> assertThat(exerciseSite.getAmountOfOptionOnLeftList(), equalTo(1)),
                () -> assertThat(exerciseSite.getAmountOfOptionOnRightList(), equalTo(14)));
    }

    @Tag("list")
    @Test
    void selectingAFewOptionAndClickingOnRemoveButtonShouldMovingThisOptionToLeftList() {
        //given
        //when
        choseExercise();
        JQueryDualList exerciseSite = new JQueryDualList(driver);
        exerciseSite
                .clickAddAllButton()
                .selectOptionOnRightList(JQueryDualListOptions.Giovanna)
                .selectOptionOnRightList(JQueryDualListOptions.MariaEduarda)
                .selectOptionOnRightList(JQueryDualListOptions.Valentina)
                .clickRemoveButton();
        //then
        assertAll(
                () -> assertThat(exerciseSite.getAmountOfOptionOnLeftList(), equalTo(3)),
                () -> assertThat(exerciseSite.getAmountOfOptionOnRightList(), equalTo(12)));
    }


    @Tag("list")
    @Test
    void clickingRemoveButtonWithoutSelectedAnyOptionShouldNotMovingAnyOptionToLeftList() {
        //given
        //when
        choseExercise();
        JQueryDualList exerciseSite = new JQueryDualList(driver);
        exerciseSite
                .clickAddAllButton()
                .clickRemoveButton();
        //then
        assertAll(
                () -> assertThat(exerciseSite.getAmountOfOptionOnLeftList(), equalTo(0)),
                () -> assertThat(exerciseSite.getAmountOfOptionOnRightList(), equalTo(15)));
    }

    @Tag("list")
    @Test
    void clickingRemoveAllButtonWithoutSelectedAnyOptionShouldMoveAllOptionsToLeftList() {
        //given
        //when
        choseExercise();
        JQueryDualList exerciseSite = new JQueryDualList(driver);
        exerciseSite
                .clickAddAllButton()
                .clickRemoveAllButton();
        //then
        assertAll(
                () -> assertThat(exerciseSite.getAmountOfOptionOnLeftList(), equalTo(15)),
                () -> assertThat(exerciseSite.getAmountOfOptionOnRightList(), equalTo(0)));
    }

    @Tag("list")
    @Test
    void selectingAFewOptionAndClickingRemoveAllShouldMovingAllOptionToLeftList() {
        //given
        //when
        choseExercise();
        JQueryDualList exerciseSite = new JQueryDualList(driver);
        exerciseSite
                .clickAddAllButton()
                .selectOptionOnRightList(JQueryDualListOptions.Julia)
                .selectOptionOnRightList(JQueryDualListOptions.Manuela)
                .selectOptionOnRightList(JQueryDualListOptions.Julia)
                .clickRemoveAllButton();
        //then
        assertAll(
                () -> assertThat(exerciseSite.getAmountOfOptionOnLeftList(), equalTo(15)),
                () -> assertThat(exerciseSite.getAmountOfOptionOnRightList(), equalTo(0)));
    }

    private void choseExercise() {
        AjaxFormSubmit ajaxFormSubmit = new AjaxFormSubmit(driver);
        ajaxFormSubmit.goToExercise(ExercisesDifficulty.INTERMEDIATE, 4);
    }
}
