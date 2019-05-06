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
public class UserExistsException extends UserException {
    private final String username;

    public UserExistsException(String username) {
        super();
        this.username = username;
    }

    @Override
    public String getMessage() {
        return "This username: \"" + this.username + "\" has already been taken.";
    }
}