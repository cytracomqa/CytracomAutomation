package utilities.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import utilities.DataFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class GetItemInfo {

    public Map<String, String> headers = new HashMap<>();
    DataFileReader d = new DataFileReader();
    String key = d.getData("apiKey");
    static String Store = new DataFileReader().getData("store");

    GetItemInfo() {
        key="cJN2OXREim9cYxu9erVwfaJ8NsjHv9evvtNP3UQ9";
        String xapikey = key;
        headers.put("Content-Type", "application/json");
        headers.put("x-api-key", xapikey);
        //headers.put("x-api-key", "uXegSDiQLQ8xwgrv1ehJCavoH4v0b7Ww9eMmQBmW");
        headers.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiI2VkMxRmpYRmhtWEd2cHo3SnZ4N09NQ1BDejNRTjM5SXh3M3I5T2p2Iiwic2NvcGUiOiJyZWFkX3Byb2ZpbGUgdXBkYXRlX3Byb2ZpbGUgcmVhZF9jb25maWcgcmVhZF9yZXdhcmRzIHdyaXRlX3Jld2FyZHMgcmVhZF9wcm9tb3Rpb25zIHJlc2V0X3Bhc3N3b3JkIHJlYWRfbWVzc2FnZXMgcmVhZF9mZWVkYmFjayB3cml0ZV9mZWVkYmFjayB3cml0ZV9hcmJpdHJhdGlvbnMiLCJzdWIiOnsiZGlnaXRhbF9pZCI6ImRiNmMyYzYzLWM0YWMtNDAyNi04MmUzLWZlY2JmODI0ZDgxNyIsImxveWFsdHlfaWQiOiIxNzU4NzczMzAwMzc1MTI5ODU2IiwiaGFzaCI6IjEzYTYzOGY5MjAxMGFkMjMzOWRjMWZlYzNkNjFhMTBhZTcyMjczM2E0OTQ4MzczMDY2ZjRiYWQ5MjRkNGVlMGYifSwiZ3JhbnRfdHlwZSI6InBhc3N3b3JkIiwiZXhwIjoxNjUyODk5MjI4LCJpYXQiOjE2NTI4MTI4Mjh9.FXpoY6Tz2Vtd_wGDT7c7rzmrQyoNnplg7BCLW2WIsR3VqonIzuC8KtFVBaKXqxmb5wfGo8MNbSXcy7t2x5e1qdzcpd_G08ACcsvf0q10pwKUliEV6MuJ9wnO62rphIoIO5Edwq7wFoo1Vjw8QNjMRiGXnSit_ywkP0L3uuqd9upVAtw-gktyCOZNZD7l-UdhoqliQnhkQOs8IQLLAyCYe8cZu4gZHnYjTM3JKdnTq13e7cFmESUGkABAWrLbFUygXP1RCzG0ho3OOlhDiwjhlFRHXczi9tLpiFFkEbE9IEiyErQGJFa22dtz3PDIaDY_z0kcCY2cyMlJP8if6Wtryg");
    }

    public static List<String> GetItemNames(int numofItems) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetItemInfo obj = new GetItemInfo();

        HttpResponse<JsonNode> Response = Unirest.post("https://api-test.7-eleven.com/now/inventory/digital/v3/store/" + Store +"/products/search")
                .headers(obj.headers)
                .queryString("query", "Snacks")
                .queryString("suggest", false)
//                .body("{\n" +
//                        "  \"query\":\"Snacks\",\n" +
//                        "  \"suggest\":false,\n" +
//                        "}")
                .asJson();
        //System.out.println(Response.getBody());         // gives the full response

        JSONObject myObj = Response.getBody().getObject();
        JSONArray results = myObj.getJSONArray("Items");

        List<String> itemNames = new ArrayList<String>();

        int len = results.length();
        int randomNum = ThreadLocalRandom.current().nextInt(1, len/2);

        for(int i=randomNum; i<numofItems + randomNum; i++){
            System.out.println(results.getJSONObject(i).getString("name"));
            //System.out.println(results.getJSONObject(i).getInt("availableQuantity"));

            if(!results.getJSONObject(i).isNull("availableQuantity")) {
                if(results.getJSONObject(i).getInt("availableQuantity") > 10) {
                    itemNames.add(results.getJSONObject(i).getString("name"));
                    System.out.println(results.getJSONObject(i).getString("name"));
                    System.out.println(results.getJSONObject(i).getInt("availableQuantity"));
                }
            }
        }
//        System.out.println(results.getJSONObject(0).toString());
//        System.out.println(results.getJSONObject(0).getString("name"));
        return itemNames;
    }

    public static String GetItemSlin(String item, int index) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetItemInfo obj = new GetItemInfo();

        HttpResponse<JsonNode> Response = Unirest.post("https://api-test.7-eleven.com/now/inventory/digital/v3/store/" + Store +"/products/search")
                .headers(obj.headers)
