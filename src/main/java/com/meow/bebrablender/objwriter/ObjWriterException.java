package com.meow.bebrablender.objwriter;

public class ObjWriterException extends RuntimeException {
    public ObjWriterException(String errorMessage) {
        super("Error in ObjWriter: " + errorMessage);
    }
}
