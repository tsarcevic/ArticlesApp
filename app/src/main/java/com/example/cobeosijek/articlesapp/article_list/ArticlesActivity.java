package com.example.cobeosijek.articlesapp.article_list;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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
import com.example.cobeosijek.articlesapp.model.utils.ArticleClickListener;
import com.example.cobeosijek.articlesapp.new_article.NewArticle;

public class ArticlesActivity extends AppCompatActivity implements View.OnClickListener, ArticleClickListener {

    RecyclerView articleList;
    FloatingActionButton addArticle;
    TextView showNoData;
    TextView toolbarText;

    ArticleListAdapter articleAdapter;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        setUI();
    }

    private void setUI() {
        articleList = findViewById(R.id.article_recycler);
        addArticle = findViewById(R.id.add_button);
        showNoData = findViewById(R.id.no_data_text);
        toolbarText = findViewById(R.id.toolbar_text);

        dbHelper = new DatabaseHelper(App.getRealm());

        articleAdapter = new ArticleListAdapter();
        articleAdapter.setArticleClickListener(this);
        loadData();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        articleList.addItemDecoration(itemDecoration);
        articleList.setLayoutManager(layoutManager);

        articleList.setAdapter(articleAdapter);

        addArticle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_button) {
            startActivity(NewArticle.getLaunchIntent(this));
        }
    }

    @Override
    public void onArticleClicked(int position) {
        startActivity(ArticleDetailActivity.getLaunchIntent(this, position));
    }

    @Override
    public void onArticleLongClicked(int position) {
        showExitDialog(position);
    }

    private void showExitDialog(int position) {
        final int innerClassPosition = position;

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setMessage(R.string.alert_dialog_text)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteArticle(innerClassPosition);
                    }
                });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface positiveDialog, int which) {
                positiveDialog.cancel();
            }
        });

        AlertDialog alertShowDialog = alertDialog.create();
        alertShowDialog.show();
    }

    private void deleteArticle(int position) {
        DatabaseHelper dbHelper = new DatabaseHelper(App.getRealm());
        dbHelper.deleteArticle(articleAdapter.getArticleList().get(position));

        loadData();
    }

    public void loadData() {
        articleAdapter.setArticles(dbHelper.getAllArticles());

        if (articleAdapter.getItemCount() < 1) {
            showNoData.setVisibility(View.VISIBLE);
            showNoData.setText(R.string.no_data_recycler);
        } else {
            showNoData.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        loadData();

        super.onResume();
    }
}