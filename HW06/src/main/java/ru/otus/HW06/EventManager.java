package ru.otus.HW06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    Map<String, List<EventListener>> listeners;

    public EventManager() {
        listeners = new HashMap<>();
    }

    public void subscribe(String eventType, EventListener listener) {
        if(this.listeners.get(eventType) == null) {
            this.listeners.put(eventType, new ArrayList<>());
        }

        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(users.indexOf(listener));
    }

    public void notify(String eventType) {
        List<EventListener> subscribers = listeners.get(eventType);
        for (EventListener listener : subscribers) {
            listener.onEvent();
        }
    }
}
