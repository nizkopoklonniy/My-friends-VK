package com.vk.friends.my.utils;

import com.vk.sdk.api.model.VKApiUserFull;

/**
 * Base interface for subject.
 */
public abstract interface Subject {

    /**
     * Registers observer.
     * @param observer Instance of observer.
     */
    public abstract void registerObserver(final Observer observer);

    /**
     * Removes observer.
     * @param observer Instance of observer.
     */
    public abstract void removeObserver(final Observer observer);

    /**
     * Notifyes all observers.
     * @param vkApiUsers Users info.
     */
    public abstract void notifyObservers(VKApiUserFull[] vkApiUsers);
}
