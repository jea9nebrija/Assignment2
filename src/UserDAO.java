/*************************************************
 File: UserDAO.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: Implementation of User's DAO
 *************************************************/


import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    User getUser(int id);

    void deleteUser(User user);

    void updateUser(User user);

    void addUser(User u);

    int resetTable() throws SQLException;
}
