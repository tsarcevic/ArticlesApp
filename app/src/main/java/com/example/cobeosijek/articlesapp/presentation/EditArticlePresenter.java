package com.example.cobeosijek.articlesapp.presentation;

import com.example.cobeosijek.articlesapp.database.DatabaseInterface;
import com.example.cobeosijek.articlesapp.model.Article;
import com.example.cobeosijek.articlesapp.model.utils.StringUtils;
import com.example.cobeosijek.articlesapp.ui.article_edit.EditArticleInterface;

/**
 * Created by COMP on 10.11.2017..
 */

public class EditArticlePresenter implements EditArticleInterface.Presenter {

    EditArticleInterface.View view;
    DatabaseInterface database;

    public EditArticlePresenter(DatabaseInterface database) {
        this.database = database;
    }

    @Override
    public void setView(EditArticleInterface.View view) {
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
            showArticleInfo(articleId);
        }
    }

    @Override
    public void noDataToShow() {
        view.showNoArticle();
    }

    @Override
    public void noArticleTextShown() {
        view.navigateBack();
    }

    @Override
    public void showArticleInfo(int articleId) {
        if (articleId != -1) {
            Article article = database.getArticleInfo(articleId);

            view.setArticleAuthor(article.getAuthor());
            view.setArticleTitle(article.getTitle());
            view.setArticleDescription(article.getDescription());
            view.setArticleType(article.getArticleType());
        }
    }

    @Override
    public void onSaveButtonClicked(String author, String title, String description, String type) {
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
            editArticle(author, title, description, type);
        }
    }

    private void editArticle(String author, String title, String description, String type) {
        database.updateArticle(author, title, description, type);
        view.articleEdited();
    }

    @Override
    public void returnToPreviousState() {
        view.navigateToPreviousState();
    }

    @Override
    public void onBackClicked() {
        view.navigateBack();
    }
}
