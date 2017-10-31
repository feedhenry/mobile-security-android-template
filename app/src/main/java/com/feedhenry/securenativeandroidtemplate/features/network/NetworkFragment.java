package com.feedhenry.securenativeandroidtemplate.features.network;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feedhenry.securenativeandroidtemplate.R;
import com.feedhenry.securenativeandroidtemplate.features.network.presenters.UploadNotesPresenter;
import com.feedhenry.securenativeandroidtemplate.features.network.views.UploadNotesView;
import com.feedhenry.securenativeandroidtemplate.mvp.views.BaseFragment;

import java.util.zip.Inflater;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

/**
 * Created by weili on 27/10/2017.
 */

public class NetworkFragment extends BaseFragment<UploadNotesPresenter, UploadNotesView> {

    @Inject
    UploadNotesPresenter uploadNotesPresenter;

    View uploadNotesView;

    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.uploadNotesPresenter = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        uploadNotesView = inflater.inflate(R.layout.fragment_network, container, false);
        unbinder = ButterKnife.bind(this, uploadNotesView);
        return uploadNotesView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected UploadNotesPresenter initPresenter() {
        return uploadNotesPresenter;
    }

    @Override
    protected UploadNotesView initView() {
        return null;
    }

    @Override
    public int getHelpMessageResourceId() {
        return 0;
    }
}
