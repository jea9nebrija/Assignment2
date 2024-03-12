/*************************************************
 File: TransactionDAO.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: [short description on what program does, what user
 enters, what result(s) are displayed]
 *************************************************/



import java.sql.SQLException;
import java.util.List;

public interface TransactionDAO {
    List<Transaction> getAllTransactions();

    Transaction getTransaction(int id);
    void addTransaction(Transaction t);

    int resetTable() throws SQLException;

}