package com.example.cobeosijek.articlesapp.ui.article_detail;

/**
 * Created by COMP on 10.11.2017..
 */

public interface ArticleDetailInterface {

    interface View{
        void setArticleAuthor(String author);

        void setArticleTitle(String title);

        void setArticleDescription(String description);

        void setArticleType(String articleType);

        void navigateBack();

        void navigateToArticleEdit();

        void showNoDataInfo();
    }

    interface Presenter {
        void setView(View view);

        void viewReady();

        void getArticleInfo(int articleId);

        void onBackPressed();

        void onEditArticlePressed();

        void noDataToShow();

        void noDataShown();

        void sendArticleId(int articleId);
    }
}
