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
package io.github.bonigarcia.seljup;

/**
 * Custom exception of selenium-jupiter extension.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class SeleniumJupiterException extends RuntimeException {

    private static final long serialVersionUID = -7026228903533825338L;

    public SeleniumJupiterException(String message) {
        super(message);
    }

    public SeleniumJupiterException(Throwable cause) {
        super(cause);
    }

    public SeleniumJupiterException(String message, Throwable cause) {
        super(message, cause);
    }

}
