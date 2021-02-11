package healenium; /**
 * Healenium-appium Copyright (C) 2019 EPAM
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.epam.healenium.appium.DriverWrapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TestSimpleNotesApp {

    private static AppiumDriver appiumDriver;

    @SneakyThrows
    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        dc.setCapability("appPackage", "com.epam.healenium.appium.testapp");
        dc.setCapability("appActivity", ".MainActivity");
        dc.setCapability(MobileCapabilityType.APP, "apps/simple-notes-app.apk");
        dc.setCapability("test_data:testFindNotesButtonOk://*[contains(@text,'NOTES')]", "testClickNotesButtonHealed://*[contains(@text,'NNOTES')]");

        //declare delegate driver
        appiumDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        //adding healing support
        appiumDriver = DriverWrapper.wrap(appiumDriver);
    }

    @SneakyThrows
    @Test
    public void testFindNotesButtonOk() {
        MobileElement notesButtonOk = (MobileElement) appiumDriver.findElement(By.xpath("//*[contains(@text,'NOTES')]"));
    }

    @SneakyThrows
    @Test
    public void testClickNotesButtonHealed() {
        MobileElement notesButtonHealed = (MobileElement) appiumDriver.findElement(By.xpath("//*[contains(@text,'NNOTES')]"));
        Assertions.assertEquals(notesButtonHealed.getText(), "NOTES");
        notesButtonHealed.click();
    }

    @SneakyThrows
    @Test
    public void testClickNotes3Ok() {
        MobileElement note3Ok = (MobileElement) appiumDriver.findElement(By.xpath("//*[contains(@text,'Note 3')]"));
        note3Ok.click();
    }

    @AfterAll
    public static void tearDown() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }
}
