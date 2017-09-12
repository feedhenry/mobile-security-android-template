package com.feedhenry.securenativeandroidtemplate;

import android.app.Activity;
import android.app.Application;


import com.feedhenry.securenativeandroidtemplate.di.DaggerSecureApplicationComponent;
import com.feedhenry.securenativeandroidtemplate.di.SecureApplicationModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * The main application class. Needs to setup dependency injection.
 */

public class SecureApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerSecureApplicationComponent
                .builder()
                .secureApplicationModule(new SecureApplicationModule(this))
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
