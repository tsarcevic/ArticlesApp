package com.example.cobeosijek.articlesapp.article_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.App;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_edit.EditArticleActivity;
import com.example.cobeosijek.articlesapp.database.DatabaseHelper;

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

    int articlePosition;

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, ArticleDetailActivity.class);
        intent.putExtra(KEY_ID_ARTICLE_DETAIL, id);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        setUI();
        getExtras();
        fillInfo();
    }

    @Override
    protected void onResume() {
        fillInfo();

        super.onResume();
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
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_ID_ARTICLE_DETAIL)) {
            articlePosition = getIntent().getIntExtra(KEY_ID_ARTICLE_DETAIL, -1);
        }
    }

    private void fillInfo() {
        DatabaseHelper dbHelper = new DatabaseHelper(App.getRealm());

        titleText.setText(dbHelper.getAllArticles().get(articlePosition).getTitle());
        authorText.setText(String.format(getString(R.string.author_details), dbHelper.getAllArticles().get(articlePosition).getAuthor()));
        typeText.setText(String.format(getString(R.string.type_description), dbHelper.getAllArticles().get(articlePosition).getArticleType()));
        descriptionText.setText(dbHelper.getAllArticles().get(articlePosition).getDescription());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                onBackPressed();

                break;
            case R.id.edit_button:
                startActivity(EditArticleActivity.getLaunchIntent(this, articlePosition));

                break;
        }
    }
}
