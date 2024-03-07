/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguye
 */
 public class Questions {
    private String idQuestion;
    private String descriptionQ;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4; 
    private String idTest,correctAnswer;
    
    // Constructor
    
    public Questions() {
    }

    public Questions(String idQuestion, String descriptionQ, String answer1, String answer2, String answer3, String answer4, String idTest, String correctAnswer) {
        this.idQuestion = idQuestion;
        this.descriptionQ = descriptionQ;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.idTest = idTest;
        this.correctAnswer = correctAnswer;
    }

    public Questions(String descriptionQ, String answer1, String answer2, String answer3, String answer4, String idTest, String correctAnswer) {
        this.descriptionQ = descriptionQ;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.idTest = idTest;
        this.correctAnswer = correctAnswer;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getDescriptionQ() {
        return descriptionQ;
    }

    public void setDescriptionQ(String descriptionQ) {
        this.descriptionQ = descriptionQ;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getIdTest() {
        return idTest;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Questions{" + "idQuestion=" + idQuestion + ", descriptionQ=" + descriptionQ + ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3=" + answer3 + ", answer4=" + answer4 + ", idTest=" + idTest + ", correctAnswer=" + correctAnswer + '}';
    }

   
    
}
