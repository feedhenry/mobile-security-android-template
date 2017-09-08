package com.feedhenry.securenativeandroidtemplate.mvp.views;

import android.app.Fragment;
import android.content.Context;
import android.widget.Toast;

import com.feedhenry.securenativeandroidtemplate.mvp.components.ProgressDialogHelper;

/**
 * A base implementation for the AppView interface.
 */

public abstract class BaseAppView implements AppView {

    private Fragment fragment;
    private ProgressDialogHelper progressDialogHelper;

    public BaseAppView(Fragment fragment) {
        this.fragment = fragment;
        this.progressDialogHelper = new ProgressDialogHelper();

    }

    @Override
    public void showLoading() {
        Context ctx = getContext();
        if (ctx != null) {
            this.progressDialogHelper.showProgress(ctx);
        }
    }

    @Override
    public void hideLoading() {
        this.progressDialogHelper.hideProgress();
    }

    @Override
    public void showError(String errorMessage) {
        Context ctx = getContext();
        if (ctx != null) {
            Toast.makeText(ctx, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    private Context getContext() {
        return this.fragment.getActivity();
    }
}
