import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver=driver;
    }

    public WebElement SouselabPrice(){
        return driver.findElement(By.className("inventory_item_price"));
    }
    public WebElement removeItemBtn(){
        return driver.findElement(By.id("remove-sauce-labs-backpack"));
    }
    public WebElement numberInTheCart(){
        return driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]"));
    }
    public WebElement Cont_ShoppingBtn(){
        return driver.findElement(By.xpath("//button[@id=\"continue-shopping\"]"));
    }
    public WebElement CheckoutBtn(){
        return driver.findElement(By.id("checkout"));
    }
}
