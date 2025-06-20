package com.bach.observe.eventObserve;

import com.bach.model.Notification;

public interface Observer {
    void update(Notification notification);
}