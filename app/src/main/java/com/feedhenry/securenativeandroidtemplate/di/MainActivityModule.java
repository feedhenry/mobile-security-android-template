package com.feedhenry.securenativeandroidtemplate.di;

import android.app.Activity;

import com.feedhenry.securenativeandroidtemplate.MainActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Define dependencies for the MainActivity. Used by Dagger2 to build dependency graph.
 */
@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = {NotesListFragmentModule.class})
    abstract MainActivity contributeMainActivityInjector();

}