//                .queryString("query", item)
//                .queryString("suggest", false)
                .body("{\n" +
                        "    \"query\": \"" + item + "\",\n" +
                        "    \"suggest\": false\n" +
                        "}")
                .asJson();
        //System.out.println(Response.getBody());         // gives the full response

        JSONObject myObj = Response.getBody().getObject();
        JSONArray results = myObj.getJSONArray("Items");

        int count = myObj.getInt("Count");
        //System.out.println(count);

        String itemSlin = results.getJSONObject(index).getString("product_id");
        System.out.println(itemSlin);

        return itemSlin;
    }

    public static String GetItemSlinName(String item, int index) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetItemInfo obj = new GetItemInfo();

        HttpResponse<JsonNode> Response = Unirest.post("https://api-test.7-eleven.com/now/inventory/digital/v3/store/" + Store +"/products/search")
                .headers(obj.headers)
//                .queryString("query", item)
//                .queryString("suggest", false)
                .body("{\n" +
                        "    \"query\": \"" + item + "\",\n" +
                        "    \"suggest\": false\n" +
                        "}")
                .asJson();
        //System.out.println(Response.getBody());  // gives the full response

        JSONObject myObj = Response.getBody().getObject();
        JSONArray results = myObj.getJSONArray("Items");

        int count = myObj.getInt("Count");
        //System.out.println(count);

        String itemSlin = results.getJSONObject(index).getString("name");
        System.out.println(itemSlin);

        return itemSlin;
    }

    public static String GetItemSlinsNames(String item, int index) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetItemInfo obj = new GetItemInfo();

        String productList = "";

        HttpResponse<JsonNode> Response = Unirest.post("https://api-test.7-eleven.com/now/inventory/digital/v3/store/" + Store +"/products/search")
                .headers(obj.headers)
//                .queryString("query", item)
//                .queryString("suggest", false)
                .body("{\n" +
                        "    \"query\": \"" + item + "\",\n" +
                        "    \"suggest\": false\n" +
                        "}")
                .asJson();
        //System.out.println(Response.getBody());  // gives the full response

        JSONObject myObj = Response.getBody().getObject();
        JSONArray results = myObj.getJSONArray("Items");

        int count = myObj.getInt("Count");
        //System.out.println(count);


            String productID = results.getJSONObject(index).getString("product_id");
        System.out.println(productID);
            int flavorsCount = isDynamic(productID);
                if(flavorsCount!=0) {
                    productList = results.getJSONObject(index).getString("name");
                    return productList;
                }

        return productList;
    }

    public static int GetTotalItems(String item) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetItemInfo obj = new GetItemInfo();

        List<String> productList = new ArrayList<String>();

        HttpResponse<JsonNode> Response = Unirest.post("https://api-test.7-eleven.com/now/inventory/digital/v3/store/" + Store +"/products/search")
                .headers(obj.headers)
