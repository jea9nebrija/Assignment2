/*************************************************
 File: UseDAOConcrete.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: Implementation of User's DAO
 *************************************************/



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class UserDAOConcrete implements UserDAO {
    List<User> users;
    static Connection connection = null;
    PreparedStatement pStatement;

    public UserDAOConcrete(){
        try {
            connection = MySQLConnection.getDBConnection();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }


        users = new ArrayList<User>();
    }

    @Override
    public void deleteUser(User user) {}

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUser(int id) {
        return users.get(id);
    }

    @Override
    public void updateUser(User user) {}

    @Override
    public void addUser(User u) {
        users.add(u);
        UserDTO.performInsert(u);
    }

    public int resetTable() throws SQLException {
        System.out.println("(Re-)creating User database");
        int res = -1;
        pStatement = connection.prepareStatement(MySQLConnection.getUserDrop());
        res = pStatement.executeUpdate();
        pStatement = connection.prepareStatement(MySQLConnection.getUserCreate());
        res = pStatement.executeUpdate();
        return res;
    }

    public int insertUser(User user) throws SQLException {
        int res = -1;
        pStatement = connection.prepareStatement(MySQLConnection.getUserInsert());
        pStatement.setString(1, Integer.toString(user.getId()));
        pStatement.setString(2, user.getUserName());
        pStatement.setString(3, user.getPassword());
        pStatement.setString(4, user.getRole());
        res = pStatement.executeUpdate();
        return res;
    }

    // Method to disconnect from the database
    public static void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public int update(User user) {
        return 0;
    }

}