package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.project.actiondriver.Action;
import org.project.base.BaseClass;

public class CartPage extends BaseClass {

    String placeOrderPath = "//span[contains(text(),'Place Order')]";
    @FindBy(xpath = "//button/span[contains(text(),'Place Order')]")
    WebElement placeOrderBtn;
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean findPlaceOrderBtn(){
        try {
            Action.explicitWait(driver, placeOrderPath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return placeOrderBtn.isDisplayed();
    }

    public PaymentPage placeOrder(){
        try {
            placeOrderBtn.click();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new PaymentPage(driver);
    }

}
