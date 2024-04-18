package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.project.base.BaseClass;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HomePage extends BaseClass {
    @FindBy(xpath = "//div[@aria-label=\"Fashion\"]") private WebElement fashionCategory;
    @FindBy(xpath = "//div[@aria-label=\"Electronics\"]") private WebElement electronicsCategory;
    @FindBy(xpath = "//div[@aria-label=\"Home & Furniture\"]") private WebElement furnitureCategory;
    @FindBy(xpath = "//input[@class='Pke_EE']") private WebElement searchBox;
    @FindBy(xpath = "//button[@aria-label=\"Search for Products, Brands and More\"]") private WebElement searchButton;
    public HomePage(WebDriver driver){
        super(driver);
    }

    public ProductsListPage searchProduct(String productName) throws AWTException {
        try {
            Robot robot = new Robot();
            searchBox.sendKeys(productName);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ProductsListPage(driver);
    }

    public void hoverOnCategories() throws InterruptedException {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(fashionCategory).perform();
            Thread.sleep(2000);
            actions.moveToElement(electronicsCategory).perform();
            Thread.sleep(2000);
            actions.moveToElement(furnitureCategory).perform();
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
