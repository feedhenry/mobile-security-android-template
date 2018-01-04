package com.feedhenry.securenativeandroidtemplate.di;

import com.feedhenry.securenativeandroidtemplate.SecureApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Define the dependencies of the app. Used by Dagger2 to build dependency graph.
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        SecureApplicationModule.class,
        ActivitiesModule.class
})
public interface SecureApplicationComponent extends AndroidInjector<SecureApplication> {

    @dagger.Component.Builder
    abstract class Builder extends AndroidInjector.Builder<SecureApplication> {
    }

}
