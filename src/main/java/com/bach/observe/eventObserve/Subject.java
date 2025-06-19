package com.bach.observe.eventObserve;

import com.bach.model.Event;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Event event);
}
