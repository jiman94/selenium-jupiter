/*
 * (C) Copyright 2018 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.seljup.test.constructor;

import static io.github.bonigarcia.seljup.BrowserType.CHROME;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class MixedInConstructorJupiterTest {

    static final int NUM_BROWSERS = 2;

    WebDriver driver1;
    RemoteWebDriver driver2;
    List<WebDriver> driverList1;

    public MixedInConstructorJupiterTest(WebDriver driver1,
            RemoteWebDriver driver2,
            @DockerBrowser(type = CHROME, size = NUM_BROWSERS) List<WebDriver> driverList1) {
        this.driver1 = driver1;
        this.driver2 = driver2;
        this.driverList1 = driverList1;
    }

    @Test
    public void testGlobalChrome(WebDriver driver3, RemoteWebDriver driver4,
            @DockerBrowser(type = CHROME, size = NUM_BROWSERS) List<RemoteWebDriver> driverList2) {
        exercise(driver1);
        exercise(driver2);
        driverList1.forEach(this::exercise);
        exercise(driver3);
        exercise(driver4);
        driverList2.forEach(this::exercise);
    }

    private void exercise(WebDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

}
