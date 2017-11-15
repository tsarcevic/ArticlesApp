package com.example.cobeosijek.articlesapp.ui.article_list;

import com.example.cobeosijek.articlesapp.model.Article;

import java.util.List;

/**
 * Created by cobeosijek on 09/11/2017.
 */

public interface ArticlesInterface {

    interface View{
        void showArticles(List<Article> articleList);

        void showNoDataText();

        void hideNoDataText();

        void showExitDialog(int articleId);

        void navigateToArticleInfo(int articleId);

        void navigateToNewArticleAdd();

        void notifyArticlesChanged();
    }

    interface Presenter{

        void setView(View view);

        void fetchData();

        void onArticleDeleteChosen(int articleId);

        void onArticleLongClicked(int articleId);

        void onArticleClicked(int articleId);

        void articleAddButtonClicked();
    }
}
