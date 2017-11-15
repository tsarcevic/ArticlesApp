package com.example.cobeosijek.articlesapp.ui.on_board.fragments;

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

public class SecondFragment extends BaseFragment {
    @BindView(R.id.forward_button)
    ImageView forwardButton;

    @BindView(R.id.back_button)
    ImageView backButton;

    @BindView(R.id.second_fragment_text)
    TextView secondFragmentText;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.second_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.forward_button)
    public void onRightButtonCLicked() {
        replaceFragment(R.id.fragment_layout, ThirdFragment.newInstance(), true);
    }

    @OnClick(R.id.back_button)
    public void onLeftButtonCLicked() {
        getActivity().onBackPressed();
    }

    @Override
    protected void prepareUi(View view) {

    }
}
