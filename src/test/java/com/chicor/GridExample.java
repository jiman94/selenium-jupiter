package com.chicor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridExample {
    
    public static WebDriver driver;

	public static void main(String[]  args) throws MalformedURLException, InterruptedException{

 		String URL = "https://chicor.com/main";
 		
 		String Node = "http://localhost:4444/wd/hub";
 		
 		DesiredCapabilities cap = DesiredCapabilities.firefox();
 		driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), DesiredCapabilities.chrome());
 		 
 		
 		driver = new RemoteWebDriver(new URL(Node), cap);

 		//ChromeDriver driver
 		
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
   //     driver.close();
 	   
 		driver.navigate().to(URL);
 		Thread.sleep(5000);
 		driver.quit();
 		
 		
 		cap = DesiredCapabilities.chrome();
 		
 		driver = new RemoteWebDriver(new URL(Node), cap);

 		//ChromeDriver driver
 		
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
   //     driver.close();
 	   
 		driver.navigate().to(URL);
 		Thread.sleep(5000);
 		driver.quit();
 	}		
	
    
}