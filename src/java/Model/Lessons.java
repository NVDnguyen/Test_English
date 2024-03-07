/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguye
 */
public class Lessons {
    private String idLesson;
    private String description;
    private String idTopic;
   
  

    public Lessons() {
    }

    public Lessons(String description, String idTopic) {
        this.description = description;
        this.idTopic = idTopic;
    }
    
    public Lessons(String idLesson,  String description, String idTopic) {
        this.idLesson = idLesson;       
        this.description = description;
        this.idTopic = idTopic;
      
  
    }

    public String getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(String idLesson) {
        this.idLesson = idLesson;
    }

  

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }





    @Override
    public String toString() {
        return "Lessons{" + "idLesson=" + idLesson +" description=" + description + ", idTopic=" + idTopic  + '}';
    }
    
    
}
