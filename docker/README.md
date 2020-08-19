## Table of contents

1. [Motivation](#motivation)
2. [Selenium-Jupiter as Java dependency](#selenium-jupiter-as-java-dependency)
   1. [Local browsers](#local-browsers)
   2. [Remote browsers](#remote-browsers)
   3. [Docker browsers](#docker-browsers)
   4. [Appium](#appium)
   5. [Examples](#examples)
3. [Selenium-Jupiter CLI](#selenium-jupiter-cli)
4. [Selenium-Jupiter server](#selenium-jupiter-server)
5. [Help](#help)
6. [About](#about)


## Selenium-Jupiter as Java dependency

In order to include *Selenium-Jupiter* in a Maven project, first add the following dependency to your `pom.xml` (Java 8 required):

```xml
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>selenium-jupiter</artifactId>
    <version>3.3.4</version>
    <scope>test</scope>
</dependency>
```

... or in Gradle project:

```
dependencies {
    testCompile("io.github.bonigarcia:selenium-jupiter:3.3.4")
}
```

*Selenium-Jupiter* is typically used by tests. For that reason, the scope of the dependency has been defined as `test` in Maven and `testCompile` in Gradle .

### Local browsers

Once we have included this dependency, *Selenium-Jupiter* can be used to control local browsers programmatically using [Selenium WebDriver]. To do that, we simply need to specify the flavor of the browser to be used by declaring `WebDriver` parameters in tests or constructors. For instance, we declare a `ChromeDriver` parameter to use Chrome, `FirefoxDriver` for Firefox, and so on. For instance:  

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class SeleniumJupiterLocalTest {

    @Test
    public void testLocalChrome(ChromeDriver driver) {
        // use local Chrome in this test
    }

    @Test
    public void testLocalFirefox(FirefoxDriver driver) {
        // use local Firefox in this test
    }

}
```

Internally, *Selenium-Jupiter* uses [WebDriverManager] to manage the WebDriver binaries (i.e. *chromedriver*, *geckodriver*,  *operadriver*, and so on) required to use local browsers.

### Remote browsers

*Selenium-Jupiter* can also be used to control remote browsers programmatically. To do that, a couple of custom annotations can be used (parameter-level or field-level): `DriverUrl` (to identify the Selenium Server URL) and `DriverCapabilities` (to configure the desired capabilities). For instance:

```java
import static org.openqa.selenium.remote.DesiredCapabilities.firefox;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.DriverUrl;
import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class SeleniumJupiterRemoteTest {

    @DriverUrl
    String url = "http://localhost:4445/wd/hub";

    @DriverCapabilities
    Capabilities capabilities = firefox();

    @Test
    void testWithRemoteChrome(@DriverUrl("http://localhost:4444/wd/hub")
            @DriverCapabilities("browserName=chrome") RemoteWebDriver driver) {
        // use remote Chrome in this test
    }

    @Test
    void testWithRemoteFirefox(RemoteWebDriver driver) {
        // use remote Firefox in this test
    }


}
```

### Docker browsers

As of version 2, *Selenium-Jupiter* allows to use browsers in [Docker] containers. The only requirement is to get installed [Docker Engine] in the machine running the tests. A simple example using this feature is the following:

```java
import static io.github.bonigarcia.seljup.BrowserType.ANDROID;
import static io.github.bonigarcia.seljup.BrowserType.CHROME;
import static io.github.bonigarcia.seljup.BrowserType.FIREFOX;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class SeleniumJupiterDockerTest {

    @Test
    public void testChrome(
            @DockerBrowser(type = CHROME, version = "latest") RemoteWebDriver driver) {
        // use Chrome (latest version) in a Docker container in this test
    }

    @Test
    public void testFirefox(
            @DockerBrowser(type = FIREFOX, version = "66.0") RemoteWebDriver driver) {
        // use Firefox (version 66.0) in a Docker container in this test
    }

    @Test
    public void testAndroid(
            @DockerBrowser(type = ANDROID, version = "9.0") RemoteWebDriver driver) {
        // use Android (version 9.0) in a Docker container in this test
    }

}
```

### Appium

*Selenium-Jupiter* also allows to control mobile devices using [Appium]. The following snippet shows an example. This test uses the annotation `@DriverCapabilities` at parameter-level to set the required capabilities and `@DriverUrl` to set the Appium Server URL:

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.seljup.DriverCapabilities;
import io.github.bonigarcia.seljup.DriverUrl;
import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class AppiumJupiterTest {

    @Test
    void testAppium(@DriverUrl("http://localhost:4723/wd/hub")
            @DriverCapabilities({ "browserName=chrome",
                    "deviceName=Android" }) AppiumDriver<WebElement> driver) {
        // use Chrome in Android device through Appium in this test
    }

}
```

### Examples

You can find more features, details and examples on the [Selenium-Jupiter user guide].


## Selenium-Jupiter CLI

As of version 2.2.0, Selenium-Jupiter can used interactively from the Command Line Interface (CLI), i.e. the shell, to get VNC sessions of Docker browsers (Chrome, Firefox, Opera, Android). There are two ways of using this feature:

* Directly from the source code, using Maven. The command to be used is ``mvn exec:java -Dexec.args="browserName"``. For instance:

```
> mvn exec:java -Dexec.args="chrome"
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Selenium-Jupiter 3.3.4
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- exec-maven-plugin:1.6.0:java (default-cli) @ selenium-jupiter ---
[INFO] Using SeleniumJupiter to execute chrome (latest) in Docker
[INFO] Using CHROME version 76.0 (latest)
[INFO] Starting Docker container aerokube/selenoid:1.8.4
[DEBUG] Creating WebDriver for CHROME at http://172.17.0.1:32782/wd/hub
Jan 07, 2019 6:54:19 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
[INFO] Starting Docker container psharkey/novnc:3.3-t6
[INFO] Session id fe492bee1ecebceb645cf58275a63bd6
[INFO] VNC URL (copy and paste in a browser navigation bar to interact with remote session)
[INFO] http://172.17.0.1:32783/vnc.html?host=172.17.0.1&port=32782&path=vnc/fe492bee1ecebceb645cf58275a63bd6&resize=scale&autoconnect=true&password=selenoid
[INFO] Press ENTER to exit

[INFO] Stopping Docker container aerokube/selenoid:1.8.4
[INFO] Stopping Docker container psharkey/novnc:3.3-t6
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 21.240 s
[INFO] Finished at: 2019-01-07T16:26:47+01:00
[INFO] Final Memory: 33M/453M
[INFO] ------------------------------------------------------------------------
```

* Using Selenium-Jupiter as a *fat-jar* (i.e. Selenium-Jupiter with all its dependencies in a single executable JAR file). This JAR file can downloaded from [here](https://github.com/bonigarcia/selenium-jupiter/releases/download/selenium-jupiter-3.3.4/selenium-jupiter-3.3.4-fat.jar) and also it can be created using the command ``mvn compile assembly:single`` from the source code. Once you get the *fat-jar*, you simply need to use the command ``java -jar selenium-jupiter-3.3.4-fat.jar browserName``, for instance:

```
> java -jar selenium-jupiter-3.3.4-fat.jar chrome
[INFO] Using SeleniumJupiter to execute chrome (latest) in Docker
[INFO] Using CHROME version 76.0 (latest)
[INFO] Starting Docker container aerokube/selenoid:1.8.4
[DEBUG] Creating WebDriver for CHROME at http://172.17.0.1:32784/wd/hub
Jan 07, 2019 6:55:17 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
[INFO] Starting Docker container psharkey/novnc:3.3-t6
[INFO] Session id 8edd28c130bb2bc62f8e4467c20f4dc0
[INFO] VNC URL (copy and paste in a browser navigation bar to interact with remote session)
[INFO] http://172.17.0.1:32785/vnc.html?host=172.17.0.1&port=32784&path=vnc/8edd28c130bb2bc62f8e4467c20f4dc0&resize=scale&autoconnect=true&password=selenoid
[INFO] Press ENTER to exit

[INFO] Stopping Docker container aerokube/selenoid:1.8.4
[INFO] Stopping Docker container psharkey/novnc:3.3-t6
```

## Selenium-Jupiter Server

As of version 3.0.0, Selenium-Jupiter can used as a server. To start this mode, the shell is used. Once again, two options are allowed:

* Directly from the source code and Maven. The command to be used is ``mvn exec:java -Dexec.args="server <port>"``. If the second argument is not specified, the default port will be used (4042):

```
$ mvn exec:java -Dexec.args="server"
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Selenium-Jupiter 3.3.4
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- exec-maven-plugin:1.6.0:java (default-cli) @ selenium-jupiter ---
[INFO] Selenium-Jupiter server listening on http://localhost:4042/wd/hub
```

* Using Selenium-Jupiter as a [fat-jar](https://github.com/bonigarcia/selenium-jupiter/releases/download/selenium-jupiter-3.3.4/selenium-jupiter-3.3.4-fat.jar). For instance:

```
> java -jar selenium-jupiter-3.3.4-fat.jar server
[INFO] Selenium-Jupiter server listening on http://localhost:4042/wd/hub
```
