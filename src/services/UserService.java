/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnect;
import db.SQLInstruct;
import etoile.javaapi.question.*;
import etoile.javapi.professor.*;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.sql.Date;
import java.util.LinkedList;

/**
 *
 * @author Ruben
 */
public class UserService implements Serializable{

    DBConnect db;
    Professor current_professor;

    public UserService(DBConnect db, Professor current_professor) {
        this.db = db;
        this.current_professor = current_professor;
    }


    public void addProfessor(Professor professor) throws SQLException {
        String sqlStatement = SQLInstruct.addProfessor(professor.getUsername(), professor.getPassword(), professor.getFirstname(), professor.getLastname(), professor.getEmail());
        db.updateDB(sqlStatement);
        System.out.println("SELECT id FROM professor WHERE professor.username = "+professor.getUsername()+"");
        ResultSet rSet = db.queryDB("SELECT id FROM professor WHERE professor.username = '"+professor.getUsername()+"'");
        while(rSet.next()){
        String statmentAddCourse = SQLInstruct.registerStudentCourse(rSet.getInt(1),1);
        db.updateDB(statmentAddCourse);
        }
        
    }

    
    //TODO: FIX ME!
    public void updateDisciplines(int professor_id) throws SQLException {
        String sqlStatement = SQLInstruct.getDisciplines(professor_id);
        ResultSet rSet = db.queryDB(sqlStatement);

        while (rSet.next()) {
            Discipline d = new Discipline(rSet.getInt(1), rSet.getString(2));
            current_professor.addDiscipline(d);
            updateModules(d);
        }
    }

    public void updateModules(Discipline discipline) throws SQLException {
        String sqlStatement = SQLInstruct.getModules(discipline.getId());
        ResultSet rSet = db.queryDB(sqlStatement);

        while (rSet.next()) {
            discipline.addModule(new Module(rSet.getInt(1), rSet.getString(2)));
        }
    }

    public void updateTests(Module module) throws SQLException {
        
        module.setTests(new LinkedList<Test>()); //to restart tests No repeats
        
        String sqlStatement = SQLInstruct.getTests(module.getId());
        ResultSet rSet = db.queryDB(sqlStatement);

        while (rSet.next()) {


            module.addTest(new Test(rSet.getInt(1), rSet.getString(2), rSet.getString(3), rSet.getString(4), rSet.getDate(5), rSet.getDate(6)));
        }
        
    }
    
    

    public void updateQuestions(Test test) throws SQLException {
        
        LinkedList<Question> openquestions = getOpenQuestions(test);
        getOpenQuestionURLs(openquestions);
        LinkedList<Question> onechoicequestions =getOneChoiceQuestions(test);
        getOneChoiceQuestionURLs(onechoicequestions);
        LinkedList<Question> multiplechoicequestions = getMultipleChoiceQuestions(test);
        getMultipleChoiceQuestionURLs(multiplechoicequestions);
        Collections.sort(test.questions);

    }

    private LinkedList<Question> getOpenQuestions(Test test) throws SQLException {
        //OPENQUESTIONS
        String sqlStatement = SQLInstruct.getOpenQuestions(test.getId());
        ResultSet rSet = db.queryDB(sqlStatement);
        LinkedList<Question> questions = new LinkedList<Question>();
        while (rSet.next()) {
            Question q = new OpenQuestion(rSet.getInt(1), rSet.getString(2));
            q.setNumber(rSet.getInt(3));
            test.addQuestion(q);
            questions.add(q);
        }
        return questions;
    }

    private LinkedList<Question> getOneChoiceQuestions(Test test) throws SQLException {
        String sqlStatement = SQLInstruct.getOneChoiceQuestions(test.getId());
        ResultSet rSet = db.queryDB(sqlStatement);
        LinkedList<Question> questions = new LinkedList<Question>();
        while (rSet.next()) {
            OneChoiceQuestion op = new OneChoiceQuestion(rSet.getInt(1), rSet.getString(2));
            op.setNumber(rSet.getInt(3));
            test.addQuestion(op);
            questions.add(op);
            //Hypothesis
            String sqlStatement_hypothesis = SQLInstruct.getOneChoiceHypothesis(rSet.getInt(1));
            ResultSet rSet_hypothesis = db.queryDB(sqlStatement_hypothesis);
            while (rSet_hypothesis.next()) {
                op.addPossibleAnswser(rSet_hypothesis.getString(2));
                if (rSet_hypothesis.getInt(3) == 1) {
                    op.setCorrectAnswer(rSet_hypothesis.getString(2));
                }
            }

        }
        
        return questions;
    }

