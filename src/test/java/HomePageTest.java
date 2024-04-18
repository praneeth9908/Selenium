import org.openqa.selenium.WebDriver;
import org.project.ConfigReader;
import org.project.base.BaseClass;
import org.project.pages.HomePage;
import org.project.pages.ProductsListPage;
import org.testng.annotations.*;

import java.awt.*;

import static org.testng.Assert.assertTrue;

public class HomePageTest {
    WebDriver driver;
    ConfigReader configReader;
    ProductsListPage productsListPage;

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

    @Test(dataProvider = "testdata")
    public void testHomePage(String productName) throws InterruptedException, AWTException {
        HomePage homePage = new HomePage(driver);
        homePage.hoverOnCategories();
        productsListPage = homePage.searchProduct(productName);
        Thread.sleep(2000);
        assertTrue(productsListPage.findFilters());

    }

    @DataProvider(name = "testdata")
    public Object[] TestDataFeed() {
        Object[] searchdata = new Object[2];
        searchdata[0] = "mobiles";
        searchdata[1] = "laptops";

        return searchdata;
    }
}
