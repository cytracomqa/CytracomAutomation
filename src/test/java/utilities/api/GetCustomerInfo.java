package utilities.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import pageObjects.BaseClassiOSAndAndroid;
import utilities.DataFileReader;

import java.util.*;

public class GetCustomerInfo extends BaseClassiOSAndAndroid {

    public Map<String, String> headers = new HashMap<>();
//    DataFileReader d = new DataFileReader();
//    String key = d.getData("apiKey");
    String key="";
    static String Store = new DataFileReader().getData("store");
    static String uname = new DataFileReader().getData("username");
    static String encryptedPassword = new DataFileReader().getData("password");
    static String pwd = new String(Base64.getDecoder().decode(encryptedPassword.getBytes()));
    static String env = new DataFileReader().getData("env");

    GetCustomerInfo() {
        super(androidDriver, iosDriver);
        if(deviceType.equalsIgnoreCase("Android")){
            key = new DataFileReader().getData("androidApikey");
        }
        else{
            key = new DataFileReader().getData("iosApikey");
        }
        key="cJN2OXREim9cYxu9erVwfaJ8NsjHv9evvtNP3UQ9";
        String xapikey = key;
        headers.put("Content-Type", "application/json");
        headers.put("x-api-key", xapikey);
        //headers.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiI2VkMxRmpYRmhtWEd2cHo3SnZ4N09NQ1BDejNRTjM5SXh3M3I5T2p2Iiwic2NvcGUiOiJyZWFkX3Byb2ZpbGUgdXBkYXRlX3Byb2ZpbGUgcmVhZF9jb25maWcgcmVhZF9yZXdhcmRzIHdyaXRlX3Jld2FyZHMgcmVhZF9wcm9tb3Rpb25zIHJlc2V0X3Bhc3N3b3JkIHJlYWRfbWVzc2FnZXMgcmVhZF9mZWVkYmFjayB3cml0ZV9mZWVkYmFjayB3cml0ZV9hcmJpdHJhdGlvbnMiLCJzdWIiOnsiZGlnaXRhbF9pZCI6ImMzOTJkZTU4LTA1OTUtNGY2Mi04NGE3LTVhZjkzNjM5MTEzNSIsImxveWFsdHlfaWQiOiIxNzU4NzczMzAwMzc1NzkzNDEyIiwiaGFzaCI6IjU1NjEyN2ZlNGM5ZWFmNzYwZTI2NDgyMTQ2MjhkNGJiZjQwOTk0NzdiNTIyMDIzMjI3ZGQ5M2M0MWFiZjVlY2IifSwiZ3JhbnRfdHlwZSI6InBhc3N3b3JkIiwiZXhwIjoxNjU1ODMzODI1LCJpYXQiOjE2NTU3NDc0MjV9.bdeNMXJ9ctJuKUKQKwB9O-FLoRirOCCWqXQLRAmhdces6vdbZ9GeEUJqAUMdyCvNYBV7bVKzOnq8ZHX0S3SMCOiTv2k5yhS5gs-L_ITTuCKvdF3ta8SDS5Sn9QGBPp6k4VUVmJpyVkznaA3CCERNq_QP5laYd8jOVeephEpdM4sS3ypgFRsBBtNtGk_bLYJEg8SA2CbOiVPbA3BUW5vTzXiZDO1llWO18WAQYg_ulUZfex59zgLGuGOqjPgEztCcaV94-rj3JWKw6xPn5yov6yaBf6mYFv1enHIfZ-eCEKCIde01J-AgtcF2sixacPbzq9zqlnJFLuUqsrJGI1fpCA");
    }

    GetCustomerInfo(String Auth) {
        super(androidDriver, iosDriver);
        if(deviceType.equalsIgnoreCase("Android")){
            key = new DataFileReader().getData("androidApikey");
        }
        else{
            key = new DataFileReader().getData("iosApikey");
        }
        key="cJN2OXREim9cYxu9erVwfaJ8NsjHv9evvtNP3UQ9";
        String xapikey = key;
//        xapikey="R2XDP5zv7naI68ttF8Lt09Eok0b0163q6F1BlWm6";
        headers.put("Content-Type", "application/json");
        headers.put("x-api-key", xapikey);
        headers.put("Authorization", "Bearer " + Auth);
    }

    public static List<String> GetCustomerDetails() throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetCustomerInfo obj = new GetCustomerInfo();

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
        GetCustomerInfo authobj = new GetCustomerInfo(token);
        HttpResponse<JsonNode> Response = Unirest.get("https://api-" + env + ".7-eleven.com/now/customers/customers/profile")
                .headers(authobj.headers)
//                .body("{\n" +
//                        "  \"query\":\"Snacks\",\n" +
//                        "  \"suggest\":false,\n" +
//                        "}")
                .asJson();
        //System.out.println(Response.getBody());         // gives the full response

        JSONObject myObj = Response.getBody().getObject();
//        JSONArray flavors = myObj.getJSONArray("related_flavor_choice");

        List<String> custInfo = new ArrayList<String>();
        custInfo.add(myObj.getString("loyalty_id"));
        custInfo.add(myObj.getString("enroll_date"));
        custInfo.add(String.valueOf(myObj.getInt("rewards_points")));

        return custInfo;
    }


}
