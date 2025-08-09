package TestDataGenerator;
import java.util.UUID;


public class TestDataHelper {
    public static String generateRandomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }

    public static String generateRandomPassword() {
        return "Pass!" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generateRandomName() {
        return "User_" + UUID.randomUUID().toString().substring(0, 4);
    }
}