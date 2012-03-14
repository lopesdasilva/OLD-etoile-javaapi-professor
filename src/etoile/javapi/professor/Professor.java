/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javapi.professor;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rui
 */
public class Professor implements Serializable {
    private final String username;
    private final String password;
    private final String email;
    private final String firstname;
    private final String lastname;
    private final int id;
    private LinkedList<Discipline> disciplines = new LinkedList<Discipline>();
    
    
    public Professor(int id,String username, String password, String firstname, String lastname, String email) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
     

    
}