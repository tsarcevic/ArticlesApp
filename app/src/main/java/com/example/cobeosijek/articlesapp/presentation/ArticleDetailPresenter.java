package com.example.cobeosijek.articlesapp.presentation;

import com.example.cobeosijek.articlesapp.database.DatabaseInterface;
import com.example.cobeosijek.articlesapp.model.Article;
import com.example.cobeosijek.articlesapp.ui.article_detail.ArticleDetailInterface;

/**
 * Created by COMP on 10.11.2017..
 */

public class ArticleDetailPresenter implements ArticleDetailInterface.Presenter {

    ArticleDetailInterface.View view;
    DatabaseInterface database;

    public ArticleDetailPresenter(DatabaseInterface database) {
        this.database = database;
    }

    @Override
    public void setView(ArticleDetailInterface.View view) {
        this.view = view;
    }

    @Override
    public void viewReady() {

    }

    @Override
    public void sendArticleId(int articleId) {
        if (articleId == -1) {
            noDataToShow();
        } else {
            getArticleInfo(articleId);
        }
    }

    @Override
    public void getArticleInfo(int articleId) {
        if (articleId != -1) {
            Article article = database.getArticleInfo(articleId);

            view.setArticleAuthor(article.getAuthor());
            view.setArticleTitle(article.getTitle());
            view.setArticleDescription(article.getDescription());
            view.setArticleType(article.getArticleType());
        }
    }

    @Override
    public void onBackPressed() {
        view.navigateBack();
    }

    @Override
    public void onEditArticlePressed() {
        view.navigateToArticleEdit();
    }

    @Override
    public void noDataToShow() {
        view.showNoDataInfo();
    }

    @Override
    public void noDataShown() {
        view.navigateBack();
    }
}