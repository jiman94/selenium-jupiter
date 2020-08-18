Selenium Grid with Debug Docker containers

# docker-compose.yaml
```yaml 
version: "3"
services:
  hub:
    image: selenium/hub
    ports:
      - "4444:4444"
    environment:
      GRID_MAX_SESSION: 16
      GRID_BROWSER_TIMEOUT: 3000
      GRID_TIMEOUT: 3000
  chrome:
    image: selenium/node-chrome-debug
    container_name: web-automation_chrome
    depends_on:
      - hub
    environment:
      HUB_PORT_4444_TCP_ADDR: hub
      HUB_PORT_4444_TCP_PORT: 4444
      NODE_MAX_SESSION: 4
      NODE_MAX_INSTANCES: 4
    volumes:
      - /dev/shm:/dev/shm
    ports:
      - "9001:5900"
    links:
      - hub
  firefox:
    image: selenium/node-firefox-debug
    container_name: web-automation_firefox
    depends_on:
      - hub
    environment:
      HUB_PORT_4444_TCP_ADDR: hub
      HUB_PORT_4444_TCP_PORT: 4444
      NODE_MAX_SESSION: 2
      NODE_MAX_INSTANCES: 2
    volumes:
      - /dev/shm:/dev/shm
    ports:
      - "9002:5900"
    links:
      - hub
```

# 2. Download VNC Viewer

3. Time to setup infrastructure!!

```sh 

cd /Users/mz03-jmryu/git/selenium-jupiter2/docker

# docker-compose -f docker-compose.yaml up -d

docker-compose  up -d

docker ps
```

# 4. Integrate VNC viewer with docker containers.
https://www.softwaretestinghelp.com/docker-selenium-tutorial/

secret

Enter your <ip address of your container>:<container port address> in vnc viewer search tab.

0.0.0.0:9001
0.0.0.0:9002





secret


# 5. Time to run tests!!
```
clean test -Dsurefire.suiteXmlFiles=Testng.xml
```

# B6. Tear Down the infrastructure.
$ docker-compose -f docker-compose.yaml down
$ docker ps

# TestClass.java
```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class TestClass {

    private WebDriver driver;
    PageClass pageClass;

    @Parameters({"Port"})
    @BeforeClass
    public void initiateDriver(String Port) throws MalformedURLException {
        if(Port.equalsIgnoreCase("9001"))
        {
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), DesiredCapabilities.chrome());
            driver.manage().window().maximize();
        }
        else if(Port.equalsIgnoreCase("9002")){
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), DesiredCapabilities.firefox());
            driver.manage().window().maximize();
        }

        pageClass = new PageClass(driver);
    }

    @AfterClass
    public void quitDriver()
    {
        driver.quit();
    }

    @Parameters("browser")
    @Test
    public void Test1(String browser){

        System.out.println("Test1 :" + browser);
        driver.get("https://www.amazon.in/");
        pageClass.enterItemToSearch("books");
        pageClass.getSearchedItem(0);
    }

    @Parameters("browser")
    @Test
    public void Test2(String browser){

        System.out.println("Test2 :" + browser);
        driver.get("https://www.amazon.in/");
        pageClass.enterItemToSearch("headphones");
        pageClass.getSearchedItem(0);
    }
}
```


PageClass.java
```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageClass {

    WebDriver driver;

    @FindBy(how = How.ID,using = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(how = How.XPATH, using = "//div[@id='nav-search']//input[@type='submit']")
    private WebElement searchButton;

    @FindBy(how = How.XPATH,using = "//span[@class='a-size-medium a-color-base a-text-normal']")
    private List<WebElement> searchResults;


    public PageClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterItemToSearch(String itemName){
        searchBox.sendKeys(itemName);
        searchButton.click();
    }

    public void getSearchedItem(int index){
        searchResults.get(index).click();
    }


}
```

# Testng.xml

```xml 
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite thread-count="4" name="SeleniumGridDocker" parallel="tests">
    <test name="Chrome Test">
        <parameter name="browser" value="chrome" />
        <parameter name="Port" value="9001" />
        <classes>
            <class name="TestClass" />
        </classes>
    </test>

        <test name="Firefox Test">
            <parameter name="browser" value="firefox" />
            <parameter name="Port" value="9002" />
            <classes>
                <class name="TestClass" />
            </classes>
        </test>

</suite>
```

# pom.xml

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>SeleniumGridDocker</groupId>
    <artifactId>SeleniumGridDocker</artifactId>
    <version>1.0-SNAPSHOT</version>


    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.20</version>
                <configuration>
                    <forkCount>3</forkCount>
                    <reuseForks>true</reuseForks>
                    <argLine>-Xmx1024m -XX:MaxPermSize=256m</argLine>
                </configuration>
            </plugin>
        </plugins>
    </build>
    <dependencies>

        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-server</artifactId>
            <version>3.141.59</version>
        </dependency>
    </dependencies>
</project>
```
