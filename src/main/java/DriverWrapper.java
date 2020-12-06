import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriverWrapper {
    WebDriver driver;
    String site = "https://aliexpress.ru/";
    public DriverWrapper() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");

    }
    public void init() {
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        this.driver.get(site);
        this.driver.manage().window().maximize();
    }

    public void close() {
        this.driver.quit();
    }

    }
