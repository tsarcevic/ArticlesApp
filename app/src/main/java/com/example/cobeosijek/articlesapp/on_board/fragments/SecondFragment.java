package com.example.cobeosijek.articlesapp.on_board.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cobeosijek on 25/10/2017.
 */

public class SecondFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.forward_button)
    ImageView forwardButton;

    @BindView(R.id.back_button)
    ImageView backButton;

    @BindView(R.id.second_fragment_text)
    TextView secondFragmentText;

    FragmentManager fragmentManager;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.second_fragment, null);

        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        setFragmentManager();
    }

    private void setFragmentManager() {
        fragmentManager = getFragmentManager();
    }

    @OnClick(R.id.forward_button)
    public void onRightButtonCLicked() {
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, ThirdFragment.newInstance()).addToBackStack(null).commit();
    }

    @OnClick(R.id.back_button)
    public void onLeftButtonCLicked() {
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, FirstFragment.newInstance()).addToBackStack(null).commit();
    }
}
