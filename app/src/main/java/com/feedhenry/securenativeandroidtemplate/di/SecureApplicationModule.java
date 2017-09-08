package com.feedhenry.securenativeandroidtemplate.di;

import android.app.Application;
import android.content.Context;

import com.feedhenry.securenativeandroidtemplate.domain.repositories.NoteRepository;
import com.feedhenry.securenativeandroidtemplate.domain.repositories.NoteRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Define the dependencies for the app. Used by Dagger2 to build dependency graph.
 */

@Module
public class SecureApplicationModule {

    private final Application application;

    public SecureApplicationModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton NoteRepository provideNoteRepository(NoteRepositoryImpl noteRepository) {
        return noteRepository;
    }
}
