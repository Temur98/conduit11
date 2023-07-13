package io.realworld.angular.conduit.exceptionshandler.exception;

public class JwtTokeNotValidExceptions extends RuntimeException {
    public JwtTokeNotValidExceptions(String message) {
        super(message);
    }
}
