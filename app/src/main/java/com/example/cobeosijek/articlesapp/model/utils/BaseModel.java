package com.example.cobeosijek.articlesapp.model.utils;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by cobeosijek on 20/10/2017.
 */

public class BaseModel {

    public static String getValueOrEmpty(String string) {
        return (string != null) ? string : "";
    }
}
