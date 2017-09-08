package com.feedhenry.securenativeandroidtemplate.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.feedhenry.securenativeandroidtemplate.AuthenticationDetailsFragment;
import com.feedhenry.securenativeandroidtemplate.AuthenticationFragment;
import com.feedhenry.securenativeandroidtemplate.HomeFragment;
import com.feedhenry.securenativeandroidtemplate.R;
import com.feedhenry.securenativeandroidtemplate.domain.models.Note;
import com.feedhenry.securenativeandroidtemplate.storagesample.ui.NotesListFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * A class to control the navigation of the app.
 */
public class Navigator {

    @Inject
    public Navigator() {

    }

    public void navigateToHomeView(Activity activity) {
        HomeFragment homeView = new HomeFragment();
        loadFragment(activity, homeView);
    }

    public void navigateToAuthenticationView(Activity activity) {
        AuthenticationFragment authFragment = new AuthenticationFragment();
        loadFragment(activity, authFragment);
    }

    public void navigateToAuthenticateDetailsView(Activity activity) {
        AuthenticationDetailsFragment authDetailsView = new AuthenticationDetailsFragment();
        loadFragment(activity, authDetailsView);
    }

    public void navigateToStorageView(Activity activity) {
        NotesListFragment notesListView = new NotesListFragment();
        loadFragment(activity, notesListView);
    }

    public void navigateToSingleNoteView(Activity activity, Note note) {
        //TODO: implement me!
    }

    public void loadFragment(Activity activity, Fragment fragment) {
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}
