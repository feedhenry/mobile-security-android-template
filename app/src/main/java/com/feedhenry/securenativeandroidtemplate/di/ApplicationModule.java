package com.feedhenry.securenativeandroidtemplate.di;

import android.app.Application;

import com.feedhenry.securenativeandroidtemplate.SecureApplication;

import dagger.Binds;
import dagger.Module;

@Module
abstract class ApplicationModule {

    @Binds
    abstract Application application(SecureApplication application);

}
