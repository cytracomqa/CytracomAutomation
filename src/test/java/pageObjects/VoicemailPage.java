package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

import static utilities.Functions.waitForElement;

// *************************************************************************************************
// ************** Below are functions for identifying all the elements related to Voicemail Page *****
// *************************************************************************************************


public class VoicemailPage extends BaseClassiOSAndAndroid {

    static WebDriverWait wait;

    public VoicemailPage() throws MalformedURLException, InterruptedException {
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

    public static WebElement voicemailMenu() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Voicemail\"]\n"));
                return element;
            default:
                return element;
        }
    }


    public static WebElement voicemailCell(String c) {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeCell["+ c +"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement playButton(String p) {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("(//XCUIElementTypeButton[@name=\"cytra play\"])["+p+"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement voicemailSlider() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeSlider"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement callbutton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
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
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"message\"]"));
                return element;
            default:
                return element;
        }
    }



    public static WebElement shareButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='History']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Share\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement deleteButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.cytracom.cytracommobile:id/indCallHistoryCallButton']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"trash\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement beginnerTimerSlider() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.cytracom.cytracommobile:id/indCallHistoryCallButton']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"0:00\"]"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement pauseButton() {
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.cytracom.cytracommobile:id/indCallHistoryCallButton']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"cytra pause\"]"));
                return element;
            default:
                return element;
        }
    }




}


