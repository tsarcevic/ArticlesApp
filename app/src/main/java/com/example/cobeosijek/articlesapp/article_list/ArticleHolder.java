package com.example.cobeosijek.articlesapp.article_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.model.Article;
import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.model.utils.ArticleClickListener;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnLongClick;

/**
 * Created by cobeosijek on 20/10/2017.
 */

public class ArticleHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.article_name)
    TextView articleName;

    @BindView(R.id.article_author)
    TextView articleAuthor;

    @BindView(R.id.root_view)
    ViewGroup root;

    @BindColor(R.color.gray)
    int gray;

    @BindColor(R.color.toolbarText)
    int white;

    ArticleClickListener articleClickListener;

    private int id;

    public ArticleHolder(View itemView, ArticleClickListener articleClickListener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.articleClickListener = articleClickListener;
    }

    public void setArticleInfo(Article article) {
        if (article != null) {
            id = article.getId();
            articleName.setText(article.getTitle());
            articleAuthor.setText(article.getAuthor());
        }
    }

    @OnClick
    public void articleClicked() {
        if (articleClickListener != null) {
            articleClickListener.onArticleClicked(id);
        }
    }

    @OnLongClick
    public boolean articleLongClicked() {
        if (articleClickListener != null) {
            articleClickListener.onArticleLongClicked(id);
        }
        return false;
    }

    public void setGray() {
        root.setBackgroundColor(gray);
    }

    public void setWhite() {
        root.setBackgroundColor(white);
    }
}
