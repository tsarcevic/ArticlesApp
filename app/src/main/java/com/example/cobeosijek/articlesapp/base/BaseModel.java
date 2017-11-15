package com.example.cobeosijek.articlesapp.base;

/**
 * Created by cobeosijek on 20/10/2017.
 */

public class BaseModel {

    public static String getValueOrEmpty(String string) {
        return (string != null) ? string : "";
    }
}
