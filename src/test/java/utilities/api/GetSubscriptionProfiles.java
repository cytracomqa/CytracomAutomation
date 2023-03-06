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

public class GetSubscriptionProfiles {

    public Map<String, String> headers = new HashMap<>();
    DataFileReader d = new DataFileReader();
    String key = d.getData("apiKey");
    static String Store = new DataFileReader().getData("store");
    static String uname = new DataFileReader().getData("username");
    static String encryptedPassword = new DataFileReader().getData("password");
    static String pwd = new String(Base64.getDecoder().decode(encryptedPassword.getBytes()));
    static String env = new DataFileReader().getData("env");

    GetSubscriptionProfiles() {
        String xapikey = key;
        headers.put("Content-Type", "application/json");
        headers.put("x-api-key", xapikey);
    }

    public static String GetSubscriptions(String subType, String param) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetSubscriptionProfiles obj = new GetSubscriptionProfiles();

        HttpResponse<JsonNode> Response = Unirest.get("https://api-" + env + ".7-eleven.com/now/cart/subscriptions/plans?country=US&source=7NOW")
                .headers(obj.headers)
//                .queryString("query", "Food")
//                .queryString("suggest", false)
//                .body("{\n" +
//                        "  \"query\":\"Snacks\",\n" +
//                        "  \"suggest\":false,\n" +
//                        "}")
                .asJson();
        //System.out.println(Response.getBody());         // gives the full response

        JSONObject myObj = Response.getBody().getObject();

        if(param.equalsIgnoreCase("MonthlyRate")) {
            if (subType.equalsIgnoreCase("Standard")) {
                JSONArray results = myObj.getJSONArray("subscription_plans").getJSONObject(0).getJSONArray("renewal_options");
                int monthlyRate = results.getJSONObject(0).getInt("price");
                return String.valueOf(monthlyRate);
            } else {
                JSONArray results = myObj.getJSONArray("subscription_plans").getJSONObject(1).getJSONArray("renewal_options");
                int monthlyRate = results.getJSONObject(0).getInt("price");
                return String.valueOf(monthlyRate);
            }
        }
        else if(param.equalsIgnoreCase("FeesText")){
            if (subType.equalsIgnoreCase("Standard")) {
                JSONArray results = myObj.getJSONArray("subscription_plans").getJSONObject(0).getJSONArray("features_offered");
                String feesText = results.getJSONObject(0).getString("name");
                return feesText;
            }
            else {
                JSONArray results = myObj.getJSONArray("subscription_plans").getJSONObject(0).getJSONArray("features_offered");
                String feesText = results.getJSONObject(1).getString("name");
                return feesText;
            }
        }
        else{
            return "";
        }
    }


}
