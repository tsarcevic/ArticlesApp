package com.example.cobeosijek.articlesapp.database;

import com.example.cobeosijek.articlesapp.model.Article;

import java.util.List;

/**
 * Created by cobeosijek on 09/11/2017.
 */

public class DatabaseManager implements DatabaseInterface {

    private static DatabaseManager dbManager;
    private static DatabaseHelper dbInstance;

    public DatabaseManager() {
        dbInstance = DatabaseHelper.getInstance();
    }

    public static DatabaseManager getDatabaseInstance() {
        if (dbManager == null) {
            dbManager = new DatabaseManager();
        }

        return dbManager;
    }

    @Override
    public List<Article> getData() {
        return dbInstance.getAllArticles();
    }

    @Override
    public void deleteArticle(int articleId) {
        dbInstance.deleteArticle(articleId);
    }

    @Override
    public void addArticle(String author, String title, String description, String type) {
        dbInstance.addArticle(new Article(author, title, description, type));
    }

    @Override
    public Article getArticleInfo(int articleId) {
        return dbInstance.getArticle(articleId);
    }

    @Override
    public void updateArticle(String author, String title, String description, String type) {
        dbInstance.updateArticle(new Article(author, title, description, type));
    }
}
