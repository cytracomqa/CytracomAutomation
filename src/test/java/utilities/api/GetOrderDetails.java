package utilities.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import utilities.DataFileReader;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GetOrderDetails {

    public Map<String, String> headers = new HashMap<>();
    DataFileReader d = new DataFileReader();
    String key = d.getData("apiKey");
    static String Store = new DataFileReader().getData("store");
    static String uname = new DataFileReader().getData("username");
    static String encryptedPassword = new DataFileReader().getData("password");
    static String pwd = new String(Base64.getDecoder().decode(encryptedPassword.getBytes()));
    static String env = new DataFileReader().getData("env");

    GetOrderDetails() {
        key="cJN2OXREim9cYxu9erVwfaJ8NsjHv9evvtNP3UQ9";
        String xapikey = key;
        headers.put("Content-Type", "application/json");
        headers.put("x-api-key", xapikey);
        //headers.put("x-api-key", "uXegSDiQLQ8xwgrv1ehJCavoH4v0b7Ww9eMmQBmW");
    }

    GetOrderDetails(String Auth) {
        key="cJN2OXREim9cYxu9erVwfaJ8NsjHv9evvtNP3UQ9";
        String xapikey = key;
//        xapikey="R2XDP5zv7naI68ttF8Lt09Eok0b0163q6F1BlWm6";
        headers.put("Content-Type", "application/json");
        headers.put("x-api-key", xapikey);
        headers.put("Authorization", "Bearer " + Auth);
    }

    public static List<String> GetOrderInfo() throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        List<String> orderInfo = new ArrayList<String>();

        GetOrderDetails obj = new GetOrderDetails();

        HttpResponse<JsonNode> authResponse = Unirest.post("https://api-stage.7-eleven.com/auth/jwt/token")
                .headers(obj.headers)
                .body("{\n" +
                        "    \"client_id\": \"6VC1FjXFhmXGvpz7Jvx7OMCPCz3QN39Ixw3r9Ojv\",\n" +
                        "    \"client_secret\": \"uxhG0yF7AnYcjeczAcgv7J0DrbpVQxss8TNdYRGEj2uWR0UDTKY9QyuLhztiZbehzEZ8O9B8YeF5cbhgOlT9bdnF0o3hJDGCgHEWO3B3MDeH3zE3i31JzA4INjdTZf8o\",\n" +
                        "    \"grant_type\": \"password\",\n" +
                        "    \"username\": \"" + uname + "\",\n" +
                        "    \"password\": \"" + pwd + "\"\n" +
                        "}")
                .asJson();
        //System.out.println(authResponse.getBody());
        JSONObject authObj = authResponse.getBody().getObject();

        String token = authObj.getString("access_token");
        GetOrderDetails authobj = new GetOrderDetails(token);

        HttpResponse<JsonNode> Response = Unirest.post("https://api-" + env + ".7-eleven.com/now/customers/orders/history")
                .headers(authobj.headers)
                .body("{\n" +
                        "    \"order_status\": [\n" +
                        "        \"submitted\"\n" +
                        "    ],\n" +
                        "    \"limit\": 1,\n" +
                        "    \"lastKey\": 0,\n" +
                        "    \"country\": \"US\"\n" +
                        "}")
                .asJson();
        //System.out.println(Response.getBody());         // gives the full response

        JSONObject myObj = Response.getBody().getObject();
        JSONArray orders = myObj.getJSONArray("orders");
        String orderid = orders.getJSONObject(0).getString("order_id");
            orderInfo.add(orderid.substring(0,4) + " " + orderid.substring(4,8) + " " + orderid.substring(8, 12) + " " + orderid.substring(12));
        String firstName = orders.getJSONObject(0).getJSONObject("user_profile").getString("first_name");
            //orderInfo.add(firstName);
        String lastName = orders.getJSONObject(0).getJSONObject("user_profile").getString("last_name");
            orderInfo.add(firstName + " " + lastName);
        String storeAddress = orders.getJSONObject(0).getJSONObject("shipping").getJSONObject("dropoff").getString("street");
            //orderInfo.add(storeAddress);
        String storeCity = orders.getJSONObject(0).getJSONObject("store_details").getString("city");
            //orderInfo.add(storeCity);
        String storeState = orders.getJSONObject(0).getJSONObject("store_details").getString("state");
            //orderInfo.add(storeState);
        String storeZip = orders.getJSONObject(0).getJSONObject("store_details").getString("zip");
            orderInfo.add(storeAddress + "\n" + storeCity + ", " + storeState + " " + storeZip);
        //XCUIElementTypeStaticText[@name="2711 McKinney Ave Dallas, TX 75204"]
        //XCUIElementTypeStaticText[@name="2711 McKinney Ave Dallas, TX 75204"]
        //XCUIElementTypeStaticText[@name="2711 McKinney Ave Dallas, TX 75204"]
        String userPhoneNum = orders.getJSONObject(0).getJSONObject("user_profile").getString("phone_number");
            orderInfo.add("(" + userPhoneNum.substring(0,3) + ") " + userPhoneNum.substring(3,6) + "-" + userPhoneNum.substring(6));
        String cardBrand = orders.getJSONObject(0).getJSONObject("payment").getString("brand");
            //orderInfo.add(cardBrand);
        String cardLast4 = orders.getJSONObject(0).getJSONObject("payment").getString("card");
            orderInfo.add("Paid with " + cardBrand + " ending with " + cardLast4);
        int total = orders.getJSONObject(0).getInt("original_total_including_tip");
        String totalInString = String.valueOf(total);
        int len = totalInString.length();
        String finalTotal = "$" + totalInString.substring(0,len-2) + "." + totalInString.substring(len-2);
            orderInfo.add(finalTotal);
//        System.out.println(finalTotal);
        String txnDatetime = orders.getJSONObject(0).getJSONObject("promo_details").getJSONObject("pmb").getString("txn_date");
            //orderInfo.add(txnDatetime);
        String orderStatus = orders.getJSONObject(0).getString("order_status");
            orderInfo.add(orderStatus.toUpperCase());
        JSONArray orderItems = orders.getJSONObject(0).getJSONArray("items");
//        System.out.println(orderItems.length());
        JSONArray summaryItems = orders.getJSONObject(0).getJSONArray("summary");
        //int tax = orders.getJSONObject(0).getInt("tax");

//        System.out.println(orderid);
//        System.out.println(firstName);
//        System.out.println(lastName);
//        System.out.println(storeAddress + "," + storeCity + "," + storeState + "," + storeZip);
//        System.out.println(userPhoneNum);
//        System.out.println(cardBrand + "," + cardLast4);
//        System.out.println(finalTotal);
//        System.out.println(txnDatetime);
//        System.out.println(orderStatus);
        for(int i=0; i<orderItems.length();i++){
//            System.out.println(orderItems.getJSONObject(i).getString("name"));
                orderInfo.add(String.valueOf(orderItems.getJSONObject(i).getInt("quantity")) + " " + orderItems.getJSONObject(i).getString("name"));
                int qty = orderItems.getJSONObject(i).getInt("quantity");
            String itemPriceInString = String.valueOf((orderItems.getJSONObject(i).getInt("price") * qty));
            int lenOfPrice = itemPriceInString.length();
            String itemPrice = "$" + itemPriceInString.substring(0,lenOfPrice-2) + "." + itemPriceInString.substring(lenOfPrice-2);
//            System.out.println(itemPrice);
                orderInfo.add(itemPrice);
        }
        for(int i=0; i<summaryItems.length();i++){
            if(!summaryItems.getJSONObject(i).getString("display_name").equals("Tax & Fees")) {
//                System.out.println(summaryItems.getJSONObject(i).getString("display_name"));
                    orderInfo.add(summaryItems.getJSONObject(i).getString("display_name"));
//                System.out.println(summaryItems.getJSONObject(i).getString("display_value"));
                    orderInfo.add(summaryItems.getJSONObject(i).getString("display_value"));
            }
            else{
                JSONArray feeDetails = orders.getJSONObject(0).getJSONArray("summary").getJSONObject(i).getJSONArray("details");
                for(int j=0; j<feeDetails.length(); j++){
//                    System.out.println(feeDetails.getJSONObject(j).getString("display_name"));
                        orderInfo.add(feeDetails.getJSONObject(j).getString("display_name"));
//                    System.out.println(feeDetails.getJSONObject(j).getString("display_value"));
                        orderInfo.add(feeDetails.getJSONObject(j).getString("display_value"));
                }
            }
        }
        return orderInfo;
    }


}
