1. docker install 
https://docs.docker.com/desktop/


How to Download & Install TestNG in Eclipse for Selenium WebDriver
http://dl.bintray.com/testng-team/testng-eclipse-release/

https://www.selenium.dev/downloads/



2. 확인 
$ docker images

3. Pull the following docker images:
* 2안으로 생략 
$ docker pull selenium/hub
$ docker pull selenium/node-chrome
$ docker pull selenium/node-firefox

4. docker-compose 사용하는 방법 
* 1안 사용  
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
    image: selenium/node-chrome
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
    image: selenium/node-firefox
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

$ docker-compose -f docker-compose.yaml up -d

$ docker ps

http://localhost:4444/grid/console

3. Configuring Testng.xml file.
```xml 
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite thread-count="2" name="SeleniumGridDocker" parallel="tests">

	<test name="Chrome Test">
		<parameter name="browser" value="chrome" />
		<parameter name="Port" value="9001" />
		<classes>
			<class name="com.chicor.SeleniumGrid" />
		</classes>
	</test>

	<test name="Firefox Test">
		<parameter name="browser" value="firefox" />
		<parameter name="Port" value="9002" />
		<classes>
			<class name="com.chicor.SeleniumGrid" />
		</classes>
	</test>

</suite>
```

```java
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
}
```


4. Time to run tests!!
$ mvn clean test -Dsurefire.suiteXmlFiles=Testng.xml



5. 

https://www.realvnc.com/en/connect/download/viewer/

secret



6. Tear Down the infrastructure.
$ docker-compose -f docker-compose.yml down
$ docker ps

```xml 
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>selenium-jupiter</artifactId>
    <version>3.3.4</version>
    <scope>test</scope>
</dependency>
```

# or in Gradle project:

```java
dependencies {
    testCompile("io.github.bonigarcia:selenium-jupiter:3.3.4")
}
```


#  참조 
https://www.softwaretestinghelp.com/docker-selenium-tutorial/


7. 빌드 

docker build -t jenkins-seleniumgrid .    


8. 실행 

D:\app\jenkins\secrets

docker run -d -p 9090:8080 -p 50001:50000 -v /Users/mz03-jmryu/Downloads/jenkins:/var/jenkins_home --restart unless-stopped --name jenkins-seleniumgrid jenkins-seleniumgrid



sudo apt-get install libnss3


