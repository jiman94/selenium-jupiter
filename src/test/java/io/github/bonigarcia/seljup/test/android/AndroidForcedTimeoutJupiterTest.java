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
package io.github.bonigarcia.seljup.test.android;

import static io.github.bonigarcia.seljup.BrowserType.ANDROID;
import static io.github.bonigarcia.seljup.CloudType.NONE;
import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.seljup.AnnotationsReader;
import io.github.bonigarcia.seljup.BrowserInstance;
import io.github.bonigarcia.seljup.InternalPreferences;
import io.github.bonigarcia.seljup.SeleniumJupiterException;
import io.github.bonigarcia.seljup.config.Config;
import io.github.bonigarcia.seljup.handler.DockerDriverHandler;

@TestInstance(PER_CLASS)
public class AndroidForcedTimeoutJupiterTest {

    final Logger log = getLogger(lookup().lookupClass());

    DockerDriverHandler dockerDriverHandler;
    Config config = new Config();
    AnnotationsReader annotationsReader = new AnnotationsReader();
    InternalPreferences preferences = new InternalPreferences(config);

    @BeforeAll
    void setup() {
        config.setVnc(true);
        config.setAndroidDeviceStartupTimeoutSec(0);
        config.setAndroidDeviceTimeoutSec(10);
        config.setAndroidLogging(true);
    }

    @AfterEach
    void cleanup() {
        if (dockerDriverHandler != null) {
            dockerDriverHandler.cleanup();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "9.0" })
    void androidTimeoutTest(String version) {
        assertThrows(SeleniumJupiterException.class, () -> {
            BrowserInstance android = new BrowserInstance(config,
                    annotationsReader, ANDROID, NONE, empty(), empty());
            dockerDriverHandler = new DockerDriverHandler(config, android,
                    version, preferences);
            WebDriver driver = dockerDriverHandler.resolve(android, version,
                    "Samsung Galaxy S6", "", true);
            log.debug("WebDriver object: {}", driver);
        });
    }

}
