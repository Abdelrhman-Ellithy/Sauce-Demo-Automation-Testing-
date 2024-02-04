import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class completePage {
    WebDriver driver;
    public completePage(WebDriver driver) {
        this.driver=driver;
    }
    public WebElement backToHomeBtn(){
        return driver.findElement(By.id("back-to-products"));
    }
}
