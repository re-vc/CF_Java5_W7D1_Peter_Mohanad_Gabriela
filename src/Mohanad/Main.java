package Mohanad;

import java.sql.*;

public class School {

    static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/school" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String user = "root";
    static final String password = "";

    public  static void main(String[] args) {

        Connection connection = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_Driver);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(url,user,password);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            Statement statement = connection.createStatement();
            String sql;
            sql = "SELECT * FROM students";
            ResultSet rs = statement.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id_student");
                String name = rs.getString("students_name");
                String address = rs.getString("students_address");


                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.println(", Address: " + address);
            }
            //STEP 6: Clean-up environment
            rs.close();
            statement.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println(" THanks Goodbye!");




    }
}

