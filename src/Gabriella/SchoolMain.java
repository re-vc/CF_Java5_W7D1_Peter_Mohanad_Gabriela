package Gabriella;
import java.sql.*;
import java.util.*;

public class SchoolMain {
    // JDBC driver name and database URL
    static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/school" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //  Database credentials
    static final String user = "root";
    static final String password = "";

    public static void main(String[] args) {
        Connection con = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_Driver);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            con = DriverManager.getConnection(url,user,password);

            //************************add student data******************
             System.out.println("Student name:");
             Scanner in = new Scanner(System.in);
             String sName = in.next();

             System.out.println("Student address:");

             String sAddress = in.next();

             System.out.println("add data into sql db...");

             String sql4;
             sql4 = "insert into students (name, address) values (?,?);";
            PreparedStatement preparedStatement1 = con.prepareStatement(sql4);

            preparedStatement1.setString(1, sName);
            preparedStatement1.setString(2, sAddress);

            preparedStatement1.executeUpdate();



            //STEP 4: Execute a query
            System.out.println("Creating our statement...");
            Statement statement = con.createStatement();
            String sql;
            sql = "SELECT * FROM students order by name";
            ResultSet rs = statement.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");  //add the field name from DB table
                String name = rs.getString("name");
                String address = rs.getString("address");


                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.println(", Address: " + address);
            }
            rs.close();
            statement.close();
            //***************************second table
            //STEP 4: Execute a query
            System.out.println("Creating our statement2...");
            Statement statement2 = con.createStatement();
            String sql2;
            sql2 = "SELECT * FROM courses";
            ResultSet rs2 = statement2.executeQuery(sql2);

            //STEP 5: Extract data from result set
            while(rs2.next()){
                //Retrieve by column name
                int id2  = rs2.getInt("id");  //add the field name from DB table
                String name2 = rs2.getString("name");


                //Display values
                System.out.print("ID: " + id2);
                System.out.println(", Name: " + name2);

            }
            rs2.close();
            statement2.close();
            //**********************end second table

            //***************************third quiry
            //STEP 4: Execute a query
            System.out.println("Creating our statement3...");
            Statement statement3 = con.createStatement();
            String sql3;
            sql3 = "SELECT enrollments.fk_students_id, students.name, enrollments.fk_courses_id, courses.name from enrollments " +
                    "join students on students.id=enrollments.fk_students_id" +
                    " join courses on courses.id=enrollments.fk_courses_id";
            ResultSet rs3 = statement3.executeQuery(sql3);

            //STEP 5: Extract data from result set
            while(rs3.next()){
                //Retrieve by column name
                int sId3  = rs3.getInt("enrollments.fk_students_id");  //add the field name from DB table
                String sName3 = rs3.getString("students.name");
                int cId3  = rs3.getInt("enrollments.fk_courses_id");  //add the field name from DB table
                String cName3 = rs3.getString("courses.name");

                //Display values
                System.out.print("Student ID: " + sId3);
                System.out.print(", Name: " + sName3);
                System.out.print("    Course ID: " + cId3);
                System.out.println(", Name: " + cName3);

            }
            //STEP 6: Clean-up environment
            rs3.close();
            statement3.close();
            //**********************end third quiry






        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(con!=null)
                    con.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Thank you for running the application!");


    }



}
