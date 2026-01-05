package com.youkwhan.trade_view.exception;

public class FavoriteAlreadyExistsException extends RuntimeException {
    public  FavoriteAlreadyExistsException(String message) {
        super("Symbol already saved as favorite: " + message);
    }
}
