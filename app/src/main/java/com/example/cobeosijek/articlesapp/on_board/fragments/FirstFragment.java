package com.example.cobeosijek.articlesapp.on_board.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cobeosijek on 25/10/2017.
 */

public class FirstFragment extends BaseFragment {
    @BindView(R.id.forward_button)
    ImageView forwardButton;

    @BindView(R.id.first_fragment_text)
    TextView firstFragmentText;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       return inflater.inflate(R.layout.first_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prepareUi(view);
    }

    @OnClick(R.id.forward_button)
    public void onRightButtonClicked() {
        replaceFragment(R.id.fragment_layout, SecondFragment.newInstance(), true);
    }

    @Override
    protected void prepareUi(View view) {
        ButterKnife.bind(this, view);
    }
}
