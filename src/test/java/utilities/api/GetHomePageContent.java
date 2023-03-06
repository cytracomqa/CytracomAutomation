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

public class GetHomePageContent {

    public Map<String, String> headers = new HashMap<>();
    DataFileReader d = new DataFileReader();
    String key = d.getData("apiKey");
    static String Store = new DataFileReader().getData("store");

    GetHomePageContent() {
        key="cJN2OXREim9cYxu9erVwfaJ8NsjHv9evvtNP3UQ9";
        String xapikey = key;
        headers.put("Content-Type", "application/json");
        headers.put("x-api-key", xapikey);
        //headers.put("x-api-key", "uXegSDiQLQ8xwgrv1ehJCavoH4v0b7Ww9eMmQBmW");
        headers.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiI2VkMxRmpYRmhtWEd2cHo3SnZ4N09NQ1BDejNRTjM5SXh3M3I5T2p2Iiwic2NvcGUiOiJyZWFkX3Byb2ZpbGUgdXBkYXRlX3Byb2ZpbGUgcmVhZF9jb25maWcgcmVhZF9yZXdhcmRzIHdyaXRlX3Jld2FyZHMgcmVhZF9wcm9tb3Rpb25zIHJlc2V0X3Bhc3N3b3JkIHJlYWRfbWVzc2FnZXMgcmVhZF9mZWVkYmFjayB3cml0ZV9mZWVkYmFjayB3cml0ZV9hcmJpdHJhdGlvbnMiLCJzdWIiOnsiZGlnaXRhbF9pZCI6ImRiNmMyYzYzLWM0YWMtNDAyNi04MmUzLWZlY2JmODI0ZDgxNyIsImxveWFsdHlfaWQiOiIxNzU4NzczMzAwMzc1MTI5ODU2IiwiaGFzaCI6IjEzYTYzOGY5MjAxMGFkMjMzOWRjMWZlYzNkNjFhMTBhZTcyMjczM2E0OTQ4MzczMDY2ZjRiYWQ5MjRkNGVlMGYifSwiZ3JhbnRfdHlwZSI6InBhc3N3b3JkIiwiZXhwIjoxNjUyODk5MjI4LCJpYXQiOjE2NTI4MTI4Mjh9.FXpoY6Tz2Vtd_wGDT7c7rzmrQyoNnplg7BCLW2WIsR3VqonIzuC8KtFVBaKXqxmb5wfGo8MNbSXcy7t2x5e1qdzcpd_G08ACcsvf0q10pwKUliEV6MuJ9wnO62rphIoIO5Edwq7wFoo1Vjw8QNjMRiGXnSit_ywkP0L3uuqd9upVAtw-gktyCOZNZD7l-UdhoqliQnhkQOs8IQLLAyCYe8cZu4gZHnYjTM3JKdnTq13e7cFmESUGkABAWrLbFUygXP1RCzG0ho3OOlhDiwjhlFRHXczi9tLpiFFkEbE9IEiyErQGJFa22dtz3PDIaDY_z0kcCY2cyMlJP8if6Wtryg");
    }

    public static List<String> GetSpecialCategoryProducts() throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        GetHomePageContent obj = new GetHomePageContent();

        List<String> ProdNames = new ArrayList<String>();

        HttpResponse<JsonNode> Response = Unirest.get("https://api-test.7-eleven.com/now/content/v1/delivery/content/find/home-page?v=5&store_id=" + Store + "&reco=on")
                .headers(obj.headers)
                .asJson();
        //System.out.println(Response.getBody());         // gives the full response

        JSONObject myObj = Response.getBody().getObject().getJSONObject("48493");
        JSONArray results = myObj.getJSONArray("content");

        for(int i=0; i< results.length(); i++){
            //System.out.println(results.getJSONObject(i).getString("name"));
            ProdNames.add(results.getJSONObject(i).getString("name"));
        }

        return ProdNames;

    }


}
