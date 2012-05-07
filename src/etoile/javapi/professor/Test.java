/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javapi.professor;

import etoile.javaapi.question.Question;
import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
/**
 *
 * @author Rui
 */
public class Test implements Serializable{
    
    public String name;
    
    public int id;
    
    //TODO: Replace String with Teacher Class
    public String author;
    
    public String description;
    
    public Date beginDate;
    
    public Date endDate;
    
    public boolean showURLS=true;
    
    public LinkedList<Question> questions= new LinkedList<Question>();

    public Test(int id,String name, String author) {
        this.name = name;
        this.id = id;
        this.author = author;
    }

    public Test(int id, String name, String author,String description, Date beginDate, Date endDate, int showURLS) {
        this.name = name;
        this.id = id;
        this.author = author;
        this.description = description;
        this.beginDate = beginDate;
        this.endDate = endDate;
        
        if(showURLS==0) this.showURLS = false;
                
    }
    
    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Question> getQuestions() {
        return questions;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestions(LinkedList<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question q) {
       questions.add(q);
    }
    
    
}
