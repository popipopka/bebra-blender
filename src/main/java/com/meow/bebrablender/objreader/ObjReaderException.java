package com.meow.bebrablender.objreader;

public class ObjReaderException extends RuntimeException {//bbb
    public ObjReaderException(String errorMessage, int lineInd) {
        super("Error parsing OBJ file on line: " + lineInd + ". " + errorMessage);
    }
}