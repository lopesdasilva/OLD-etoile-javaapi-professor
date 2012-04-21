/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;

public class SQLInstruct implements Serializable {

    //Database Configs:
//    public static final String dbAdress = "jdbc:mysql://localhost:3306/etoilev3";
//    public static final String dbUsername = "root";
    //public static final String dbPassword = "";
//    public static final String dbPassword = "etoile";
//    public static final String dbAdress = "jdbc:mysql://localhost:3306/etoilev3";
//    public static final String dbUsername = "root";
//    public static final String dbPassword = "";
    //public static final String dbPassword = "etoile";
    public static final String dbAdress = "jdbc:mysql://localhost:3306/etoilepl_etoilev3";
//    public static final String dbAdress = "jdbc:mysql://84.40.22.48:3306/etoilepl_etoilev3";
    public static final String dbUsername = "etoilepl_etoile";
    public static final String dbPassword = "WryDiluteQuirkyRider";
    
    
    // fazer no cmd ssh -L 3306:localhost:3306 -l user v3.etoilecascadesideas.eu
    public static String login(String username, String parseSHA1Password) {
        return "SELECT id,username,password,firstname,surname,email FROM professor WHERE professor.username='" + username
                + "' AND professor.password='" + parseSHA1Password + "'";

    }
        public static String getNews(){
       return "SELECT id,title,news,url, professor FROM news"; 
    }
        
    public static String getCourses(int student_id) {
        return "SELECT course.id,course.name from student,student_course, course "
                + "WHERE student.id='" + student_id + "' AND student.id=student_course.student_id "
                + "AND student_course.course_id=course.id";
        
       
    }

    public static String getDisciplines(int professor_id) {
        return "SELECT discipline.id, discipline.name FROM professor, professor_discipline, discipline WHERE "
                + "professor.id='" + professor_id + "' AND professor_discipline.professor_id = professor.id AND "
                + "professor_discipline.discipline_id = discipline.id";
    }

    public static String getModules(int discipline_id) {
        return "SELECT module.id, module.name FROM discipline,discipline_module,module WHERE "
                + "discipline.id='" + discipline_id + "' AND discipline.id=discipline_module.discipline_id AND "
                + "discipline_module.module_id=module.id";
    }

    public static String getTests(int module_id) {
        return "SELECT test.id, test.name, test.author,description, test.beginDate,test.endDate "
                + "FROM module,module_test,test WHERE "
                + "module.id='" + module_id + "' AND module.id=module_test.module_id AND module_test.test_id=test.id";
    }

    public static String getOpenQuestions(int test_id) {
        return "SELECT openquestion.id, openquestion.text , test_openquestion.number FROM test, test_openquestion,openquestion WHERE "
                + "test.id='" + test_id + "' AND test.id=test_openquestion.test_id AND test_openquestion.openquestion_id=openquestion.id";
    }

    public static String getOneChoiceQuestions(int test_id) {
        return "SELECT onechoicequestion.id, onechoicequestion.text, test_onechoicequestion.number FROM test, test_onechoicequestion,onechoicequestion WHERE "
                + "test.id='" + test_id + "' AND test.id=test_onechoicequestion.test_id AND test_onechoicequestion.onechoicequestion_id=onechoicequestion.id";
    }

    public static String getMultipleChoiceQuestions(int test_id) {
        return "SELECT multiplechoicequestion.id, multiplechoicequestion.text, test_multiplechoicequestion.number FROM test, test_multiplechoicequestion,multiplechoicequestion WHERE "
                + "test.id='" + test_id + "' AND test.id=test_multiplechoicequestion.test_id "
                + "AND test_multiplechoicequestion.multiplechoicequestion_id=multiplechoicequestion.id";
    }

    public static String getOneChoiceHypothesis(int question_id) {
        return "SELECT hypothesis.id,hypothesis.hypothesis,isCorrect FROM onechoicequestion,onechoicequestion_hypothesis, hypothesis WHERE "
                + "onechoicequestion.id='" + question_id + "' AND onechoicequestion_hypothesis.onechoicequestion_id = onechoicequestion.id AND "
                + "onechoicequestion_hypothesis.hypothesis_id=hypothesis.id";
    }