//                .queryString("query", item)
//                .queryString("suggest", false)
                .body("{\n" +
                        "    \"query\": \"" + item + "\",\n" +
                        "    \"suggest\": false\n" +
                        "}")
                .asJson();
        //System.out.println(Response.getBody());  // gives the full response

        JSONObject myObj = Response.getBody().getObject();
        JSONArray results = myObj.getJSONArray("Items");

        int count = myObj.getInt("Count");
        //System.out.println(count);

        return count;
    }

    public static String GetItemProductIds(String item, int index) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetItemInfo obj = new GetItemInfo();

        String productIds = "";

        HttpResponse<JsonNode> Response = Unirest.post("https://api-test.7-eleven.com/now/inventory/digital/v3/store/" + Store +"/products/search")
                .headers(obj.headers)
//                .queryString("query", item)
//                .queryString("suggest", false)
                .body("{\n" +
                        "    \"query\": \"" + item + "\",\n" +
                        "    \"suggest\": false\n" +
                        "}")
                .asJson();
        //System.out.println(Response.getBody());  // gives the full response

        JSONObject myObj = Response.getBody().getObject();
        JSONArray results = myObj.getJSONArray("Items");

        int count = myObj.getInt("Count");
        //System.out.println(count);

            String productID = results.getJSONObject(index).getString("product_id");
        System.out.println(" Product ID " + productID);
            int flavorsCount = isDynamic(productID);
            if(flavorsCount!=0) {
                productIds = results.getJSONObject(index).getString("product_id");
                return productIds;
            }

        return productIds;
    }

    public static List<String> GetFlavors(String itemSlin) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetItemInfo obj = new GetItemInfo();
        //System.out.println("https://api-test.7-eleven.com/now/inventory/store/" + Store + "/products/" + itemSlin + "/info");
        HttpResponse<JsonNode> Response = Unirest.get("https://api-test.7-eleven.com/now/inventory/store/" + Store + "/products/" + itemSlin + "/info")
                .headers(obj.headers)
//                .body("{\n" +
//                        "  \"query\":\"Snacks\",\n" +
//                        "  \"suggest\":false,\n" +
//                        "}")
                .asJson();
        //System.out.println(Response.getBody());         // gives the full response

        JSONObject myObj = Response.getBody().getObject();
        JSONArray flavors = myObj.getJSONArray("related_flavor_choice");
        JSONArray sizes = myObj.getJSONArray("related_size_value_choice");

        List<String> flavorsList = new ArrayList<String>();

        for(int i=0; i<flavors.length(); i++){
            flavorsList.add(flavors.getString(i));
        }
        return flavorsList;
    }

    public static List<String> GetSizes(String itemSlin) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetItemInfo obj = new GetItemInfo();

        HttpResponse<JsonNode> Response = Unirest.get("https://api-test.7-eleven.com/now/inventory/store/" + Store + "/products/" + itemSlin + "/info")
                .headers(obj.headers)
//                .body("{\n" +
//                        "  \"query\":\"Snacks\",\n" +
//                        "  \"suggest\":false,\n" +
//                        "}")
                .asJson();
        //System.out.println(Response.getBody());         // gives the full response

        JSONObject myObj = Response.getBody().getObject();
        JSONArray sizes = myObj.getJSONArray("related_size_value_choice");

        List<String> sizesList = new ArrayList<String>();

        for(int i=0; i<sizes.length(); i++){
            sizesList.add(sizes.getString(i));
        }
        return sizesList;
    }

    public static int isDynamic(String itemSlin) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetItemInfo obj = new GetItemInfo();

        HttpResponse<JsonNode> Response = Unirest.get("https://api-test.7-eleven.com/now/inventory/store/" + Store + "/products/" + itemSlin + "/info")
                .headers(obj.headers)
//                .body("{\n" +
//                        "  \"query\":\"Snacks\",\n" +
//                        "  \"suggest\":false,\n" +
//                        "}")
                .asJson();
        System.out.println(Response.getBody());         // gives the full response

        JSONObject myObj = Response.getBody().getObject();
        JSONArray flavors = myObj.getJSONArray("related_flavor_choice");

        int flavorsCount = flavors.length();
        System.out.println(flavorsCount);

        return flavorsCount;
    }
}
