package io.realworld.angular.conduit.exception;

public class UserNameOrPasswordInvalid extends RuntimeException{
    public UserNameOrPasswordInvalid(String message){
        super(message);
    }
}