        public LinkedList<Question> getMultipleChoiceQuestions(Test test) throws SQLException {
        String sqlStatement = SQLInstruct.getMultipleChoiceQuestions(test.getId());
        ResultSet rSet = db.queryDB(sqlStatement);
        LinkedList<Question> questions = new LinkedList<Question>();
        while (rSet.next()) {
            MultipleChoiceQuestion mp = new MultipleChoiceQuestion(rSet.getInt(1), rSet.getString(2));
            mp.setNumber(rSet.getInt(3));
            test.addQuestion(mp);
            questions.add(mp);
            //Hypothesis
            String sqlStatement_hypothesis = SQLInstruct.getMultipleChoiceHypothesis(rSet.getInt(1));
            ResultSet rSet_hypothesis = db.queryDB(sqlStatement_hypothesis);
            LinkedList<String> correct = new LinkedList<String>();
            while (rSet_hypothesis.next()) {
                mp.addPossibleAnswser(rSet_hypothesis.getString(2));
                if (rSet_hypothesis.getInt(3) == 1) {
                    correct.add(rSet_hypothesis.getString(2));
                }
            }
        }
        
        return questions;
    }
    
     private void getOpenQuestionURLs(LinkedList<Question> questions) throws SQLException {
      for(Question q : questions){
          String sqlStatement = SQLInstruct.getOpenQuestionURLs(q.getId());
          ResultSet rSet = db.queryDB(sqlStatement);
          while(rSet.next()){
              q.addURL(new URL(rSet.getInt(1),rSet.getString(2),rSet.getString(3),"noname",rSet.getInt(4)));
          }
      }
    }
     
    private void getOneChoiceQuestionURLs(LinkedList<Question> questions) throws SQLException {
        for(Question q : questions){
          String sqlStatement = SQLInstruct.getOneChoiceURLs(q.getId());
          ResultSet rSet = db.queryDB(sqlStatement);
          while(rSet.next()){
              q.addURL(new URL(rSet.getInt(1),rSet.getString(2),rSet.getString(3),"noname",rSet.getInt(4)));
          }
      }
    } 
    
     private void getMultipleChoiceQuestionURLs(LinkedList<Question> questions) throws SQLException {
       for(Question q : questions){
          String sqlStatement = SQLInstruct.getMultipleChoiceURLs(q.getId());
          ResultSet rSet = db.queryDB(sqlStatement);
          while(rSet.next()){
              q.addURL(new URL(rSet.getInt(1),rSet.getString(2),rSet.getString(3),"noname",rSet.getInt(4)));
          }
      } 
    } 
     
     //TODO: FIX ME!
     public Discipline getDiscipline(String name){
       
        for(Discipline d: current_professor.getDisciplines()){
            
                if(d.getName().equals(name))
                    return d;
            
        }
    return null;
    }

     
     //TODO: FIX ME!
    public Module getModule(String value) {
        //TODO Cant exist modules with the same name
            for(Discipline d: current_professor.getDisciplines()){
               for(Module m: d.getModules())
                   if(m.getName().equals(value))
                       return m;
            }
        
        return null;
    }

    

    //UPDATE QUESTIONS
    

    

   
    
    public void vote(int url_id) throws SQLException{
        String sqlStatement = SQLInstruct.vote(url_id);
        db.updateDB(sqlStatement);
    }
    
