package com.example.cobeosijek.articlesapp.ui.article_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.presentation.ArticlesPresenter;
import com.example.cobeosijek.articlesapp.ui.article_detail.ArticleDetailView;
import com.example.cobeosijek.articlesapp.database.DatabaseManager;
import com.example.cobeosijek.articlesapp.listeners.DeleteListener;
import com.example.cobeosijek.articlesapp.model.Article;
import com.example.cobeosijek.articlesapp.listeners.ArticleClickListener;
import com.example.cobeosijek.articlesapp.model.utils.DialogUtils;
import com.example.cobeosijek.articlesapp.ui.new_article.NewArticleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticlesView extends AppCompatActivity implements ArticleClickListener, DeleteListener, ArticlesInterface.View {

    @BindView(R.id.article_recycler)
    RecyclerView articleList;

    @BindView(R.id.add_button)
    FloatingActionButton addArticle;

    @BindView(R.id.no_data_text)
    TextView noDataTextView;

    @BindView(R.id.toolbar_text)
    TextView toolbarText;

    ArticleListAdapter articleAdapter;

    ArticlesInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        setUI();

        presenter = new ArticlesPresenter(DatabaseManager.getDatabaseInstance());
        presenter.setView(this);
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
    public void notifyArticlesChanged() {
        articleAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToArticleInfo(int articleId) {
        startActivity(ArticleDetailView.getLaunchIntent(this, articleId));
    }

    @Override
    public void navigateToNewArticleAdd() {
        startActivity(NewArticleView.getLaunchIntent(this));
    }
}