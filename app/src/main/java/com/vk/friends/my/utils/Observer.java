package com.vk.friends.my.utils;

import com.vk.sdk.api.model.VKApiUserFull;

/**
 * Base interface for observers.
 */
public interface Observer {

    /**
     * Updates the data.
     * @param users Users info.
     */
    public abstract void update(final VKApiUserFull[] users);
}