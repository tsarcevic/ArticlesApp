package com.example.cobeosijek.articlesapp.presentation;

import com.example.cobeosijek.articlesapp.database.DatabaseInterface;
import com.example.cobeosijek.articlesapp.model.Article;
import com.example.cobeosijek.articlesapp.ui.article_list.ArticlesInterface;

import java.util.List;

/**
 * Created by cobeosijek on 09/11/2017.
 */

public class ArticlesPresenter implements ArticlesInterface.Presenter {

    protected ArticlesInterface.View view;
    protected DatabaseInterface database;

    protected List<Article> articleList;

    public ArticlesPresenter(DatabaseInterface database) {
        this.database = database;
    }

    @Override
    public void setView(ArticlesInterface.View view) {
        this.view = view;
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
//        deleteFromAdapter(articleId);
    }

/*    private void deleteFromAdapter(int articleId) {
        for (Article article : articleList) {
            if (article.getId() == articleId) {
                articleList.remove(article);
                view.showArticles(articleList);
            }
        }
    }*/

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
