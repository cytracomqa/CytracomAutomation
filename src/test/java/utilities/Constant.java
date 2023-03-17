package utilities;

public class Constant {

    private static String fileSeperator = System.getProperty("file.separator");
    public static final String URL = "http://127.0.0.1:4723/wd/hub";
    public static final String httpsProtocols = "TLSv1,TLSv1.1,TLSv1.2";
    //public static final String deviceName = "sdk_gphone_x86_64_arm64";
    //public static final String deviceName = "Google Pixel 3a XL";
    public static final String deviceName = "emulator-5554";
    public static final String version = "8.1";

    public static final String deviceName1 = "GalaxyS10";
    public static final String version1 = "12.0";
    public static final String platformName = "Android";
    public static final String Path_TestData = "/Users/kram3014/Documents/AutomationScripts/Android/src/test/resources/com";
    public static final String File_TestData = "TestData.xlsx";
    public static final String Path_ScreenShot = System.getProperty("user.dir") + fileSeperator + "Screenshots/";
    public static final String appPath = System.getProperty("user.dir") + "/app/builds/cytracom.apk";

    public static final int Col_TestCaseName = 0;
    public static final int Col_Username = 1;
    public static final int Col_Password = 2;
    public static final int Col_Items = 3;
    public static final int Col_Quantities = 4;
    public static final int Col_Card = 5;
    public static final int Col_Result = 6;
    public static final int Col_Orderid = 7;
    public static final int Col_Attachment = 8;
    public static final int Col_TicketType = 9;
    public static final int Col_IssueType = 10;
    public static final int Col_DelOrPick = 11;
    public static final int Col_DelOrPickLoc = 12;
    public static final int Col_Rating = 13;

}
