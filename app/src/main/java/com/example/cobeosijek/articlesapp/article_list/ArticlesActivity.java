package com.example.cobeosijek.articlesapp.article_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_detail.ArticleDetailActivity;
import com.example.cobeosijek.articlesapp.database.DatabaseManager;
import com.example.cobeosijek.articlesapp.listeners.DeleteListener;
import com.example.cobeosijek.articlesapp.model.Article;
import com.example.cobeosijek.articlesapp.model.utils.ArticleClickListener;
import com.example.cobeosijek.articlesapp.model.utils.DialogUtils;
import com.example.cobeosijek.articlesapp.new_article.NewArticleActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticlesActivity extends AppCompatActivity implements ArticleClickListener, DeleteListener, ArticlesActivityInterface.View {

    @BindView(R.id.article_recycler)
    RecyclerView articleList;

    @BindView(R.id.add_button)
    FloatingActionButton addArticle;

    @BindView(R.id.no_data_text)
    TextView noDataTextView;

    @BindView(R.id.toolbar_text)
    TextView toolbarText;

    ArticleListAdapter articleAdapter;

    ArticlesActivityInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        setUI();

        presenter = new ArticlesActivityPresenter(DatabaseManager.getDatabaseInstance());
        presenter.setView(this);
        presenter.viewReady();
    }

    @Override
    protected void onResume() {
        presenter.fetchData();

        super.onResume();
    }

    private void setUI() {
        ButterKnife.bind(this);

        articleAdapter = new ArticleListAdapter();
        articleAdapter.setArticleClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        articleList.setLayoutManager(layoutManager);

        articleList.setAdapter(articleAdapter);
    }

    @OnClick(R.id.add_button)
    void addArticle() {
        presenter.articleAddButtonClicked();
    }

    @Override
    public void onArticleClicked(int articleId) {
        presenter.onArticleClicked(articleId);
    }

    @Override
    public void onArticleLongClicked(int articleId) {
        presenter.onArticleLongClicked(articleId);
    }

    @Override
    public void showExitDialog(final int articleId) {
        DialogUtils.showDialog(this, articleId, this);
    }

    @Override
    public void onDeleteClicked(int articleId) {
        presenter.onArticleDeleteChosen(articleId);
    }

    @Override
    public void showArticles(List<Article> articleList) {
        articleAdapter.setArticles(articleList);
    }

    @Override
    public void showNoDataText() {
        noDataTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoDataText() {
        noDataTextView.setVisibility(View.GONE);
    }

    @Override
    public void navigateToArticleInfo(int articleId) {
        startActivity(ArticleDetailActivity.getLaunchIntent(this, articleId));
    }

    @Override
    public void navigateToNewArticleAdd() {
        startActivity(NewArticleActivity.getLaunchIntent(this));
    }
}