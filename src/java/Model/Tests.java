/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguye
 */
public class Tests {

    private String idTest;
    private String descriptionTs;
    private String idLesson,Time;
    

    // Constructors
    public Tests() {
        // Default constructor
    }

    public Tests(String idTest, String descriptionTs, String idLesson, String Time) {
        this.idTest = idTest;
        this.descriptionTs = descriptionTs;
        this.idLesson = idLesson;
        this.Time = Time;
    }

    public Tests(String descriptionTs, String idLesson, String Time) {
        this.descriptionTs = descriptionTs;
        this.idLesson = idLesson;
        this.Time = Time;
    }

    public String getIdTest() {
        return idTest;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }

    public String getDescriptionTs() {
        return descriptionTs;
    }

    public void setDescriptionTs(String descriptionTs) {
        this.descriptionTs = descriptionTs;
    }

    public String getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(String idLesson) {
        this.idLesson = idLesson;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
    

    
}
