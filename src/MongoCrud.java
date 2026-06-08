import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class MongoCrud {
    private final MongoCollection<Document> collection;

    public MongoCrud() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("retail_store");
        this.collection = database.getCollection("customers");
    }

    public void create(Customer customer) {
        Document doc = new Document("mysql_id", customer.getCustomerId())
                .append("name", customer.getName())
                .append("email", customer.getEmail())
                .append("balance", customer.getBalance());
        collection.insertOne(doc);
    }

    public List<Customer> readAll() {
        List<Customer> list = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                list.add(new Customer(
                        doc.getInteger("mysql_id"),
                        doc.getString("name"),
                        doc.getString("email"),
                        doc.getDouble("balance")
                ));
            }
        }
        return list;
    }

    public void update(Customer customer) {
        collection.updateOne(
                Filters.eq("mysql_id", customer.getCustomerId()),
                Updates.combine(
                        Updates.set("name", customer.getName()),
                        Updates.set("email", customer.getEmail()),
                        Updates.set("balance", customer.getBalance())
                )
        );
    }

    public void delete(int customerId) {
        collection.deleteOne(Filters.eq("mysql_id", customerId));
    }
}
