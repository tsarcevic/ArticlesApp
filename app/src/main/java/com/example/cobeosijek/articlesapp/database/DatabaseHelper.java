package com.example.cobeosijek.articlesapp.database;

import android.support.annotation.Nullable;

import com.example.cobeosijek.articlesapp.model.Article;

import java.util.List;

import io.realm.Realm;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class DatabaseHelper {

    private static DatabaseHelper instance;

    private Realm realm;

    private DatabaseHelper(Realm realm) {
        this.realm = realm;
    }

    public static DatabaseHelper getInstance() {
        if (instance == null) {
            instance = new DatabaseHelper(Realm.getDefaultInstance());
        }

        return instance;
    }

    public void addArticle(Article article) {
        if (article != null) {
            int id;

            realm.beginTransaction();

            if (realm.where(Article.class).count() == 0) {
                id = 0;
            } else {
                id = realm.where(Article.class).max("id").intValue() + 1;
            }

            article.setID(id);

            realm.copyToRealm(article);

            realm.commitTransaction();
        }
    }

    public List<Article> getAllArticles() {
        return realm.copyFromRealm(realm.where(Article.class).findAll());
    }


    @Nullable
    public Article getArticle(int id) {
        return realm.copyFromRealm(realm.where(Article.class).equalTo("id", id).findFirst());
    }

    public void updateArticle(Article article) {
        if (article != null) {
            realm.beginTransaction();

            realm.copyToRealmOrUpdate(article);

            realm.commitTransaction();
        }
    }

    public void deleteArticle(int articleId) {
        realm.beginTransaction();

        Article articleToDelete = realm.where(Article.class).equalTo("id", articleId).findFirst();

        if (articleToDelete != null) {
            articleToDelete.deleteFromRealm();
        }

        realm.commitTransaction();

    }
}
