import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    @FindBy(xpath = "//*[@id=\"6216442440\"]/div/div/img")
    WebElement SkipKupon;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div/div[3]/div")
    WebElement CountryLanguageValue;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div/div[3]/div/div/div/div[1]/div/a[1]/span/span")
    WebElement Country;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div/div[3]/div/div/div/div[2]/div/span/a")
    WebElement Language;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div/div[3]/div/div/div/div[3]/div/span/a")
    WebElement Value;

    @FindBy(xpath = "//div/button[@data-role='save']")
    WebElement SaveButton;

    @FindBy(xpath = "//div/input[@name='SearchText']")

    WebElement SearchString;

    @FindBy(xpath = "//div/input[@type='submit']")
    WebElement FindButton;



    public MainPage() {
        PageFactory.initElements(DriverWrapper.driver,this);
    }
    @Step("Закрытие первого купона")
    public void Skip() {
        DriverWrapper.driver.switchTo().frame(DriverWrapper.driver.findElement(By.xpath("/html/body/iframe[1]")));
        SkipKupon.click();
        DriverWrapper.driver.switchTo().defaultContent();
    }
    @Step("Настройка языка,валюты,региона")
    public void SetUp()  {
        CountryLanguageValue.click();
        String country = Country.getText();
        String needCountry = "United States";
        String needLanguage = "Русский";
        String needValue = "UAH ( Ukraine Hryvnia )";
        if (country.equals(needCountry) == false) {
            DriverWrapper.driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[3]/div/div/div/div[1]/div/a[1]")).click();
            DriverWrapper.driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[3]/div/div/div/div[1]/div/div[1]/ul/li[224]")).click();
        }
        String language = Language.getText();
        if (language.equals(needLanguage) == false) {
            DriverWrapper.driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[3]/div/div/div/div[2]/div")).click();
            DriverWrapper.driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[3]/div/div/div/div[2]/div/ul/li[2]")).click();
        }

        String value = Value.getText();
        if (value.equals(needValue) == false) {
            DriverWrapper.driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[3]/div/div/div/div[3]/div")).click();
            DriverWrapper.driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[3]/div/div/div/div[3]/div/ul/li[83]")).click();
        }
        SaveButton.click();
    }
    @Step
    public void Find(String findWord){
        WebDriverWait wait = new WebDriverWait(DriverWrapper.driver,5);
        wait.until(ExpectedConditions.invisibilityOf(SaveButton));
        SearchString.sendKeys(findWord);
        FindButton.click();
    }
}