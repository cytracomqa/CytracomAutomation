package utilities.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.impl.client.HttpClientBuilder;
import utilities.DataFileReader;

import java.util.HashMap;
import java.util.Map;

public class UpdateStatus {

        public Map<String, String> headers = new HashMap<>();



        UpdateStatus() {
            DataFileReader d = new DataFileReader();
            String xapikey = d.getData("apiKey");
            xapikey="cJN2OXREim9cYxu9erVwfaJ8NsjHv9evvtNP3UQ9";
            headers.put("Content-Type", "application/json");
            headers.put("accept", "*/*");
            //headers.put("x-api-key", "wdWxS2HOR596qoAIwJrBiaa1BqdN4wql45s39HUB");
            headers.put("x-api-key", xapikey);
            headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6IjNmZmZhNmU3LTM0OTItNDExMi1iZDQ5LTg4NGI1NzEzMGYyOSIsImxveWFsdHlfaWQiOiIxNzU4NzczMzAwMzcwNjU0OTczIiwic2NvcGUiOlsic2lnbmVkSW4iXSwiYWNjZXNzX3Rva2VuIjoidklUUlZXMUp5bmJLS25aa0dHVldRMjY1azFnRU5kIiwicmVmcmVzaF90b2tlbiI6InJqN0taVk5ZVjY1MThNSVBXSWZWWnVEME1yVE9WTSIsImlhdCI6MTU5NDg0MTk5OSwiZXhwIjoxNTk0OTI4Mzk5fQ.09Ggxz3nROr52AGpkzRbxdDd2_5803WOqwPUEg2yujg");
        }

    public static void updateStatusDelivery(String orderID) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        JsonNode response = updateStatusInProgress(orderID);
        System.out.println("In Progress - " + response);

            if(response.toString().equals("{\"status\":\"success\"}")){
                response = updateStatusReady(orderID);
                System.out.println("Ready - " + response);
            }

            else{
                System.exit(0);
            }

            if(response.toString().equals("{\"status\":\"success\"}")){
                response = updateStatusOutForDelivery(orderID);
                System.out.println("OutForDelivery - " + response);
            }

            else{
                System.exit(0);
            }

            if(response.toString().equals("{\"status\":\"success\"}")){
                response = updateStatusDelivered(orderID);
                System.out.println("Delivered - " + response);
            }

            else{
                System.exit(0);
            }

    }

    public static void updateStatusPickup(String orderID) throws UnirestException {

        HttpClientBuilder clientBuilder = Client.getClient();
        Unirest.setHttpClient(clientBuilder.build());

        JsonNode response = updateStatusInProgress(orderID);
        System.out.println("In Progress - " + response);

        if(response.toString().equals("{\"status\":\"success\"}")){
            response = updateStatusReady(orderID);
            System.out.println("Ready - " + response);
        }

        else{
            System.exit(0);
        }

        if(response.toString().equals("{\"status\":\"success\"}")){
            response = updateStatusPickedUp(orderID);
            System.out.println("Picked Up - " + response);
        }

        else{
            System.exit(0);
        }

    }

    public static JsonNode updateStatusInProgress(String orderID) throws UnirestException {

            UpdateStatus obj = new UpdateStatus();

            HttpResponse<JsonNode> jsonResponse = Unirest.put("https://api-test.7-eleven.com/now/order/order/" + orderID)
                    .headers(obj.headers)
                    .body("{\n" +
                            "  \"status\":\"in_progress\",\n" +
                            "  \"employeeId\":\"19\",\n" +
                            "  \"terminal\": 40\n" +
                            "}")
                    .asJson();
            //System.out.println(jsonResponse.getBody());         // gives the full json response

        return jsonResponse.getBody();
    }

    public static JsonNode updateStatusReady(String orderID) throws UnirestException {

        UpdateStatus obj = new UpdateStatus();

        HttpResponse<JsonNode> jsonResponse = Unirest.put("https://api-test.7-eleven.com/now/order/order/" + orderID)
                .headers(obj.headers)
                .body("{\n" +
                        "  \"status\":\"ready\",\n" +
                        "  \"employeeId\":\"19\"\n" +
                        "}")
                .asJson();
        //System.out.println(jsonResponse.getBody());         // gives the full json response

        return jsonResponse.getBody();
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

    public static JsonNode updateStatusCanceled(String orderID) throws UnirestException {

        UpdateStatus obj = new UpdateStatus();

        HttpResponse<JsonNode> jsonResponse = Unirest.put("https://api-test.7-eleven.com/now/order/order/" + orderID)
                .headers(obj.headers)
                .body("{\n" +
                        "  \"status\":\"canceled\",\n" +
                        "  \"employeeId\":\"40\",\n" +
                        "  \"reason\": \"items are not good\"\n" +
                        "}")
                .asJson();
        System.out.println(jsonResponse.getBody());         // gives the full json response

        return jsonResponse.getBody();
    }

    public static JsonNode updateStatusPickedUp(String orderID) throws UnirestException {

        UpdateStatus obj = new UpdateStatus();

        HttpResponse<JsonNode> jsonResponse = Unirest.put("https://api-test.7-eleven.com/now/order/order/" + orderID)
                .headers(obj.headers)
                .body("{\n" +
                        "  \"status\":\"picked_up\",\n" +
                        "  \"employeeId\":\"40\"\n" +
                        "\n" +
                        "}")
                .asJson();
        //System.out.println(jsonResponse.getBody());         // gives the full json response

        return jsonResponse.getBody();
    }
}
