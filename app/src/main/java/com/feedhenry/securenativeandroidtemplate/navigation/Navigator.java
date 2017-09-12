package com.feedhenry.securenativeandroidtemplate.navigation;

import android.app.FragmentTransaction;

import com.feedhenry.securenativeandroidtemplate.BaseActivity;
import com.feedhenry.securenativeandroidtemplate.features.authentication.AuthenticationDetailsFragment;
import com.feedhenry.securenativeandroidtemplate.features.authentication.AuthenticationFragment;
import com.feedhenry.securenativeandroidtemplate.features.home.HomeFragment;
import com.feedhenry.securenativeandroidtemplate.R;
import com.feedhenry.securenativeandroidtemplate.domain.models.Note;
import com.feedhenry.securenativeandroidtemplate.features.storage.NotesListFragment;
import com.feedhenry.securenativeandroidtemplate.mvp.views.BaseFragment;

import javax.inject.Inject;

/**
 * A class to control the navigation of the app.
 */
public class Navigator {

    @Inject
    public Navigator() {

    }

    public void navigateToHomeView(BaseActivity activity) {
        HomeFragment homeView = new HomeFragment();
        loadFragment(activity, homeView);
    }

    public void navigateToAuthenticationView(BaseActivity activity) {
        AuthenticationFragment authFragment = new AuthenticationFragment();
        loadFragment(activity, authFragment);
    }

    public void navigateToAuthenticateDetailsView(BaseActivity activity) {
        AuthenticationDetailsFragment authDetailsView = new AuthenticationDetailsFragment();
        loadFragment(activity, authDetailsView);
    }

    public void navigateToStorageView(BaseActivity activity) {
        NotesListFragment notesListView = new NotesListFragment();
        loadFragment(activity, notesListView);
    }

    public void navigateToSingleNoteView(BaseActivity activity, Note note) {
        //TODO: implement me!
    }

    public void loadFragment(BaseActivity activity, BaseFragment fragment) {
        activity.setInformationTextResourceId(fragment.getHelpMessageResourceId());
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}
