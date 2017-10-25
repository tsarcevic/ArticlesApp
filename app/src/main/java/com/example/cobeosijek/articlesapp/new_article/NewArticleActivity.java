package com.example.cobeosijek.articlesapp.new_article;

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
import com.example.cobeosijek.articlesapp.database.DatabaseHelper;
import com.example.cobeosijek.articlesapp.model.Article;
import com.example.cobeosijek.articlesapp.model.utils.StringUtils;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class NewArticleActivity extends AppCompatActivity {

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

    @BindView(R.id.add_article)
    Button addArticle;

    @BindString(R.string.blank_field)
    String blankField;

    @BindString(R.string.new_article_added)
    String newArticleAdded;

    DatabaseHelper dbHelper;

    public static Intent getLaunchIntent(Context from) {
        return new Intent(from, NewArticleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        setUI();
    }

    private void setUI() {
        ButterKnife.bind(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_item));
        articleCategory.setAdapter(spinnerAdapter);
    }

    @OnClick(R.id.back_button)
    void goBack() {
        onBackPressed();
    }

    @OnClick(R.id.add_article)
    void addArticle() {
        if (checkForEmptyString()) {
            addNewArticle();
            Toast.makeText(App.getInstance(), newArticleAdded, Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    private void addNewArticle() {
        dbHelper = DatabaseHelper.getInstance();

        dbHelper.addArticle(new Article(authorName.getText().toString().trim(), titleName.getText().toString().trim(),
                description.getText().toString().trim(), articleCategory.getSelectedItem().toString()));
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
