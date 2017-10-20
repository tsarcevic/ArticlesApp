package com.example.cobeosijek.articlesapp.article_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.Article;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.utils.ArticleClickListener;

/**
 * Created by cobeosijek on 20/10/2017.
 */

public class ArticleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView articleName;
    TextView articleAuthor;

    ArticleClickListener articleClickListener;

    public ArticleHolder(View itemView, ArticleClickListener articleClickListener) {
        super(itemView);

        articleName = itemView.findViewById(R.id.article_name);
        articleAuthor = itemView.findViewById(R.id.article_author);

        this.articleClickListener = articleClickListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (articleClickListener != null) {
            articleClickListener.onArticleSelected(getAdapterPosition());
        }
    }

    public void setArticleInfo(Article article) {
        if (article != null) {
            articleName.setText(article.getTitle());
            articleAuthor.setText(article.getAuthor());
        }
    }
}
