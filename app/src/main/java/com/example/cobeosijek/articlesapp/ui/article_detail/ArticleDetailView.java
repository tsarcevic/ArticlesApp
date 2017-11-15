package com.example.cobeosijek.articlesapp.ui.article_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.articlesapp.App;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.presentation.ArticleDetailPresenter;
import com.example.cobeosijek.articlesapp.ui.article_edit.EditArticleView;
import com.example.cobeosijek.articlesapp.database.DatabaseManager;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cobeosijek on 24/10/2017.
 */

public class ArticleDetailView extends AppCompatActivity implements ArticleDetailInterface.View {

    private static String KEY_ID_ARTICLE_DETAIL = "id";

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

    int articleId;

    ArticleDetailInterface.Presenter presenter;

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, ArticleDetailView.class);
        intent.putExtra(KEY_ID_ARTICLE_DETAIL, id);

        return intent;
    }

    public static Intent getResultIntent(int articleId) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_ID_ARTICLE_DETAIL, articleId);

        return resultIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        presenter = new ArticleDetailPresenter(DatabaseManager.getDatabaseInstance());
        presenter.setView(this);
        presenter.viewReady();

        setUI();
        getExtras();
    }

    @Override
    protected void onResume() {
        getExtras();

        super.onResume();
    }

    private void setUI() {
        ButterKnife.bind(this);
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_ID_ARTICLE_DETAIL)) {
            articleId = getIntent().getIntExtra(KEY_ID_ARTICLE_DETAIL, -1);
            presenter.sendArticleId(articleId);
        }
    }

    @Override
    public void showNoDataInfo() {
        Toast.makeText(App.getInstance(), noString, Toast.LENGTH_SHORT).show();
        presenter.noDataShown();
    }

    @OnClick(R.id.back_button)
    void goBack() {
        presenter.onBackPressed();
    }

    @OnClick(R.id.edit_button)
    void editText() {
        presenter.onEditArticlePressed();
    }

    @Override
    public void navigateToArticleEdit() {
        startActivity(EditArticleView.getLaunchIntent(this, articleId));
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    public void setArticleAuthor(String author) {
        authorText.setText(author);
    }

    @Override
    public void setArticleTitle(String title) {
        titleText.setText(title);
    }

    @Override
    public void setArticleDescription(String description) {
        descriptionText.setText(description);
    }

    @Override
    public void setArticleType(String articleType) {
        typeText.setText(articleType);
    }
}