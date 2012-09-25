/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javapi.professor;

import etoile.javaapi.question.MultipleChoiceQuestion;
import etoile.javaapi.question.OneChoiceQuestion;
import etoile.javaapi.question.Question;
import etoile.javaapi.question.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Date;
import java.util.LinkedList;
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
    
     public static void main(String[] args) throws NoSuchAlgorithmException {
         System.out.println("Entrei");
        new EtoileJavapiProfessor().run();
    }

    private void run() throws NoSuchAlgorithmException {
       
            ServiceManager manager;
        try {
            manager = new ServiceManager();

            manager.setAuthentication("Admin", "2e6f9b0d5885b6010f9167787445617f553a735f");
            manager.userService().updateDisciplines(professor_id);
            
            //manager.userService().resetPassword("ruben.npaixao@gmail.com");
            manager.userService().changePassword("123");
            
            
            
           /* System.out.println("Disciplines");
            for(Discipline d: manager.getCurrentProfessor().getDisciplines()){
                System.out.println("Modules");
                if(d.getName().equals("Hypernetworks Course")){
                    d.getModules();
                    for(Module m : d.modules){
                        if(m.getName().equals("Hypergraphs and Galois Lattice")){
                            System.out.println("Tests"+m.getTests().size());
                            manager.userService().updateTests(m);
                            for(Test t : m.getTests()){
                                if(t.getName().equals("Test For Testing 4")){
                                    System.out.println(manager.userService().getResultsTXT(d.getId(),m.getId(),t.getId()));
                                    
                                }
                            }
                        }
                    }
                }
            }*/
//            
////            Date begin = new Date(2010, 07, 02);
////
////            String correctAnswer = "Correct Answer";
////            LinkedList<String> answers = new LinkedList<String>();
////            answers.add("Correct Answer");
////            answers.add("Wrong Answer");
////            Question onechoice = new OneChoiceQuestion("ExperimentalOneChoiceQuestion?", 0, answers, correctAnswer);
////            manager.userService().addOneChoiceQuestion(onechoice, 1,6);
//
//              LinkedList<String> correctAnswers = new LinkedList<String>();
//              correctAnswers.add("CorrectAnswer");
//              correctAnswers.add("CorrectAnswer2");
//              
//              LinkedList<String> PossibleAnswers = new LinkedList<String>();
//              PossibleAnswers.add("CorrectAnswer");
//              PossibleAnswers.add("CorrectAnswer2");
//              PossibleAnswers.add("Wrong Answer");
//              
//              Question multiplechoice = new MultipleChoiceQuestion("ExperimentalMultipleChoiceQuestion?",0,PossibleAnswers,correctAnswers);
//              manager.userService().addMultipleChoiceQuestion(multiplechoice, 1, 7);
            
            
//        LinkedList<Result> results = manager.userService().getOpenQuestionTestResults(1);           
//        for(Result result:results){
//            System.out.println(result.getUsername() + " - " +result.getQuestion()+" - "+result.getAnswer());
//        }
          
          //manager.userService().insertNews("First Test2", "This is the first insertURL Test.", "www.barulhentos.org");  
            
//          manager.userService().getNews();
//          for(News n : manager.current_professor.getNews()){
//              System.out.println("NEW: " + n.getTitle());
//          }
//            manager.userService().getNews();
//            System.out.println("ANTES");
//            for(News n : manager.current_professor.news){
//                System.out.println(n.getId());
//            }
//            manager.userService().removeNews(24);
//            System.out.println("DEPOIS");
//            for(News n : manager.current_professor.news){
//                System.out.println(n.getId());
//            }

                  
                 
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

