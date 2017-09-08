package com.feedhenry.securenativeandroidtemplate.di;

import android.content.Context;

import com.feedhenry.securenativeandroidtemplate.SecureApplication;
import com.feedhenry.securenativeandroidtemplate.domain.repositories.NoteRepository;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Define the dependencies of the app. Used by Dagger2 to build dependency graph.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, SecureApplicationModule.class, MainActivityModule.class })
public interface SecureApplicationComponent extends AndroidInjector<SecureApplication> {

    Context context();
    NoteRepository noteRepository();
}
