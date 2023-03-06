package utilities.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import utilities.*;

import java.util.HashMap;
import java.util.Map;

public class LineItemEdit {

    public Map<String, String> headers = new HashMap<>();

    public static Map<String, String> headersForGetOrder = new HashMap<>();
    public static Map<String, String> headersForLIE = new HashMap<>();
    public static void GetOrderHeaders(String AccessToken) {
        headersForGetOrder.put("Content-Type", "application/json");
        headersForGetOrder.put("accept", "*/*");
        headersForGetOrder.put("Authorization", "Bearer " + AccessToken);
        headersForGetOrder.put("x-sei-zd-role","USER-7NOW VOC Admin - TEST");
    }

    public static void LineItemUpdate() {
        DataFileReader d = new DataFileReader();
        String xapikey = d.getData("apiKey");
        headersForLIE.put("Content-Type", "application/json");
        headersForLIE.put("accept", "*/*");
        headersForLIE.put("x-api-key", xapikey);
    }

    LineItemEdit() {
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("accept", "*/*");
        //headers.put("x-api-key", "uXegSDiQLQ8xwgrv1ehJCavoH4v0b7Ww9eMmQBmW");
        //headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6IjNmZmZhNmU3LTM0OTItNDExMi1iZDQ5LTg4NGI1NzEzMGYyOSIsImxveWFsdHlfaWQiOiIxNzU4NzczMzAwMzcwNjU0OTczIiwic2NvcGUiOlsic2lnbmVkSW4iXSwiYWNjZXNzX3Rva2VuIjoidklUUlZXMUp5bmJLS25aa0dHVldRMjY1azFnRU5kIiwicmVmcmVzaF90b2tlbiI6InJqN0taVk5ZVjY1MThNSVBXSWZWWnVEME1yVE9WTSIsImlhdCI6MTU5NDg0MTk5OSwiZXhwIjoxNTk0OTI4Mzk5fQ.09Ggxz3nROr52AGpkzRbxdDd2_5803WOqwPUEg2yujg");
    }

    public static void LineItemE(int iTestCaseRow) throws Exception {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        String OrderID = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Orderid);

        UpdateStatus.updateStatusInProgress(OrderID);

        String response = ZDAppAuth();
        //System.out.println("Response - " + response);

        Thread.sleep(5000);

        JSONArray results = GetOrderByID(OrderID, response);

        int len = results.length();

        String ProdID = results.getJSONObject(0).getString("product_id");
        System.out.println(ProdID);
        int Quantity = results.getJSONObject(0).getInt("quantity");
        System.out.println(Quantity);
        LineItemsUpdate(OrderID, ProdID, Quantity);

        if(Quantity > 1){
            for(int i=1; i<Quantity; i++){
                LineItemsFulFill(OrderID, ProdID, i);
            }
        }

        for(int i=1; i<len; i++) {
            ProdID = results.getJSONObject(i).getString("product_id");
            System.out.println(ProdID);
            Quantity = results.getJSONObject(i).getInt("quantity");
            System.out.println(Quantity);
            for(int j=0; j<Quantity; j++){
                LineItemsFulFill(OrderID, ProdID, j);
            }
        }

        //completeOrder(OrderID);
    }

    public static String ZDAppAuth() throws UnirestException {

        LineItemEdit obj = new LineItemEdit();

        HttpResponse<JsonNode> Response = Unirest.post("https://api-test.7-eleven.com/v1/now/oauth/accesstoken?grant_type=client_credentials")
                .headers(obj.headers)
                .field("client_id", "MdhsGKJFWnFeIB13rYNMuCOH8fcwQSgp")
                .field("client_secret","bKG589UqGEGch9rV")
                .asJson();
        //System.out.println(Response.getBody());         // gives the full response

        JSONObject myObj = Response.getBody().getObject();
        String results = myObj.getString("access_token");
        return results;
    }

    public static JSONArray GetOrderByID(String OrderID, String AccessToken) throws UnirestException, InterruptedException {

        LineItemEdit obj = new LineItemEdit();
        GetOrderHeaders(AccessToken);

        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://api-test.7-eleven.com/v1/now/admin/orders/" + OrderID)
                .headers(headersForGetOrder)
                .asJson();
        Thread.sleep(5000);

        System.out.println(jsonResponse.getBody()); // gives the full response

        JSONObject myObj = jsonResponse.getBody().getObject();
        JSONArray results = myObj.getJSONArray("original_items");

        return results;
    }

    public static void LineItemsUpdate(String OrderID, String ProductID, int Quantity) throws UnirestException {

        LineItemUpdate();

        HttpResponse<JsonNode> jsonResponse = Unirest.put("https://api-test.7-eleven.com/now/order/order/item/index/" + OrderID + "/" + ProductID)
                .headers(headersForLIE)
                .body("{\n" +
                        "  \"index\":\"0\",\n" +
                        "  \"employeeId\":\"19\",\n" +
                        "  \"status\":\"canceled\",\n" +
                        "  \"reason\": \"Training\"\n" +
                        "}")
                .asJson();
        System.out.println(ProductID + " 0");
        System.out.println(jsonResponse.getBody());         // gives the full json response

    }

    public static void LineItemsFulFill(String OrderID, String ProductID, int index) throws UnirestException {


        HttpResponse<JsonNode> jsonResponse = Unirest.put("https://api-test.7-eleven.com/now/order/order/item/index/" + OrderID + "/" + ProductID)
                .headers(headersForLIE)
                .body("{\n" +
                        "  \"index\":\"" + index + "\",\n" +
                        "  \"employeeId\":\"19\",\n" +
                        "  \"status\":\"complete\",\n" +
                        "  \"reason\": \"Training\"\n" +
                        "}")
                .asJson();

        System.out.println(ProductID + " " + index);
        System.out.println(jsonResponse.getBody());

    }


    public static JsonNode updateStatusOutForDelivery(String orderID) throws UnirestException {

        UpdateStatus obj = new UpdateStatus();

        HttpResponse<JsonNode> jsonResponse = Unirest.put("https://api-test.7-eleven.com/now/order/order/" + orderID)
                .headers(obj.headers)
                .body("{\n" +
                        "  \"status\":\"out_for_delivery\",\n" +
                        "  \"employeeId\":\"19\"\n" +
                        "}")
                .asJson();
        //System.out.println(jsonResponse.getBody());         // gives the full json response

        return jsonResponse.getBody();
    }

    public static JsonNode updateStatusDelivered(String orderID) throws UnirestException {

        UpdateStatus obj = new UpdateStatus();

        HttpResponse<JsonNode> jsonResponse = Unirest.put("https://api-test.7-eleven.com/now/order/order/" + orderID)
                .headers(obj.headers)
                .body("{\n" +
                        "  \"status\":\"delivered\",\n" +
                        "  \"employeeId\":\"19\"\n" +
                        "\n" +
                        "}")
                .asJson();
        //System.out.println(jsonResponse.getBody());         // gives the full json response

        return jsonResponse.getBody();
    }
}
