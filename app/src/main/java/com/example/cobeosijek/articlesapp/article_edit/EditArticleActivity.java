package com.example.cobeosijek.articlesapp.article_edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.App;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.database.DatabaseHelper;
import com.example.cobeosijek.articlesapp.model.Article;
import com.example.cobeosijek.articlesapp.model.utils.StringUtils;

/**
 * Created by cobeosijek on 24/10/2017.
 */

public class EditArticleActivity extends AppCompatActivity implements View.OnClickListener {

    private static String KEY_ID_EDIT_ARTICLE = "id";

    TextView toolbarText;
    ImageView backButton;
    EditText authorName;
    EditText titleName;
    EditText description;
    Spinner articleCategory;
    Button editArticle;

    int id;
    Article article;

    ArrayAdapter<String> spinnerAdapter;

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, EditArticleActivity.class);
        intent.putExtra(KEY_ID_EDIT_ARTICLE, id);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        setUI();
        getExtras();
        fillInfo();
    }

    private void setUI() {
        toolbarText = findViewById(R.id.toolbar_text);
        backButton = findViewById(R.id.back_button);
        authorName = findViewById(R.id.article_author);
        titleName = findViewById(R.id.article_title);
        description = findViewById(R.id.article_description);
        articleCategory = findViewById(R.id.category_picker);
        editArticle = findViewById(R.id.save_button);

        editArticle.setOnClickListener(this);
        backButton.setOnClickListener(this);

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_item));
        articleCategory.setAdapter(spinnerAdapter);
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_ID_EDIT_ARTICLE)) {
            id = getIntent().getIntExtra(KEY_ID_EDIT_ARTICLE, -1);
        }
    }

    private void fillInfo() {
        DatabaseHelper dbHelper = new DatabaseHelper(App.getRealm());

        article = dbHelper.getArticle(id);

        titleName.setText(article.getTitle());
        authorName.setText(article.getAuthor());
        description.setText(article.getDescription());

        int spinnerPosition = spinnerAdapter.getPosition(article.getArticleType());

        articleCategory.setSelection(spinnerPosition);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                onBackPressed();

                break;

            case R.id.save_button:
                if (checkForEmptyString()) {
                    editArticle();
                    onBackPressed();
                }

                break;
        }
    }

    private void editArticle() {
        DatabaseHelper dbHelper = new DatabaseHelper(App.getRealm());

        article.setAuthor(authorName.getText().toString().trim());
        article.setTitle(titleName.getText().toString().trim());
        article.setDescription(description.getText().toString().trim());
        article.setArticleType(articleCategory.getSelectedItem().toString());

        dbHelper.updateArticle(article);
    }

    private boolean checkForEmptyString() {
        boolean nonEmptyField = true;

        if (!StringUtils.checkIfStringIsEmpty(authorName.getText().toString().trim())) {
            authorName.setError(getString(R.string.blank_field));
            nonEmptyField = false;
        }

        if (!StringUtils.checkIfStringIsEmpty(titleName.getText().toString().trim())) {
            titleName.setError(getString(R.string.blank_field));
            nonEmptyField = false;
        }

        if (!StringUtils.checkIfStringIsEmpty(description.getText().toString().trim())) {
            description.setError(getString(R.string.blank_field));
            nonEmptyField = false;
        }

        return nonEmptyField;
    }
}
