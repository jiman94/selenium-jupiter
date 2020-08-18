package com.chicor;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class LoginTest {
	
		
		@Test
		public void login(ChromeDriver driver)
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