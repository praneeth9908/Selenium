package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.project.actiondriver.Action;
import org.project.base.BaseClass;

public class ProductsListPage extends BaseClass {
    private String availabilityBtnPath= "//div[text()='Availability']";
    private String filtersPath = "//span[contains(text(),'Filters')]";
    @FindBy(xpath = "//span[contains(text(),'Filters')]")
    private WebElement filters;
    @FindBy(xpath = "(//div[@class='_2kHMtA'])[2]")
    private WebElement product;
    @FindBy(xpath = "//div[text()='Availability']")
    private WebElement availabilityBtn;
    @FindBy(xpath = "//div[contains(text(),'Out of Stock')]")
    private WebElement excludeOutOfStock;

    public ProductsListPage(WebDriver driver) {
        super(driver);
    }

    public boolean findFilters(){
        try {
            Action.explicitWait(driver, filtersPath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return filters.isDisplayed();
    }

    public ProductPage selectProduct() throws InterruptedException {
        try {
            Action.explicitWait(driver, availabilityBtnPath);
            availabilityBtn.click();
            Thread.sleep(1000);
            if(!excludeOutOfStock.isSelected()){
                excludeOutOfStock.click();
            }
            Thread.sleep(2000);
            product.click();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductPage(driver);
    }
}
