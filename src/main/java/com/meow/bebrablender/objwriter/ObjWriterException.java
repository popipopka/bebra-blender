package com.meow.bebrablender.objwriter;

import com.meow.bebrablender.notifications.NotificationsView;

public class ObjWriterException extends RuntimeException {
    public ObjWriterException(String message) {
        super("Error in ObjWriter: " + message);
        NotificationsView.setMessage("Error in ObjWriter: " + message);
        NotificationsView.showMessage();
    }
}
