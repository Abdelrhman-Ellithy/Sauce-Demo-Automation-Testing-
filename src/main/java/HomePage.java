import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;
    public HomePage (WebDriver driver){
        this.driver=driver;
    }
    public WebElement addToCartSouseLabs(){
        return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    }
    public WebElement removeBtn(){
        return driver.findElement(By.id("remove-sauce-labs-backpack"));
    }
    public WebElement numberInTheCart(){
        return driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]"));
    }
    public WebElement optionBtn(){
        return driver.findElement(By.id("react-burger-menu-btn"));
    }
    public WebElement logoutBtn(){
        optionBtn().click();
        return driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"));
    }
}
