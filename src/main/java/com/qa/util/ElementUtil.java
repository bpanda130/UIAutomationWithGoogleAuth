package com.qa.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	Logger log = Logger.getLogger(ElementUtil.class);

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public void launchURL(String url) {
		driver.get(url);
	}
	
	//=============Wrapper FindElement and FindElements actions======================

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public int getNumberOfElements(By locator) {
		return getElements(locator).size();
	}
	
	public void navigateBack() {
		driver.navigate().back();
	}
	
	public void dopressKeyOnElement(By locator, CharSequence ch) {
		
		getElement(locator).sendKeys(ch);
	}
	
	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void doSendKeysAndTab(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
		element.sendKeys(Keys.TAB);
	}
	
	public void doKeyBoardAction(By locator, String action) {
		WebElement element = getElement(locator);
		if(action.equalsIgnoreCase("Tab")){
			element.sendKeys(Keys.TAB);
		}
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	public void doClick(By locator) {
		//WebElement web = getElement(locator);
		waitForRequiredSec(3);
		WebElement web = waitForElementPresent(locator, 20);
		web.click();
	}

	public String doGetText(By locator) {
		return waitForElementPresent(locator, 20).getText();
	}

	public String doGetAttribute(By locator, String attribute) {
		return waitForElementPresent(locator, 20).getAttribute(attribute);
	}

	public boolean getDisplayedStatus(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			return getElement(locator).isDisplayed();
		}catch(Exception e) {
			return false;
		}
		
	}
	public boolean getEnabledStatus(By locator) {
		return getElement(locator).isEnabled();
	}

	public String getPageUrl() {
		return driver.getCurrentUrl();
	}

	public List<String> getElementsTextList(By locator) {
		List<String> eleTextList = new ArrayList<String>();

		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			if (!e.getText().isEmpty()) {
				eleTextList.add(e.getText());
			}
		}

		return eleTextList;
	}

	public void doActionsMoveToElement(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).perform();
	}

	public void doActionsMoveToElementAndClick(By locator) {
		doActionsMoveToElement(locator);
		getElement(locator).click();
	}
	
	public void doClickFromSectionByText(By locator, String reqSection) {
		List<WebElement> elements = getElements(locator);
		for(WebElement ele: elements) {
			if(ele.getText().equalsIgnoreCase(reqSection)) {
				jsUtil.scrollIntoView(ele);
				jsUtil.clickElementByJS(ele);
				//ele.click();
				break;
			}
		}
	}
	
	public void doClickWhenElementIdMatched(By locator, String ExpectedId) {
		List<WebElement> eleList = getElements(locator);
		for(WebElement ele : eleList) {
			if(ele.getAttribute("id").equals(ExpectedId)) {
				jsUtil.scrollIntoView(ele);
				ele.click();
				break;
			}
		}
	}
	
	public void clickOnRequiredItemsFromList(String reqItems, By locator) {
		List<WebElement> eleList = getElements(locator);
		for(WebElement ele : eleList) {
			//System.out.println(ele.getText());
			if(ele.getText().equalsIgnoreCase(reqItems) || reqItems.equalsIgnoreCase("All")) {
				ele.click();
			}
		}
	}

	/********************************** drop down utils ********************/

	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByVisibleText(By locator, String visibletext) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibletext);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void doSelectDropDownValueWithoutSelect(By locator, String value) {
		List<WebElement> list = getElements(locator);
		for (WebElement e : list) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	public String switchToWindowAndGetTitle(String windowId) {
		driver.switchTo().window(windowId);
		String title = driver.getTitle();
		return title;
	}

	// ***************************** Wait Utils **************************

//				}
	public Alert waitForAlertPresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		return waitForAlertPresent(timeOut).getText();
	}

	public void acceptAlert(int timeOut) {
		waitForAlertPresent(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlertPresent(timeOut).dismiss();
	}

	public String waitForTitle(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public String waitForTitleContains(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public String waitForTitle(int timeOut, String title, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public boolean waitForUrl(int timeOut, String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(url));
	}

	public void waitForFrameAndSwitchToIt(String idOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public void waitForFrameAndSwitchToIt(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitForFrameAndSwitchToIt(int index, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitForFrameAndSwitchToIt(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 */
	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void setTextWhenReady(By locator, String text, int timeOut) {
		waitForElementPresent(locator, timeOut).clear();
		doSendKeys(locator, text);
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 *
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 *
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public void printElementsText(By locator, int timeOut) {
		waitForVisibilityOfElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}

	public void printListElements(List<String> eleList) {
		eleList.forEach(ele -> System.out.println(ele));
	}

	public List<String> getElementsListWithText(By locator, int timeOut, String filterVal) {
		return waitForVisibilityOfElements(locator, timeOut).stream().filter(ele -> ele.getText().contains(filterVal))
				.map(ele -> ele.getText()).collect(Collectors.toList());
	}

	public List<WebElement> getElementsList(By locator, int timeOut, String filterVal) {
		return waitForVisibilityOfElements(locator, timeOut).stream().filter(ele -> ele.getText().contains(filterVal))
				.collect(Collectors.toList());
	}

	public WebElement waitForElementVisibleWithElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	/**
	 * This method is specifically for tagName
	 *
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForPresentOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public List<String> getElementsTextList(By locator, int timeOut) {
		List<String> eleTextList = new ArrayList<String>();
		List<WebElement> eleList = waitForVisibilityOfElements(locator, timeOut);
		for (WebElement e : eleList) {
			if (!e.getText().isEmpty()) {
				eleTextList.add(e.getText());
			}
		}
		return eleTextList;
	}


	public boolean verifyElementPresentInList(By locator, String eleText) {
		boolean flag = false;
		try {
			List<WebElement> eleList = waitForVisibilityOfElements(locator, 10);
			for (WebElement e : eleList) {
				if(e.getText().equalsIgnoreCase(eleText)) {
					flag=true;
					break;
				}
			}
		}catch (Exception e) {
			log.info("Required Element list not found.");
			return flag;
		}
		return flag;
	}

	public boolean verifyElementPresentInRequiredColumnInList(By locator1, By locator2, String header, String eleText) {
		boolean flag = false;
		try {
			List<WebElement> headers = waitForVisibilityOfElements(locator1, 10);
			List<WebElement> rows = waitForVisibilityOfElements(locator2, 10);
			int i =0;
			for (WebElement headEle : headers) {
				if(headEle.getText().equalsIgnoreCase(header)) {
					if(rows.get(i).getText().contains(eleText)) {
						flag = true;
						break;
					}
				}
				i = i+1;
			}
		}catch (Exception e) {
			log.info("Required Element list not found.");
			return flag;
		}
		return flag;
	}

	public boolean ClickOnElementPresentInRequiredRowInList(By locator1, By locator2, String eleText) {
		boolean flag = false;
		try {
			List<WebElement> rows = waitForVisibilityOfElements(locator1, 10);
			List<WebElement> ele = waitForVisibilityOfElements(locator2, 10);
			int i =0;
			for (WebElement reqRow : rows) {
				if(reqRow.getText().contains(eleText)) {
					ele.get(i).click();
				}
				i=i+1;
			}
		}catch (Exception e) {
			log.info("Required Element list not found.");
			return flag;
		}
		return flag;
	}


	public boolean ClickOnElementByJSPresentInRequiredRowInList(By locator1, By locator2, String eleText) {
		boolean flag = false;
		try {
			List<WebElement> rows = waitForVisibilityOfElements(locator1, 10);
			List<WebElement> ele = waitForVisibilityOfElements(locator2, 10);
			int i =0;
			for (WebElement reqRow : rows) {
				if(reqRow.getText().contains(eleText)) {
					jsUtil.clickElementByJS(ele.get(i));
				}
				i=i+1;
			}
		}catch (Exception e) {
			log.info("Required Element list not found.");
			return flag;
		}
		return flag;
	}

	public boolean verifyElementDisplayedInElementList(By locatorlist, By locator, String eleList) {
		boolean flag = false;
		try {
			List<WebElement> rows = waitForVisibilityOfElements(locatorlist, 10);
			List<WebElement> ele = waitForVisibilityOfElements(locator, 10);
			int i =0;
			for (WebElement reqRow : rows) {
				if(reqRow.getText().contains(eleList)) {
					return ele.get(i).isDisplayed();
				}
				i=i+1;
		}
		}catch (Exception e) {
			log.info("Required Element list not found.");
			return flag;
		}
		return flag;
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 *
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementToBeClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void clickWhenReady(By locator, int timeOut) {
		waitForElementToBeClickable(locator, timeOut).click();
	}

	public void waitForRequiredTime(int timeInMin) {
		try {
			Thread.sleep(timeInMin * 60 * 1000);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitForRequiredSec(int timeInSec) {
		try {
			Thread.sleep(timeInSec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void doScreenActiveForRequiredTime() {
		Actions builder = new Actions(driver);
		try {
			for (int i = 0; i < 30; i++) {
				Thread.sleep(5000);
				builder.click().moveByOffset(0, 1).build().perform();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
