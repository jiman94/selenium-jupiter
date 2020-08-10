package com.chicor.docker;

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