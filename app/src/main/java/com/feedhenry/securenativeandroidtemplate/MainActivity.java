package com.feedhenry.securenativeandroidtemplate;


import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.content.Intent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.feedhenry.securenativeandroidtemplate.authenticate.AuthenticateResult;
import com.feedhenry.securenativeandroidtemplate.authenticate.KeycloakAuthenticateProviderImpl;
import com.feedhenry.securenativeandroidtemplate.authenticate.OpenIDAuthenticationProvider;
import com.feedhenry.securenativeandroidtemplate.domain.models.Note;
import com.feedhenry.securenativeandroidtemplate.navigation.Navigator;
import com.feedhenry.securenativeandroidtemplate.storagesample.ui.NotesListFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NotesListFragment.NoteListListener,  OpenIDAuthenticationProvider, HasFragmentInjector {

    private String infoText;
    private KeycloakAuthenticateProviderImpl keycloak = new KeycloakAuthenticateProviderImpl(this);

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Inject
    Navigator navigator;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     * @param savedInstanceState - the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Default to the app description for the help popup
        setInformationText(getString(R.string.app_description));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.helpButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.popup_title));
                builder.setCancelable(true);
                builder.setMessage(infoText);
                builder.show();
            }
        });
        // load the main menu fragment
        navigator.navigateToHomeView(this);
    }


    /**
     * Perform an auth request
     */
    @Override
    public void performAuthRequest() {
        keycloak.performAuthRequest();
    }

    /**
     * Perform a logout request
     */
    @Override
    public void logout() {
        keycloak.logout();
    }

    /**
     * Handling to close the sidebar on back button press
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Listen for new intents
     * @param intent - the incoming intent
     */
    protected void onNewIntent(Intent intent) {
        keycloak.checkIntent(intent);
    }

    /**
     * On start listener to check if there are any incoming intents
     */
    @Override
    protected void onStart() {
        super.onStart();
        keycloak.checkIntent(getIntent());
    }

    /**
     * Set the current text for the information dialog
     */
    public void setInformationText(String text) {
        infoText = text;
    }

    /**
     * Handling for Sidebar Navigation
     * @param item - the menu item that was selected from the menu
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Visit the Authentication Screen
        if (id == R.id.nav_home) {
            setInformationText(getString(R.string.popup_home_fragment));
            navigator.navigateToHomeView(this);
        }
        // Visit the Authentication Screen
        if (id == R.id.nav_authentication) {
            setInformationText(getString(R.string.popup_authentication_fragment));
            navigator.navigateToAuthenticationView(this);
        }

        if (id == R.id.nav_storage) {
            navigator.navigateToStorageView(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Show a snackbar message
     * @param message
     */
    private void showSnackbar(String message) {
        Snackbar.make(getCurrentFocus(), message, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }

    @Override
    public void onNoteClicked(Note note) {
        Log.i("SecureAndroidApp", "Note selected: " + note.getContent());
        navigator.navigateToSingleNoteView(this, note);
    }
}


