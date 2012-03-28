/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javapi.professor;


/**
 *
 * @author rubenpaixao
 */
public class Result{
    
    String username;

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getUsername() {
        return username;
    }
    String question;
    String answer;
    
    public Result(String username, String question,String answer){
        this.username=username;
        this.question=question;
        this.answer=answer;
    }
    
    
}
