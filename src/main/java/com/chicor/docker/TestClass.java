package com.chicor.docker;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestClass {

    private WebDriver driver;
    
    PageClass pageClass;

    @Parameters({"Port"})
    @BeforeClass
    public void initiateDriver(String Port) throws MalformedURLException {
        if(Port.equalsIgnoreCase("9001"))
        {
            driver = new RemoteWebDriver(new URL("http:192.168.219.101:4444/wd/hub"), DesiredCapabilities.chrome());
            driver.manage().window().maximize();
        }
        else if(Port.equalsIgnoreCase("9002")){
            driver = new RemoteWebDriver(new URL("http:192.168.219.101:4444/wd/hub"), DesiredCapabilities.firefox());
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
    
 
    
    @Parameters("browser")
	@Test
	public void login(String browser)
	{
   	 
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
       // driver.close();
        			
		
	}
	
}