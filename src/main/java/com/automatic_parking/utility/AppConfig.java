package com.automatic_parking.utility;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import redis.clients.jedis.Jedis;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class AppConfig {
    public static Connection connection;
    public static Statement statement;
    public static Properties properties;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;
    private static String client;

    public void fileConfig() {
        try {
            properties = new Properties();
            properties.load(new FileReader("src/main/resources/config.properties"));
            this.client = properties.getProperty("Client");
        }catch (Exception e) {
                 e.printStackTrace();
        }
}

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }

    public MongoCollection<Document> getDocument() {
        return collection;
    }

    public void setDocument(MongoCollection<Document> collection) {
        AppConfig.collection = collection;
    }

    public void mySqlConnection(){
            try{
                Class.forName(properties.getProperty("DRIVER"));
                this.setConnection(DriverManager.getConnection(properties.getProperty("URL"),properties.getProperty("USERNAME"),properties.getProperty("PASSWORD")));
                this.setStatement(connection.createStatement());
                System.out.println("Connected with MySQL Database.");
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    public void mongoConnection(){
            MongoClient mongo = new MongoClient(properties.getProperty("MONGO_HOST"),Integer.parseInt(properties.getProperty("PORT")));
            MongoDatabase database = mongo.getDatabase("mongo");
            this.setDatabase(database);
            MongoCollection<Document> collection = database.getCollection("parking_system");
            this.setDocument(collection);
            System.out.println("Connected with MongoDB Database.");
    }

    public Jedis redisConnection(){
        Jedis jedis=new Jedis(properties.getProperty("REDIS_HOST"),Integer.parseInt(properties.getProperty("PORT_REDIS")));
        return jedis;
    }
}