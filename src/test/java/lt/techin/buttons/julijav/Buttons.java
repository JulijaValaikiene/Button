package lt.techin.buttons.julijav;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class Buttons {
    WebDriver driver;
        WebDriverWait waitingTime;


    String dropHereUrl = "https://webdriveruniversity.com/Click-Buttons/index.html";


    @BeforeEach
    void beforeEach() {
        driver = new ChromeDriver();
        driver.get(dropHereUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }


    @Test
//1.WEbElement Click

    void webElementClick() {
        waitingTime = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.findElement(By.xpath("//p[.='CLICK ME!']")).click();

        WebElement popUpMessage = driver.findElement(By.xpath("//div[@id='myModalClick']//p"));
        waitingTime.until(ExpectedConditions.visibilityOf(popUpMessage));
        System.out.println(popUpMessage.getText());
    Assertions.assertEquals("Well done for successfully using the click() method!", popUpMessage.getText());
        driver.findElement(By.xpath("//div[@id='myModalClick']//div[@class='modal-header']/button[@type='button']")).click();
    }

//2.JavaScript Click

    @Test
    void javaScriptClick (){
        waitingTime = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement javaScrButton = driver.findElement(By.id("button2"));
        JavascriptExecutor javaS = (JavascriptExecutor) driver;
        javaS.executeScript("arguments[0].click();", javaScrButton);
        WebElement popUpMessage = driver.findElement(By.xpath("//div[@id='myModalJSClick']//p"));
        waitingTime.until(ExpectedConditions.visibilityOf(popUpMessage));
        WebElement popUpTitle = driver.findElement(By.xpath("//div[@id='myModalJSClick']//h4[@class='modal-title']"));
    Assertions.assertEquals("We can use JavaScript code if all else fails! Remember always try to use the WebDriver Library method(s) first such as WebElement.click(). (The Selenium development team have spent allot of time developing WebDriver functions etc).", popUpMessage.getText());
    Assertions.assertEquals("It’s that Easy!! Well I think it is.....", popUpTitle.getText());
        driver.findElement(By.xpath("//div[@id='myModalJSClick']//div[@class='modal-footer']")).click();
    }
//3. MOve and Click

         @Test
        void actionMoveAndClick(){
        waitingTime = new WebDriverWait(driver, Duration.ofSeconds(3));

        Actions anyAction = new Actions(driver);
        WebElement moveButton = driver.findElement(By.id("button3"));
        WebElement toElement = driver.findElement(By.xpath("//h2[.='Action Move & Click']"));
        anyAction.moveToElement(toElement).click(moveButton).build().perform();

        WebElement popUpMessage = driver.findElement(By.xpath("//div[@id='myModalMoveClick']//p"));
        WebElement popUpTitle = driver.findElement(By.xpath("//div[@id='myModalMoveClick']//h4[@class='modal-title']"));
        waitingTime.until(ExpectedConditions.visibilityOf(popUpMessage));
    Assertions.assertEquals("Advanced user interactions (API) has been developed to enable you to perform more complex interactions such as:", popUpMessage.getText());
    Assertions.assertEquals("Well done! the Action Move & Click can become very useful!", popUpTitle.getText());
        driver.findElement(By.cssSelector("#myModalMoveClick .btn.btn-default"));
    }
}
