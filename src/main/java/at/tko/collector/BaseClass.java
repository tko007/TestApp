package at.tko.collector;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseClass(WebDriver driver){
        this.driver = driver;

        wait = new WebDriverWait(driver, 10);
    }

    /**
     * Wait until that the selected element is clickable
     * @param cssSelector
     * @return
     */
    protected WebElement waitUntilElementClickable(String cssSelector){
        return waitUntilElementClickable(driver.findElement(
                By.cssSelector(cssSelector)));
    }

    protected WebElement waitUntilElementClickable(WebElement element){
        try {
            wait.until(ExpectedConditions
                    .elementToBeClickable(element));

            return element;
        } catch (NoSuchElementException e){
            // do something
        }

        return null;
    }

    protected WebElement checkIfElementVisible(By by){
        try {
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(by));

            return driver.findElement(by);
        } catch (NoSuchElementException e){
            // do something
        }

        return null;
    }

    protected List<WebElement> checkIfElementVisible(By by, String a){
        try {
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(by));

            return driver.findElements(by);
        } catch (NoSuchElementException e){
            // do something
        }

        return null;
    }

    protected WebElement exactTestMatching(String text, String value){
        By exactMatch =
                By.xpath(String.format("//*[contains(@class, \"%s\") and @title=\"%s\"]",value, text));

        return checkIfElementVisible(exactMatch);
    }

    protected List<WebElement> exactTestMatchingA(String text, String value){
        By exactMatch =
                By.xpath(String.format("//*[contains(@class, \"%s\") and @title=\"%s\"]",value, text));

        return checkIfElementVisible(exactMatch, "");
    }

    protected void hoverOverAnElement(WebElement hoverOver){
        hoverOver(hoverOver, hoverOver);
    }

    protected void hoverOver(WebElement from, WebElement to){
        Actions action = new Actions(driver);

        action.moveToElement(from)
                .moveToElement(to)
                .click()
                .build()
                .perform();
    }

    protected List<WebElement> executeJsForList(String cssSelector){
        return (List<WebElement>)
                execute(buildJsExecutor(cssSelector));
    }

    protected WebElement executeJs(String cssSelector){
        return executeJsForList(cssSelector).get(0);
    }

    private Object execute(String jsCode){
        // no js executor is supported
        if (!(driver instanceof JavascriptExecutor)) { throw new RuntimeException("No js supported"); }

        return ((JavascriptExecutor) driver)
                .executeScript(jsCode);
    }

    private String buildJsExecutor(String cssSelector){
        String template = "return document.%s('%s');";

        return String.format(template,
                "querySelectorAll", cssSelector);
    }

}
