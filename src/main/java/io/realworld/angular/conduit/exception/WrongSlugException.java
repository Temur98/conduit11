package io.realworld.angular.conduit.exception;

public class WrongSlugException extends RuntimeException{
    public WrongSlugException(String message) {
        super(message);
    }
}
