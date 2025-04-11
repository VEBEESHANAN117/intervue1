package intervue1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.ArrayList;

public class IntervueLoginTest {
    public static void main(String[] args) throws InterruptedException {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Open Intervue.io homepage
        driver.get("https://www.intervue.io/");

        // Wait for the first login button to be clickable and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement loginButton1 = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//a[@class='ivhn-contact-link loginBtn'])[1]")));
        loginButton1.click();

        // Wait for a moment
        Thread.sleep(3000);

        // Open the login URL in a new tab using JavaScript
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.intervue.io/login', '_blank');");

        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));  // Switch to the second tab

        // Maximize again (forces window refresh in many environments)
        driver.manage().window().maximize();

        // Wait for the login button on the new page to verify switch
        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/login' or contains(@class, 'AccessAccount-ColoredButton')]")));

        loginLink.click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("login_email")));
        emailField.sendKeys("neha@intervue.io");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("login_password")));
        passwordField.sendKeys("Ps@neha@123");


       

        // Click the login/submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@type='submit'])[1]")));
        submitButton.click();

     // Wait for the search placeholder to be visible and send keys
     // Click on the search placeholder span to activate the input field
        WebElement searchSpan = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//span[@class='search_placeholder'])[1]")));
        searchSpan.click();

        // Wait for the input field and send the text "hello"
        WebElement searchInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//input[@placeholder='Type what you want to search for'])[1]")));
        searchInputField.sendKeys("hello");

        // Optional small pause
        Thread.sleep(1000);

        // Click on the first search result div
        WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@class='SearchThrough__PlaceholderText-sc-8f4vh4-0 fEvpzS'])[1]")));
        firstResult.click();

        System.out.println("Search performed and result clicked.");

        
        
        System.out.println("Switched to new tab and login page is visible.");   
     // 1. Click the dropdown (user profile icon)
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div.ant-dropdown-link.ProfileHeader__StyedDropdownHoverLink-sc-1gwp6c1-3.cwhrSp")));
        dropdown.click();

        // Optional wait if the menu takes time to show
        Thread.sleep(1000);

        // 2. Click the Logout link
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='/logout']")));
        logoutButton.click();


        
        // Optional pause
        Thread.sleep(3000);

       
        // Close the browser
        driver.quit();
    }
}
