/*************************************************
 File: UserDTO.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: Execution of User's DTO and DAO
 *************************************************/



 import java.sql.SQLException;

public class UserDTO {

    private int id;
    private String username;
    private String password;
    private String role;

    static UserDAOConcrete udc = new UserDAOConcrete();

    public UserDTO() {
        // Default constructor
    }

    public UserDTO(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter and Setter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public int getID() {
        return id;
    }

    public void setID(int anID) {
        this.id = anID;
    }

    public static int performInsert(User user) {
        int updateResult = -1;

        try {
            updateResult = udc.insertUser(user);
        } catch(SQLException se) {
            System.out.println(se.getMessage());
        }

        if(updateResult != -1) System.out.println("User database updated");
        return updateResult;
    }
    public static int performUpdate(User user) {
        return 0;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
