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
package io.github.bonigarcia.seljup.test.android;

// tag::snippet-in-doc[]
import static io.github.bonigarcia.seljup.BrowserType.ANDROID;
import static io.github.bonigarcia.seljup.CloudType.GENYMOTION_SAAS;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumExtension;
import io.github.bonigarcia.seljup.config.Config;

public class AndroidGenymotionJupiterTest {

    @RegisterExtension
    static SeleniumExtension seleniumExtension = new SeleniumExtension();

    @BeforeAll
    static void setup() {
        Config config = seleniumExtension.getConfig();
        config.setAndroidGenymotionDeviceName("SamsungS7V6");
        config.setAndroidGenymotionTemplate("Samsung Galaxy S7");
        config.setAndroidGenymotionAndroidVersion("6.0.0");
        config.setAndroidGenymotionAndroidApi("23");
        config.setAndroidGenymotionScreenSize("1440x2560");

        // The following values need to be set (it can be overridden using Java
        // properties or environment variables)
        config.setAndroidGenymotionUser("my-genymotion-user");
        config.setAndroidGenymotionPassword("my-genymotion-pass");
        config.setAndroidGenymotionLicense("my-genymotion-license");
    }

    @Test
    public void testAndroidInGenymotionSaas(
            @DockerBrowser(type = ANDROID, cloud = GENYMOTION_SAAS, deviceName = "SamsungS7V6", browserName = "browser")
            RemoteWebDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

}
// end::snippet-in-doc[]
