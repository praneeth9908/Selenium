package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.project.actiondriver.Action;
import org.project.base.BaseClass;

public class ProductPage extends BaseClass {
    String buyNowPath = "//button[text()='Buy Now']";
    @FindBy(xpath = "//button[text()='Buy Now']")
    WebElement buyNow;
    @FindBy(xpath = "//button[text()='Add to cart']")
    WebElement addToCart;
    @FindBy(xpath = "//button[text()='GO TO CART']")
    WebElement goToCart;
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean findBuyNowBtn(){
        try {
            Action.explicitWait(driver, buyNowPath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return buyNow.isDisplayed();
    }

    public CartPage addToCart(){
        try {
            if(addToCart.isDisplayed()){
                addToCart.click();
            }else {
                goToCart.click();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new CartPage(driver);
    }
}
