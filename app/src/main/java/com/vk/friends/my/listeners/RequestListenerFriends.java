package com.vk.friends.my.listeners;

import com.vk.friends.my.utils.Observer;
import com.vk.friends.my.utils.Subject;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Listener for gets friends information by current user.
 * Notifies all observers about event loading user info.
 */
public class RequestListenerFriends extends VKRequest.VKRequestListener implements Subject {

    @Override
    public void onComplete(VKResponse response) {
        JSONArray jsonArray = null;

        try {
            jsonArray = response.json.getJSONObject("response").getJSONArray("items");

            int length = jsonArray.length();
            final VKApiUserFull[] vkApiUsers = new VKApiUserFull[length];

            for (int i = 0; i < length; i++) {

                VKApiUserFull user = new VKApiUserFull(jsonArray.getJSONObject(i));
                vkApiUsers[i] = user;
            }

            notifyObservers(vkApiUsers);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Array of observers.
     */
    private ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);

        if (index >= 0){
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers(VKApiUserFull[] vkApiUsers) {
        for (int index=0; index < observers.size(); index++){
            Observer observer = observers.get(index);
            observer.update(vkApiUsers);
        }
    }
}
