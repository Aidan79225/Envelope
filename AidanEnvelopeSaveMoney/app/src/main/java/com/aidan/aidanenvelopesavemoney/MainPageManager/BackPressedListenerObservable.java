package com.aidan.aidanenvelopesavemoney.MainPageManager;

import com.aidan.aidanenvelopesavemoney.Interface.OnBackPressedListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by s352431 on 2016/11/9.
 */
public class BackPressedListenerObservable {
    Map<String, OnBackPressedListener> listeners = new HashMap<>();
    static BackPressedListenerObservable observer;

    private BackPressedListenerObservable() {

    }

    public static BackPressedListenerObservable getInstance() {
        if (observer == null) {
            synchronized (BackPressedListenerObservable.class) {
                if (observer == null) {
                    observer = new BackPressedListenerObservable();
                }
            }
        }
        return observer;
    }

    public void register(String id, OnBackPressedListener listener) {
        listeners.put(id, listener);
    }

    public void unregister(String id) {
        if (listeners.containsKey(id)) listeners.remove(id);
    }

    public boolean backPressed() {
        for (String id : listeners.keySet()) {
            OnBackPressedListener backPressedListener = listeners.get(id);
            if (backPressedListener != null) {
                if (backPressedListener.onBackPressed())
                    return true;
            }
        }
        return false;
    }
}
