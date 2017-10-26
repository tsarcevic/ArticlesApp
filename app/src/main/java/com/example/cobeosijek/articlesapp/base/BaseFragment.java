package com.example.cobeosijek.articlesapp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.cobeosijek.articlesapp.R;

/**
 * Created by cobeosijek on 26/10/2017.
 */

public class BaseFragment extends Fragment {

    protected void replaceFragment(int fragmentContainer, Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainer, fragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    protected void addFragment(int fragmentContainer, Fragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(fragmentContainer, fragment);

        fragmentTransaction.commit();

    }
}
