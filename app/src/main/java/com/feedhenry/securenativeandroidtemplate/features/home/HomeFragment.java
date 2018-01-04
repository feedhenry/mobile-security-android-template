package com.feedhenry.securenativeandroidtemplate.features.home;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feedhenry.securenativeandroidtemplate.R;
import com.feedhenry.securenativeandroidtemplate.features.home.presenters.HomeViewPresenter;
import com.feedhenry.securenativeandroidtemplate.features.home.views.HomeView;
import com.feedhenry.securenativeandroidtemplate.features.home.views.HomeViewImpl;
import com.feedhenry.securenativeandroidtemplate.mvp.views.BaseFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeViewPresenter, HomeView> {

    public static final String TAG = "home";

    @Inject
    HomeViewPresenter homeViewPresenter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.homeViewPresenter = null;
    }

    @Override
    protected HomeViewPresenter initPresenter() {
        return homeViewPresenter;
    }

    @Override
    protected HomeView initView() {
        return new HomeViewImpl(this) {
        };
    }

    @Override
    public int getHelpMessageResourceId() {
        return R.string.popup_home_fragment;
    }
}
