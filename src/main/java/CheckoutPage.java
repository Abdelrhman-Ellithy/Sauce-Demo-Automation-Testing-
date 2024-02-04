import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    WebDriver driver;
    public CheckoutPage (WebDriver driver){
        this.driver=driver;
    }
    public WebElement cancelBtn(){
        return driver.findElement(By.id("cancel"));
    }
    public WebElement ContBtn(){
        return driver.findElement(By.id("continue"));
    }
    public WebElement fristName(){
        return driver.findElement(By.id("first-name"));
    }
    public WebElement lastName(){
        return driver.findElement(By.id("last-name"));
    }
    public WebElement postalCode(){
        return driver.findElement(By.id("postal-code"));
    }
    public WebElement errorMessage(){
        return driver.findElement(By.xpath("//h3[@data-test=\"error\"]"));
    }

    public void CheckoutSteps(String Firstname, String LastName, String postal){
        fristName().sendKeys(Firstname);
        lastName().sendKeys(LastName);
        postalCode().sendKeys(postal);
    }
}
