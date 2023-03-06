package pageObjects;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.ITestNGListener;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class BaseClassiOSAndAndroid {

    public static AndroidDriver androidDriver;
    public static IOSDriver iosDriver;
    public static int numofItems;
    public static String deviceType = "";
    public static List<String> itemNames;
    public static List<Integer> itemQuantities;
    public static String username = "";
    public static String password = "";
    public static int counter=0;
    public static int counterForNotifications=0;
    public static int counterForNotTrack=0;
    public static String globalStoreID = "35832";
    public static String rewardsPoints = "";
    public static List<String> users = new ArrayList<String>();
    static Object[][] obj = TestData.getUsernameData();
    public static int countForGP = Integer.parseInt(obj[0][1].toString());
    //    public static int countForSGP = Integer.parseInt(obj[1][1].toString());
    public static String OrderID = "";

    public static boolean bResult;

    public BaseClassiOSAndAndroid(AndroidDriver androidDriver, IOSDriver iosDriver){
        BaseClassiOSAndAndroid.androidDriver = androidDriver;
        BaseClassiOSAndAndroid.iosDriver = iosDriver;
        BaseClassiOSAndAndroid.bResult = true;
    }

    @DataProvider(name = "data-provider")
    public static Object[][] dpMethod(){
        return new Object[][] {{"autotest@example.com","TWFuYWdlcjI="}, {"testuser1999@example.com","TWFuYWdlcjI="}};
    }

}
 