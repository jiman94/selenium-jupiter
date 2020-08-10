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
package io.github.bonigarcia.seljup.test.annotations;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.seljup.Options;

public class ClassWithMultipleOptions {

    @Options
    Object options = new Object();

    @Options
    EdgeOptions edgeOptions = new EdgeOptions();

    @Options
    FirefoxOptions firefoxOptions = new FirefoxOptions();

    @Options
    OperaOptions operaOptions = new OperaOptions();

    @Options
    SafariOptions safariOptions = new SafariOptions();
}