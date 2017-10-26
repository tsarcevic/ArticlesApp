package com.example.cobeosijek.articlesapp.on_board;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.on_board.fragments.FirstFragment;

/**
 * Created by cobeosijek on 25/10/2017.
 */

public class StartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        setUpFragment();
    }

    private void setUpFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_layout, FirstFragment.newInstance()).commit();
    }
}
