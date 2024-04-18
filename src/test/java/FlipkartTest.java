import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.project.ConfigReader;
import org.project.base.BaseClass;
import org.project.pages.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class FlipkartTest{
    WebDriver driver;
    ProductsListPage productsListPage;
    ProductPage productPage;
    CartPage cartPage;
    PaymentPage paymentPage;
    ConfigReader configReader;
    @FindBy(xpath = "//button[text()='Deliver Here']")
    WebElement deliverAddress;
    @FindBy(xpath = "//button[text()='CONTINUE']") WebElement continueBtn;
    @FindBy(xpath = "//button[text()='Accept & Continue']") WebElement acceptContinueBtn;


    @BeforeTest
    @Parameters("browser")
    public void setUp(String browserName){
        BaseClass baseClass = new BaseClass(driver);
        driver = baseClass.launchApp(browserName);
        configReader = new ConfigReader();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testFlipkart() throws InterruptedException, AWTException, IOException {
        HomePage homePage = new HomePage(driver);
        homePage.hoverOnCategories();

        productsListPage = homePage.searchProduct(configReader.productName());
        Thread.sleep(2000);
        assertTrue(productsListPage.findFilters());
        System.out.println("Products List Page");

        productPage = productsListPage.selectProduct();
        Thread.sleep(2000);

        String original_window = driver.getWindowHandle();
        Set<String> all_windows = driver.getWindowHandles();
        for (String str:all_windows){
            if(!Objects.equals(str, original_window)){
                driver.switchTo().window(str);
            }
        }

        assertTrue(productPage.findBuyNowBtn());
        System.out.println("Product Page");

        cartPage = productPage.addToCart();
        Thread.sleep(2000);
        assertTrue(cartPage.findPlaceOrderBtn());
        System.out.println("Cart Page");

        paymentPage = cartPage.placeOrder();
        Thread.sleep(2000);

        paymentPage.enterLoginNumber();
        paymentPage.clickOnContinueBtn();
        Thread.sleep(4000);

        driver.close();
        driver.switchTo().window(original_window);
    }
}
