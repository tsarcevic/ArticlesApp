package com.example.cobeosijek.articlesapp.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.example.cobeosijek.articlesapp.model.utils.BaseModel.getValueOrEmpty;

/**
 * Created by cobeosijek on 20/10/2017.
 */

public class Article extends RealmObject implements Serializable {

    @PrimaryKey
    private int id = 0;
    private String author;
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
    }

    public int getId() {
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
        return getValueOrEmpty(articleType);
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }
}
