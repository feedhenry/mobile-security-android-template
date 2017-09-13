package com.feedhenry.securenativeandroidtemplate.features.authentication.presenters;

import com.feedhenry.securenativeandroidtemplate.R;
import com.feedhenry.securenativeandroidtemplate.domain.callbacks.Callback;
import com.feedhenry.securenativeandroidtemplate.features.authentication.providers.KeycloakAuthenticateProviderImpl;
import com.feedhenry.securenativeandroidtemplate.features.authentication.providers.OpenIDAuthenticationProvider;
import com.feedhenry.securenativeandroidtemplate.features.authentication.views.AuthenticationView;
import com.feedhenry.securenativeandroidtemplate.mvp.presenters.BasePresenter;

import net.openid.appauth.TokenResponse;

import javax.inject.Inject;

/**
 * Created by weili on 12/09/2017.
 */

public class AuthenticationViewPresenter extends BasePresenter<AuthenticationView> {

    OpenIDAuthenticationProvider authProvider;

    @Inject
    public AuthenticationViewPresenter(OpenIDAuthenticationProvider authProviderImpl) {
        this.authProvider = authProviderImpl;
    }

    public void doLogin() {
        //TODO: if user is already logged in, render the user token right away
        authProvider.performAuthRequest(new Callback() {
            @Override
            public void onSuccess(Object response) {
                TokenResponse token = (TokenResponse) response;
                view.renderTokenInfo(token);
            }

            @Override
            public void onError(Throwable error) {
                view.showAuthError((Exception) error);
            }
        });
    }

    public void doLogout() {
        authProvider.logout(new Callback() {
            @Override
            public void onSuccess(Object models) {
                view.logoutSuccess();
            }

            @Override
            public void onError(Throwable error) {
                view.logoutFailure();
            }
        });
    }
}
