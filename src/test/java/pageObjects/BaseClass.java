package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

public class BaseClass {

    public static AndroidDriver driver;

    public static boolean bResult;

    public  BaseClass(AndroidDriver driver){
        BaseClass.driver = driver;
        BaseClass.bResult = true;
    }
}