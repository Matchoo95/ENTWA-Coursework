/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.except;

import matt.onlineDiary.ents.User;

/**
 *
 * @author Matthew Hawkins
 */
public class IncorrectPasswordException extends UserException {
    private final User user;

    public IncorrectPasswordException(User user) {
        super();
        this.user = user;
    }

    @Override
    public String getMessage() {
        return "Incorrect password.";
    }
}