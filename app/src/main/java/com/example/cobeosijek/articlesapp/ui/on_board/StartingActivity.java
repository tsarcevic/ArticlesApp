package com.example.cobeosijek.articlesapp.ui.on_board;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.base.BaseActivity;
import com.example.cobeosijek.articlesapp.ui.on_board.fragments.FirstFragment;

/**
 * Created by cobeosijek on 25/10/2017.
 */

public class StartingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        setUpFragment();
    }

    private void setUpFragment() {
        addFragment(R.id.fragment_layout, FirstFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void prepareUi() {
    }
}
