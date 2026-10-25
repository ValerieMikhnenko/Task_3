
package tests;

import org.junit.jupiter.api.Test;
import pageobjects.MainPage;
import support.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    public void bunsTabShouldBeSelectedByDefault() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        assertTrue(mainPage.getBunsTab().getAttribute("class").contains("tab_type_current"),
                "По умолчанию должна быть выбрана вкладка «Булки»");
    }

    @Test
    public void saucesTabShouldBeSelectedAfterClick() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSaucesTab();

        assertTrue(mainPage.getSaucesTab().getAttribute("class").contains("tab_type_current"),
                "После нажатия должна быть выбрана вкладка «Соусы»");
    }

    @Test
    public void fillingsTabShouldBeSelectedAfterClick() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFillingsTab();

        assertTrue(mainPage.getFillingsTab().getAttribute("class").contains("tab_type_current"),
                "После нажатия должна быть выбрана вкладка «Начинки»");
    }
}



