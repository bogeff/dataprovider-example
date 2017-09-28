package internal_class_example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reader.ExcelReader;

/**
 * Created by Atanas.Bogev on 28-Sep-17.
 */
public class SearchByProductsTests {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String page = "https://www.etsy.com/";

    private final By searchField = By.id("search-query");
    private final By searchButton = By.xpath("//*[@id=\"gnav-search\"]/div/div[2]/button");
    private final By searchResults = By.xpath("//*[@id=\"content\"]/div/div[1]/div/div/div[2]/div[2]/div[1]/div[1]/div/span[5]");
    private final By firstProductPrice = By.xpath("//*[@id=\"content\"]/div/div[1]/div/div/div[2]/div[2]/div[4]/div[2]/a/div[2]/p[2]/span[2]");

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 60);
        driver.navigate().to(page);
    }

    @Test(dataProvider="products")
    public void testForSearching(String searchText, String expectedNumberOfItems, String firstProductExpectedPrice) {
        WebElement tmp1 = driver.findElement(searchField);
        wait.until(ExpectedConditions.visibilityOf(tmp1));
        tmp1.clear();
        tmp1.sendKeys(searchText);

        WebElement tmp2 = driver.findElement(searchButton);
        wait.until(ExpectedConditions.elementToBeClickable(tmp2));
        tmp2.click();

        WebElement tmp3 = driver.findElement(searchResults);
        wait.until(ExpectedConditions.visibilityOf(tmp3));
        String ar = tmp3.getText().split(" ")[0];

        WebElement tmp4 = driver.findElement(firstProductPrice);
        wait.until(ExpectedConditions.visibilityOf(tmp4));

        Assert.assertEquals(expectedNumberOfItems, ar.substring(1, ar.length()));
        Assert.assertEquals(firstProductExpectedPrice, tmp4.getText());
    }

    @DataProvider(name="products")
    public Object[][] loginData() {
        return new ExcelReader().getExcelData("src/test/resources/products.xls","Sheet1");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}