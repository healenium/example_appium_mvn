package healenium;

import com.epam.healenium.appium.wrapper.DriverWrapper;
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;
import com.github.dhiraj072.randomwordgenerator.datamuse.DataMuseRequest;
import com.github.dhiraj072.randomwordgenerator.datamuse.WordsRequest;
import com.github.dhiraj072.randomwordgenerator.exceptions.DataMuseException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TestEmulatorLoginForm {

    private static AppiumDriver appiumDriver;

    private static String byXpath = "//android.widget.Button[@text = 'SIGN IN OR REGISTER']";

    @SneakyThrows
    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        dc.setCapability(MobileCapabilityType.APP, "https://github.com/healenium/example_appium_mvn/raw/feature/EPMHLM-209/src/test/resources/apps/v3/app-debug.apk");

        //declare delegate driver
//        appiumDriver = new AndroidDriver<AndroidElement>(new URL("http://localhost:8085"), dc);
        appiumDriver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), dc);
//        adding healing support
        appiumDriver = DriverWrapper.wrap(appiumDriver);
    }


    @Test
    public void testFindElementsOk2() throws DataMuseException, InterruptedException {
        WordsRequest customRequest = new DataMuseRequest().topics("Car", "Road");
        String randomWord = RandomWordGenerator.getRandomWord(customRequest);

        WebElement username = appiumDriver.findElement(By.id("username"));
        WebElement login = appiumDriver.findElement(By.xpath(byXpath));

        ((MobileElement) username).setValue(randomWord);

        Thread.sleep(600);
        WebElement healedLogin = appiumDriver.findElement(By.xpath(byXpath));

        String text = healedLogin.getText();
        System.out.println(text);

        assertEquals(randomWord.toUpperCase(Locale.ROOT), text);
    }

    @AfterAll
    public static void tearDown() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }

}
