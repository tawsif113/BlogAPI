package com.tawsif.CRUDBlog.execptions;

public class DuplicateItemException extends RuntimeException{
    public DuplicateItemException(String message) {
        super(message);
    }
}
