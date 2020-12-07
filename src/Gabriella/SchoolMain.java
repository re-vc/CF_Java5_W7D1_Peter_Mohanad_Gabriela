package Gabriella;
import java.sql.*;

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

            //STEP 4: Execute a query
            System.out.println("Creating our statement...");
            Statement statement = con.createStatement();
            String sql;
            sql = "SELECT * FROM students";
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
            //**********************end second table

            //STEP 6: Clean-up environment
            rs.close();
            statement.close();
            rs2.close();
            statement2.close();
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
