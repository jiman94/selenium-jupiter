package io.github.bonigarcia.seljup.test.singlessession;

import io.github.bonigarcia.seljup.SeleniumExtension;
import io.github.bonigarcia.seljup.SingleSession;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SingleSession
@Disabled
public class ScreenshotNamedWithClassTest {

    @RegisterExtension
    static SeleniumExtension seleniumExtension = new SeleniumExtension();

    RemoteWebDriver driver;

    @BeforeAll
    void setup() {
        seleniumExtension.getConfig()
                .setScreenshotAtTheEndOfTests("whenfailure");
        seleniumExtension.getConfig().takeScreenshotAsPng();
    }

    @BeforeAll
    void resolveWebDriver(ChromeDriver webDriver) {
        this.driver = webDriver;
    }

    @Test
    void shouldFailAndCreateScreenshotTest() {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("Test should fail and create screenshot"));
    }

}
