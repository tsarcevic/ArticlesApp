package com.example.cobeosijek.articlesapp.new_article;

import com.example.cobeosijek.articlesapp.database.DatabaseInterface;
import com.example.cobeosijek.articlesapp.model.utils.StringUtils;

/**
 * Created by COMP on 9.11.2017..
 */

public class NewArticlePresenter implements NewArticleInterface.Presenter {

    protected NewArticleInterface.View view;
    protected DatabaseInterface database;

    public NewArticlePresenter(DatabaseInterface database) {
        this.database = database;
    }

    @Override
    public void setView(NewArticleInterface.View view) {
        this.view = view;
    }

    @Override
    public void onBackClicked() {
        view.navigateBack();
    }

    @Override
    public void onArticleAddedNavigateBack() {
        view.navigateBack();
    }

    @Override
    public void onAddButtonClicked(String author, String title, String description, String type) {
        boolean isEmpty = false;

        if (!StringUtils.checkIfStringNotEmpty(author)) {
            view.authorTextError();
            isEmpty = true;
        }

        if (!StringUtils.checkIfStringNotEmpty(title)) {
            view.titleTextError();
            isEmpty = true;
        }

        if (!StringUtils.checkIfStringNotEmpty(description)) {
            view.descriptionTextError();
            isEmpty = true;
        }

        if (!isEmpty) {
            addNewArticle(author, title, description, type);
        }
    }

    private void addNewArticle(String author, String title, String description, String type) {
        database.addArticle(author, title, description, type);
        view.articleAdded();
    }
}