    public static String getMultipleChoiceHypothesis(int question_id) {
        return "SELECT hypothesis.id,hypothesis.hypothesis,isCorrect FROM multiplechoicequestion,multiplechoicequestion_hypothesis, hypothesis WHERE "
                + "multiplechoicequestion.id='" + question_id + "' AND multiplechoicequestion_hypothesis.multiplechoicequestion_id = multiplechoicequestion.id AND "
                + "multiplechoicequestion_hypothesis.hypothesis_id=hypothesis.id";

    }

    public static String getOneChoiceCorrect(int question_id) {
        return "SELECT hypothesis.id,hypothesis.hypothesis FROM onechoicequestion,onechoicequestion_hypothesis, hypothesis WHERE "
                + "onechoicequestion.id='" + question_id + "' AND onechoicequestion_hypothesis.onechoicequestion_id = onechoicequestion.id AND "
                + "onechoicequestion_hypothesis.hypothesis_id=hypothesis.id AND hypothesis.isCorrect='1'";

    }

    public static String getMultipleChoiceCorrect(int question_id) {
        return "SELECT hypothesis.id,hypothesis.hypothesis FROM multiplechoicequestion,multiplechoicequestion_hypothesis, hypothesis WHERE "
                + "multiplechoicequestion.id='" + question_id + "' AND multiplechoicequestion_hypothesis.multiplechoicequestion_id = multiplechoicequestion.id AND "
                + "multiplechoicequestion_hypothesis.hypothesis_id=hypothesis.id AND hypothesis.isCorrect='1'";


    }

    public static String getOneChoiceAnswer(int question_id, int student_id) {
        return "SELECT onechoiceanswer.id, onechoiceanswer.text FROM student, hypothesis,onechoiceanswer,onechoicequestion WHERE "
                + "student.id='" + student_id + "' AND onechoicequestion.id='" + question_id + "' AND student.id=onechoiceanswer.student_id AND onechoiceanswer.onechoicequestion_id=onechoicequestion.id";
                
    }

    public static String getOpenQuestionAnswer(int question_id, int student_id) {
        return "SELECT openanswer.id, openanswer.text FROM openanswer,student,openquestion WHERE "
                + "student.id='" + student_id + "' AND openquestion.id='" + question_id + "' AND student.id=openanswer.student_id AND openanswer.openquestion_id=openquestion.id";
    }

    public static String getMultipleChoiceAnswer(int question_id, int student_id) {
        return "SELECT multiplechoiceanswer.id, multiplechoiceanswer.text FROM student,multiplechoiceanswer,multiplechoicequestion WHERE "
                + "student.id='" + student_id + "' AND  multiplechoicequestion.id = '" + question_id + "' AND student.id=multiplechoiceanswer.student_id AND multiplechoiceanswer.multiplechoicequestion_id=multiplechoicequestion.id";
               
    }

    public static String getMultipleChoiceURLs(int question_id) {
        return "SELECT url.id, url.name, url.url, url.votes FROM url,multiplechoicequestion_url,multiplechoicequestion WHERE "
                +"multiplechoicequestion.id="+question_id+" AND multiplechoicequestion.id=multiplechoicequestion_url.multiplechoicequestion_id AND multiplechoicequestion_url.url_id=url.id";
    }
    
     public static String getOneChoiceURLs(int question_id) {
         return "SELECT url.id, url.name, url.url, url.votes FROM url,onechoicequestion_url,onechoicequestion WHERE "
                +"onechoicequestion.id="+question_id+" AND onechoicequestion.id=onechoicequestion_url.onechoicequestion_id AND onechoicequestion_url.url_id=url.id";
     }
     
