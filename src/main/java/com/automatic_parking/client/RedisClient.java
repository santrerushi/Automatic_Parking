package com.automatic_parking.client;

import com.automatic_parking.utility.AppConfig;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisClient extends BaseClient {

    @Override
    public void entry() {
        AppConfig appConfig=new AppConfig();
//
        Jedis jedis=appConfig.redisConnection();
        String regNo=registrationNumber();
        String color=color();
        jedis.mset("Reg Number",regNo,"Color",color);
        String value=jedis.get("Registration");
        String value1=jedis.get("Color");
        System.out.println("Reg num "+value+"  Color "+value1);
}

    @Override
    public void dePark() {

    }

    @Override
    public void getRegistrationNumberByColor() {

    }

    @Override
    public void getSlotNumberByRegistrationNumber() {

    }

    @Override
    public void getSlotNumberByColor() {

    }

    @Override
    public void getListOfSlot() {

    }

}
