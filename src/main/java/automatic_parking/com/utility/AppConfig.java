package automatic_parking.com.utility;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class AppConfig {
    public static Connection connection;
    public static Statement statement;
    public static Properties properties;
    private static String client;

    public void fileConfig() {
        try {
            properties = new Properties();
            properties.load(new FileReader("src/main/resources/config.properties"));
            this.client = properties.getProperty("Client");
        } catch (Exception e) {
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
}