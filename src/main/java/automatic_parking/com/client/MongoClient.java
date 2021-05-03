package automatic_parking.com.client;

import automatic_parking.com.utility.AppConfig;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoClient extends BaseClient{
    AppConfig appConfig=new AppConfig();
    @Override
    public void entry() {
            String regNumber=registrationNumber();
            String color=color();
            BasicDBObject updateDocument = new BasicDBObject();
            updateDocument.append("Registration Number",regNumber);
            updateDocument.append("Color", color);
            updateDocument.append("$set", new BasicDBObject().append("Registration Number", regNumber));
            updateDocument.append("$set", new BasicDBObject().append("Color",color));
            appConfig.getDocument().updateOne(Filters.eq("Registration Number",null), Updates.set("Registration Number",regNumber));
            appConfig.getDocument().updateOne(Filters.eq("Color",null), Updates.set("Color",color));
            System.out.println("Car is Entered.");
    }

    @Override
    public void dePark() {
        String regNumber=registrationNumber();
        Bson filter=new Document("Registration Number",regNumber);
        Bson newValue = new Document("Registration Number",null).append("Color",null);
        Bson updateOperation =new Document("$set",newValue);
        appConfig.getDocument().updateOne(filter,updateOperation);
        System.out.println("Car is Removed.");
    }

    @Override
    public void getRegistrationNumberByColor() {
        String color=color();
        FindIterable<Document> documents=appConfig.getDocument().find();
        for(Document info:documents){
           if( info.get("Color") !=null && info.get("Color").equals(color)){
               System.out.println(info.get("Registration Number"));
           }
        }
    }

    @Override
    public void getSlotNumberByRegistrationNumber() {
        String regNumber=registrationNumber();
        FindIterable<Document> documents=appConfig.getDocument().find();
        for (Document info:documents){
            if (info.get("Registration Number")!=null && info.get("Registration Number").equals(regNumber)){
                System.out.println(info.get("_id"));
            }
        }
    }

    @Override
    public void getSlotNumberByColor() {
        String color=color();
        FindIterable<Document> documents=appConfig.getDocument().find();
        for(Document info:documents){
            if( info.get("Color") !=null && info.get("Color").equals(color)){
                System.out.println(info.get("_id"));
            }
        }
    }

    @Override
    public void getListOfSlot() {
        FindIterable<Document> documents=appConfig.getDocument().find();
        for(Document info:documents){
            if( info.get("Color") !=null ){
                System.out.println(info);
            }
        }
    }

    public void createCollection(){
        MongoCollection<Document> collection=appConfig.getDatabase().getCollection("parking_system");
        Document document=new Document();
        for(int i=1;i<101;i++){
            document.append("Registration Number",null);
            document.append("Color",null);
            document.append("_id",i);
            collection.insertOne(document);
        }
    }
}
