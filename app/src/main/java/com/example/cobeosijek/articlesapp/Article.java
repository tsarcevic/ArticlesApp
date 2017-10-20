package com.example.cobeosijek.articlesapp;

import android.widget.BaseAdapter;

import com.example.cobeosijek.articlesapp.utils.ArticleType;
import com.example.cobeosijek.articlesapp.utils.BaseModel;

/**
 * Created by cobeosijek on 20/10/2017.
 */

public class Article extends BaseModel {

    private String author;
    private String title;
    private String description;
    private ArticleType articleType;

    public Article(String author, String title, String description, ArticleType articleType) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.articleType = articleType;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArticleType getArticleType() {
        return articleType;
    }
}
