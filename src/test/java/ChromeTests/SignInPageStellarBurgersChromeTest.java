package ChromeTests;

import TestDataGenerator.TestDataHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.ForgotPasswordPageStellarBurgers;
import pageObjects.MainPageStellarBurgers;
import pageObjects.SignInPageStellarBurgers;
import pageObjects.SignUpPageStellarBurgers;

import java.util.stream.Stream;

public class SignInPageStellarBurgersChromeTest {
    private WebDriver driver;
    private SignUpPageStellarBurgers signUpPage;
    private SignInPageStellarBurgers signInPage;
    private MainPageStellarBurgers mainPage;
    private ForgotPasswordPageStellarBurgers forgotPasswordPage;


    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        signInPage = new SignInPageStellarBurgers(driver);
        signUpPage = new SignUpPageStellarBurgers(driver);
        mainPage = new MainPageStellarBurgers(driver);
        forgotPasswordPage = new ForgotPasswordPageStellarBurgers(driver);

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }

    @ParameterizedTest
    @MethodSource("Credentials")
    @DisplayName("Авторизация по кнопке «Войти в аккаунт» на главной")
    public void shouldAuthorizeThroughMainPageSignUpButton(String name, String email, String password){
        signUpPage.signUp(name, email, password);
        mainPage.openMainPage();
        mainPage.waitForMainPageLoading();
        mainPage.clickLogInButton();
        signInPage.waitForSignInPageLoad();
        signInPage.logIn(email, password);
        mainPage.waitForMainPageLoading();
        Assertions.assertTrue(signInPage.isCreateOrderButtonVisible(), "Авторизации не произошло");
    }



    public static Stream<Arguments> Credentials() {
        return Stream.of(
                Arguments.of(
                        TestDataHelper.generateRandomName(),
                        TestDataHelper.generateRandomEmail(),
                        TestDataHelper.generateRandomPassword()
                ),
                Arguments.of(
                        TestDataHelper.generateRandomName(),
                        TestDataHelper.generateRandomEmail(),
                        TestDataHelper.generateRandomPassword()
                ),
                Arguments.of(
                        TestDataHelper.generateRandomName(),
                        TestDataHelper.generateRandomEmail(),
                        TestDataHelper.generateRandomPassword()
                )

        );

    }

    @ParameterizedTest
    @MethodSource("Credentials")
    @DisplayName("Авторизация по кнопке «Личный кабинет»")
    public void shouldAuthorizeThroughPersonalAccountButton(String name, String email, String password){
        signUpPage.signUp(name, email, password);
        mainPage.openMainPage();
        mainPage.waitForMainPageLoading();
        mainPage.clickPersonalAccountButton();
        signInPage.logIn(email, password);
        mainPage.waitForMainPageLoading();
        Assertions.assertTrue(signInPage.isCreateOrderButtonVisible(), "Авторизации не произошло");
    }

    @ParameterizedTest
    @MethodSource("Credentials")
    @DisplayName("Авторизация через кнопку в форме регистрации")
    public void shouldAuthorizeThroughButtonOnSignUpPage(String name, String email, String password){
        signUpPage.signUp(name, email, password);
        signUpPage.openSignUpPage();
        signUpPage.clickSignInLink();
        signInPage.logIn(email, password);
        mainPage.waitForMainPageLoading();
        Assertions.assertTrue(signInPage.isCreateOrderButtonVisible(), "Авторизации не произошло");
    }

    @ParameterizedTest
    @MethodSource("Credentials")
    @DisplayName("Авторизация через кнопку в форме восстановления пароля")
    public void shouldAuthorizeThroughButtonOnForgotPasswordForm(String name, String email, String password){
        signUpPage.signUp(name, email, password);
        forgotPasswordPage.openForgotPasswordPage();
        forgotPasswordPage.clickLogInLink();
        signInPage.logIn(email, password);
        mainPage.waitForMainPageLoading();
        Assertions.assertTrue(signInPage.isCreateOrderButtonVisible(), "Авторизации не произошло");
    }

}
