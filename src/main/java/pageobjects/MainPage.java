
package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Кнопка входа в аккаунт на главной странице
    private final By loginButton = By.xpath(
            "//button[text()='Войти в аккаунт']"
    );

    // Ссылка на личный кабинет
    private final By accountButton = By.xpath(
            "//*[self::a or self::button][normalize-space()='Личный Кабинет' or normalize-space()='Личный кабинет']"
    );

    // Переход в конструктор
    private final By constructorButton = By.xpath(
            "//*[self::a or self::p][normalize-space()='Конструктор']"
    );

    // Логотип Stellar Burgers
    private final By logo = By.xpath(
            "//*[name()='svg' and ancestor::*[self::a or self::button]]"
    );

    // Вкладки конструктора
    private final By bunsTab = By.xpath(
            "//*[normalize-space()='Булки']"
    );

    private final By saucesTab = By.xpath(
            "//*[normalize-space()='Соусы']"
    );

    private final By fillingsTab = By.xpath(
            "//*[normalize-space()='Начинки']"
    );

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть главную страницу Stellar Burgers")
    public void open() {
        driver.get("https://qa-stellarburgers.education-services.ru/");
    }

    @Step("Нажать «Войти в аккаунт»")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Перейти в личный кабинет")
    public void clickAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(accountButton)).click();
    }

    @Step("Перейти в конструктор")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    @Step("Нажать на логотип Stellar Burgers")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }

    @Step("Выбрать вкладку «Булки»")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Выбрать вкладку «Соусы»")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Выбрать вкладку «Начинки»")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Получить элемент вкладки «Булки»")
    public WebElement getBunsTab() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
    }

    @Step("Получить элемент вкладки «Соусы»")
    public WebElement getSaucesTab() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTab));
    }

    @Step("Получить элемент вкладки «Начинки»")
    public WebElement getFillingsTab() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
    }
}
