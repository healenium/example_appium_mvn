package healenium;

import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;
import com.github.dhiraj072.randomwordgenerator.datamuse.DataMuseRequest;
import com.github.dhiraj072.randomwordgenerator.datamuse.WordsRequest;
import com.github.dhiraj072.randomwordgenerator.exceptions.DataMuseException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.util.Locale;

import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TestEmulatorLoginForm {

    private static AppiumDriver appiumDriver;

    private static String byXpath = "//android.widget.Button[@text = 'SIGN IN OR REGISTER']";

    @SneakyThrows
    @BeforeAll
    public static void setUp() {
        MutableCapabilities dc = new MutableCapabilities();
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.healenium_appium_example_login");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ui.login.LoginActivity");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        dc.setCapability("nativeWebScreenshot",true);

        String nodeURL = "http://localhost:8085";

        appiumDriver = new AppiumDriver(new URL(nodeURL), dc);
        appiumDriver.manage().timeouts()
                .pageLoadTimeout(ofMinutes(5))
                .implicitlyWait(ofSeconds(10));
    }


    @Test
    public void testFindElementsOk2() throws DataMuseException, InterruptedException {
        WordsRequest customRequest = new DataMuseRequest().topics("Car", "Road");
        String randomWord = RandomWordGenerator.getRandomWord(customRequest);

        WebElement username = appiumDriver.findElement(By.id("username"));
        WebElement login = appiumDriver.findElement(By.xpath(byXpath));

        username.sendKeys(randomWord);

        WebElement healedLogin = appiumDriver.findElement(By.xpath(byXpath));
        healedLogin.click();

        assertEquals(randomWord.toUpperCase(Locale.ROOT), healedLogin.getText());
    }

    @AfterAll
    public static void tearDown() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }

}
