package utilities.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import utilities.DataFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotspotLocations {

    public Map<String, String> headers = new HashMap<>();
    DataFileReader d = new DataFileReader();
    String key = d.getData("apiKey");
    static String Store = new DataFileReader().getData("store");
    static String uname = new DataFileReader().getData("username");
    static String pwd = new DataFileReader().getData("password");

    HotspotLocations() {
        String xapikey = key;
        headers.put("Content-Type", "application/json");
//        headers.put("x-api-key", xapikey);
        //headers.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiI2VkMxRmpYRmhtWEd2cHo3SnZ4N09NQ1BDejNRTjM5SXh3M3I5T2p2Iiwic2NvcGUiOiJyZWFkX3Byb2ZpbGUgdXBkYXRlX3Byb2ZpbGUgcmVhZF9jb25maWcgcmVhZF9yZXdhcmRzIHdyaXRlX3Jld2FyZHMgcmVhZF9wcm9tb3Rpb25zIHJlc2V0X3Bhc3N3b3JkIHJlYWRfbWVzc2FnZXMgcmVhZF9mZWVkYmFjayB3cml0ZV9mZWVkYmFjayB3cml0ZV9hcmJpdHJhdGlvbnMiLCJzdWIiOnsiZGlnaXRhbF9pZCI6ImMzOTJkZTU4LTA1OTUtNGY2Mi04NGE3LTVhZjkzNjM5MTEzNSIsImxveWFsdHlfaWQiOiIxNzU4NzczMzAwMzc1NzkzNDEyIiwiaGFzaCI6IjU1NjEyN2ZlNGM5ZWFmNzYwZTI2NDgyMTQ2MjhkNGJiZjQwOTk0NzdiNTIyMDIzMjI3ZGQ5M2M0MWFiZjVlY2IifSwiZ3JhbnRfdHlwZSI6InBhc3N3b3JkIiwiZXhwIjoxNjU1ODMzODI1LCJpYXQiOjE2NTU3NDc0MjV9.bdeNMXJ9ctJuKUKQKwB9O-FLoRirOCCWqXQLRAmhdces6vdbZ9GeEUJqAUMdyCvNYBV7bVKzOnq8ZHX0S3SMCOiTv2k5yhS5gs-L_ITTuCKvdF3ta8SDS5Sn9QGBPp6k4VUVmJpyVkznaA3CCERNq_QP5laYd8jOVeephEpdM4sS3ypgFRsBBtNtGk_bLYJEg8SA2CbOiVPbA3BUW5vTzXiZDO1llWO18WAQYg_ulUZfex59zgLGuGOqjPgEztCcaV94-rj3JWKw6xPn5yov6yaBf6mYFv1enHIfZ-eCEKCIde01J-AgtcF2sixacPbzq9zqlnJFLuUqsrJGI1fpCA");
    }

    HotspotLocations(String Auth) {
        String xapikey = key;
        headers.put("Content-Type", "application/json");
        headers.put("x-api-key", xapikey);
        headers.put("Authorization", "Bearer " + Auth);
    }

    public static String GetHotspotLocations() throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        HotspotLocations obj = new HotspotLocations();

        HttpResponse<JsonNode> authResponse = Unirest.get("https://d21c9usmdrkboa.cloudfront.net/hotspots/qa/locations.json")
                .headers(obj.headers)
//                .body("{\n" +
//                        "    \"client_id\": \"6VC1FjXFhmXGvpz7Jvx7OMCPCz3QN39Ixw3r9Ojv\",\n" +
//                        "    \"client_secret\": \"uxhG0yF7AnYcjeczAcgv7J0DrbpVQxss8TNdYRGEj2uWR0UDTKY9QyuLhztiZbehzEZ8O9B8YeF5cbhgOlT9bdnF0o3hJDGCgHEWO3B3MDeH3zE3i31JzA4INjdTZf8o\",\n" +
//                        "    \"grant_type\": \"password\",\n" +
//                        "    \"username\": \"" + uname + "\",\n" +
//                        "    \"password\": \"" + pwd + "\"\n" +
//                        "}")
                .asJson();
        //System.out.println(authResponse.getBody());
        JSONObject authObj = authResponse.getBody().getObject();
        JSONArray res = authResponse.getBody().getArray();
        String input = res.getJSONObject(4).getString("name");
        System.out.println(input);

        authResponse = Unirest.post("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Woodland+Park+Picnic+Shelter+%231&key=AIzaSyAZclQYSdLUsFo5fuXoRh2tOuu3NkhIGCE&sessiontoken=782c8a37-9c42-4f90-a438-811d26cb7cfc")
                .headers(obj.headers)
//                .body("{\n" +
//                        "    \"client_id\": \"6VC1FjXFhmXGvpz7Jvx7OMCPCz3QN39Ixw3r9Ojv\",\n" +
//                        "    \"client_secret\": \"uxhG0yF7AnYcjeczAcgv7J0DrbpVQxss8TNdYRGEj2uWR0UDTKY9QyuLhztiZbehzEZ8O9B8YeF5cbhgOlT9bdnF0o3hJDGCgHEWO3B3MDeH3zE3i31JzA4INjdTZf8o\",\n" +
//                        "    \"grant_type\": \"password\",\n" +
//                        "    \"username\": \"" + uname + "\",\n" +
//                        "    \"password\": \"" + pwd + "\"\n" +
//                        "}")
                .asJson();
        //System.out.println(authResponse.getBody());
        authObj = authResponse.getBody().getObject();
        res = authObj.getJSONArray("predictions");
        String placeid = res.getJSONObject(0).getString("place_id");
        //System.out.println(placeid);

        authResponse = Unirest.get("https://maps.googleapis.com/maps/api/place/details/json?placeid=" + placeid + "&key=AIzaSyAZclQYSdLUsFo5fuXoRh2tOuu3NkhIGCE&sessiontoken=782c8a37-9c42-4f90-a438-811d26cb7cfc&types=address&fields=address_component,formatted_address,geometry,place_id,type")
                .headers(obj.headers)
//                .body("{\n" +
//                        "    \"client_id\": \"6VC1FjXFhmXGvpz7Jvx7OMCPCz3QN39Ixw3r9Ojv\",\n" +
//                        "    \"client_secret\": \"uxhG0yF7AnYcjeczAcgv7J0DrbpVQxss8TNdYRGEj2uWR0UDTKY9QyuLhztiZbehzEZ8O9B8YeF5cbhgOlT9bdnF0o3hJDGCgHEWO3B3MDeH3zE3i31JzA4INjdTZf8o\",\n" +
//                        "    \"grant_type\": \"password\",\n" +
//                        "    \"username\": \"" + uname + "\",\n" +
//                        "    \"password\": \"" + pwd + "\"\n" +
//                        "}")
                .asJson();
        //System.out.println(authResponse.getBody());
        authObj = authResponse.getBody().getObject();
        res = authResponse.getBody().getArray();
        //System.out.println(res);


        JSONObject subObj = res.getJSONObject(0).getJSONObject("result");
        String locName = subObj.getString("formatted_address");

        System.out.println(locName);

        return locName;
    }

    //https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Green Lake Park&key=AIzaSyAZclQYSdLUsFo5fuXoRh2tOuu3NkhIGCE&sessiontoken=782c8a37-9c42-4f90-a438-811d26cb7cfc
    //https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJEbIG9xUUkFQReKeuJo4qVKk&key=AIzaSyAZclQYSdLUsFo5fuXoRh2tOuu3NkhIGCE&sessiontoken=782c8a37-9c42-4f90-a438-811d26cb7cfc&types=address&fields=address_component,formatted_address,geometry,place_id,type


}
