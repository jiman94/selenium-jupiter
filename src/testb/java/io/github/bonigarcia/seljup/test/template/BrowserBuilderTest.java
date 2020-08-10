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
package io.github.bonigarcia.seljup.test.template;

import static io.github.bonigarcia.seljup.BrowserBuilder.android;
import static io.github.bonigarcia.seljup.BrowserBuilder.appium;
import static io.github.bonigarcia.seljup.BrowserBuilder.chrome;
import static io.github.bonigarcia.seljup.BrowserBuilder.chromeInDocker;
import static io.github.bonigarcia.seljup.BrowserBuilder.edge;
import static io.github.bonigarcia.seljup.BrowserBuilder.firefox;
import static io.github.bonigarcia.seljup.BrowserBuilder.firefoxInDocker;
import static io.github.bonigarcia.seljup.BrowserBuilder.iexplorer;
import static io.github.bonigarcia.seljup.BrowserBuilder.opera;
import static io.github.bonigarcia.seljup.BrowserBuilder.operaInDocker;
import static io.github.bonigarcia.seljup.BrowserBuilder.phantomjs;
import static io.github.bonigarcia.seljup.BrowserBuilder.safari;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import io.github.bonigarcia.seljup.BrowserBuilder;
import io.github.bonigarcia.seljup.BrowsersTemplate.Browser;

public class BrowserBuilderTest {

    static Stream<BrowserBuilder> browserBuilderProvider() {
        return Stream.of(chrome(), firefox(), opera(), android(), appium(),
                edge(), chromeInDocker(), firefoxInDocker(), operaInDocker(),
                phantomjs(), safari(), iexplorer());
    }

    @ParameterizedTest
    @MethodSource("browserBuilderProvider")
    void templateTest(BrowserBuilder browserBuilder) {
        Browser browser = browserBuilder.version("").browserName("")
                .deviceName("").build();
        assertThat(browser, notNullValue());
    }

}