    public void addURL(String url_name, String url , QuestionType type, int question_id ) throws SQLException{
        String sqlStatement = SQLInstruct.addUrl(url_name, url);
        db.updateDB(sqlStatement);
                String sqlGetUrl = SQLInstruct.getLastURLInserted();
        ResultSet rSet=db.queryDB(sqlGetUrl);
                if(rSet.next()){
        if (type==QuestionType.OPEN){ // adiciona À openquestion


            String sqlLinkURLOpenQuestion = SQLInstruct.linkURLOpenQuestion(question_id, rSet.getInt(1));
            System.out.println("OPEN: "+sqlLinkURLOpenQuestion);

            db.updateDB(sqlLinkURLOpenQuestion);
         
        }else if(type==QuestionType.ONE_CHOICE){ // adiciona À onechoicequestion

            String sqlLinkURLOneChoiceQuestion = SQLInstruct.linkURLOneChoiceQuestion(question_id, rSet.getInt(1));
            System.out.println("ONECHOICE: "+sqlLinkURLOneChoiceQuestion);
            db.updateDB(sqlLinkURLOneChoiceQuestion);
          
            
        }else{//adiciona à multiplechoicequestion

            String sqlLinkURLMultipleChoiceQuestion = SQLInstruct.linkURLMultipleChoiceQuestion(question_id, rSet.getInt(1));
            System.out.println("MULTIPLE: "+sqlLinkURLMultipleChoiceQuestion);

            db.updateDB(sqlLinkURLMultipleChoiceQuestion);
        
    }
    
                }
    }
    
    
    //ADD MODULES, TESTS AND QUESTIONS
    
    public void addModule(String name, int discipline_id) throws SQLException{
        String SQL_addModule = SQLInstruct.addModule(name);
        db.updateDB(SQL_addModule);
        
        int module_id;
        String SQL_moduleId = SQLInstruct.getModuleAdded();
        ResultSet rSet = db.queryDB(SQL_moduleId);
        
        if(rSet.next()){
        module_id=rSet.getInt(1);
        String SQL_connectModule=SQLInstruct.connectDisciplineModule(discipline_id, module_id);
        db.updateDB(SQL_connectModule);
        }
    }
    
    public int addTest(String name, String professor, Date beginDate, Date finishDate, String description, int module_id, String url) throws SQLException{
        beginDate.setYear(beginDate.getYear()-1900);
        beginDate.setMonth(beginDate.getMonth()-1);
        
        String SQL_addTest = SQLInstruct.addTest(name, professor, beginDate, finishDate, description,url);
        System.out.println("DATE"+beginDate.getDate());
        db.updateDB(SQL_addTest);
        
        int test_id = 0;
        String SQL_testId = SQLInstruct.getTestAdded();
        ResultSet rSet = db.queryDB(SQL_testId);
        
        if(rSet.next()){
            test_id = rSet.getInt(1);
            String SQL_connectTest = SQLInstruct.connectModuleTest(module_id, test_id);
            db.updateDB(SQL_connectTest);
        }
        
        return test_id;
    }
    
    public void addOpenQuestion(String question, int test_id, int number) throws SQLException{
        String SQL_addQUestion = SQLInstruct.addOpenQuestion(question);
        db.updateDB(SQL_addQUestion);
        
        int question_id;
        String SQL_questionId = SQLInstruct.getOpenQuestionAdded();
        ResultSet rSet = db.queryDB(SQL_questionId);
        
        if(rSet.next()){
            question_id = rSet.getInt(1);
            String SQL_connectQuestion = SQLInstruct.connectTestOpenQuestion(test_id, question_id, number);
            db.updateDB(SQL_connectQuestion);
        }
    }
    
