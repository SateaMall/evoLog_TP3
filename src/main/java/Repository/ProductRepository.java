package Repository;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import entities.Product;

import org.bson.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
public class ProductRepository {
    private MongoCollection<Document> productCollection;

    // Constructor to connect to MongoDB
    public ProductRepository() {
        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017"); // Adjust URI if needed
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("productdb");
        productCollection = database.getCollection("products");
    }

    // Add a product
    public void addProduct(Product product) throws Exception {
        if (productExists(product.getId())) {
            throw new Exception("Product with this ID already exists.");
        }
        productCollection.insertOne(productToDocument(product));
    }

    // Fetch a product by ID
    public Product getProduct(int id) throws Exception {
        Document doc = productCollection.find(eq("id", id)).first();
        if (doc == null) {
            throw new Exception("Product not found.");
        }
        return documentToProduct(doc);
    }

    // Delete a product by ID
    public void deleteProduct(int id) throws Exception {
        if (!productExists(id)) {
            throw new Exception("Product not found.");
        }
        productCollection.deleteOne(eq("id", id));
    }

    // Update a product
    public void updateProduct(Product product) throws Exception {
        if (!productExists(product.getId())) {
            throw new Exception("Product not found.");
        }
        productCollection.replaceOne(eq("id", product.getId()), productToDocument(product));
    }

    // Display all products
    public void displayAllProducts() {
        List<Product> products = new ArrayList<>();
        for (Document doc : productCollection.find()) {
            products.add(documentToProduct(doc));
        }

        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            products.forEach(System.out::println);
        }
    }

    // Helper methods
    private boolean productExists(int id) {
        return productCollection.find(eq("id", id)).first() != null;
    }

    private Document productToDocument(Product product) {
        return new Document("id", product.getId())
                .append("name", product.getName())
                .append("price", product.getPrice())
                .append("expirationDate", product.getExpirationDate().toString());
    }

    private Product documentToProduct(Document doc) {
        int id = doc.getInteger("id");
        String name = doc.getString("name");
        double price = doc.getDouble("price");
        LocalDate expirationDate = LocalDate.parse(doc.getString("expirationDate"));
        return new Product(id, name, price, expirationDate);
    }
}