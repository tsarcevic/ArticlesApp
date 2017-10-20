package com.example.cobeosijek.articlesapp.article_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobeosijek.articlesapp.Article;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.utils.ArticleClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 20/10/2017.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleHolder> {

    private List<Article> articleList = new ArrayList<>();

    ArticleClickListener articleClickListener;

    public void setArticleClickListener(ArticleClickListener articleClickListener) {
        this.articleClickListener = articleClickListener;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View articleView = layoutInflater.inflate(R.layout.item_article, parent, false);
        return new ArticleHolder(articleView, articleClickListener);
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {
        Article article = articleList.get(position);

        holder.setArticleInfo(article);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
