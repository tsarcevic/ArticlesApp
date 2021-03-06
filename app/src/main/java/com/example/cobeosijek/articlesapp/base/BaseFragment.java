package com.example.cobeosijek.articlesapp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.cobeosijek.articlesapp.R;

/**
 * Created by cobeosijek on 26/10/2017.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract void prepareUi(View view);

    protected void replaceFragment(int fragmentContainer, Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainer, fragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

}