     public static String getOpenQuestionURLs(int question_id) {
         return "SELECT url.id, url.name, url.url, url.votes FROM url,openquestion_url,openquestion WHERE "
                +"openquestion.id="+question_id+" AND openquestion.id=openquestion_url.openquestion_id AND openquestion_url.url_id=url.id";
     }
    //INSERTS
    public static String addProfessor(String username, String password, String firstname, String surname, String email) {

        return "INSERT INTO professor (username, password, firstname, surname, email) VALUES ('" + username + "', '" + password + "','" + firstname + "','" + surname + "','" + email + "');";
    }
    
    public static String registerStudentCourse(int student_id, int course_id){
        return "INSERT INTO student_course (student_id, course_id) VALUES( '"+student_id+"','"+course_id+"')";
    }
    
    public static String insertOpenQuestionAnswer(int student_id, int question_id, String answer){
        return "INSERT INTO openanswer ( student_id, openquestion_id, text ) VALUES ( '"+student_id+"','"+question_id+"','"+answer+"' )";
    }
    public static String insertOneChoiceQuestionAnswer(int student_id, int question_id){
        return "INSERT INTO onechoiceanswer ( student_id, onechoicequestion_id) VALUES ( '"+student_id+"','"+question_id+"')";
    }
    
    public static String insertMultipleChoiceAnswer(int question_id, int student_id, String answer) {
        return "INSERT INTO multiplechoiceanswer ( multiplechoicequestion_id, student_id, text ) VALUES ('"+question_id+"','"+student_id+"','"+answer+"' )";
    }
    
    public static String updateOpenQuetionAnswer(int answer_id, String answer){
        return "UPDATE openanswer SET openanswer.text='"+answer+"' WHERE EXISTS"
            +" (SELECT openanswer.id FROM student WHERE"
            +" openanswer.id='"+answer_id+"')";
    }
       
    public static String updateOneChoiceAnswer(int answer_id, String answer) {
        return "UPDATE onechoiceanswer SET onechoiceanswer.text='"+answer+"' WHERE EXISTS"
                +" (SELECT onechoiceanswer.id FROM student WHERE onechoiceanswer.id='"+answer_id+"')";
    }
    
    public static String vote(int url_id){
        return "UPDATE url SET votes=(votes+1) where id='"+url_id+"';";
    }
    
    public static String addUrl(String name, String url){
        return "INSERT INTO url (name,url) VALUES ('"+name+"','"+url+"')";
    }
    
    public static String getLastURLInserted(){
        return  "SELECT MAX(url.id) FROM url;";
    }

    public static String linkURLOpenQuestion(int question_id, int url_id) {
        return "INSERT INTO openquestion_url( openquestion_id, url_id ) VALUES('"+question_id+"','"+url_id+"');";
    }

    public static String linkURLMultipleChoiceQuestion(int question_id, int url_id) {
        return "INSERT INTO multiplechoicequestion_url( multiplechoicequestion_id, url_id ) VALUES('"+question_id+"','"+url_id+"');";
    }

    public static String linkURLOneChoiceQuestion(int question_id, int url_id) {
        return "INSERT INTO onechoicequestion_url( onechoicequestion_id, url_id ) VALUES('"+question_id+"','"+url_id+"');";
    }

    // Insert Modules and Tests
    
    // Add Module
    public static String addModule(String name){
        return "INSERT INTO module (name) VALUES ('"+name+"');";   
    }
    
    public static String getModuleAdded(){
        return "SELECT MAX(module.id) FROM module;";
    }
    
    public static String connectDisciplineModule(int discipline_id, int module_id){
        return "INSERT INTO discipline_module (discipline_id,module_id) VALUES ('"+discipline_id+"','"+module_id+"')";
    }
    
    //Add Test
    public static String addTest(String name, String professor_name, Date beginDate, Date finishDate, String Description, String url){
        return "INSERT INTO test (name, author, beginDate, endDate, description, url) VALUES ('"+name+"','"+professor_name+"','"+beginDate+"','"+finishDate+"', '"+Description+"', '"+url+"');";
    }
    
