import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckoutPageTests {
    WebDriver driver;
    private final String Login_Link="https://www.saucedemo.com/";
    private final String checout_link="https://www.saucedemo.com/checkout-step-one.html";
    private final String checoutStep2_link="https://www.saucedemo.com/checkout-step-two.html";
    private final String cartPage_Link="https://www.saucedemo.com/cart.html";
    private final String completePage_Link="https://www.saucedemo.com/checkout-step-two.html";
    private  final String ValidUsername = "standard_user";
    private final String ValidPassword= "secret_sauce";
    CheckoutPage checkoutpage;
    @BeforeTest
    public void setup_browser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        checkoutpage=new CheckoutPage(driver);
    }
    @Test(priority = 1) // validate lastname field functionality when make it empty
    public void TC_13_Checkout() throws InterruptedException {
        new loginPage(driver).login_steps(Login_Link,ValidUsername,ValidPassword);
        new HomePage(driver).addToCartSouseLabs().click();
        checkoutpage.driver.navigate().to(checout_link);
        checkoutpage.fristName().clear();
        checkoutpage.lastName().clear();
        checkoutpage.postalCode().clear();
        checkoutpage.CheckoutSteps("Abdelarhamn","","11");
        checkoutpage.ContBtn().click();
        SoftAssert Assert= new SoftAssert();
        String expectedResult="Error: Last Name is required";
        Assert.assertTrue(checkoutpage.errorMessage().getText()==expectedResult);
        Thread.sleep(1000);
    }
    @Test(priority = 2)     // validate postal code field functionality when make it empty
    public void TC_14_Checkout()throws InterruptedException{
        checkoutpage.fristName().clear();
        checkoutpage.lastName().clear();
        checkoutpage.postalCode().clear();
        checkoutpage.driver.navigate().to(checout_link);
        checkoutpage.CheckoutSteps("Abdelarhamn","Ellithy","");
        checkoutpage.ContBtn().click();
        SoftAssert Assert= new SoftAssert();
        String expectedResult="Error: Postal Code is required";
        Assert.assertTrue(checkoutpage.errorMessage().getText()==expectedResult);
        Thread.sleep(1000);
    }
    @Test(priority = 3)     // validate firstname field functionality when make it empty
    public void TC_15_Checkout() throws InterruptedException {
        checkoutpage.fristName().clear();
        checkoutpage.lastName().clear();
        checkoutpage.postalCode().clear();
        checkoutpage.driver.navigate().to(checout_link);
        checkoutpage.CheckoutSteps("","Ellithy","1");
        checkoutpage.ContBtn().click();
        SoftAssert Assert= new SoftAssert();
        String expectedResult="Error: First Name is required";
        Assert.assertTrue(checkoutpage.errorMessage().getText()==expectedResult);
        Thread.sleep(1000);
    }
    @Test(priority = 4)     // checkout with valid data inputs
    public void TC_16_Checkout() throws InterruptedException {
        checkoutpage.fristName().clear();
        checkoutpage.lastName().clear();
        checkoutpage.postalCode().clear();
        checkoutpage.driver.navigate().to(checout_link);
        checkoutpage.CheckoutSteps("Abdelrahman", "Ellithy", "1");
        checkoutpage.ContBtn().click();
        SoftAssert Assert = new SoftAssert();
        Assert.assertTrue(new CheckoutStep2Page(driver).finishBtn().isDisplayed());
        Thread.sleep(1000);
    }
  @Test(priority = 5)     // checkout with valid data inputs
    public void TC_17_Checkout() throws InterruptedException {
        checkoutpage.driver.navigate().to(checoutStep2_link);
        SoftAssert Assert = new SoftAssert();
        new CheckoutStep2Page(driver).finishBtn().click();
        Assert.assertTrue(new completePage(driver).backToHomeBtn().isDisplayed());
      Thread.sleep(1000);
    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
