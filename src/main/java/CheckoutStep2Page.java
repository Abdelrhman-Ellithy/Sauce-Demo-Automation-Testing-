import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutStep2Page {
    WebDriver driver;
    public CheckoutStep2Page(WebDriver driver) {
        this.driver=driver;
    }
    public WebElement finishBtn(){
        return driver.findElement(By.id("finish"));
    }
}
