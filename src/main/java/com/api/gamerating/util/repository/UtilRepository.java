package com.api.gamerating.util.repository;

public class UtilRepository {

    public String orderingForDatabase(String value, String firstOrder, String SecondOrder){
        if(value.contains(firstOrder)){
            return "ASC";

        }else if(value.contains(SecondOrder)){
            return "DESC";

        }else{
            return null;
        }
    }

}
