package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.DataProvider;

import java.util.List;

import static pageObjects.BaseClassiOSAndAndroid.androidDriver;

public class TestData {

    @DataProvider(name = "data-provider")
    public static Object[][] dpMethod() {
        return new Object[][]{{"autotest@example.com", "TWFuYWdlcjI="}, {"testuser1999@example.com", "TWFuYWdlcjI="},
                {"autotest1@example.com", "TWFuYWdlcjI="}, {"autotest2@example.com", "TWFuYWdlcjI="},
                {"autotest3@example.com", "TWFuYWdlcjI="}, {"autotest4@example.com", "TWFuYWdlcjI="},
                {"autotest5@example.com", "TWFuYWdlcjI="}
        };
    }

    @DataProvider(name = "data-provider-username")
    public static Object[][] getUsernameData(){
        return new Object[][] {
                //testdf10@g.com, true for standard else false,
                // timestamp to generate random email and not in sequence like 10, 11 etc. if 10, 11 needed make it blank ""
                // 3 for number of users
//                {"p", "61", "@7.com", "true", "", "24", "VGVzdEAxMjM0"},
//                {"p", "24", "@7.edu", "true", "", "30", "VGVzdEAxMjM0"},
                {"kn", "12", "@uc.edu", "true", "", "1", "VGVzdEAxMjM0"}
                //for Device Farm use below
//                {"testdfarm", "10", "@example.com", "true", "timestamp", "1", "TWFuYWdlcjI="},
//                {"testdfarm", "10", "@uc.edu", "true", "timestamp", "1", "TWFuYWdlcjI="}
        };
    }







    @DataProvider(name = "data-provider-bundleid")
    public static Object[][] getBundleId() {
        return new Object[][]{
                {"ios", "com.PhoenixiOS"}
        };
    }

    @DataProvider(name = "data-provider-packagename")
    public static Object[][] getPackageName() {
        String packageName = ((AndroidDriver) androidDriver).getCurrentPackage();
        return new Object[][]{
                {"Android", packageName}
        };
    }



    @DataProvider(name = "CustomerSupportOptions")
    public static Object[][] getCustSupportOptions() {
        return new Object[][]{
                {"I need support about an order", ""},
                {"I'm having trouble placing an order", ""},
                {"I need support with my subscriptions"},
                {"Give us feedback"}
        };
    }

    @DataProvider(name = "ActiveOrdersCustomerSupportOptions")
    public static Object[][] getCustSupportOptionsForActiveOrders() {
        return new Object[][]{
                {"How to cancel my order", ""},
                {"I want to change my delivery or pickup address", ""},
                {"I want to change my phone number", ""},
                {"I want to cancel my order", ""},
                {"I want to check the status of my order ", ""}
        };
    }

    @DataProvider(name = "PastOrdersCustomerSupportOptions")
    public static Object[][] getCustSupportOptionsForPastOrders() {
        return new Object[][]{
                {"I had an issue with my items", ""},
                {"My whole order was incorrect", ""},
                {"Items were removed or substituted", ""},
                {"Promo code didn't apply", ""},
                {"My order was never delivered", ""},
                {"I had a problem with my driver", ""},
                {"Why was my order canceled? ", ""},
                {"My order was canceled", ""},
                {"Another Issue", ""}
        };
    }

    @DataProvider(name = "x-api-key")
    public static Object[][] getxapikey() {
        return new Object[][]{
                {"apiKey", "cJN2OXREim9cYxu9erVwfaJ8NsjHv9evvtNP3UQ9"},
                {"androidApiKey", "4iywNSs8y114BjJA0cp2s5RObCJvnPa7pEDkJTBc"},
                {"iOSApiKey", "R2XDP5zv7naI68ttF8Lt09Eok0b0163q6F1BlWm6"}
        };
    }

    @DataProvider(name = "cards")
    public static Object[][] getCardDetails() {
        return new Object[][]{
                {"cardLast4", "NDI0Mg=="},
                {"card", "NDI0MjQyNDI0MjQyNDI0Mg=="},
                {"incompleteCard", "NDI0MjQyNDI0MjQyNDI0"}
        };
    }

    @DataProvider(name = "environment")
    public static Object[][] getEnvironmentDetails() {
        return new Object[][]{
                {"staging", "test"},
                {"mapi", "stage"}
        };
    }


    @DataProvider(name = "genericPasswords")
    public static Object[][] getPasswords() {
        return new Object[][]{
                {"password1", "TWFuYWdlcjI="}
        };
    }



}


