package com.feedhenry.securenativeandroidtemplate;

import com.datatheorem.android.trustkit.TrustKit;
import com.feedhenry.securenativeandroidtemplate.di.DaggerSecureApplicationComponent;

import net.sqlcipher.database.SQLiteDatabase;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * The main application class. Needs to setup dependency injection.
 */

public class SecureApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize TrustKit for Certificate Pinning
        TrustKit.initializeWithNetworkSecurityConfiguration(this, R.xml.network_security_config);

        try {
            SQLiteDatabase.loadLibs(this);
        } catch (UnsatisfiedLinkError e) {
            //only thrown during tests, ignore it
        }

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerSecureApplicationComponent
                .builder()
                .create(this);
    }

}
