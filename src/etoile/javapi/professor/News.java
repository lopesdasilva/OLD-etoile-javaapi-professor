/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javapi.professor;

/**
 *
 * @author Ruben
 */


public class News {
    int id;
    String title;
    String url;
    String text;
    String professor;

    public News(int id, String title, String url, String text, String professor) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.text = text;
        this.professor=professor;
    }

    public String getProfessor() {
        return professor;
    }
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
 
}
