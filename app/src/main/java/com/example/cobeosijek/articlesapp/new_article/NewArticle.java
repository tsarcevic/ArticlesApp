package com.example.cobeosijek.articlesapp.new_article;

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
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.articlesapp.App;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_list.ArticleListAdapter;
import com.example.cobeosijek.articlesapp.database.DatabaseHelper;
import com.example.cobeosijek.articlesapp.model.Article;
import com.example.cobeosijek.articlesapp.model.utils.StringUtils;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class NewArticle extends AppCompatActivity implements View.OnClickListener {

    TextView toolbarText;
    ImageView backButton;
    EditText authorName;
    EditText titleName;
    EditText description;
    Spinner articleCategory;
    Button addArticle;

    public static Intent getLaunchIntent(Context from) {
        return new Intent(from, NewArticle.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        setUI();
    }

    private void setUI() {
        toolbarText = findViewById(R.id.toolbar_text);
        backButton = findViewById(R.id.back_button);
        authorName = findViewById(R.id.article_author);
        titleName = findViewById(R.id.article_title);
        description = findViewById(R.id.article_description);
        articleCategory = findViewById(R.id.category_picker);
        addArticle = findViewById(R.id.add_article);

        addArticle.setOnClickListener(this);
        backButton.setOnClickListener(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_item));
        articleCategory.setAdapter(spinnerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                onBackPressed();

                break;

            case R.id.add_article:
                if (checkForEmptyString()) {
                    addNewArticle();
                    Toast.makeText(getApplicationContext(), "You added new article!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

                break;
        }
    }

    private void addNewArticle() {
        DatabaseHelper dbHelper = new DatabaseHelper(App.getRealm());

        dbHelper.addArticle(new Article(authorName.getText().toString().trim(), titleName.getText().toString().trim(), description.getText().toString().trim(), articleCategory.getSelectedItem().toString()));
    }

    private boolean checkForEmptyString() {
        return (StringUtils.checkIfStringIsEmpty(authorName.getText().toString()) &&
                StringUtils.checkIfStringIsEmpty(titleName.getText().toString()) &&
                StringUtils.checkIfStringIsEmpty(description.getText().toString()));
    }
}
