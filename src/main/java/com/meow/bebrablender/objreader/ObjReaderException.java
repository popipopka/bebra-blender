package com.meow.bebrablender.objreader;

import com.meow.bebrablender.notifications.NotificationsView;

public class ObjReaderException extends RuntimeException {
    public ObjReaderException(String message, int lineInd) {
        super("Error parsing OBJ file on line: " + lineInd + ". " + message);
        NotificationsView.setMessage("Error parsing OBJ file on line: " + lineInd + ". " + message);
        NotificationsView.showMessage();
    }

    public ObjReaderException(String message) {
        super("Error parsing OBJ file: " + message);
        NotificationsView.setMessage("Error parsing OBJ file: " + message);
        NotificationsView.showMessage();
    }
}