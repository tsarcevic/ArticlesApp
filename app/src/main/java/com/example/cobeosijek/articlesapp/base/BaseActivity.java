package com.example.cobeosijek.articlesapp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by cobeosijek on 26/10/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void prepareUi();

    protected void addFragment(int fragmentContainer, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainer, fragment);

        fragmentTransaction.commit();
    }
}
