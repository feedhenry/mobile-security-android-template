package com.feedhenry.securenativeandroidtemplate;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.feedhenry.securenativeandroidtemplate.domain.models.Note;
import com.feedhenry.securenativeandroidtemplate.features.authentication.providers.KeycloakAuthenticateProviderImpl;
import com.feedhenry.securenativeandroidtemplate.features.authentication.providers.OpenIDAuthenticationProvider;
import com.feedhenry.securenativeandroidtemplate.features.storage.NotesListFragment;
import com.feedhenry.securenativeandroidtemplate.navigation.Navigator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, NotesListFragment.NoteListListener,  OpenIDAuthenticationProvider, HasFragmentInjector {

    private KeycloakAuthenticateProviderImpl keycloak = new KeycloakAuthenticateProviderImpl(this);

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Inject
    Navigator navigator;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     * @param savedInstanceState - the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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
            navigator.navigateToHomeView(this);
        }
        // Visit the Authentication Screen
        if (id == R.id.nav_authentication) {
            navigator.navigateToAuthenticationView(this);
        }

        if (id == R.id.nav_storage) {
            navigator.navigateToStorageView(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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


