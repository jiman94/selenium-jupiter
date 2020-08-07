/*
 * (C) Copyright 2019 Boni Garcia (http://bonigarcia.github.io/)
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
package io.github.bonigarcia.seljup.test.selenide;

// tag::snippet-in-doc[]
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

// end::snippet-in-doc[]
import org.junit.jupiter.api.Disabled;
// tag::snippet-in-doc[]
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codeborne.selenide.SelenideDriver;

import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.DriverUrl;
import io.github.bonigarcia.seljup.SeleniumExtension;

// end::snippet-in-doc[]
@Disabled
// tag::snippet-in-doc[]
@ExtendWith(SeleniumExtension.class)
public class SelenideRemoteJupiterTest {

    @DriverUrl
    String url = "http://localhost:4444/wd/hub";

    @DriverCapabilities
    DesiredCapabilities capabilities = DesiredCapabilities.firefox();

    @Test
    public void testRemoteSelenideGlobalConfig(SelenideDriver driver) {
        exercise(driver);
    }

    @Test
    public void testRemoteSelenideParameterConfig(
            @DriverUrl("http://localhost:4444/wd/hub")
            @DriverCapabilities("browserName=chrome") SelenideDriver driver) {
        exercise(driver);
    }

    private void exercise(SelenideDriver driver) {
        driver.open("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.title(),
                containsString("JUnit 5 extension for Selenium"));
    }

}
// end::snippet-in-doc[]
