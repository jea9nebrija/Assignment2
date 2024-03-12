/*************************************************
 File: User.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: Execution of User's log in account information
 *************************************************/



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class User {
    private final int id;
    private String userName;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private List<Account> accounts;
    ObjectMapper objectMapper =new ObjectMapper();

    public User(int id, String userName, String role){
        this.id=id;
        this.userName = userName;
        this.role=role;
    }

    public static User parseJson(String s) {
        ObjectMapper om=new ObjectMapper();
        JsonNode jn = null;
        try {jn = om.readTree(s);}
        catch (JsonProcessingException e) {throw new RuntimeException(e);}
        return new User(jn.get("id").asInt(),jn.get("userName").textValue(),jn.get("role").textValue());
    }

    public int getId(){
        return this.id;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){this.userName=userName;}
    public void setPassword(String password){this.password=password;}

    public String display(){
        return "id : " + this.getId()
                + "\nuserName : " + this.getUserName()
                + "\nrole : " + this.getRole();
    }

    public String toJson(){
        String string = "";
        try {string = objectMapper.writeValueAsString(this);}
        catch (JsonProcessingException jpe) {System.out.println(jpe.getMessage());}
        return string;
    }

    public List<Account> getAccounts(){
        //retrieve accounts from database
        return this.accounts;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

}
