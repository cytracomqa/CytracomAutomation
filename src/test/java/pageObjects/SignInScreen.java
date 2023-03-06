package pageObjects;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Functions;

import java.net.MalformedURLException;
import java.util.List;

// *************************************************************************************************
// ************** Below are functions for identifying all the elements related to SignIn Page *****
// *************************************************************************************************


public class SignInScreen extends BaseClassiOSAndAndroid {

    static WebDriverWait wait;

    SignInScreen() throws MalformedURLException, InterruptedException {
        super(androidDriver, iosDriver);
    }


    public static WebElement WelcomeToCytracom() throws InterruptedException{
        WebElement element = null;
        //waitForAddItems();
        switch (deviceType) {
            case "Android":
                element = androidDriver.findElement(By.xpath("(//*[@text=\"Welcome to Cytracom!\"])"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeTextField"));
                return element;
            default:
                return element;
        }
    }

    public static WebElement buttonSignIn() throws InterruptedException {
        WebElement element=null;
            switch (deviceType) {
                case "Android":
                    element = androidDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Sign In With Email/7 Rewards Button\"]/android.widget.TextView"));
                    break;
                case "iOS":
                    element = iosDriver.findElement(new MobileBy.ByAccessibilityId("Sign In With Email/7 Rewards Button"));
                    break;
                default:
                    element = null;
                    System.out.println("Neither Android nor iOS");
                    break;
            }
            return element;
    }

    public static void waitForbuttonSignIn() throws InterruptedException {
            switch (deviceType) {
                case "Android":
                    //Functions.waitForElement("classname", "android.widget.TextView", 10);
                    Functions.waitForElement("xpath", "//android.view.ViewGroup[@content-desc=\"Sign In With Email/7 Rewards Button\"]/android.widget.TextView", 5);
                    break;
                case "iOS":
                    Functions.waitForElement("ID", "Sign In With Email/7 Rewards Button", 2);
                    break;
                default:
                    System.out.println("Neither Android nor iOS");
                    break;
            }
    }

    public static void waitForbuttonRegister() throws InterruptedException {
        switch (deviceType) {
            case "Android":
                Functions.waitForElement("xpath", "//android.widget.TextView[@text=\"REGISTER\"]", 35);
                break;
            case "iOS":
                Functions.waitForElement("xpath", "//XCUIElementTypeButton[@name=\"Register\"]", 15);
                break;
            default:
                System.out.println("Neither Android nor iOS");
                break;
        }
    }

    public static WebElement textfieldUsername() throws InterruptedException {

        WebElement element = null;

            switch (deviceType) {

                case "Android":
                    Functions.waitForElement("classname", "android.widget.EditText", 10);
                    List<WebElement> elements = androidDriver.findElements(By.className("android.widget.EditText"));
                    return elements.get(0);

                case "iOS":
                    Functions.waitForElement("xpath", "(//XCUIElementTypeOther[@name=\"Email\"])[3]", 10);
                    element = iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Email\"])[3]"));
                    return element;

                default:
                    element = null;
                    System.out.println("Neither Android nor iOS");
                    return element;

            }

    }

    public static WebElement textUsername() throws InterruptedException {

        WebElement element=null;

            switch (deviceType) {
                case "Android":
                    //List<WebElement> androidElements = androidDriver.findElements(By.className("android.widget.EditText"));
                    //return androidElements.get(0);
                    element = androidDriver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[1]"));
                    return element;
                case "iOS":
                    element = iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Email\"])[3]"));
                    return element;

                default:
                    return element;
            }

    }

    public static WebElement textPassword() throws InterruptedException {

        WebElement element=null;

            switch (deviceType) {
                case "Android":
                    element = androidDriver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[2]"));
                    return element;

                case "iOS":
                    WebElement iOSElement = iosDriver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Password\"]"));
                    return iOSElement;

                default:
                    element = null;
                    System.out.println("Neither Android nor iOS");
                    return element;
            }

    }

    public static void waitForbtnSignIn() throws InterruptedException {
        switch (deviceType) {
            case "Android":
                Functions.waitForElement("xpath", "(//*[@text=\"LOG IN\"])", 5);
                break;
            case "iOS":
                Functions.waitForElement("ID", "Sign In Button", 5);
                break;
            default:
                System.out.println("Neither Android nor iOS");
                break;
        }
    }

    public static WebElement signIn(){
        WebElement element=null;
            switch (deviceType) {
                case "Android":
                    element = androidDriver.findElement(By.xpath("(//*[@text=\"LOG IN\"])"));
                    return element;
                case "iOS":
                    element = iosDriver.findElement(new MobileBy.ByAccessibilityId("Sign In Button"));
                    return element;
                default:
                    System.out.println("Neither Android nor iOS");
                    return element;
            }
    }



}
