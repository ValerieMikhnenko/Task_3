
package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import pageobjects.LoginPage;
import support.ApiClient;
import support.BaseTest;
import support.TestUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void userShouldBeAbleToLoginWithValidCredentials() throws Exception {
        ApiClient apiClient = new ApiClient();
        TestUser user = new TestUser();

        String accessToken = apiClient.createUser(user);

        try {
            LoginPage loginPage = new LoginPage(driver);

            loginPage.open();
            loginPage.enterEmail(user.getEmail());
            loginPage.enterPassword(user.getPassword());
            loginPage.clickLoginButton();

            assertTrue(
                    new org.openqa.selenium.support.ui.WebDriverWait(
                            driver, java.time.Duration.ofSeconds(10)
                    ).until(
                            org.openqa.selenium.support.ui.ExpectedConditions.urlToBe(
                                    "https://qa-stellarburgers.education-services.ru/"
                            )
                    ),
                    "После успешного входа должна открыться главная страница"
            );
        } finally {
            apiClient.deleteUser(accessToken);
        }
    }


    @Test
    public void userShouldBeAbleToLoginThroughAccountButton() throws Exception {
        ApiClient apiClient = new ApiClient();
        TestUser user = new TestUser();

        String accessToken = apiClient.createUser(user);

        try {
            pageobjects.MainPage mainPage = new pageobjects.MainPage(driver);
            LoginPage loginPage = new LoginPage(driver);

            mainPage.open();
            mainPage.clickAccountButton();

            loginPage.enterEmail(user.getEmail());
            loginPage.enterPassword(user.getPassword());
            loginPage.clickLoginButton();

            assertTrue(
                    new org.openqa.selenium.support.ui.WebDriverWait(
                            driver, java.time.Duration.ofSeconds(10)
                    ).until(
                            org.openqa.selenium.support.ui.ExpectedConditions.urlToBe(
                                    "https://qa-stellarburgers.education-services.ru/"
                            )
                    ),
                    "После входа через «Личный кабинет» должна открыться главная страница"
            );
        } finally {
            apiClient.deleteUser(accessToken);
        }
    }


    @Test
    public void userShouldBeAbleToLoginThroughRegistrationForm() throws Exception {
        ApiClient apiClient = new ApiClient();
        TestUser user = new TestUser();

        String accessToken = apiClient.createUser(user);

        try {
            LoginPage loginPage = new LoginPage(driver);
            pageobjects.RegistrationPage registrationPage =
                    new pageobjects.RegistrationPage(driver);

            loginPage.open();
            loginPage.clickRegisterLink();
            registrationPage.clickLoginLink();

            loginPage.enterEmail(user.getEmail());
            loginPage.enterPassword(user.getPassword());
            loginPage.clickLoginButton();

            assertTrue(
                    new org.openqa.selenium.support.ui.WebDriverWait(
                            driver, java.time.Duration.ofSeconds(10)
                    ).until(
                            org.openqa.selenium.support.ui.ExpectedConditions.urlToBe(
                                    "https://qa-stellarburgers.education-services.ru/"
                            )
                    ),
                    "После входа через форму регистрации должна открыться главная страница"
            );
        } finally {
            apiClient.deleteUser(accessToken);
        }
    }

    @Test
    public void userShouldBeAbleToLoginThroughForgotPasswordForm() throws Exception {
        ApiClient apiClient = new ApiClient();
        TestUser user = new TestUser();

        String accessToken = apiClient.createUser(user);

        try {
            LoginPage loginPage = new LoginPage(driver);
            pageobjects.ForgotPasswordPage forgotPasswordPage =
                    new pageobjects.ForgotPasswordPage(driver);

            loginPage.open();
            loginPage.clickForgotPasswordLink();
            forgotPasswordPage.clickLoginLink();

            loginPage.enterEmail(user.getEmail());
            loginPage.enterPassword(user.getPassword());
            loginPage.clickLoginButton();

            assertTrue(
                    new org.openqa.selenium.support.ui.WebDriverWait(
                            driver, java.time.Duration.ofSeconds(10)
                    ).until(
                            org.openqa.selenium.support.ui.ExpectedConditions.urlToBe(
                                    "https://qa-stellarburgers.education-services.ru/"
                            )
                    ),
                    "После входа через форму восстановления пароля должна открыться главная страница"
            );
        } finally {
            apiClient.deleteUser(accessToken);
        }
    }
}

