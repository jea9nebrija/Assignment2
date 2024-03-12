/*************************************************
 File: AccountDAOConcrete.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: [short description on what program does, what user
 enters, what result(s) are displayed]
 *************************************************/



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.PriorityQueue;

class AccountDAOConcrete implements AccountDAO {
    private final PriorityQueue<Account> accounts = new PriorityQueue<>();
    static Connection connection = null;
    PreparedStatement pStatement;


    public AccountDAOConcrete(){
        try {
            connection = MySQLConnection.getDBConnection();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    @Override
    public PriorityQueue<Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public Account getAccount(int id) {return null;}

    @Override
    public void deleteAccount(Account account) {}

    @Override
    public void updateAccount(Account account) {}

    public void addAccount(Account account) {
        try {
            AccountDTO.performInsert(account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        accounts.add(account);
    }

    public int resetTable() throws SQLException {
        System.out.println("(Re-)creating Account database");
        int res = -1;
        pStatement = connection.prepareStatement(MySQLConnection.getAccountDrop());
        res = pStatement.executeUpdate();
        pStatement = connection.prepareStatement(MySQLConnection.getAccountCreate());
        res = pStatement.executeUpdate();
        return res;
    }

    public int insertAccount(Account account) throws SQLException {
        int res = -1;
        pStatement = connection.prepareStatement(MySQLConnection.getAccountInsert());
        pStatement.setString(1, Integer.toString(account.getId()));
        pStatement.setString(2, Integer.toString(account.getUser().getId()));
        pStatement.setString(3, account.getBalance().toString());
        res = pStatement.executeUpdate();
        return res;
    }
}