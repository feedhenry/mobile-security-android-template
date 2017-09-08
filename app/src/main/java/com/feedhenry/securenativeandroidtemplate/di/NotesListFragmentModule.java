package com.feedhenry.securenativeandroidtemplate.di;

import com.feedhenry.securenativeandroidtemplate.storagesample.ui.NotesListFragment;

import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;

/**
 * Define dependencies used by the NotesListFragment. Used by Dagger2 to build dependency graph.
 */

@Module
public abstract class NotesListFragmentModule {

    @ContributesAndroidInjector
    abstract NotesListFragment contributeNotesListFragmentInjector();

}
