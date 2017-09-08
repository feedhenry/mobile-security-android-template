package com.feedhenry.securenativeandroidtemplate.mvp.views;

/**
 * Interface to represent a View that will load data.
 * The data loading should happen in a backgroun thread.
 * The View will show/hide the progress indicator accordingly.
 */

public interface AppView {

    /**
     * Show the progress bar to indicate the loading is in progress
     */
    void showLoading();

    /**
     * Hide the progress bar
     */
    void hideLoading();

    /**
     * Show an error message
     * @param errorMessage the error
     */
    void showError(String errorMessage);
}
