package com.example.cobeosijek.articlesapp.utils;

import java.io.Serializable;

/**
 * Created by cobeosijek on 20/10/2017.
 */

public class BaseModel implements Serializable {

    protected String getValueOrEmpty(String string) {
        return (string != null) ? string : "";
    }
}
