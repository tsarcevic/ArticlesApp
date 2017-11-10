package com.example.cobeosijek.articlesapp.article_edit;

/**
 * Created by COMP on 10.11.2017..
 */

public interface EditArticleInterface {

    interface View{
        void showNoArticle();

        void navigateBack();

        void setArticleAuthor(String author);

        void setArticleTitle(String title);

        void setArticleDescription(String description);

        void setArticleType(String articleType);

        void authorTextError();

        void titleTextError();

        void descriptionTextError();

        void articleEdited();

        void navigateToPreviousState();
    }

    interface Presenter{

        void setView(View view);

        void viewReady();

        void noDataToShow();

        void returnBack();

        void showArticleInfo(int articleId);

        void onSaveButtonClicked(String author, String title, String description, String type);

        void returnToPreviousState();
    }
}
