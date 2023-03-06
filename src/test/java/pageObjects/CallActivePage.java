package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

// *************************************************************************************************
// ************** Below are functions for identifying all the elements related to Call Details Page *****
// *************************************************************************************************


public class CallActivePage extends BaseClassiOSAndAndroid {

    static WebDriverWait wait;

    public CallActivePage() throws MalformedURLException, InterruptedException {
        super(androidDriver, iosDriver);
    }


    public static WebElement pauseHoldButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"hold\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement addCallButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"add call\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement transferCallButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"transfer call\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement muteButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"microphone pdf\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement unmuteButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"muteActive\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement speakerButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"sound\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement dialPadButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"dialpad\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement endCallButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//*[@content-desc=\"End Call Button\"]"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeOther[3]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement nameFirstRow() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeStaticText[1]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement numberSecondRow() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeStaticText[2]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement timerThirdRow() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//XCUIElementTypeStaticText[3]"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"back arrow\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement backButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.cytracom.cytracommobile:id/multitask']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"back arrow\"]"));
                return element;
            default:
                return element;
        }
    }


}


