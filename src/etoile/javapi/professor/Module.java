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
public class Module implements Serializable{
    
    public String name;
    
    public int id;
    
    public LinkedList<Test> tests= new LinkedList<Test>();
    
    public Module(int id, String name) {
        this.name = name;
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

        public LinkedList<Test> getTests() {
        return tests;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTests(LinkedList<Test> tests) {
        this.tests = tests;
    }

    public void addTest(Test t) {
        tests.add(t);
    }
    
    
}
