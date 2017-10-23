package com.example.cobeosijek.articlesapp.model.utils;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class StringUtils {
    public static boolean checkIfStringIsEmpty (String string) {
        return !(string.trim().isEmpty() || string == null);
    }
}
