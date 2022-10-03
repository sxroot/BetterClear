package gg.fullwin.betterclear.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.lang.Nullable;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

public enum MongoDatabase {
    STATS;

    private static @Nullable MongoClient client;

    public static void connect(String URI) {
        client = new MongoClient(new MongoClientURI(URI));
    }

    public @NotNull MongoCollection<Document> collection() {
        if (client == null) throw new MongoClientException("MongoClient cannot be null");
        return client.getDatabase("fullwin").getCollection(name().toLowerCase());
    }
}
