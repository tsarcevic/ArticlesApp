package com.example.cobeosijek.articlesapp.article_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobeosijek.articlesapp.model.Article;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.model.utils.ArticleClickListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 20/10/2017.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleHolder> implements Serializable {

    private List<Article> articleList = new ArrayList<>();

    ArticleClickListener articleClickListener;

    public void setArticleClickListener(ArticleClickListener articleClickListener) {
        this.articleClickListener = articleClickListener;
    }

    public void setArticles(List<Article> articleList) {
        this.articleList.clear();
        if (articleList != null || !articleList.isEmpty()) {
            this.articleList.addAll(articleList);
            notifyDataSetChanged();
        }
    }

    public List<Article> getArticleList() {
        return articleList;
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
        return articleList.size();
    }
}
