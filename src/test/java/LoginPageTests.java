import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class LoginPageTests {
    WebDriver driver;
    private final String Login_Link="https://www.saucedemo.com/";
    loginPage login;
    @BeforeTest
    public void setup_browser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        login=new loginPage(driver);
    }
    @Test(priority = 1)     // valid pass and valid username
    public void TC_Login_01() throws InterruptedException {
        login.login_steps(Login_Link, "standard_user", "secret_sauce");
        SoftAssert Assert= new SoftAssert();
        Assert.assertTrue(driver.findElement(By.className("app_logo")).isDisplayed(), "TC_Login_01 failed");
        Assert.assertAll();
    }
    @Test(priority = 2)     // empty username and valid password
    public void TC_Login_02() throws InterruptedException {
        login.login_steps(Login_Link, "", "secret_sauce");
        SoftAssert Assert= new SoftAssert();
        String expectedResult= "Epic sadface: Username is required";
        String actualResult =login.errorMessage().getText();
        Assert.assertTrue(actualResult.contains(expectedResult),"TC_login_02 is failed");
        Assert.assertAll();
    }
    @Test(priority = 3)     // empty pass and valid username
    public void TC_Login_03() throws InterruptedException {
        login.login_steps(Login_Link, "standard_user", "");
        SoftAssert Assert= new SoftAssert();
        String expectedResult= "Epic sadface: Password is required";
        String actualResult =login.errorMessage().getText();
        Assert.assertTrue(actualResult.contains(expectedResult),"TC_login_03 is failed");
        Assert.assertAll();
    }
    @Test(priority = 4)     // invalid pass and invalid username
    public void TC_Login_04() throws InterruptedException {
        login.login_steps(Login_Link, "standard_", "secret_ce");
        SoftAssert Assert= new SoftAssert();
        String expectedResult= "Epic sadface: Username and password do not match any user in this service";
        String actualResult =login.errorMessage().getText();
        Assert.assertTrue(actualResult.contains(expectedResult),"TC_login_04 is failed");
        Assert.assertAll();
    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