    public static String getTestAdded(){
        return "SELECT MAX(test.id) FROM test;";
    }
    public static String connectModuleTest(int module_id, int test_id){
        return "INSERT INTO module_test (module_id,test_id) VALUES ('"+module_id+"','"+test_id+"')";
    }
    
    //Add OpenQuestion
    
    public static String addOpenQuestion(String question){
        return "INSERT INTO openquestion (text) VALUES ('"+question+"');";
    }
    
    public static String getOpenQuestionAdded(){
        return "SELECT MAX(openquestion.id) FROM openquestion;";
    }
    
    public static String connectTestOpenQuestion(int test_id, int question_id, int number){
        return "INSERT INTO test_openquestion (test_id, openquestion_id, number) VALUES ('"+test_id+"','"+question_id+"','"+number+"');";
    }
    
    
    //Add OneChoiceQuestion
    
    public static String addOneChoiceQuestion(String question){
        return "INSERT INTO onechoicequestion (text) VALUES ('"+question+"');";
    }
    
    public static String getOneChoiceQuestionAdded(){
        return "SELECT MAX(onechoicequestion.id) FROM onechoicequestion;";
    }
    
    public static String connectTestOneChoiceQuestion(int test_id, int question_id, int number){
        return "INSERT INTO test_onechoicequestion (test_id, onechoicequestion_id, number) VALUES ('"+test_id+"','"+question_id+"','"+number+"');";
    }
    
    
    public static String connectOneChoiceQuestionHypothesis(int question_id, int hypothesis_id){
        return "INSERT INTO onechoicequestion_hypothesis (onechoicequestion_id, hypothesis_id) VALUES ('"+question_id+"','"+hypothesis_id+"');";
    }
    
    //Add MultipleChoiceQuestion
    
    public static String addMultipleChoiceQuestion(String question){
        return "INSERT INTO multiplechoicequestion (text) VALUES ('"+question+"');";
    }
    
    public static String getMultipleChoiceQuestionAdded(){
        return "SELECT MAX(multiplechoicequestion.id) FROM multiplechoicequestion;";
    }
    
    public static String connectTestMultipleChoiceQuestion(int test_id, int question_id, int number){
        return "INSERT INTO test_multiplechoicequestion (test_id, multiplechoicequestion_id, number) VALUES ('"+test_id+"','"+question_id+"','"+number+"');";
    }
    public static String connectMultipleChoiceQuestionHypothesis(int question_id, int hypothesis_id){
        return "INSERT INTO multiplechoicequestion_hypothesis (multiplechoicequestion_id, hypothesis_id) VALUES ('"+question_id+"','"+hypothesis_id+"')";
    }
    
    //Multiple and One Choice Questions
    
       public static String insertHypothesis(String hypothesis, int isCorrect){
        return "INSERT INTO hypothesis (hypothesis,isCorrect) VALUES ('"+hypothesis+"', '"+isCorrect+"');";
        }
    
       public static String getHypothesisAdded(){
        return "SELECT MAX(hypothesis.id) FROM hypothesis;";
        }
       
    //GET TEST RESULTS
       
    public static String getOpenAnswers(int test_id){
        return "SELECT student.username, openquestion.text, openanswer.text from student, openanswer, test, test_openquestion, openquestion WHERE  student.id=openanswer.student_id AND test.id='"+test_id+"' AND test.id=test_openquestion.test_id AND test_openquestion.openquestion_id=openquestion.id AND openquestion.id = openanswer.openquestion_id ORDER BY student.id";
    }
    
    //DELETE MODULES AND TESTS
    
    public static String removeModule(int discipline_id, int module_id){
        return "DELETE FROM discipline_module WHERE discipline_id = '"+discipline_id+"' AND module_id='"+module_id+"'";
    }
     
    public static String removeTest(int module_id, int test_id){
        return "DELETE FROM module_test WHERE module_id='"+module_id+"' AND test_id='"+test_id+"'";
    }

    public static String insertNews(String title, String news, String url, String professor) {
        return "INSERT INTO news (title,news,url,professor) VALUES('"+title+"','"+news+"','"+url+"','"+professor+"')";
    }
       
}
