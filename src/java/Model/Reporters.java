package Model;

public class Reporters {
    String idReporter, userName, idTest, Grade,idRoom;

    public Reporters() {
    }

    public Reporters(String userName, String idTest, String Grade) {
        this.userName = userName;
        this.idTest = idTest;
        this.Grade = Grade;
       
    }

    public Reporters(String userName, String idTest, String Grade, String idRoom) {
        this.userName = userName;
        this.idTest = idTest;
        this.Grade = Grade;
        this.idRoom = idRoom;
    }

    public Reporters(String idReporter, String userName, String idTest, String Grade,String idRoom) {
        this.idReporter = idReporter;
        this.userName = userName;
        this.idTest = idTest;
        this.Grade = Grade;
        this.idRoom = idRoom;
    }

    public String getIdReporter() {
        return idReporter;
    }

    public void setIdReporter(String idReporter) {
        this.idReporter = idReporter;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdTest() {
        return idTest;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String Grade) {
        this.Grade = Grade;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    @Override
    public String toString() {
        return "Reporters{" + "idReporter=" + idReporter + ", userName=" + userName + ", idTest=" + idTest + ", Grade=" + Grade + ", idRoom=" + idRoom + '}';
    }
    


    

    
}
