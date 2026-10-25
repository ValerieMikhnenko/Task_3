package tests;

import org.junit.jupiter.api.Test;
import pageobjects.AccountPage;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import support.ApiClient;
import support.BaseTest;
import support.TestUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest extends BaseTest {

    @Test
    public void userShouldBeAbleToOpenAccount() throws Exception {
        ApiClient apiClient = new ApiClient();
        TestUser user = new TestUser();

        String accessToken = apiClient.createUser(user);

        try {
            LoginPage loginPage = new LoginPage(driver);
            MainPage mainPage = new MainPage(driver);

            loginPage.open();
            loginPage.enterEmail(user.getEmail());
            loginPage.enterPassword(user.getPassword());
            loginPage.clickLoginButton();

            mainPage.clickAccountButton();

            assertTrue(
                    new org.openqa.selenium.support.ui.WebDriverWait(
                            driver, java.time.Duration.ofSeconds(10)
                    ).until(
                            org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/account")
                    ),
                    "После нажатия «Личный кабинет» должна открыться страница аккаунта"
            );
        } finally {
            apiClient.deleteUser(accessToken);
        }
    }

    @Test
    public void userShouldBeAbleToReturnToConstructorByButton() throws Exception {
        ApiClient apiClient = new ApiClient();
        TestUser user = new TestUser();

        String accessToken = apiClient.createUser(user);

        try {
            LoginPage loginPage = new LoginPage(driver);
            MainPage mainPage = new MainPage(driver);

            loginPage.open();
            loginPage.enterEmail(user.getEmail());
            loginPage.enterPassword(user.getPassword());
            loginPage.clickLoginButton();

            mainPage.clickAccountButton();
            mainPage.clickConstructorButton();

            assertTrue(
                    new org.openqa.selenium.support.ui.WebDriverWait(
                            driver, java.time.Duration.ofSeconds(10)
                    ).until(
                            org.openqa.selenium.support.ui.ExpectedConditions.urlToBe(
                                    "https://qa-stellarburgers.education-services.ru/"
                            )
                    ),
                    "После нажатия «Конструктор» должна открыться главная страница"
            );
        } finally {
            apiClient.deleteUser(accessToken);
        }
    }

    @Test
    public void userShouldBeAbleToReturnToConstructorByLogo() throws Exception {
        ApiClient apiClient = new ApiClient();
        TestUser user = new TestUser();

        String accessToken = apiClient.createUser(user);

        try {
            LoginPage loginPage = new LoginPage(driver);
            MainPage mainPage = new MainPage(driver);

            loginPage.open();
            loginPage.enterEmail(user.getEmail());
            loginPage.enterPassword(user.getPassword());
            loginPage.clickLoginButton();

            mainPage.clickAccountButton();
            mainPage.clickLogo();

            assertTrue(
                    new org.openqa.selenium.support.ui.WebDriverWait(
                            driver, java.time.Duration.ofSeconds(10)
                    ).until(
                            org.openqa.selenium.support.ui.ExpectedConditions.urlToBe(
                                    "https://qa-stellarburgers.education-services.ru/"
                            )
                    ),
                    "После нажатия на логотип должна открыться главная страница"
            );
        } finally {
            apiClient.deleteUser(accessToken);
        }
    }

    @Test
    public void userShouldBeAbleToLogout() throws Exception {
        ApiClient apiClient = new ApiClient();
        TestUser user = new TestUser();

        String accessToken = apiClient.createUser(user);

        try {
            LoginPage loginPage = new LoginPage(driver);
            MainPage mainPage = new MainPage(driver);
            AccountPage accountPage = new AccountPage(driver);

            loginPage.open();
            loginPage.enterEmail(user.getEmail());
            loginPage.enterPassword(user.getPassword());
            loginPage.clickLoginButton();

            mainPage.clickAccountButton();
            accountPage.clickLogoutButton();

            assertTrue(
                    new org.openqa.selenium.support.ui.WebDriverWait(
                            driver, java.time.Duration.ofSeconds(10)
                    ).until(
                            org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login")
                    ),
                    "После выхода должна открыться страница входа"
            );
        } finally {
            apiClient.deleteUser(accessToken);
        }
    }
}
