package com.example.cobeosijek.articlesapp.article_list;

import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.articlesapp.App;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.database.DatabaseHelper;
import com.example.cobeosijek.articlesapp.model.utils.ArticleClickListener;
import com.example.cobeosijek.articlesapp.new_article.NewArticle;

public class ArticlesActivity extends AppCompatActivity implements View.OnClickListener, ArticleClickListener{

    RecyclerView articleList;
    FloatingActionButton addArticle;
    TextView showNoData;
    TextView toolbarText;

    ArticleListAdapter articleAdapter;

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

        DatabaseHelper dbHelper = new DatabaseHelper(App.getRealm());

        articleAdapter = new ArticleListAdapter();
        articleAdapter.setArticleClickListener(this);
        articleAdapter.setArticles(dbHelper.getAllArticles());

        if (!dbHelper.getAllArticles().isEmpty()) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

            articleList.addItemDecoration(itemDecoration);
            articleList.setLayoutManager(layoutManager);

            articleList.setAdapter(articleAdapter);
        } else {
            showNoData.setVisibility(View.VISIBLE);
            showNoData.setText(R.string.no_data_recycler);
        }

        addArticle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_button) {
            startActivity(NewArticle.getLaunchIntent(this));
        }
    }

    @Override
    public void onArticleSelected(int position) {
        Toast.makeText(getApplicationContext(), "Stisnio si na broj " + position, Toast.LENGTH_SHORT).show();

        DatabaseHelper dbHelper = new DatabaseHelper(App.getRealm());
        dbHelper.deleteArticle(articleAdapter.getArticleList().get(position));
    }
}