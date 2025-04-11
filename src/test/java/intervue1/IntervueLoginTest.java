package intervue1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.ArrayList;

public class IntervueLoginTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.intervue.io/");

        assert driver.getTitle() != null && !driver.getTitle().isEmpty() : "Website title is not loaded";
        System.out.println("-------Sucessfully naviagte to website-------");  

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement loginButton1 = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='ivhn-contact-link loginBtn'])[1]")));
        assert loginButton1.isDisplayed() : "Login button is not displayed";
        loginButton1.click();

        System.out.println("-------Sucessfully Clicked -------");  

        Thread.sleep(3000);

        ((JavascriptExecutor) driver).executeScript("window.open('https://www.intervue.io/login', '_blank');");

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.manage().window().maximize();

        assert driver.getCurrentUrl().contains("login") : "Did not navigate to login tab";
        System.out.println("-------Sucessfully naviagted and maximized-------"); 

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_email")));
        assert emailField.isDisplayed() : "Email field is not visible";
        emailField.sendKeys("neha@intervue.io");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_password")));
        assert passwordField.isDisplayed() : "Password field is not visible";
        passwordField.sendKeys("Ps@neha@123");

        System.out.println("-------Sucessfully Entered all the data -------"); 

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@type='submit'])[1]")));
        assert submitButton.isDisplayed() : "Submit button is not visible";
        submitButton.click();

        System.out.println("-------Sucessfully clicked the submit button-------"); 
        Thread.sleep(1000);

        Actions actions1 = new Actions(driver);

        WebElement searchSpan1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//span[@class='search_placeholder'])[1]")));
        assert searchSpan1.isDisplayed() : "Search span not visible";

        actions1.moveToElement(searchSpan1).perform();
        actions1.click(searchSpan1).perform();

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//input[@placeholder='Type what you want to search for'])[1]")));
        assert searchInput.isDisplayed() : "Search input is not visible";

        searchInput.click();
        searchInput.sendKeys("hello");

        Thread.sleep(500);

        WebElement searchResult = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@class='SearchThrough__PlaceholderText-sc-8f4vh4-0 fEvpzS'])[1]")));
        assert searchResult.isDisplayed() : "Search result not clickable";
        searchResult.click();

        System.out.println("-------Sucessfully clicked the search field and Enter the data inside the search fields and click the data -------"); 

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div.ant-dropdown-link.ProfileHeader__StyedDropdownHoverLink-sc-1gwp6c1-3.cwhrSp")));
        assert dropdown.isDisplayed() : "Profile dropdown is not visible";
        dropdown.click();

        Thread.sleep(2000);

        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='/logout']")));
        assert logoutButton.isDisplayed() : "Logout button is not visible";
        logoutButton.click();

        System.out.println("-----Successfully logout------ ");

        Thread.sleep(3000);

        System.out.println("-----Excepted result : Successfully All Test Case passed------ ");
        
        driver.quit();
    }
}
