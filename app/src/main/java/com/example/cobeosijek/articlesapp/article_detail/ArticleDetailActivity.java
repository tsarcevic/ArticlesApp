package com.example.cobeosijek.articlesapp.article_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.articlesapp.App;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_edit.EditArticleActivity;
import com.example.cobeosijek.articlesapp.database.DatabaseHelper;
import com.example.cobeosijek.articlesapp.model.Article;

/**
 * Created by cobeosijek on 24/10/2017.
 */

public class ArticleDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static String KEY_ID_ARTICLE_DETAIL = "id";

    TextView toolbarText;
    TextView titleText;
    TextView authorText;
    TextView descriptionText;
    TextView typeText;
    ImageView backButton;
    ImageView editButton;

    Article article;

    DatabaseHelper dbHelper;

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, ArticleDetailActivity.class);
        intent.putExtra(KEY_ID_ARTICLE_DETAIL, id);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        setDBInstance();
        setUI();
        getExtras();
        fillInfo();
    }

    @Override
    protected void onResume() {
        fillInfo();

        super.onResume();
    }

    private void setDBInstance() {
        dbHelper = new DatabaseHelper();
        dbHelper.setRealmInstance(App.getRealm());
    }

    private void setUI() {
        toolbarText = findViewById(R.id.toolbar_text);
        titleText = findViewById(R.id.article_title);
        authorText = findViewById(R.id.article_author);
        descriptionText = findViewById(R.id.article_description);
        typeText = findViewById(R.id.article_type);
        backButton = findViewById(R.id.back_button);
        editButton = findViewById(R.id.edit_button);

        backButton.setOnClickListener(this);
        editButton.setOnClickListener(this);

        titleText.setMovementMethod(new ScrollingMovementMethod());
        authorText.setMovementMethod(new ScrollingMovementMethod());
        descriptionText.setMovementMethod(new ScrollingMovementMethod());
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_ID_ARTICLE_DETAIL)) {
            if (dbHelper.getArticle(getIntent().getIntExtra(KEY_ID_ARTICLE_DETAIL, -1)) != null) {
                article = dbHelper.getArticle(getIntent().getIntExtra(KEY_ID_ARTICLE_DETAIL, -1));
            } else {
                Toast.makeText(getApplicationContext(), R.string.no_article, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        }
    }

    private void fillInfo() {
        getExtras();

        titleText.setText(article.getTitle());
        authorText.setText(String.format(getString(R.string.author_details), article.getAuthor()));
        typeText.setText(String.format(getString(R.string.type_description), article.getArticleType()));
        descriptionText.setText(article.getDescription());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                onBackPressed();

                break;
            case R.id.edit_button:
                startActivity(EditArticleActivity.getLaunchIntent(this, article.getId()));

                break;
        }
    }
}
