package com.vk.friends.my.fragments.callbacks;

/**
 * Login callback interface for  interaction between activity and
 * fragment login.
 */
public interface LoginCallback {

    /**
     * Vk sdk login. Runs form vk login from sdk.
     */
    public abstract void vkSDKLogin();

    /**
     * Shows friends by current user.
     */
    public abstract void showFriends();
}

