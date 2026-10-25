
package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.xpath(
            "//label[text()='Email']/following-sibling::input"
    );

    private final By passwordInput = By.xpath(
            "//label[text()='Пароль']/following-sibling::input"
    );

    private final By loginButton = By.xpath(
            "//button[normalize-space()='Войти']"
    );

    private final By registerLink = By.xpath(
            "//a[normalize-space()='Зарегистрироваться']"
    );

    private final By forgotPasswordLink = By.xpath(
            "//a[normalize-space()='Восстановить пароль']"
    );

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть страницу входа")
    public void open() {
        driver.get("https://qa-stellarburgers.education-services.ru/login");
    }

    @Step("Ввести email для входа")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput))
                .sendKeys(email);
    }

    @Step("Ввести пароль для входа")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput))
                .sendKeys(password);
    }

    @Step("Нажать кнопку «Войти»")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Перейти к регистрации")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Перейти к восстановлению пароля")
    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }


    @Step("Получить текущий URL после входа")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

