package com.example.cobeosijek.articlesapp.article_list;

import com.example.cobeosijek.articlesapp.database.DatabaseInterface;
import com.example.cobeosijek.articlesapp.model.Article;

import java.util.List;

/**
 * Created by cobeosijek on 09/11/2017.
 */

public class ArticlesActivityPresenter implements ArticlesActivityInterface.Presenter {

    protected ArticlesActivityInterface.View view;
    protected DatabaseInterface database;

    protected List<Article> articleList;

    public ArticlesActivityPresenter(DatabaseInterface database) {
        this.database = database;
    }

    @Override
    public void setView(ArticlesActivityInterface.View view) {
        this.view = view;
    }

    @Override
    public void viewReady() {
        fetchData();
    }

    @Override
    public void fetchData() {
        articleList = database.getData();

        if (articleList != null) {
            view.showArticles(articleList);

            if (articleList.isEmpty()) {
                view.showNoDataText();
            } else {
                view.hideNoDataText();
            }
        }
    }

    @Override
    public void onArticleDeleteChosen(int articleId) {
        database.deleteArticle(articleId);

        fetchData();
    }

    @Override
    public void onArticleLongClicked(int articleId) {
        view.showExitDialog(articleId);
    }

    @Override
    public void onArticleClicked(int articleId) {
        view.navigateToArticleInfo(articleId);
    }

    @Override
    public void articleAddButtonClicked() {
        view.navigateToNewArticleAdd();
    }
}
