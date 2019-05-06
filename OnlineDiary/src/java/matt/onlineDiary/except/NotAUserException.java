/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.except;

/**
 *
 * @author Matthew Hawkins
 */
public class NotAUserException extends UserException {
    private final String username;

    public NotAUserException(String username) {
        super();
        this.username = username;
    }

    @Override
    public String getMessage() {
        return "This username does not exist.";
    }
}