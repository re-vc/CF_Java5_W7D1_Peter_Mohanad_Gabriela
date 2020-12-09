package Simon;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static DataAccess dataAccess;

    public static void main(String[] args) {
    init();
    try {
        displayRowsStudents(dataAccess.getAllRowsStudent());
        displayRowsCourses(dataAccess.getAllRowsCourses());
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        stop();
    }
    }

    static public void init() {
        try {
            dataAccess = new DataAccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void stop() {
        try {
            dataAccess.closeDb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void displayRowsStudents(List<Student> list) {

        String tableName = "";
        try {// This is just to read the database (table) meta data!
            Statement stmt = dataAccess.getConnection().createStatement();
            String query = "SELECT * FROM students";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            tableName = resultSetMetaData.getTableName(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n#####PARSING DATA FROM " + tableName + " TABLE#####\n");
        for(Student item : list) {
            System.out.println(item + "\n");
        }
    }

    static public void displayRowsCourses(List<Classes> list) {
        String tableName = "";
        try {// This is just to read the database (table) meta data!
            Statement stmt = dataAccess.getConnection().createStatement();
            String query = "SELECT * FROM courses";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            tableName = resultSetMetaData.getTableName(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n#####PARSING DATA FROM " + tableName + " TABLE#####\n");
        for(Classes item : list) {
            System.out.println(item + "\n");
        }
    }
}
