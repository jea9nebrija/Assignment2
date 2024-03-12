/*************************************************
 File: TransactionDAOConcrete.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: Implementation of Transaction of Data Access Object (DAO)
 *************************************************/



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.PriorityQueue;

class TransactionDAOConcrete implements TransactionDAO {
    static Connection connection = null;
    PreparedStatement pStatement;


    public TransactionDAOConcrete(){
        try {
            connection = MySQLConnection.getDBConnection();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public Transaction getTransaction(int id) {
        return null;
    }

    @Override
    public int resetTable() throws SQLException {
        System.out.println("(Re-)creating Transaction database");
        int res = -1;
        pStatement = connection.prepareStatement(MySQLConnection.getTransactionDrop());
        res = pStatement.executeUpdate();
        pStatement = connection.prepareStatement(MySQLConnection.getTransactionCreate());
        res = pStatement.executeUpdate();
        return res;
    }

    @Override
    public void addTransaction(Transaction t) {
        TransactionDTO.performInsert(t);
    }

    public int insertTransaction(Transaction transaction) throws SQLException {
        int res = -1;
        pStatement = connection.prepareStatement(MySQLConnection.getTransactionInsert());
        pStatement.setString(1, Integer.toString(transaction.getId()));
        pStatement.setString(2, transaction.getAmount().toString());
        pStatement.setString(3, String.valueOf(transaction.getTransactionDate()));
        res = pStatement.executeUpdate();
        return res;
    }
}