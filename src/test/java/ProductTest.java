import org.openqa.selenium.WebDriver;
import org.project.ConfigReader;
import org.project.base.BaseClass;
import org.project.pages.CartPage;
import org.project.pages.HomePage;
import org.project.pages.ProductPage;
import org.project.pages.ProductsListPage;
import org.testng.annotations.*;

import java.awt.*;
import java.util.Objects;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class ProductTest {
    WebDriver driver;
    ConfigReader configReader;
    ProductsListPage productsListPage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browserName) {
        BaseClass baseClass = new BaseClass(driver);
        driver = baseClass.launchApp(browserName);
        configReader = new ConfigReader();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testProductPage() throws InterruptedException, AWTException {
        ConfigReader configReader = new ConfigReader();
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
    }
}