    public void addOneChoiceQuestion(Question question, int test_id, int number) throws SQLException{
        
        String SQL_addQuestion = SQLInstruct.addOneChoiceQuestion(question.getText());
        db.updateDB(SQL_addQuestion);
        
        int question_id = 0;
        String SQL_questionId = SQLInstruct.getOneChoiceQuestionAdded();
        ResultSet rSet = db.queryDB(SQL_questionId);
        
        if(rSet.next()){
            question_id = rSet.getInt(1);
            String SQL_connectQuestion = SQLInstruct.connectTestOneChoiceQuestion(test_id, question_id, number);
            db.updateDB(SQL_connectQuestion);
        }
  
        for(String hyp: question.getPossibleAnswers()){
            String SQL_addHypotehsis;
            if(hyp.equals(question.getCorrectAnswer())){
            SQL_addHypotehsis = SQLInstruct.insertHypothesis(hyp, 1);
            }else{
            SQL_addHypotehsis = SQLInstruct.insertHypothesis(hyp, 0);
            }
            
            db.updateDB(SQL_addHypotehsis);
            
            String SQL_hypothesisId = SQLInstruct.getHypothesisAdded();
            ResultSet rSet_hypId = db.queryDB(SQL_hypothesisId);
            int hyp_id;
            if(rSet_hypId.next()){
            hyp_id = rSet_hypId.getInt(1);
            String SQL_connectHypothesis = SQLInstruct.connectOneChoiceQuestionHypothesis(question_id, hyp_id);
            db.updateDB(SQL_connectHypothesis);
            }
        }
    }
    
     public void addMultipleChoiceQuestion(Question question, int test_id, int number) throws SQLException{
         
         String SQL_addQuestion=SQLInstruct.addMultipleChoiceQuestion(question.getText());
         db.updateDB(SQL_addQuestion);
         
         int question_id=0;
         String SQL_QuestionId= SQLInstruct.getMultipleChoiceQuestionAdded();
         ResultSet rSet = db.queryDB(SQL_QuestionId);
         
         if(rSet.next()){
             question_id=rSet.getInt(1);
             String SQL_connectQuestion = SQLInstruct.connectTestMultipleChoiceQuestion(test_id,question_id,number);
             db.updateDB(SQL_connectQuestion);          
         }
         
         for(String hyp: question.getPossibleAnswers()){
             String SQL_addHypothesis = null;
             for(String correct:question.getCorrectAnswers()){
                    if(hyp.equals(correct)){
                       SQL_addHypothesis = SQLInstruct.insertHypothesis(hyp, 1);
                    }else{
                        SQL_addHypothesis = SQLInstruct.insertHypothesis(hyp, 0);
                    }
                    
             }     
             db.updateDB(SQL_addHypothesis);
             String SQL_hypothesisId = SQLInstruct.getHypothesisAdded();
             ResultSet rSet_hypId = db.queryDB(SQL_hypothesisId);
             int hyp_id;
             if(rSet_hypId.next()){
             hyp_id = rSet_hypId.getInt(1);
             String SQL_connectHypothesis = SQLInstruct.connectMultipleChoiceQuestionHypothesis(question_id, hyp_id);
             db.updateDB(SQL_connectHypothesis);
             
         }
     }
   
}
     
     
    public LinkedList<Result> getOpenQuestionTestResults(int test_id) throws SQLException{
        String SQLStatement = SQLInstruct.getOpenAnswers(test_id);
        ResultSet rSet = db.queryDB(SQLStatement);
        LinkedList results = new LinkedList<Result>();
        while(rSet.next()){
            results.add(new Result(rSet.getString(1), rSet.getString(2), rSet.getString(3)));
        }
 
        return results;
    }
    
    //REMOVER MODULOS e TESTES
    
    public void removeModule(int discipline_id, int module_id) throws SQLException{
        for( Discipline d : current_professor.getDisciplines()){
            if( d.getId()==discipline_id){
                for( Module m : d.getModules()){
                    if(m.getId()==module_id){
                        d.getModules().remove(m);
                    }
                }
            }
        }
        System.out.println();
        String SQLStatement = SQLInstruct.removeModule(discipline_id, module_id);
        db.updateDB(SQLStatement);
    }
    
    
    public void removeTest(int discipline_id, int module_id,int test_id) throws SQLException{
         for( Discipline d : current_professor.getDisciplines()){
            if( d.getId()==discipline_id){
                for( Module m : d.getModules()){
                    if(m.getId()==module_id){
                        for( Test t: m.getTests()){
                            if(t.getId()==test_id){
                               m.getTests().remove(t);
                            }
                        }
                    }
                }
            }
        }

        String SQLStatement = SQLInstruct.removeTest(module_id,test_id);
        db.updateDB(SQLStatement);
    }
    
}
