package com.automatic_parking.client;

import com.automatic_parking.utility.AppConfig;
import redis.clients.jedis.Jedis;
import java.util.*;

public class RedisClient extends BaseClient {
    AppConfig appConfig=new AppConfig();

    @Override
    public void entry() {
        String registrationNumber = registrationNumber();
        String color = color();
        for (int i=1; i<6; i++) {
            Map<String, String> slotHash = new HashMap<>();
            String i1=String.valueOf(i);
            slotHash.put("Registration_Number", registrationNumber);
            slotHash.put("Color", color);
            if (!appConfig.redisConnection().hexists(i1,"Registration_Number")){
                appConfig.redisConnection().hmset(i1, slotHash);
                System.out.println("car is Entered.");
                System.out.println(" Slot INFO - " + appConfig.redisConnection().hmget(i1, "Registration_Number", "Color","Slot_Number"));
                break;
           }
        }
    }

    @Override
    public void dePark() {
        String registrationNumber = registrationNumber();
        for (int i =1;i<6;i++){
            String i1=String.valueOf(i);
            if (appConfig.redisConnection().hget(i1,"Registration_Number").equalsIgnoreCase(registrationNumber)){
                appConfig.redisConnection().hdel(i1,"Registration_Number");
                appConfig.redisConnection().hdel(i1,"Color");
                System.out.println("Car is Removed.");
                break;
            }
        }
    }

    @Override
    public void getRegistrationNumberByColor() {
        String color=color();
        for (int i = 1; i < 6; i++) {
            String i1 = String.valueOf(i);
            if (appConfig.redisConnection().hget(i1, "Color")!=null && appConfig.redisConnection().hget(i1, "Color").equalsIgnoreCase(color)) {
                System.out.println(appConfig.redisConnection().hget(i1, "Registration_Number"));
            }
        }
    }

    @Override
    public void getSlotNumberByRegistrationNumber() {
        String registrationNumber=registrationNumber();
        for (int i=1;i<6;i++){
            String i1=String.valueOf(i);
            if (appConfig.redisConnection().hget(i1,"Registration_Number")!=null && appConfig.redisConnection().hget(i1,"Registration_Number").equalsIgnoreCase(registrationNumber)){
                System.out.println(appConfig.redisConnection().hget(i1,"Slot_Number"));
            }
        }
    }

    @Override
    public void getSlotNumberByColor() {
        String color=color();
        for (int i=1;i<6;i++){
            String i1=String.valueOf(i);
            if (appConfig.redisConnection().hget(i1,"Color")!=null && appConfig.redisConnection().hget(i1,"Color").equalsIgnoreCase(color)){
                System.out.println(appConfig.redisConnection().hget(i1,"Slot_Number"));
            }
        }
    }

    @Override
    public void getListOfSlot() {
        for (int i=1;i<6;i++){
            String i1=String.valueOf(i);
            if (appConfig.redisConnection().hexists(i1,"Color")) {
                System.out.println(appConfig.redisConnection().hgetAll(i1));
            }
        }
    }

    public void createHash(){
        Jedis jedis=appConfig.redisConnection();
        Map<String,String> hash=new HashMap<>();
        for (int i=1;i<6;i++){
            hash.put("Slot_Number",String.valueOf(i));
            jedis.hmset(String.valueOf(i),hash);
        }
    }
}
