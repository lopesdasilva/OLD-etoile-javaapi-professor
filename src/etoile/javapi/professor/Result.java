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
    String question;
    String answer;
    String useremail;
    int questionnumber;

    public int getQuestionnumber() {
        return questionnumber;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getUsername() {
        return username;
    }

    
    public Result(String username, String question,String answer, String useremail, int questionnumber){
        this.username=username;
        this.question=question;
        this.answer=answer;
        this.useremail=useremail;
        this.questionnumber=questionnumber;
    }

    public void setAnswer(String new_answer) {
        this.answer=new_answer;
    }
    
    
}
