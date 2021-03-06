package com.example.cobeosijek.articlesapp.database;

import com.example.cobeosijek.articlesapp.model.Article;

import java.util.List;

/**
 * Created by cobeosijek on 09/11/2017.
 */

public interface DatabaseInterface {

    List<Article> getData();

    void deleteArticle(int articleId);

    void addArticle(String author, String title, String description, String type);

    Article getArticleInfo(int articleId);

    void updateArticle(String author, String title, String description, String type);
}
