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
package com.chicor;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class LocalWebDriverTest {

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


   @Test
    public void Login(ChromeDriver driver) {
        driver.get("https://chicor.com/main");
        driver.manage().window().setSize(new Dimension(1516, 737));
        {
            WebElement element = driver.findElement(By.cssSelector(".menu-item:nth-child(4) .gra-text"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("오늘 하루 보지 않기")).click();
        driver.findElement(By.linkText("닫기")).click();
        driver.findElement(By.linkText("오늘 하루 보지 않기")).click();
        driver.findElement(By.linkText("닫기")).click();
        driver.findElement(By.linkText("로그인")).click();
        {
            WebElement element = driver.findElement(By.linkText("로그인"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.id("lginId")).click();
        driver.findElement(By.id("lginId")).sendKeys("820104");
        driver.findElement(By.id("lginPw")).click();
        driver.findElement(By.id("lginPw")).sendKeys("2359145js>");
        driver.findElement(By.cssSelector(".btn-login > .btn")).click();
    }
}
