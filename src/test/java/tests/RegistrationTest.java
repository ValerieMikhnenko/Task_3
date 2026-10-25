
package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import pageobjects.RegistrationPage;
import support.BaseTest;
import support.TestUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {


    @Test
    public void successfulRegistrationShouldRedirectToLoginPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        TestUser user = new TestUser();

        registrationPage.open();
        registrationPage.enterName(user.getName());
        registrationPage.enterEmail(user.getEmail());
        registrationPage.enterPassword(user.getPassword());
        registrationPage.clickRegisterButton();

        assertTrue(
                new org.openqa.selenium.support.ui.WebDriverWait(
                        driver, java.time.Duration.ofSeconds(10)
                ).until(
                        org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login")
                ),
                "После успешной регистрации должна открыться страница входа"
        );
    }


    @Test
    public void registrationWithShortPasswordShouldShowError() {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.open();
        registrationPage.enterName("Test User");
        registrationPage.enterEmail("short_password_test@yandex.ru");
        registrationPage.enterPassword("123");
        registrationPage.clickRegisterButton();

        assertTrue(
                registrationPage.getErrorMessage().length() > 0,
                "При пароле короче 6 символов должно появиться сообщение об ошибке"
        );
    }
}
