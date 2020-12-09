package Simon;

import java.sql.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;


public class DataAccess {

    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/school?useTimezone=true&serverTimezone=UTC";


    public DataAccess() throws SQLException, ClassNotFoundException {

        // Load JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establish connection to database
        System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(
                url,
                new SECRET().getUser(),
                new SECRET().getPassworrd());

        // We will enable auto commit so each single update sent to the database will be commited immediately
        connection.setAutoCommit(true);
        connection.setReadOnly(false);

    }

    public void closeDb() throws SQLException {
        System.out.println("Closing connection...");
        connection.close();
    }

    // Get student table data
    public List<Student> getAllRowsStudent() throws SQLException {
        String sql = "SELECT * FROM students";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Student> list = new ArrayList<>();

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            list.add(new Student(id, name, address));
        }



        preparedStatement.close();
        return list;
    }

    public List<Classes> getAllRowsCourses() throws SQLException {
        String sql = "SELECT * FROM courses";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Classes> list = new ArrayList<>();

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            list.add(new Classes(id, name));
        }



        preparedStatement.close();
        return list;
    }

    public Connection getConnection() {
        return connection;
    }
}
