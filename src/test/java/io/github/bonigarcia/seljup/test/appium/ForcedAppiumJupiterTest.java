/*
 * (C) Copyright 2017 Boni Garcia (http://bonigarcia.github.io/)
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
package io.github.bonigarcia.seljup.test.appium;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.SeleniumExtension;

public class ForcedAppiumJupiterTest {

    @RegisterExtension
    static SeleniumExtension seleniumExtension = new SeleniumExtension();

    @BeforeEach
    void setup() {
        seleniumExtension.getConfig().setExceptionWhenNoDriver(false);
    }

    @Test
    public void appiumNoCapabilitiesTest(AppiumDriver<WebElement> driver) {
        assertThat(driver, nullValue());
    }

    @Test
    public void appiumWithCapabilitiesTest(
            @DriverCapabilities({ "browserName=chrome",
                    "deviceName=Android" }) AppiumDriver<WebElement> driver) {
        assertThat(driver, nullValue());
    }

}
