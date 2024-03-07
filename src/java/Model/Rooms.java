/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguye
 */
public class Rooms {
    private String idRoom,nameRoom,codeRoom,creater,idTest,active;

    public Rooms() {
    }

    public Rooms(String nameRoom, String codeRoom, String creater, String idTest, String active) {
        this.nameRoom = nameRoom;
        this.codeRoom = codeRoom;
        this.creater = creater;
        this.idTest = idTest;
        this.active = active;
    }

    public Rooms(String idRoom, String nameRoom, String codeRoom, String creater, String idTest, String active) {
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.codeRoom = codeRoom;
        this.creater = creater;
        this.idTest = idTest;
        this.active = active;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public String getCodeRoom() {
        return codeRoom;
    }

    public void setCodeRoom(String codeRoom) {
        this.codeRoom = codeRoom;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getIdTest() {
        return idTest;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Rooms{" + "idRoom=" + idRoom + ", nameRoom=" + nameRoom + ", codeRoom=" + codeRoom + ", creater=" + creater + ", idTest=" + idTest + ", active=" + active + '}';
    }

    
}
