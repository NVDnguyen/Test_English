/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.Random;

/**
 *
 * @author nguye
 */
public class Rooms {

    private String idRoom, nameRoom, codeRoom, creater, idTest, active;

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

    public Rooms(String nameRoom, String creater, String idTest, String active) {
        this.nameRoom = nameRoom;
        this.creater = creater;
        this.idTest = idTest;
        this.active = active;
        this.codeRoom = randomCode();
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

    public String randomCode() {
      //  StringBuilder sb = new StringBuilder();
       // Random random = new Random();
        long now = System.currentTimeMillis();
        String codeNow = Long.toHexString(now);
//        String charr = "QUERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm1234567890";
//        sb.append(now);
//        for (int i = 0; i < 3; i++) {
//            int randomInt = random.nextInt(charr.length());
//            String c = charr.indent(randomInt);
//            sb.append(c);
//        }

        return codeNow;
    }

}
