package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.project.ConfigReader;
import org.project.base.BaseClass;

public class PaymentPage extends BaseClass {
    @FindBy(xpath = "//div/input")
    WebElement loginInputField;
    @FindBy(xpath = "//span[text()='CONTINUE']")
    WebElement continueBtn;
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public ProductsListPage navigateToPrevTab(){
        return new ProductsListPage(driver);
    }

    public void enterLoginNumber(){
        try {
            ConfigReader configReader = new ConfigReader();
            loginInputField.sendKeys(configReader.getMobileNumber());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void clickOnContinueBtn(){
        try {
            continueBtn.click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
