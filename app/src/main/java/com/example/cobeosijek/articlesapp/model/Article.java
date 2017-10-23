package com.example.cobeosijek.articlesapp.model;

import com.example.cobeosijek.articlesapp.model.utils.ArticleType;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

import static com.example.cobeosijek.articlesapp.model.utils.BaseModel.getValueOrEmpty;

/**
 * Created by cobeosijek on 20/10/2017.
 */

public class Article extends RealmObject implements Serializable {

    private static int id = 0;
    private String author;
    @PrimaryKey
    private String title;
    private String description;
    private String articleType;

    public Article() {
    }

    public Article(String author, String title, String description, String articleType) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.articleType = articleType;
        id++;
    }

    public static int getId() {
        return id;
    }

    public String getAuthor() {
        return getValueOrEmpty(author);
    }

    public String getTitle() {
        return getValueOrEmpty(title);
    }

    public String getDescription() {
        return getValueOrEmpty(description);
    }

    public String getArticleType() {
        return articleType;
    }
}
