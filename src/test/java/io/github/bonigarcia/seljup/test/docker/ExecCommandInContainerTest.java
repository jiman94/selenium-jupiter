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
package io.github.bonigarcia.seljup.test.docker;

import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import com.spotify.docker.client.exceptions.DockerException;

import io.github.bonigarcia.seljup.SeleniumExtension;

public class ExecCommandInContainerTest {

    final Logger log = getLogger(lookup().lookupClass());

    @RegisterExtension
    static SeleniumExtension seleniumExtension = new SeleniumExtension();

    @Test
    void execTest(WebDriver driver)
            throws DockerException, InterruptedException {
        String command = "ls";
        Optional<String> containerId = seleniumExtension.getContainerId(driver);
        assertTrue(containerId.isPresent());
        String result = seleniumExtension.getDockerService()
                .execCommandInContainer(containerId.get(), command);
        log.debug("Result of executing command {} in Docker container: {}",
                command, result);
        assertThat(result, notNullValue());
    }

}
