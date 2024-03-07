/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguye
 */
public class Topics {

    private String idTopic;
    private String nameTopic;
    private String describeTopic;
    private String imageTopic;

    public Topics() {
    }

    public Topics(String idTopic, String nameTopic, String describeTopic, String imageTopic) {
        this.idTopic = idTopic;
        this.nameTopic = nameTopic;
        this.describeTopic = describeTopic;
        this.imageTopic = imageTopic;
    }

    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getDescribeTopic() {
        return describeTopic;
    }

    public void setDescribeTopic(String describeTopic) {
        this.describeTopic = describeTopic;
    }

    public String getImageTopic() {
        return imageTopic;
    }

    public void setImageTopic(String imageTopic) {
        this.imageTopic = imageTopic;
    }

    public boolean checkNotNull() {
        return idTopic != null
                && nameTopic != null && !nameTopic.trim().isEmpty()
                && describeTopic != null && !describeTopic.trim().isEmpty()
                && imageTopic != null && !imageTopic.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Topic{" + "idTopic=" + idTopic + ", nameTopic=" + nameTopic + ", describeTopic=" + describeTopic + ", imageTopic=" + imageTopic + '}';
    }

}
