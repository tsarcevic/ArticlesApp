package com.example.cobeosijek.articlesapp.article_list;

import com.example.cobeosijek.articlesapp.model.Article;

import java.util.List;

/**
 * Created by cobeosijek on 09/11/2017.
 */

public interface ArticlesActivityInterface {

    interface View{
        void showArticles(List<Article> articleList);

        void showNoDataText();

        void hideNoDataText();

        void showExitDialog(int articleId);

        void navigateToArticleInfo(int articleId);

        void navigateToNewArticleAdd();
    }

    interface Presenter{

        void setView(View view);

        void fetchData();

        void viewReady();

        void onArticleDeleteChosen(int articleId);

        void onArticleLongClicked(int articleId);

        void onArticleClicked(int articleId);

        void articleAddButtonClicked();
    }
}
