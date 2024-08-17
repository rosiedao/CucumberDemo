package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BasePage {
    public WebDriverWait explicitWait;
    public Actions actions;
    JavascriptExecutor jsExecutor;
    public void openPageUrl(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
        driver.manage().timeouts().implicitlyWait(GlobalConstant.IMPLICIT_TIMEOUT);
    }
    public void closeBrowser(WebDriver driver){
        driver.close();
    }
    public Set<Cookie> getBrowserCookies(WebDriver driver){
        return driver.manage().getCookies();
    }
    public void setCookies(WebDriver driver, Set<Cookie> cookies){
        cookies.forEach(cookie -> driver.manage().addCookie(cookie));
    }
    public void deleteAllCookies(WebDriver driver){
        driver.manage().deleteAllCookies();
    }
    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
    public String getPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }
    public String getPageSourceCode(WebDriver driver){
        return driver.getPageSource();
    }
    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }
    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }
    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }
    public void acceptAlert(WebDriver driver){
        driver.switchTo().alert().accept();
    }
    public void hoverElementByLocator(WebDriver driver, String elementLocator, String... restParam){
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(getByLocator(getDynamicLocator(elementLocator, restParam)))).build().perform();
    }
    public void hoverElement(WebDriver driver, WebElement webElement){
        actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
    }
    public void clickToElement(WebDriver driver, String elementLocator){
        explicitWait = new WebDriverWait(driver, GlobalConstant.EXPLICIT_LONG_TIMEOUT,GlobalConstant.POLLING_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(elementLocator))).click();
    }
    public void clickToElement(WebDriver driver, String locator, String... restParams){
        explicitWait = new WebDriverWait(driver, GlobalConstant.EXPLICIT_LONG_TIMEOUT,GlobalConstant.POLLING_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locator, restParams)))).click();
    }
    public String getTextOfElement(WebDriver driver, String elementLocator){
        explicitWait = new WebDriverWait(driver, GlobalConstant.EXPLICIT_LONG_TIMEOUT,GlobalConstant.POLLING_TIMEOUT);
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(elementLocator))).getText();
    }
    public String getTextOfElement(WebDriver driver, String elementLocator,String... restParam){
        explicitWait = new WebDriverWait(driver, GlobalConstant.EXPLICIT_LONG_TIMEOUT,GlobalConstant.POLLING_TIMEOUT);
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(elementLocator, restParam)))).getText();
    }
    public String getValueOfElement(WebDriver driver, String elementLocator){
        explicitWait = new WebDriverWait(driver, GlobalConstant.EXPLICIT_LONG_TIMEOUT,GlobalConstant.POLLING_TIMEOUT);
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(elementLocator))).getAttribute("value");
    }
    public boolean checkElementIsChecked(WebDriver driver, String elementLocator){
        explicitWait = new WebDriverWait(driver, GlobalConstant.EXPLICIT_LONG_TIMEOUT,GlobalConstant.POLLING_TIMEOUT);
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(elementLocator))).isSelected();
    }
    public boolean checkElementIsChecked(WebDriver driver, String elementLocator, String...restParam){
        explicitWait = new WebDriverWait(driver, GlobalConstant.EXPLICIT_LONG_TIMEOUT,GlobalConstant.POLLING_TIMEOUT);
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(elementLocator, restParam)))).isSelected();
    }
    public void inputDataToTextField(WebDriver driver, String elementLocator, String data){
        driver.findElement(getByLocator(elementLocator)).clear();
        driver.findElement(getByLocator(elementLocator)).sendKeys(data);
    }
    public void inputDataToTextField(WebDriver driver, String dynamicLocator, String data, String... restParam){
        driver.findElement(getByLocator(getDynamicLocator(dynamicLocator,restParam))).clear();
        driver.findElement(getByLocator(getDynamicLocator(dynamicLocator,restParam))).sendKeys(data);
    }
    public void sleep(long second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateRandomStringNumber(int length){
        String chars = "1234567890";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            int index = random.nextInt(chars.length());
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString();
    }
    public void selectValueOfDropdownList(WebDriver driver, String elementLocator, String value){
        Select select = new Select(driver.findElement(getByLocator(elementLocator)));
        select.selectByVisibleText(value);
    }
    public String getSelectedValueOfDropdownList(WebDriver driver, String elementLocator){
        Select select = new Select(driver.findElement(getByLocator(elementLocator)));
        return select.getFirstSelectedOption().getText();
    }
    public WebElement filterElementFromListWebElement(WebDriver driver, String elementLocator, String filteredName){
        List<WebElement> listTopMenu = driver.findElements(getByLocator(elementLocator));
        return listTopMenu.stream().filter(e -> e.getText().equals(filteredName)).findFirst().orElse(null);
    }
    public String getValueOfProperty(WebDriver driver,String elementLocator, String propertyName){
        return driver.findElement(getByLocator(elementLocator)).getAttribute(propertyName);
    }
    public List<WebElement> getListWebElement(WebDriver driver, String elementLocator){
        return driver.findElements(getByLocator(elementLocator));
    }
    public List<WebElement> getListWebElement(WebDriver driver, String dynamicLocator, String... restParam){
        return driver.findElements(getByLocator(getDynamicLocator(dynamicLocator, restParam)));
    }
    public static By getByLocator(String locatorValue) {
        By by = null;

        if (locatorValue.toLowerCase().startsWith("xpath=")) {
            by = By.xpath(locatorValue.substring(6).trim());
        } else if (locatorValue.toLowerCase().startsWith("xpath =")) {
            by = By.xpath(locatorValue.substring(7).trim());
        } else if (locatorValue.toLowerCase().startsWith("css=")) {
            by = By.cssSelector(locatorValue.substring(4).trim());
        } else if (locatorValue.toLowerCase().startsWith("css =")) {
            by = By.cssSelector(locatorValue.substring(5).trim());
        } else if (locatorValue.toLowerCase().startsWith("id=")) {
            by = By.id(locatorValue.substring(3).trim());
        } else if (locatorValue.toLowerCase().startsWith("id =")) {
            by = By.id(locatorValue.substring(4).trim());
        } else if (locatorValue.toLowerCase().startsWith("tagname=")) {
            by = By.tagName(locatorValue.substring(8).trim());
        } else if (locatorValue.toLowerCase().startsWith("tagname =")) {
            by = By.tagName(locatorValue.substring(9).trim());
        }else{
            throw new RuntimeException("Locator type is not valid");
        }
        return by;
    }

    public String getDynamicLocator(String locator, String... restParams){
        return String.format(locator,(Object[]) restParams);
    }
    /**
     * Case 1: Element co hien thi tren UI va co trong HTML: isDisplayed() tra ve true
     * Case 2: Element ko hien thi tren UI va van co trong HTML: isDisplayed() tra ve false
     */
    public boolean verifyElementVisible(WebDriver driver, String dynamicLocator, String... restParam){
        return driver.findElement(getByLocator(getDynamicLocator(dynamicLocator,restParam))).isDisplayed();
    }
    public boolean verifyElementVisible(WebDriver driver, String elementLocator){
        return driver.findElement(getByLocator(elementLocator)).isDisplayed();
    }
    //Case 3: Element khong co tren UI va HTML (Compare number of element = 0)
    public boolean isElementUnDisplayed(WebDriver driver, String ElementLocator){
        //set timeout shorter
        setImplicitWait(driver, GlobalConstant.EXPLICIT_SHORT_TIMEOUT);
        boolean status = getListWebElement(driver, ElementLocator).isEmpty();
        setImplicitWait(driver,GlobalConstant.IMPLICIT_TIMEOUT);
        return status;
    }
    public void setImplicitWait(WebDriver driver, Duration timeout){
        driver.manage().timeouts().implicitlyWait(timeout);
    }

    public boolean verifyTextIsContained(WebDriver driver, String text, String dynamicLocator, String... restParam){
        return driver.findElement(getByLocator(getDynamicLocator(dynamicLocator,restParam))).getText().contains(text);
    }
    public boolean verifyListContainASpecificElement(WebDriver driver,String locatorOfListWebElement, String element){
        boolean check = false;
        List<WebElement> wishlistProducts = getListWebElement(driver, locatorOfListWebElement );
        for(WebElement e : wishlistProducts){
            if(e.getText().contains(element)) check = true;
        }
        return check;
    }
    //JavaScript Executor
    public void setAttributeForElementUsingJS(WebDriver driver, String attribute, String value, String dynamicLocator, String... restPram){
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(getByLocator(getDynamicLocator(dynamicLocator,restPram)));
        jsExecutor.executeScript("arguments[0].setAttribute('"+attribute+"', arguments[1]);",element,value);
        element.sendKeys(Keys.RETURN);
    }
    //Wait Ajax Loading invisibility
    public void waitingElementInVisible(WebDriver driver, String elementLocator){
        explicitWait = new WebDriverWait(driver, GlobalConstant.EXPLICIT_LONG_TIMEOUT, GlobalConstant.POLLING_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(elementLocator)));
    }

    public boolean isPageLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, GlobalConstant.EXPLICIT_LONG_TIMEOUT);
        jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }
}
