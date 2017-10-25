package com.example.cobeosijek.articlesapp.article_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.App;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_detail.ArticleDetailActivity;
import com.example.cobeosijek.articlesapp.database.DatabaseHelper;
import com.example.cobeosijek.articlesapp.listeners.DeleteListener;
import com.example.cobeosijek.articlesapp.model.utils.ArticleClickListener;
import com.example.cobeosijek.articlesapp.model.utils.DialogUtils;
import com.example.cobeosijek.articlesapp.new_article.NewArticleActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticlesActivity extends AppCompatActivity implements ArticleClickListener, DeleteListener {

    @BindView(R.id.article_recycler)
    RecyclerView articleList;

    @BindView(R.id.add_button)
    FloatingActionButton addArticle;

    @BindView(R.id.no_data_text)
    TextView showNoData;

    @BindView(R.id.toolbar_text)
    TextView toolbarText;

    @BindString(R.string.no_data_recycler)
    String noData;

    ArticleListAdapter articleAdapter;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        setDBInstance();
        setUI();
    }

    @Override
    protected void onResume() {
        loadData();

        super.onResume();
    }

    private void setDBInstance() {
        dbHelper = DatabaseHelper.getInstance();
    }

    private void setUI() {
        ButterKnife.bind(this);

        articleAdapter = new ArticleListAdapter();
        articleAdapter.setArticleClickListener(this);
        loadData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        articleList.addItemDecoration(itemDecoration);
        articleList.setLayoutManager(layoutManager);

        articleList.setAdapter(articleAdapter);
    }

    @OnClick(R.id.add_button)
    void addArticle() {
        startActivity(NewArticleActivity.getLaunchIntent(this));
    }

    @Override
    public void onArticleClicked(int articleId) {
        startActivity(ArticleDetailActivity.getLaunchIntent(this, articleId));
    }

    @Override
    public void onArticleLongClicked(int articleId) {
        showExitDialog(articleId);
    }

    private void showExitDialog(final int articleId) {
        DialogUtils.showDialog(this, articleId, this);
    }

    public void loadData() {
        articleAdapter.setArticles(dbHelper.getAllArticles());

        if (articleAdapter.getItemCount() < 1) {
            showNoData.setVisibility(View.VISIBLE);
            showNoData.setText(noData);
        } else {
            showNoData.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDeleteClicked(int articleId) {
        dbHelper.deleteArticle(articleId);

        loadData();
    }
}