package com.chicor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class SeleniumGrid {

    WebDriver driver;
    String baseURL, nodeURL;

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
    
    
    @Test
    public void GNBTest(ChromeDriver driver) {
        driver.get("https://chicor.com/main");
        driver.manage().window().setSize(new Dimension(1516, 737));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("오늘 하루 보지 않기")).click();
        driver.findElement(By.linkText("닫기")).click();
        driver.findElement(By.linkText("오늘 하루 보지 않기")).click();
        driver.findElement(By.linkText("닫기")).click();
        driver.findElement(By.linkText("BEST")).click();
        driver.findElement(By.linkText("BRANDS")).click();
        driver.findElement(By.linkText("STORY")).click();
        driver.findElement(By.linkText("DEAL")).click();
        driver.close();
    }


    

}