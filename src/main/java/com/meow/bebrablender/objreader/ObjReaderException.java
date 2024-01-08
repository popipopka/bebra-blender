package com.meow.bebrablender.objreader;

public class ObjReaderException extends RuntimeException {
    public ObjReaderException(String message, int lineInd) {
        super("Error parsing OBJ file on line: " + lineInd + ". " + message);
    }

    public ObjReaderException(String message) {
        super("Error parsing OBJ file: " + message);
    }
}