import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.project.ConfigReader;
import org.project.pages.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Objects;
import java.util.Set;

public class TestFlipKart {
    WebDriver driver;
    @FindBy(xpath = "//input[@class='Pke_EE']") WebElement searchBox;
    @FindBy(linkText = "Bluetooth Headphones") WebElement earphones;
    @FindBy(xpath = "//div[@class='_2kHMtA'") WebElement product;
    @FindBy(xpath = "//span[contains(text(),'Place Order')]") WebElement placeOrder;
    @FindBy(xpath = "//button[text()='Add to cart']")
    WebElement addToCart;
    @FindBy(xpath = "//button[text()='Deliver Here']") WebElement deliverAddress;
    @FindBy(xpath = "//button[text()='CONTINUE']") WebElement continueBtn;
//    Accept & Continue
    @FindBy(xpath = "//button[text()='Accept & Continue']") WebElement acceptContinueBtn;

    TestFlipKart(){
        driver = new ChromeDriver();
        PageFactory.initElements(driver,this);
    }
    @BeforeTest
    public void setUp(){
        driver.get("https://www.flipkart.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void testFlipkart() throws InterruptedException, AWTException {
        ConfigReader configReader = new ConfigReader();
        try{
            Robot robot = new Robot();
            searchBox.sendKeys(configReader.productName());
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);

            ProductsListPage productsListPage = new ProductsListPage(driver);
            ProductPage productPage = productsListPage.selectProduct();
            Thread.sleep(2000);

            String original_window = driver.getWindowHandle();
            Set<String> all_windows = driver.getWindowHandles();
            for (String str:all_windows){
                if(!Objects.equals(str, original_window)){
                    driver.switchTo().window(str);
                }
            }

            Thread.sleep(2000);
            addToCart.click();
            Thread.sleep(2000);

            placeOrder.click();
            Thread.sleep(2000);

            PaymentPage paymentPage = new PaymentPage(driver);
            paymentPage.enterLoginNumber();
            paymentPage.clickOnContinueBtn();

            Thread.sleep(Duration.ofSeconds(20));

            deliverAddress.click();
            Thread.sleep(2000);

            continueBtn.click();
            Thread.sleep(2000);

            acceptContinueBtn.click();
            Thread.sleep(2000);
        }
        catch (TimeoutException e){
            System.out.println("timeout waiting for visibility of element located");
        }
        catch (NoSuchElementException e){
            System.out.println("Element Not Found");
        }
        catch (Exception e){
            System.out.println("unknown exception");
        }
    }
}
