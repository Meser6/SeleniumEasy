import Tests.Helpers.BootstrapLists;
import Tests.Helpers.ExercisesDifficulty;
import Tests.PageObjects.IntermediateExercises.BootstrapListBox;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BootstrapListBoxTests extends BaseTest {

    @Tag("list")
    @ParameterizedTest
    @EnumSource(BootstrapLists.class)
    void itPossibleToSelectOneElement(BootstrapLists list) {
        //given
        BootstrapListBox exerciseSite = new BootstrapListBox(driver);
        //when
        choseExercise();
        int selectedElements = exerciseSite
                .selectElement(list, 2)
                .getAmountOfSelectedElements();
        //then
        assertThat(selectedElements, is(1));

    }

    @Tag("list")
    @ParameterizedTest
    @EnumSource(BootstrapLists.class)
    void itPossibleToMoveOneElement(BootstrapLists list) {
        //given
        BootstrapListBox exerciseSite = new BootstrapListBox(driver);
        //when
        choseExercise();
        int elementsAtList = exerciseSite
                .selectElement(list, 2)
                .switchFromList(list)
                .getAmountOfElements(list);
        //then
        assertThat(elementsAtList, is(4));
    }

    @Tag("list")
    @ParameterizedTest
    @EnumSource(BootstrapLists.class)
    void itPossibleToSelectAllElementsByButton(BootstrapLists list) {
        //given
        BootstrapListBox exerciseSite = new BootstrapListBox(driver);
        //when
        choseExercise();
        int selectedElements = exerciseSite
                .selectAll(list)
                .getAmountOfSelectedElements();
        //then
        assertThat(selectedElements, is(5));
    }

    @Tag("list")
    @ParameterizedTest
    @EnumSource(BootstrapLists.class)
    void doubleClickOnSelectAllShouldDeselectAllElements(BootstrapLists list) {
        //given
        BootstrapListBox exerciseSite = new BootstrapListBox(driver);
        //when
        choseExercise();
        int selectedElements = exerciseSite
                .selectAll(list)
                .selectAll(list)
                .getAmountOfSelectedElements();
        //then
        assertThat(selectedElements, is(0));
    }

    @Tag("list")
    @ParameterizedTest
    @EnumSource(BootstrapLists.class)
    void itPossibleToMoveAllElements(BootstrapLists list) {
        //given
        BootstrapListBox exerciseSite = new BootstrapListBox(driver);
        //when
        choseExercise();
        int elementsAtList = exerciseSite
                .selectAll(list)
                .switchFromList(list)
                .getAmountOfElements(list);
        //then
        assertThat(elementsAtList, is(0));
    }

    @Tag("list")
    @ParameterizedTest
    @EnumSource(BootstrapLists.class)
    void itPossibleToFindElementByFullName(BootstrapLists list) {
        //given
        String elementName = "Vestibulum at eros";
        BootstrapListBox exerciseSite = new BootstrapListBox(driver);
        //when
        choseExercise();
        int elementsAtList = exerciseSite
                .findElement(list, elementName)
                .getAmountOfElements(list);
        //then
        assertThat(elementsAtList, is(1));
    }

    @Tag("list")
    @ParameterizedTest
    @EnumSource(BootstrapLists.class)
    void itPossibleToFindElementByPartName(BootstrapLists list) {
        //given
        String elementName = "ac";
        BootstrapListBox exerciseSite = new BootstrapListBox(driver);
        //when
        choseExercise();
        int elementsAtList = exerciseSite
                .findElement(list, elementName)
                .getAmountOfElements(list);
        //then
        assertThat(elementsAtList, is(2));

    }

    private void choseExercise() {
        BootstrapListBox exerciseSite = new BootstrapListBox(driver);
        exerciseSite.goToExercise(ExercisesDifficulty.INTERMEDIATE, 3);
    }

}