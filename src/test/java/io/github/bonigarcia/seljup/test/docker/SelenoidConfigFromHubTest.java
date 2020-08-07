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
package io.github.bonigarcia.seljup.test.docker;

import static io.github.bonigarcia.seljup.BrowserType.CHROME;
import static io.github.bonigarcia.seljup.CloudType.NONE;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import io.github.bonigarcia.seljup.AnnotationsReader;
import io.github.bonigarcia.seljup.BrowserInstance;
import io.github.bonigarcia.seljup.SelenoidConfig;
import io.github.bonigarcia.seljup.config.Config;

@ExtendWith(MockitoExtension.class)
public class SelenoidConfigFromHubTest {

    Config config = new Config();
    AnnotationsReader annotationsReader = new AnnotationsReader();
    BrowserInstance chrome = new BrowserInstance(config, annotationsReader,
            CHROME, NONE, empty(), empty());

    @InjectMocks
    SelenoidConfig selenoidConfig = new SelenoidConfig(config, chrome, "");

    @Test
    @SuppressWarnings("serial")
    void testBrowserConfig() throws IOException {
        String browsersJsonFromProperties = selenoidConfig
                .getBrowsersJsonAsString();

        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> browserMap = gson
                .fromJson(browsersJsonFromProperties, mapType);

        assertTrue(browserMap.containsKey("chrome"));
    }

}
