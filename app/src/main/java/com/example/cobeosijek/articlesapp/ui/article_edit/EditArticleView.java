package com.example.cobeosijek.articlesapp.ui.article_edit;

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
import com.example.cobeosijek.articlesapp.presentation.EditArticlePresenter;
import com.example.cobeosijek.articlesapp.ui.article_detail.ArticleDetailView;
import com.example.cobeosijek.articlesapp.database.DatabaseManager;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cobeosijek on 24/10/2017.
 */

public class EditArticleView extends AppCompatActivity implements EditArticleInterface.View {

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
    EditText articleDescription;

    @BindView(R.id.category_picker)
    Spinner articleCategory;

    @BindView(R.id.save_button)
    Button saveArticle;

    @BindString(R.string.no_article)
    String noArticle;

    @BindString(R.string.blank_field)
    String blankField;

    int articleId;

    ArrayAdapter<String> spinnerAdapter;

    EditArticleInterface.Presenter presenter;

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, EditArticleView.class);
        intent.putExtra(KEY_ID_EDIT_ARTICLE, id);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        presenter = new EditArticlePresenter(DatabaseManager.getDatabaseInstance());
        presenter.setView(this);
        presenter.viewReady();

        setUI();
        getExtras();
    }

    private void setUI() {
        ButterKnife.bind(this);

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_item));
        articleCategory.setAdapter(spinnerAdapter);
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_ID_EDIT_ARTICLE)) {
            articleId = getIntent().getIntExtra(KEY_ID_EDIT_ARTICLE, -1);
            presenter.sendArticleId(articleId);
        }
    }

    @Override
    public void navigateBack() {
        finish();
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
        articleDescription.setError(blankField);
    }

    @Override
    public void articleEdited() {
        presenter.returnToPreviousState();
    }

    @Override
    public void showNoArticle() {
        Toast.makeText(getApplicationContext(), noArticle, Toast.LENGTH_SHORT).show();
        presenter.noArticleTextShown();
    }

    @Override
    public void setArticleAuthor(String author) {
        authorName.setText(author);
    }

    @Override
    public void setArticleTitle(String title) {
        titleName.setText(title);
    }

    @Override
    public void setArticleDescription(String description) {
        articleDescription.setText(description);
    }

    @Override
    public void setArticleType(String articleType) {
        int spinnerPosition = spinnerAdapter.getPosition(articleType);

        articleCategory.setSelection(spinnerPosition);
    }

    @OnClick(R.id.save_button)
    public void saveArticle() {
        presenter.onSaveButtonClicked(authorName.getText().toString().trim(), titleName.getText().toString().trim(),
                articleDescription.getText().toString().trim(), articleCategory.getSelectedItem().toString());
    }

    @OnClick(R.id.back_button)
    public void returnBack() {
        presenter.onBackClicked();
    }

    @Override
    public void navigateToPreviousState() {
        setResult(RESULT_OK, ArticleDetailView.getResultIntent(articleId));
        finish();
    }
}
