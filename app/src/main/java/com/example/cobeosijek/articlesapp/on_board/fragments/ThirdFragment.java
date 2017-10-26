package com.example.cobeosijek.articlesapp.on_board.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cobeosijek.articlesapp.R;
import com.example.cobeosijek.articlesapp.article_list.ArticlesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cobeosijek on 25/10/2017.
 */

public class ThirdFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.forward_button)
    ImageView forwardButton;

    @BindView(R.id.back_button)
    ImageView backButton;

    @BindView(R.id.third_fragment_text)
    TextView thirdFragmentText;

    FragmentManager fragmentManager;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.third_fragment, null);

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
        Intent intent = new Intent(getActivity(), ArticlesActivity.class);

        startActivity(intent);

        getActivity().finish();
    }

    @OnClick(R.id.back_button)
    public void onLeftButtonCLicked() {
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, SecondFragment.newInstance()).addToBackStack(null).commit();
    }
}
