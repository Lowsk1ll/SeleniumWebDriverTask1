import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    @FindBy(xpath = "/html/body/iframe[2]")
    WebElement kupon;

    @FindBy(xpath = "/html/body/div[7]/div[2]/div/a")
    WebElement closeKupon;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div/div[2]/span/span")
    WebElement result;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/span[3]/span[1]/label/span[1]/input")
    WebElement SALE;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[2]/div[1]/span/span[4]")
    WebElement sortByCosts;

    public SearchPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void skipKupon(){
        closeKupon.click();

    }
    public void setUpFilters(WebDriver driver) throws InterruptedException {
        while (SALE.isEnabled()!=true){
            driver.navigate().refresh();
        }
        if(SALE.getAttribute("aria-checked").equals("false")==true){
            SALE.click();
            new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(sortByCosts));
        }
        while (sortByCosts.getAttribute("class").equals("sort-item")==true || sortByCosts.getAttribute("ae_object_value").equals("price(highest)")!=true){
            sortByCosts.click();
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.attributeToBe(sortByCosts,"ae_object_value","price(highest)"));
        }

    }

    public String getResults(){
        String results = result.getText();
        if(SALE.getAttribute("aria-checked").equals("true")==true){
            results += ", по акции";
        }
        if(sortByCosts.getAttribute("class").equals("sort-item active")==true){
            results += ", цены указаны по возрастанию.";
        }
        return results;
    }

}
