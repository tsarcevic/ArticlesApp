package com.example.cobeosijek.articlesapp.article_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.articlesapp.R;

/**
 * Created by cobeosijek on 24/10/2017.
 */

public class ArticleDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private static String KEY_POSITION_ARTICLE_DETAIL = "position";

    TextView toolbarText;
    TextView titleText;
    TextView authorText;
    TextView descriptionText;
    TextView typeText;
    ImageView backButton;
    ImageView editButton;

    public static Intent getLaunchIntent(Context from, int position) {
        Intent intent = new Intent(from, ArticleDetailActivity.class);
        intent.putExtra(KEY_POSITION_ARTICLE_DETAIL, position);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        setUI();
    }

    private void setUI() {
        toolbarText = findViewById(R.id.toolbar_text);
        titleText = findViewById(R.id.article_title);
        authorText = findViewById(R.id.article_author);
        descriptionText = findViewById(R.id.article_description);
        typeText = findViewById(R.id.article_type);
        backButton = findViewById(R.id.back_button);
        editButton = findViewById(R.id.edit_button);

        backButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back_button:
                onBackPressed();

                break;
            case R.id.edit_button:
                Toast.makeText(this, "Check if button works", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
