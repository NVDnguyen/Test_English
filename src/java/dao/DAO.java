/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Accounts;
import Model.Lessons;
import Model.Questions;
import Model.Reporters;
import Model.Rooms;

import Model.Tests;
import Model.Topics;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author nguye
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //topic
    public ArrayList<Topics> getAllTopic() {
        ArrayList<Topics> list = new ArrayList<>();
        String query = "select * from Topic";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Topics getSQL = new Topics(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                if (getSQL.checkNotNull()) {
                    list.add(getSQL);
                }

            }
        } catch (Exception e) {
            System.out.println("getAllTopic" + e.getMessage());
        }
        return list;
    }

    public Topics getTopicWithID(String id) {

        String query = "select * from Topic where [idTopic]=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Topics tp = new Topics(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                return tp;

            }
        } catch (Exception e) {
            System.out.println("getTopicWithID" + e.getMessage());
        }
        return null;
    }

    public String getIdTopicWithIDLeson(String idLesson) {

        String query = "select * from [Lessons] where [idTopic]=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idLesson);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(4);
            }
        } catch (Exception e) {
            System.out.println("getIdTopicWithIDLeson" + e.getMessage());
        }
        return null;
    }

    public boolean addTopic(Topics tp) {
        String query = "INSERT INTO Questions(\n"
                + "    [descriptionQ]\n"
                + "    ,[answer1]\n"
                + "    ,[answer2]\n"
                + "    ,[answer3]\n"
                + "    ,[answer4]\n"
                + "    ,[CorrectAnswer]\n"
                + "    ,[idTest])\n"
                + "VALUES (?,?,?,?,?,?,?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
//            ps.setString(1, q.getDescriptionQ());
//            ps.setString(2, q.getAnswer1());
//            ps.setString(3, q.getAnswer2());
//            ps.setString(4, q.getAnswer3());
//            ps.setString(5, q.getAnswer4());
//            ps.setString(6, correctAnswer);
//            ps.setString(7, q.getIdTest());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addQuestion" + e);
        }

        return false;
    }

    //lesson
    public ArrayList<Lessons> getAllLessons() {
        ArrayList<Lessons> list = new ArrayList<>();
        String query = "select * from Lessons";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Lessons getSQL = new Lessons(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                );

                list.add(getSQL);

            }
        } catch (Exception e) {
            System.out.println("getAllLessons" + e.getMessage());

        }
        return list;
    }

    public Lessons getLessonWithID(String id) {

        String query = "select * from Lessons where [idLesson]=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Lessons ls = new Lessons(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                );

                return ls;

            }
        } catch (Exception e) {
            System.out.println("getLessonWithID" + e.getMessage());

        }
        return null;
    }

    public ArrayList<Lessons> getLessonsWithTopic(String idTopic) {
        ArrayList<Lessons> list = new ArrayList<>();
        String query = "select * from Lessons\n"
                + "where idTopic =?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idTopic);
            rs = ps.executeQuery();
            while (rs.next()) {
                Lessons getSQL = new Lessons(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));

                list.add(getSQL);

            }
        } catch (Exception e) {
            System.out.println("getLessonsWithTopic:" + e.getMessage());

        }
        return list;
    }

    public boolean addLesson(Lessons ls) {
        String query = " insert into Lessons(descriptionL,idTopic)\n"
                + "  values (?,?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, ls.getDescription());
            ps.setString(2, ls.getIdTopic());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addLesson" + e);
        }

        return false;
    }

    public boolean deleteLesson(String idLesson) {
        String query = "BEGIN TRANSACTION;\n"
                // delete question for test 
                + "DELETE FROM [Questions]\n"
                + "WHERE idTest IN (\n"
                + "    SELECT idTest\n"
                + "    FROM [Test]\n"
                + "    WHERE idLesson = ?\n"
                + ");\n"
                //delete idTest in Rooms
                + "DELETE FROM [Rooms]\n"
                + "WHERE idTest IN (\n"
                + "    SELECT idTest\n"
                + "    FROM [Test]\n"
                + "    WHERE idLesson = ?\n"
                + ");\n"
                //delete idTest in Reporter
                + "DELETE FROM [Reporter]\n"
                + "WHERE idTest IN (\n"
                + "    SELECT idTest\n"
                + "    FROM [Test]\n"
                + "    WHERE idLesson = ?\n"
                + ");\n"
                // delete Test with idTest
                + "DELETE FROM [Test]\n"
                + "WHERE idLesson = ?;\n"
                // delete Lesson with idLEsson
                + "DELETE FROM [Lessons]\n"
                + "WHERE idLesson = ?;\n"
                + "COMMIT;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idLesson);
            ps.setString(2, idLesson);
            ps.setString(3, idLesson);
            ps.setString(4, idLesson);
            ps.setString(5, idLesson);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deleteLesson" + e.getMessage());
        }

        return false;
    }

    public boolean updateLesson(Lessons ls) {
        String query = " update Lessons set descriptionL = ? where idLesson=?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, ls.getDescription());
            ps.setString(2, ls.getIdLesson());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateLesson" + e.getMessage());
        }

        return false;
    }

    //Test
    public String createNewTest(String descriptionTs, String idLesson, int Timer) {
        int idTest = -1;
        String query = "INSERT INTO Test(descriptionTs, idLesson, Time) VALUES (?, ?,?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS); // Thực thi truy vấn
            ps.setString(1, descriptionTs);
            ps.setString(2, idLesson);
            ps.setString(3, String.valueOf(Timer));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                // Lấy khóa chính của bản ghi vừa được thêm vào
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idTest = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("createTest" + e);
        }

        return String.valueOf(idTest);
    }

    public ArrayList<Tests> getAllTest() {
        ArrayList<Tests> list = new ArrayList<>();
        String query = "SELECT * FROM Test"; // Replace 'YourTestTable' with the actual table name
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tests getSQL = new Tests(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                list.add(getSQL);
            }
        } catch (Exception e) {
            System.out.println("getAllTest:" + e.getMessage());
        }
        return list;
    }

    public Tests getTestWithID(String id) {
        String query = "SELECT * FROM Test where [idTest]=? "; 
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tests t = new Tests(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                return t;
            }
        } catch (Exception e) {
            System.out.println("getAllTest:" + e.getMessage());
        }
        return null;
    }

    public ArrayList<Tests> getTestWithLesson(String idLesson) {
        ArrayList<Tests> list = new ArrayList<>();
        String query = "SELECT * FROM Test where idLesson=? "; 
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idLesson);
            rs = ps.executeQuery();
            while (rs.next()) {
                Tests getSQL = new Tests(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                list.add(getSQL);
            }
        } catch (Exception e) {
            System.out.println("getTestWithLesson:" + e.getMessage());

        }
        return list;
    }

    public boolean deleteTest(String idTest) {

        String query = "  delete Questions\n"
                + "  where idTest = ?\n"
                //+ "  go\n"
                + "  delete Test\n"
                + "  where idTest = ? ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idTest);
            ps.setString(2, idTest);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("deleteTest:" + e.getMessage());

        }
        return false;
    }

    public boolean updateTest(Tests test) {
        String query = "UPDATE dbo.Test\n"
                + "SET\n"
                + "    descriptionTs = ?,\n"
                + "    Time = ?\n"
                + "WHERE\n"
                + "    idTest = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, test.getDescriptionTs());
            ps.setString(2, test.getTime());
            ps.setString(3, test.getIdTest());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("updateTest: " + e.getMessage());
        }

        return false;
    }

    //question
    public ArrayList<Questions> getAllQuestion() {
        ArrayList<Questions> list = new ArrayList<>();
        String query = "SELECT * FROM Questions"; 
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Questions getSQL = new Questions(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
                list.add(getSQL);
            }
        } catch (Exception e) {
            System.out.println("getAllQuestion:" + e.getMessage());

        }
        return list;
    }

    public ArrayList<Questions> getQuestionWithTest(String idTest) {
        ArrayList<Questions> list = new ArrayList<>();
        String query = "SELECT * FROM Questions where idTest=? "; 
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idTest);
            rs = ps.executeQuery();
            while (rs.next()) {
                Questions getSQL = new Questions(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getString(7)
                );
                list.add(getSQL);
            }
        } catch (Exception e) {
            System.out.println("getQuestionWithTest:" + e.getMessage());

        }
        return list;
    }

    public boolean addQuestion(Questions q) {
        String query = "INSERT INTO Questions(\n"
                + "    [descriptionQ]\n"
                + "    ,[answer1]\n"
                + "    ,[answer2]\n"
                + "    ,[answer3]\n"
                + "    ,[answer4]\n"
                + "    ,[CorrectAnswer]\n"
                + "    ,[idTest])\n"
                + "VALUES (?,?,?,?,?,?,?)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, q.getDescriptionQ());
            ps.setString(2, q.getAnswer1());
            ps.setString(3, q.getAnswer2());
            ps.setString(4, q.getAnswer3());
            ps.setString(5, q.getAnswer4());
            ps.setString(6, q.getCorrectAnswer());
            ps.setString(7, q.getIdTest());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addQuestion" + e);
        }

        return false;
    }

    public boolean updateQuestion(Questions q) {
        String query = "UPDATE Questions\n"
                + "SET\n"
                + "    descriptionQ = ?,\n"
                + "    answer1 = ?,\n"
                + "    answer2 = ?,\n"
                + "    answer3 = ?,\n"
                + "    answer4 = ?,\n"
                + "    CorrectAnswer = ?\n"
                + "WHERE\n"
                + "    idQ = ?;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, q.getDescriptionQ());
            ps.setString(2, q.getAnswer1());
            ps.setString(3, q.getAnswer2());
            ps.setString(4, q.getAnswer3());
            ps.setString(5, q.getAnswer4());
            ps.setString(6, q.getCorrectAnswer());
            ps.setString(7, q.getIdQuestion());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateQuestion" + e.getMessage());
        }

        return false;
    }

    public boolean deleteQuestion(String idQuestion) {
        String query = "DELETE FROM Questions WHERE idQ = ?;";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idQuestion);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deleteQuestion: " + e.getMessage());
        }

        return false;
    }

    //account
    public Accounts getAccount(String userName, String password) {
        Accounts acc = new Accounts();
        String query = "select *\n"
                + "from Account\n"
                + "where userName=? and password=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) { 
                acc.setUserName(rs.getString(1));
                acc.setPassword(rs.getString(2));
                acc.setIsAdmin(String.valueOf(rs.getBoolean(3)));
                return acc;
            }
        } catch (Exception e) {
         
            System.out.println("getAccount:" + e.getMessage());

        }
        return null;
    }

    public ArrayList<Accounts> getAllAccount() {
        ArrayList<Accounts> data = new ArrayList<>();
        String query = "select *\n"
                + "from Account\n";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) { 
                Accounts acc = new Accounts();
                acc.setUserName(rs.getString(1));
                acc.setPassword(rs.getString(2));
                acc.setIsAdmin(String.valueOf(rs.getBoolean(3)));
                data.add(acc);
            }
            return data;
        } catch (Exception e) {
      
            System.out.println("getAllAccount:" + e.getMessage());

        }
        return null;
    }

    public boolean addAccount(Accounts acc) {
        String query = "insert into Account(userName,password,isAdmin)\n"
                + "values (?,?,?) "; 
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, acc.getUserName());
            ps.setString(2, acc.getPassword());
            ps.setString(3, acc.getIsAdmin());
            int rowsAffected = ps.executeUpdate();
           
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("addAccount:" + e.getMessage());
            return false;

        }

    }

    public boolean checkAccount(Accounts acc) {
        Accounts acc1 = getAccount(acc.getUserName(), acc.getPassword());
        if (acc1 != null) {
            return true;
        }
        return false;
    }

    //Rooms Exam code
    public String getTestRoomIsActive(String codeRoom) {
        String query = "  select idTest,active\n"
                + "  from [Rooms]\n"
                + "  where codeRoom= ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, codeRoom);
            rs = ps.executeQuery();
            if (rs.next()) {
                String idTest = rs.getString(1);
                boolean isActive = rs.getBoolean(2);
                System.out.println("room:" + isActive);
                if (isActive) {
                    return idTest;
                }
            }
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("getTestRoomIsActive:" + e.getMessage());

        }
        return null;
    }

    public Rooms getRoom(String codeRoom) {
        String query = "  select *\n"
                + "  from [Rooms]\n"
                + "  where codeRoom= ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, codeRoom);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rooms r = new Rooms(rs.getString(1), rs.getString(6), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return r;
            }
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("getRoom:" + e.getMessage());

        }
        return null;
    }

    public ArrayList<Rooms> getAllRoom(Accounts acc) {
        ArrayList<Rooms> r = new ArrayList<>();
        String query = "  select *\n"
                + "  from [Rooms]\n"
                + "  where creater= ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, acc.getUserName());
            rs = ps.executeQuery();
            while (rs.next()) {
                r.add(new Rooms(
                        rs.getString(1),
                        rs.getString(6),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getBoolean(5))
                ));

            }
            return r;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("getAllRoom:" + e.getMessage());

        }
        return null;
    }

    public ArrayList<Rooms> getAllRoom() {
        ArrayList<Rooms> r = new ArrayList<>();
        String query = "  select *\n"
                + "  from [Rooms]\n";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                r.add(new Rooms(
                        rs.getString(1),
                        rs.getString(6),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getBoolean(5))
                ));

            }
            return r;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("getAllRoom:" + e.getMessage());

        }
        return null;
    }

    public boolean addRoom(Rooms r) {
        String query = "  insert into Rooms(codeRoom,creater,idTest,active,nameRoom)\n"
                + "  values(?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, r.getCodeRoom());
            ps.setString(2, r.getCreater());
            ps.setString(3, r.getIdTest());
            ps.setString(4, r.getActive());
            ps.setString(5, r.getNameRoom());
            int x = ps.executeUpdate();
            return x > 0;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("addRoom:" + e.getMessage());
        }
        return false;

    }

    public boolean updateActiveRoom(String idRoom, String active) {
        String query = "  UPDATE Rooms\n"
                + "SET active = ?\n"
                + "WHERE idRoom=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, active);
            ps.setString(2, idRoom);
            int x = ps.executeUpdate();
            return x > 0;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("updateActiveRoom:" + e.getMessage());
        }
        return false;

    }

    // Reporter
    public ArrayList<Reporters> gettAllReporter() {
        ArrayList<Reporters> data = new ArrayList<>();
        String query = "select *\n"
                + "from Reporter\n";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) { // Move the cursor to the first row
                data.add(new Reporters(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return data;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("gettAllReporter:" + e.getMessage());

        }
        return null;
    }

    public ArrayList<Reporters> gettAllReporter(String userName) {
        ArrayList<Reporters> data = new ArrayList<>();
        String query = "select *\n"
                + "from Reporter\n"
                + "where userName=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) { // Move the cursor to the first row
                data.add(new Reporters(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return data;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("gettAllReporter with userName:" + e.getMessage());

        }
        return null;
    }

    public ArrayList<Reporters> gettAllReporterWithTest(String idTest) {
        ArrayList<Reporters> data = new ArrayList<>();
        String query = "select *\n"
                + "from Reporter\n"
                + "where idTest=? and idRoom is null";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idTest);
            rs = ps.executeQuery();
            while (rs.next()) { // Move the cursor to the first row
                data.add(new Reporters(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return data;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("gettAllReporter with userName:" + e.getMessage());

        }
        return null;
    }

    public ArrayList<Reporters> gettAllReporterWithTestDESC(String idTest) {
        ArrayList<Reporters> data = new ArrayList<>();
        String query = "select *\n"
                + "from Reporter\n"
                + "where idTest=? and idRoom is null \n"
                + "ORDER BY Grade DESC; \n";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idTest);
            rs = ps.executeQuery();
            while (rs.next()) { // Move the cursor to the first row
                data.add(new Reporters(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return data;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("gettAllReporterWithTestDESC with userName:" + e.getMessage());

        }
        return null;
    }

    public ArrayList<Reporters> gettAllReporterWithTestASC(String idTest) {
        ArrayList<Reporters> data = new ArrayList<>();
        String query = "select *\n"
                + "from Reporter\n"
                + "where idTest=? and idRoom is null \n"
                + "ORDER BY Grade ASC; \n";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idTest);
            rs = ps.executeQuery();
            while (rs.next()) { // Move the cursor to the first row
                data.add(new Reporters(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return data;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("gettAllReporterWithTestASC with userName:" + e.getMessage());

        }
        return null;
    }

    public ArrayList<Reporters> gettAllReporterWithCode(String idTest, String idRoom) {
        ArrayList<Reporters> data = new ArrayList<>();
        String query = "select *\n"
                + "from Reporter\n"
                + "where idTest=? and idRoom =?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idTest);
            ps.setString(2, idRoom);
            rs = ps.executeQuery();
            while (rs.next()) { // Move the cursor to the first row
                data.add(new Reporters(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return data;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("gettAllReporter with userName:" + e.getMessage());

        }
        return null;
    }

    public ArrayList<Reporters> gettAllReporterWithCodeDESC(String idTest, String idRoom) {
        ArrayList<Reporters> data = new ArrayList<>();
        String query = "select *\n"
                + "from Reporter\n"
                + "where idTest=? and idRoom =? \n"
                + "ORDER BY Grade DESC \n";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idTest);
            ps.setString(2, idRoom);
            rs = ps.executeQuery();
            while (rs.next()) { // Move the cursor to the first row
                data.add(new Reporters(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return data;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("gettAllReporterWithCodeDESC with userName:" + e.getMessage());

        }
        return null;
    }

    public ArrayList<Reporters> gettAllReporterWithCodeASC(String idTest, String idRoom) {
        ArrayList<Reporters> data = new ArrayList<>();
        String query = "select *\n"
                + "from Reporter\n"
                + "where idTest=? and idRoom =? \n"
                + "ORDER BY Grade ASC \n";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idTest);
            ps.setString(2, idRoom);
            rs = ps.executeQuery();
            while (rs.next()) { // Move the cursor to the first row
                data.add(new Reporters(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return data;
        } catch (Exception e) {
            // Handle exceptions as needed
            System.out.println("gettAllReporterWithCodeASC with userName:" + e.getMessage());

        }
        return null;
    }

    public boolean addReporter(Reporters rp) {
        String query;
        try {
            if (rp.getIdRoom().isBlank()) {
                query = "insert into [Reporter](userName,idTest,Grade)\n"
                        + "values (?,?,?)";
            } else {
                query = "insert into [Reporter](userName,idTest,Grade,idRoom)\n"
                        + "values (?,?,?,?)";
            }

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, rp.getUserName());
            ps.setString(2, rp.getIdTest());
            ps.setString(3, rp.getGrade());

            if (!rp.getIdRoom().isBlank()) {
                ps.setString(4, rp.getIdRoom());
            }

            int x = ps.executeUpdate();
            return x > 0;
        } catch (Exception e) {
            System.out.println("addReporter: " + e.getMessage() + rp.toString());
        }
        return false;
    }

    //main test
    public static void main(String[] args) {
        DAO dao = new DAO();
//        List<Topic> list = dao.getAllTopic();
//        for (Topics topic : list) {
//            System.out.println(topic.toString());
//
//        }
//        List<Lessons> listL = dao.getAllLessons();
//        for (Lessons topic : listL) {
//            System.out.println(topic.toString());
//
//        }
////        // Get and print Tests
//        List<Tests> listT = dao.getAllTest();
//        for (Tests test : listT) {
//            System.out.println(test.toString());
//        }
//        //
//        List<Questions> listQ = dao.getAllQuestion();
//        for (Questions q : listQ) {
//            System.out.println(q.toString());
//        }
//        //
//        List<Answer> listA = dao.getAllAnswer();
//        for (Answer q : listA) {
//            System.out.println(q.toString());
//        }
//        List<Accounts> a = dao.getAllAccount();
//        for (Accounts accounts : a) {
//            System.out.println(accounts.toString());
//        }
//        System.out.println(dao.getTestRoomIsActive("X34sd99"));

        //System.out.println(dao.getAnswerWithQuestion("603"));
//        System.out.println(dao.createNewTest("22", null,60));
        // System.out.println(dao.getIdTopicWithIDLeson("1"));
        //       System.out.println(dao.deleteTest("30"));
        //System.out.println(dao.addRoom(new Rooms("hello", "yyyy", "user12345", "1", "true")));
//        System.out.println(dao.gettAllReporter());
//        System.out.println(dao.gettAllReporter("️user12345"));
//        System.out.println(dao.gettAllReporterWithTest("1"));
        //System.out.println(dao.getRoom("yyyy"));
        // System.out.println(dao.gettAllReporterWithCode("1", "1"));
        // System.out.println(dao.addReporter(new Reporters("user12345", "5", "2")));
        //System.out.println(dao.getAllAccount());
        // System.out.println(dao.getQuestionWithTest("1"));
        //System.out.println(dao.gettAllReporterWithTest("1"));
        //System.out.println(dao.deleteLesson("8"));
        System.out.println(randomCode());
    }
       public static String randomCode() {
        long now = System.currentTimeMillis();
        String codeNow = Long.toHexString(now);
        return codeNow;
        
       }
}
