package Tests;

import TestDataGenerator.TestDataHelper;
import WebDriverFactory.WebDriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pageObjects.AccountPageStellarBurgers;
import pageObjects.MainPageStellarBurgers;
import pageObjects.SignInPageStellarBurgers;
import pageObjects.SignUpPageStellarBurgers;

public class AccountPageStellarBurgersTest {
    private WebDriver driver;
    private SignUpPageStellarBurgers signUpPage;
    private SignInPageStellarBurgers signInPage;
    private MainPageStellarBurgers mainPage;
    private AccountPageStellarBurgers accountPage;
    String name;
    String email;
    String password;


    @BeforeEach
    public void setUp() {

        driver = WebDriverFactory.getDriver("yandex");

        signInPage = new SignInPageStellarBurgers(driver);
        signUpPage = new SignUpPageStellarBurgers(driver);
        mainPage = new MainPageStellarBurgers(driver);
        accountPage = new AccountPageStellarBurgers(driver);
        name = TestDataHelper.generateRandomName();
        email = TestDataHelper.generateRandomEmail();
        password = TestDataHelper.generateRandomPassword();

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на кнопку Конструктор")
    public void shouldOpenConstructorPageAfterClickingOnConstructorLink() {
        signUpPage.signUp(name, email, password);
        signInPage.openSignInPage();
        signInPage.logIn(email, password);
        mainPage.waitForMainPageLoading();
        mainPage.clickPersonalAccountButton();
        accountPage.waitForAccountPageLoad();
        accountPage.clickConstructorLink();
        Assertions.assertTrue(mainPage.isMainPageLoaded(), "Не удалось перейти в конструктор");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на лого")
    public void shouldOpenConstructorAfterClickingOnLogo() {
        signUpPage.signUp(name, email, password);
        signInPage.openSignInPage();
        signInPage.logIn(email, password);
        mainPage.waitForMainPageLoading();
        mainPage.clickPersonalAccountButton();
        accountPage.waitForAccountPageLoad();
        accountPage.clickLogo();
        Assertions.assertTrue(mainPage.isMainPageLoaded(), "Не удалось перейти в конструктор по клику на лого");
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void shouldLogoutAfterClickingLogOutButton() {
        signUpPage.signUp(name, email, password);
        signInPage.openSignInPage();
        signInPage.logIn(email, password);
        mainPage.waitForMainPageLoading();
        mainPage.clickPersonalAccountButton();
        accountPage.waitForAccountPageLoad();
        accountPage.clickLogoutButton();
        signInPage.waitForSignInPageLoad();
        Assertions.assertTrue(signInPage.isLogInPageLoaded(), "Не удалось выйти из аккаунта");
    }
}