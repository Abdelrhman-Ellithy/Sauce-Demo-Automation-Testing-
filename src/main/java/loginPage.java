import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage {
    WebDriver driver;
    public loginPage (WebDriver driver){
        this.driver=driver;
    }
    public WebElement usernameEl(){
       return driver.findElement(By.id("user-name"));
    }
    public WebElement passwordEl(){
        return driver.findElement(By.xpath("//input[@type=\"password\"]"));
    }
    public WebElement loginBtn(){
        return driver.findElement(By.xpath("//input[@type=\"submit\"]"));
    }
    public WebElement errorMessage(){
        return driver.findElement(By.xpath("//h3[@data-test=\"error\"]"));
    }
    public void login_steps(String link,String username, String password){
        driver.navigate().to(link);
        passwordEl().sendKeys(password);
        usernameEl().sendKeys(username);
        loginBtn().click();
    }
}
