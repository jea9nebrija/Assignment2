/*************************************************
 File: AccountDTO.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: [short description on what program does, what user
 enters, what result(s) are displayed]
 *************************************************/



import java.sql.SQLException;

public class AccountDTO {

    private int id;
    private String accountId;
    private String balance;
    private String dateCreated;

    static AccountDAOConcrete adc = new AccountDAOConcrete();

    public AccountDTO() {
        // Default constructor
    }

    public AccountDTO(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void setID(int anID) {
        this.id = anID;
    }

    public static int performInsert(Account account) throws SQLException {
        int updateResult = -1;

        updateResult = adc.insertAccount(account);

        if(updateResult != -1) System.out.println("Account database updated");
        return updateResult;
    }
}
