package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

import static utilities.Functions.waitForElement;

// *************************************************************************************************
// ************** Below are functions for identifying all the elements related to Call history Page *****
// *************************************************************************************************


public class CallHistoryPage extends BaseClassiOSAndAndroid {

    static WebDriverWait wait;

    public CallHistoryPage() throws MalformedURLException, InterruptedException {
        super(androidDriver, iosDriver);
    }


    public static void waitForSeeMoreButton() {
        try {
            switch (deviceType) {
                case "Android":
                    waitForElement("xpath", "//*[@text = 'See More' and @displayed = 'true']", 10);
                    break;
                case "iOS":
                    waitForElement("xpath", "//*[contains(@name,'See More') and @accessible = 'true']", 10);
                    break;
                case "default":
                    System.out.println("Neither Android nor iOS");
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static WebElement callHistoryMenu() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"History\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement firstCallItem() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("(//android.view.ViewGroup[1])[2]"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement secondCallItem() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("(//android.view.ViewGroup[2])"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[2]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement thirdCallItem() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("(//android.view.ViewGroup[3])"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[3]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement infoButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("(//XCUIElementTypeButton[@name=\"Button\"])[8]"));
                return element;
            default:
                return element;
        }
    }



    public static WebElement backArrowButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"back arrow\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement callButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.cytracom.cytracommobile:id/indCallHistoryCallButton']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Call\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement messageButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Message\"]"));
                return element;
            default:
                return element;
        }
    }


}


