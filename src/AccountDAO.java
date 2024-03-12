/*************************************************
 File: AccountDAO.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: [short description on what program does, what user
 enters, what result(s) are displayed]
 *************************************************/



import java.sql.SQLException;
import java.util.PriorityQueue;

public interface AccountDAO {
    PriorityQueue<Account> getAllAccounts();

    Account getAccount(int id);

    void deleteAccount(Account account);

    void updateAccount(Account account);

    void addAccount(Account a);

    int resetTable() throws SQLException;
}