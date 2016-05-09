package com.vk.friends.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.vk.friends.my.fragments.callbacks.LoginCallback;
import com.vk.friends.my.fragments.LoginFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

/**
 * Activity which displays a login screen to the user,
 * offering registration as well.
 */
public class LoginActivity extends FragmentActivity implements LoginCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        loginFragment.setLoginCallback(this);

        VKSdk.wakeUpSession(this, new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {
                if (isResumed) {
                    switch (res) {
                        case LoggedOut:
                            showLogin();
                            break;
                        case LoggedIn:
                            showFriends();
                            break;
                        case Pending:
                            break;
                        case Unknown:
                            break;
                    }
                }
            }

            @Override
            public void onError(VKError error) {
                // Intentionally do nothing.
            }
        });
    }

    //region Fragments.

    /**
     * Login fragment.
     */
    private final LoginFragment loginFragment = new LoginFragment();

    /**
     * Shows login fragment.
     */
    public void showLogin() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, loginFragment)
                .commitAllowingStateLoss();
    }

    //endregion

    /**
     * Flag which shows resumed activity or not.
     * True - resumed, false - another.
     */
    private boolean isResumed = false;

    @Override
    protected void onResume() {
        super.onResume();

        isResumed = true;

        if (VKSdk.isLoggedIn()) {
            showFriends();
        } else {
            showLogin();
        }
    }

    @Override
    protected void onPause() {
        isResumed = false;

        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // User passed Authorization.
                showFriends();
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization.
                // Intentionally do nothing.
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * Starts friends list activity.
     */
    @Override
    public void showFriends() {
        startActivity(new Intent(this, FriendsActivity.class));
    }

    //region VK SDK login.
    @Override
    public void vkSDKLogin() {
        VKSdk.login(this, sMyScope);
    }

    /**
     * Scope is set of required permissions for your application.
     */
    private static final String[] sMyScope = new String[]{
            VKScope.FRIENDS,
            VKScope.PHOTOS
    };

    //endregion.
}