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
package io.github.bonigarcia.seljup.handler;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.seljup.AnnotationsReader;
import io.github.bonigarcia.seljup.Options;
import io.github.bonigarcia.seljup.config.Config;

/**
 * Handler for Internet Explorer.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 3.2.0
 */
public class InternetExplorerDriverHandler extends DriverHandler {

    public InternetExplorerDriverHandler(Config config,
            AnnotationsReader annotationsReader) {
        super(config, annotationsReader);
    }

    public InternetExplorerDriverHandler(Parameter parameter,
            ExtensionContext context, Config config,
            AnnotationsReader annotationsReader) {
        super(parameter, context, config, annotationsReader);
    }

    @Override
    public void resolve() {
        try {
            Optional<Object> testInstance = context.getTestInstance();
            Optional<Capabilities> capabilities = annotationsReader
                    .getCapabilities(parameter, testInstance);
            InternetExplorerOptions internetExplorerOptions = (InternetExplorerOptions) getOptions(
                    parameter, testInstance);
            if (capabilities.isPresent()) {
                internetExplorerOptions.merge(capabilities.get());
            }
            object = new InternetExplorerDriver(internetExplorerOptions);
        } catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public MutableCapabilities getOptions(Parameter parameter,
            Optional<Object> testInstance)
            throws IOException, IllegalAccessException {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        InternetExplorerOptions optionsFromAnnotatedField = annotationsReader
                .getFromAnnotatedField(testInstance, Options.class,
                        InternetExplorerOptions.class);
        if (optionsFromAnnotatedField != null) {
            internetExplorerOptions = optionsFromAnnotatedField;
        }
        return internetExplorerOptions;
    }

}
