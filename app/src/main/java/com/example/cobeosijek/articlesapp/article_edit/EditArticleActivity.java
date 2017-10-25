package com.example.cobeosijek.articlesapp.article_edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.articlesapp.App;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_detail.ArticleDetailActivity;
import com.example.cobeosijek.articlesapp.database.DatabaseHelper;
import com.example.cobeosijek.articlesapp.model.Article;
import com.example.cobeosijek.articlesapp.model.utils.StringUtils;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cobeosijek on 24/10/2017.
 */

public class EditArticleActivity extends AppCompatActivity {

    private static String KEY_ID_EDIT_ARTICLE = "id";
    private static String KEY_RESULT_CODE_EDIT_ARTICLE = "result_code";

    @BindView(R.id.toolbar_text)
    TextView toolbarText;

    @BindView(R.id.back_button)
    ImageView backButton;

    @BindView(R.id.article_author)
    EditText authorName;

    @BindView(R.id.article_title)
    EditText titleName;

    @BindView(R.id.article_description)
    EditText description;

    @BindView(R.id.category_picker)
    Spinner articleCategory;

    @BindView(R.id.save_button)
    Button saveArticle;

    @BindString(R.string.no_article)
    String noArticle;

    @BindString(R.string.blank_field)
    String blankField;

    Article article;

    ArrayAdapter<String> spinnerAdapter;

    DatabaseHelper dbHelper;

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, EditArticleActivity.class);
        intent.putExtra(KEY_ID_EDIT_ARTICLE, id);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        setDBInstance();
        setUI();
        getExtras();
        fillInfo();
    }

    private void setDBInstance() {
        dbHelper = DatabaseHelper.getInstance();
    }

    private void setUI() {
        ButterKnife.bind(this);

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_item));
        articleCategory.setAdapter(spinnerAdapter);
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_ID_EDIT_ARTICLE)) {
            article = dbHelper.getArticle(getIntent().getIntExtra(KEY_ID_EDIT_ARTICLE, -1));
        }

        if (article == null) {
            Toast.makeText(App.getInstance(), noArticle, Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    private void fillInfo() {
        if (article != null) {
            titleName.setText(article.getTitle());
            authorName.setText(article.getAuthor());
            description.setText(article.getDescription());

            int spinnerPosition = spinnerAdapter.getPosition(article.getArticleType());

            articleCategory.setSelection(spinnerPosition);
        }
    }

    @OnClick(R.id.save_button)
    public void saveArticle() {
        if (checkForEmptyString()) {
            editArticle();
            returnToPreviousState();
        }
    }

    @OnClick(R.id.back_button)
    public void returnBack() {
        onBackPressed();
    }

    private void returnToPreviousState() {
        setResult(RESULT_OK, ArticleDetailActivity.getResultIntent(article));
        finish();
    }

    private void editArticle() {
        if (article != null) {
            article.setAuthor(authorName.getText().toString().trim());
            article.setTitle(titleName.getText().toString().trim());
            article.setDescription(description.getText().toString().trim());
            article.setArticleType(articleCategory.getSelectedItem().toString());

            dbHelper.updateArticle(article);
        }
    }

    private boolean checkForEmptyString() {
        boolean nonEmptyField = true;

        if (!StringUtils.checkIfStringNotEmpty(authorName.getText().toString().trim())) {
            authorName.setError(blankField);
            nonEmptyField = false;
        }

        if (!StringUtils.checkIfStringNotEmpty(titleName.getText().toString().trim())) {
            titleName.setError(blankField);
            nonEmptyField = false;
        }

        if (!StringUtils.checkIfStringNotEmpty(description.getText().toString().trim())) {
            description.setError(blankField);
            nonEmptyField = false;
        }

        return nonEmptyField;
    }
}
