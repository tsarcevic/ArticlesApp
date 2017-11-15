package com.example.cobeosijek.articlesapp.ui.new_article;

/**
 * Created by COMP on 9.11.2017..
 */

public interface NewArticleInterface {

    interface View {
        void navigateBack();

        void authorTextError();

        void titleTextError();

        void descriptionTextError();

        void articleAdded();
    }

    interface Presenter{
        void setView(View view);

        void onBackClicked();

        void onAddButtonClicked(String author, String title, String description, String type);

        void onArticleAdded();
    }
}
