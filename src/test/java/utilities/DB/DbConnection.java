package utilities.DB;


import com.mongodb.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DbConnection {

    @SuppressWarnings("deprecation")
    public static void DBConnect() throws Exception{

        List<ServerAddress> saList = new ArrayList<>();
        saList.add(new ServerAddress("qa-cluster-delivery-shard-00-00-rma7f.mongodb.net", 27017));
        saList.add(new ServerAddress("qa-cluster-delivery-shard-00-01-rma7f.mongodb.net", 27017));
        saList.add(new ServerAddress("qa-cluster-delivery-shard-00-02-rma7f.mongodb.net", 27017));

        char[] pwd =  "".toCharArray();


        //set sslEnabled to true here
        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.readPreference(ReadPreference.primaryPreferred())
//                .retryWrites(true)
                .requiredReplicaSetName("QA-Cluster-Delivery-shard-0")
                .maxConnectionIdleTime(6000)
                .sslEnabled(true)
                .build();

        MongoClientOptions opts = options.build();

        MongoCredential credential = MongoCredential.createCredential("", "admin", pwd);

        MongoClient mongoClient = new MongoClient(saList, Arrays.asList(credential),opts);

        DB database = mongoClient.getDB("digital_delivery_qa");

        //MongoCollection<Document> collection = database.getCollection("stores");
        DBCollection collection = database.getCollection("orders");

        System.out.println(database.getName());

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("order_id", "1574374198999803");
        BasicDBObject fields = new BasicDBObject();
        fields.put("customer_id", 1);

        DBCursor cursor = collection.find(searchQuery, fields);

        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }



    }

}
