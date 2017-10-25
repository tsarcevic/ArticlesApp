package com.example.cobeosijek.articlesapp.article_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by cobeosijek on 24/10/2017.
 */

public class ArticleDetailActivity extends AppCompatActivity {

    private static String KEY_ID_ARTICLE_DETAIL = "id";
    private static String KEY_CLASS_ARTICLE_DETAIL = "class";
    private static int KEY_REQUEST_CODE_ARTICLE_DETAIL = 1;

    @BindView(R.id.toolbar_text)
    TextView toolbarText;

    @BindView(R.id.article_title)
    TextView titleText;

    @BindView(R.id.article_author)
    TextView authorText;

    @BindView(R.id.article_description)
    TextView descriptionText;

    @BindView(R.id.article_type)
    TextView typeText;

    @BindView(R.id.back_button)
    ImageView backButton;

    @BindView(R.id.edit_button)
    ImageView editButton;

    @BindString(R.string.no_article)
    String noString;

    @BindString(R.string.result_intent_error)
    String missingResultInIntent;

    @BindString(R.string.author_details)
    String authorDetails;

    @BindString(R.string.type_description)
    String articleType;

    Article article;

    DatabaseHelper dbHelper;

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, ArticleDetailActivity.class);
        intent.putExtra(KEY_ID_ARTICLE_DETAIL, id);

        return intent;
    }

    public static Intent getResultIntent(Article article) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_CLASS_ARTICLE_DETAIL, article);

        return resultIntent;
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
        dbHelper = DatabaseHelper.getInstance();
    }

    private void setUI() {
        ButterKnife.bind(this);
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_ID_ARTICLE_DETAIL)) {
            article = dbHelper.getArticle(getIntent().getIntExtra(KEY_ID_ARTICLE_DETAIL, -1));
        }

        if (article == null) {
            Toast.makeText(App.getInstance(), noString, Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }


    private void fillInfo() {
        getExtras();

        titleText.setText(article.getTitle());
        authorText.setText(String.format(authorDetails, article.getAuthor()));
        typeText.setText(String.format(articleType, article.getArticleType()));
        descriptionText.setText(article.getDescription());
    }

    @OnClick(R.id.back_button)
    void goBack() {
        onBackPressed();
    }

    @OnClick(R.id.edit_button)
    void editText() {
        startActivity(EditArticleActivity.getLaunchIntent(this, article.getId()));
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == KEY_REQUEST_CODE_ARTICLE_DETAIL) {
//            if (resultCode == RESULT_OK) {
//                fillInfo();
//            } else {
//                Toast.makeText(this, missingResultInIntent, Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
