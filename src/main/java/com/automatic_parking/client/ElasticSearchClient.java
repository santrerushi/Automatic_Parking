package com.automatic_parking.client;

import com.automatic_parking.utility.AppConfig;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.HashMap;
import java.util.Map;

public class ElasticSearchClient extends BaseClient{
    AppConfig appConfig=new AppConfig();

    @Override
    public void entry() {
        boolean flag=true;
        try {
            SearchSourceBuilder searchSource = new SearchSourceBuilder();
            searchSource.query(QueryBuilders.matchQuery("Registration_Number", "NULL"));
            SearchRequest searchRequest = new SearchRequest("parking_system");
            searchRequest.source(searchSource);
            SearchResponse response = appConfig.getElasticDatabase().search(searchRequest, RequestOptions.DEFAULT);

            Map<String, Object> mapIndex = null;
            int id = 10;
            for (SearchHit hit : response.getHits()) {
                mapIndex = hit.getSourceAsMap();
                if ((int) mapIndex.get("Slot_Number") < id) {
                    id = (int) mapIndex.get("Slot_Number");
                }
                flag=false;
            }
            if (!flag) {
                Map<String, Object> newMap = new HashMap<>();
                newMap.put("Registration_Number", registrationNumber().toUpperCase());
                newMap.put("Color", color().toUpperCase());
                newMap.put("Slot_Number", id);

                IndexRequest request = new IndexRequest("parking_system");
                request.id(String.valueOf(id));
                request.source(newMap);
                IndexResponse indexResponseUpdate = appConfig.getElasticDatabase().index(request, RequestOptions.DEFAULT);
                System.out.println("Car is Entered !!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dePark() {
        try {
            String regNumber=registrationNumber();
            SearchSourceBuilder searchSource = new SearchSourceBuilder();
            searchSource.query(QueryBuilders.matchQuery("Registration_Number", regNumber));
            SearchRequest searchRequest = new SearchRequest("parking_system");
            searchRequest.source(searchSource);
            SearchResponse response = appConfig.getElasticDatabase().search(searchRequest, RequestOptions.DEFAULT);

            for (SearchHit hit : response.getHits()) {
                Map <String, Object> map = hit.getSourceAsMap();
                Object registrationNumber = map.get("Registration_Number");
                String id = String.valueOf(map.get("Slot_Number"));

                 if (!registrationNumber.equals(regNumber)) {
                         Map <String, Object> newMap = new HashMap <> ();
                         newMap.put("Registration_Number", "NULL");
                         newMap.put("Color", "NULL");
                         UpdateRequest request = new UpdateRequest("parking_system", id).doc(newMap);
                         UpdateResponse updateDoc = appConfig.getElasticDatabase().update(request, RequestOptions.DEFAULT);
                         System.out.println("Car is Removed !");
                 }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getRegistrationNumberByColor() {
        String color=color();
        try {
            SearchSourceBuilder searchSource = new SearchSourceBuilder();
            searchSource.query(QueryBuilders.matchQuery("Color",color));
            SearchRequest searchRequest = new SearchRequest("parking_system");
            searchRequest.source(searchSource);

            SearchResponse response=appConfig.getElasticDatabase().search(searchRequest,RequestOptions.DEFAULT);
            for (SearchHit hit : response.getHits()){
                Map<String, Object> newMap = hit.getSourceAsMap();
                Object carColor=newMap.get("Color");
                if(!carColor.equals(color)){
                    System.out.println(newMap.get("Registration_Number"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void getSlotNumberByRegistrationNumber() {
        String regNumber=registrationNumber();
        try {
            SearchSourceBuilder searchSource = new SearchSourceBuilder();
            searchSource.query(QueryBuilders.matchQuery("Registration_Number",regNumber));
            SearchRequest searchRequest = new SearchRequest("parking_system");
            searchRequest.source(searchSource);

            SearchResponse response=appConfig.getElasticDatabase().search(searchRequest,RequestOptions.DEFAULT);
            for (SearchHit hit : response.getHits()){
                Map<String, Object> newMap = hit.getSourceAsMap();
                Object regNum=newMap.get("Registration_Number");
                if(!regNum.equals(regNumber)){
                    System.out.println(newMap.get("Slot_Number"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getSlotNumberByColor() {
        String color=color();
        try {
            SearchSourceBuilder searchSource = new SearchSourceBuilder();
            searchSource.query(QueryBuilders.matchQuery("Color",color));
            SearchRequest searchRequest = new SearchRequest("parking_system");
            searchRequest.source(searchSource);

            SearchResponse response=appConfig.getElasticDatabase().search(searchRequest,RequestOptions.DEFAULT);
            for (SearchHit hit : response.getHits()){
                Map<String, Object> newMap = hit.getSourceAsMap();
                Object carColor=newMap.get("Color");
                if(!carColor.equals(color)){
                    System.out.println(newMap.get("Slot_Number"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getListOfSlot() {
        try {
            SearchSourceBuilder searchSource = new SearchSourceBuilder();
            SearchRequest searchRequest = new SearchRequest("parking_system");
            searchRequest.source(searchSource);
            SearchResponse response=appConfig.getElasticDatabase().search(searchRequest,RequestOptions.DEFAULT);
            for (SearchHit hit : response.getHits()){
                Map<String, Object> newMap = hit.getSourceAsMap();
                Object carColor=newMap.get("Color");
                Object regNumber=newMap.get("Registration_Number");
                if(!regNumber.equals("NULL") && !carColor.equals("NULL")) {
                    System.out.println(newMap.get("Slot_Number"));
                    System.out.println(newMap.get("Registration_Number"));
                    System.out.println(newMap.get("Color"));
                    System.out.println("-------");
                   }
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createIndex() {
        for (int i = 1; i <= 10; i++) {
            IndexRequest request = new IndexRequest("parking_system").id(String.valueOf(i)).source("Registration_Number", "NULL", "Color", "NULL", "Slot_Number", i);
            try {
                appConfig.getElasticDatabase().index(request, RequestOptions.DEFAULT);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
