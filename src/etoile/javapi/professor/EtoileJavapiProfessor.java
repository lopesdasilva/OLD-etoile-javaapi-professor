/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javapi.professor;

import etoile.javaapi.question.Question;
import etoile.javaapi.question.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui
 */
public class EtoileJavapiProfessor {

    /**
     * @param args the command line arguments
     */
    int professor_id=1;
    
     public static void main(String[] args) {
         System.out.println("Entrei");
        new EtoileJavapiProfessor().run();
    }

    private void run() {
       
            ServiceManager manager;
        try {
            manager = new ServiceManager();

            manager.setAuthentication("ruip", "40bd001563085fc35165329ea1ff5c5ecbdbbeef");
            manager.userService().updateDisciplines(professor_id);
            
            System.out.println("Disciplines");
            for(Discipline d: manager.getCurrentProfessor().getDisciplines()){
                System.out.println("X- " + d.getName());
            }
            
            Date begin = new Date(2010, 07, 02);

            manager.userService().addTest("ExperimentalTest", "Teacher", begin, begin, "Description", 1);
                
                 
         } catch (SQLException ex) {
            Logger.getLogger(EtoileJavapiProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EtoileJavapiProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(EtoileJavapiProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EtoileJavapiProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
                    }
                
                
                
                
}

