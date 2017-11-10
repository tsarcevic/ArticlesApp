package com.example.cobeosijek.articlesapp.new_article;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.database.DatabaseManager;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cobeosijek on 23/10/2017.
 */

public class NewArticleActivity extends AppCompatActivity implements NewArticleInterface.View {

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

    NewArticleInterface.Presenter presenter;

    public static Intent getLaunchIntent(Context from) {
        return new Intent(from, NewArticleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        setUI();

        presenter = new NewArticlePresenter(DatabaseManager.getDatabaseInstance());
        presenter.setView(this);
    }

    private void setUI() {
        ButterKnife.bind(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_item));
        articleCategory.setAdapter(spinnerAdapter);
    }

    @OnClick(R.id.back_button)
    void goBack() {
        presenter.onBackClicked();
    }

    @Override
    public void navigateBack() {
        onBackPressed();
    }

    @OnClick(R.id.add_article)
    void addArticle() {
        presenter.onAddButtonClicked(authorName.getText().toString().trim(), titleName.getText().toString().trim(),
                description.getText().toString().trim(), articleCategory.getSelectedItem().toString());
    }

    @Override
    public void articleAdded() {
        Toast.makeText(getApplicationContext(), newArticleAdded, Toast.LENGTH_SHORT).show();
        presenter.onArticleAddedNavigateBack();
    }

    @Override
    public void authorTextError() {
        authorName.setError(blankField);
    }

    @Override
    public void titleTextError() {
        titleName.setError(blankField);
    }

    @Override
    public void descriptionTextError() {
        description.setError(blankField);
    }
}