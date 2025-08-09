package Tests;

import TestDataGenerator.TestDataHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.SignInPageStellarBurgers;
import pageObjects.SignUpPageStellarBurgers;

import java.util.stream.Stream;

public class SignUpPageStellarBurgersChromeTest {

    private WebDriver driver;
    private SignUpPageStellarBurgers signUpPage;
    private SignInPageStellarBurgers signInPage;


    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        signInPage = new SignInPageStellarBurgers(driver);
        signUpPage = new SignUpPageStellarBurgers(driver);

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }

    @ParameterizedTest
    @MethodSource("Credentials")
    @DisplayName("Регистрация с валидными данными")
    public void shouldCreateAccountWhenValidData(String name, String email, String password){
        signUpPage.signUp(name, email, password);
        signInPage.waitForSignInPageLoad();
        Assertions.assertTrue(signInPage.isLogInPageLoaded(), "Регистрации не произошло");
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
    @ValueSource(strings = {" ", "p", "pass", "passw"})
    @DisplayName("Проверка появления сообщения об ошибке при вводе невалидного пароля")
    public void shouldShowErrorWhenInvalidPassword(String password){
        signUpPage.signUp(TestDataHelper.generateRandomName(), TestDataHelper.generateRandomEmail(), password);
        Assertions.assertTrue(signUpPage.isPasswordErrorVisible(), "Сообщение об ошибке не появилось");
    }


}
