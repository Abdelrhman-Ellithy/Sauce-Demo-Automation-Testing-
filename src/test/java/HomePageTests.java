import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class HomePageTests {
    WebDriver driver;
    private final String HomePage_Link="https://www.saucedemo.com/inventory.html";
    private  final String ValidUsername = "standard_user";
    private final String ValidPassword= "secret_sauce";

    HomePage Home;
    @BeforeTest
    public void setup_browser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        Home=new HomePage(driver);
    }
    @Test(priority = 1)     // valid pass and valid username
    public void TC_Home_05() throws InterruptedException {
        new loginPage(driver).login_steps(HomePage_Link,ValidUsername,ValidPassword);
        Home.addToCartSouseLabs().click();
        SoftAssert Assert= new SoftAssert();
        Assert.assertTrue(Home.removeBtn().isDisplayed(),"TC_Home_05 Failed, Remove button Not displayed in home");
        Thread.sleep(1000);
    }
    @Test(priority = 2)     // valiadate that the number in the card after adding 1 item is one
    public void TC_Home_06() throws InterruptedException {
        SoftAssert Assert= new SoftAssert();
        String expectedResult ="1";
        String actualResult =Home.numberInTheCart().getText();
        Assert.assertTrue(expectedResult.contains(actualResult));
        Thread.sleep(1000);
    }
    @Test(priority = 3)     // navigate to cart page
    public void TC_Home_07() throws InterruptedException {

        Home.numberInTheCart().click();
        String expectedResult ="https://www.saucedemo.com/cart.html";
        String actualResult =Home.driver.getCurrentUrl();
        SoftAssert Assert= new SoftAssert();
        Assert.assertTrue(expectedResult==actualResult);
        Thread.sleep(1000);
    }
    @Test(priority = 4)
    public void TC_Home_08() throws InterruptedException {
        Home.driver.navigate().to(HomePage_Link);
        Home.removeBtn().click();
        SoftAssert Assert = new SoftAssert();
        Assert.assertTrue((Home.addToCartSouseLabs().isDisplayed()), "TC_Home_08 Failed, Remove button Still displayed in home");
        Thread.sleep(1000);
    }
    @AfterTest
        public void closeBrowser(){
            driver.quit();
        }
}
