import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CartPageTests {
    WebDriver driver;
    CartPage cartpage;
    private final String HomePage_Link="https://www.saucedemo.com/inventory.html";
    private final String cartPage_Link="https://www.saucedemo.com/cart.html";
    private  final String ValidUsername = "standard_user";
    private final String ValidPassword= "secret_sauce";
    private final String SousePrice= "29.99";

    @BeforeTest
    public void setup_browser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        cartpage=new CartPage(driver);
    }

    @Test(priority = 1)     // check the item price is correct ?
    public void TC_09_Cart() throws InterruptedException {
        new loginPage(driver).login_steps(HomePage_Link,ValidUsername,ValidPassword);
        new HomePage(driver).addToCartSouseLabs().click();
        SoftAssert Assert= new SoftAssert();
        Assert.assertTrue(cartpage.SouselabPrice().getText().contains(SousePrice));
        Assert.assertAll();
    }
    @Test(priority = 2)     // validate continue button functionality
    public void TC_10_Cart() throws InterruptedException {
        cartpage.driver.navigate().to(cartPage_Link);
        cartpage.Cont_ShoppingBtn().click();
        // exepcted result is home page
        SoftAssert Assert= new SoftAssert();
        Assert.assertTrue(new HomePage(driver).optionBtn().isDisplayed());
        Assert.assertAll();
    }
    @Test(priority = 3)     // navigate to cart page
    public void TC_11_Cart() throws InterruptedException {
        cartpage.driver.navigate().to(cartPage_Link);
        String expectedResult ="1";
        String actualResult =cartpage.numberInTheCart().getText();
        SoftAssert Assert= new SoftAssert();
        Assert.assertTrue(expectedResult==actualResult);
        Assert.assertAll();
    }
    @Test(priority = 4)
    public void TC_12_Cart() throws InterruptedException {
        cartpage.CheckoutBtn().click();
        SoftAssert Assert= new SoftAssert();
        Assert.assertTrue(new CheckoutPage(driver).fristName().isDisplayed());
        Assert.assertAll();
    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

}
