/*************************************************
 File: Main.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: Main driver for SQL, Account Data Access Object (DAO) of the customer.
 Evaluates data access of the users.
 *************************************************/



import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = MySQLConnection.getDBConnection();
        PreparedStatement pStatement = connection.prepareStatement("DROP schema IF EXISTS bank");
        pStatement.executeUpdate();
        pStatement = connection.prepareStatement("CREATE schema bank");
        pStatement.executeUpdate();

        AccountDAO accountDAO = new AccountDAOConcrete();
        accountDAO.resetTable();

        UserDAO userDAO = new UserDAOConcrete();
        userDAO.resetTable();

        TransactionDAO transactionDAO = new TransactionDAOConcrete();
        transactionDAO.resetTable();

        //set up and manipulate sample accounts
        User u;
        Account a;
        Transaction t;
        System.out.println();
        System.out.println("Setting up sample accounts:");

        // Account #0 /////////////////////////
        u = User.parseJson("{\"id\":0,\"userName\":\"Josh\",\"role\":\"CUSTOMER\",\"accounts\":null}");
        System.out.println("User created from JSON string; id#" + u.getId());
        userDAO.addUser(u);
        a = Account.parseJson("{\"id\":0}");
        System.out.println("Account created from JSON string; id#" + a.getId());
        a.setUser(u);
        t = new Transaction(0, a, "INITIAL DEPOSIT", BigDecimal.valueOf(1000000), LocalDateTime.now());
        transactionDAO.addTransaction(t);
        accountDAO.addAccount(a);

        // Account #1 /////////////////////////
        u = User.parseJson("{\"id\":1,\"userName\":\"Dianne\",\"role\":\"CUSTOMER\",\"accounts\":null}");
        System.out.println("User created from JSON string; id#" + u.getId());
        userDAO.addUser(u);
        a = Account.parseJson("{\"id\":1}");
        System.out.println("Account created from JSON string; id#" + a.getId());
        a.setUser(u);
        t = new Transaction(1, a, "INITIAL DEPOSIT", BigDecimal.valueOf(100), LocalDateTime.now());
        transactionDAO.addTransaction(t);
        accountDAO.addAccount(a);

        // Account #2 /////////////////////////
        u = User.parseJson("{\"id\":2,\"userName\":\"Waffle\",\"role\":\"CUSTOMER\",\"accounts\":null}");
        System.out.println("User created from JSON string; id#" + u.getId());
        userDAO.addUser(u);
        a = Account.parseJson("{\"id\":2}");
        System.out.println("Account created from JSON string; id#" + a.getId());
        a.setUser(u);
        t = new Transaction(2, a, "INITIAL DEPOSIT", BigDecimal.valueOf(4), LocalDateTime.now());
        transactionDAO.addTransaction(t);
        accountDAO.addAccount(a);

        // Account #3 /////////////////////////
        u = User.parseJson("{\"id\":3,\"userName\":\"Bilbo\",\"role\":\"CUSTOMER\",\"accounts\":null}");
        System.out.println("User created from JSON string; id#" + u.getId());
        userDAO.addUser(u);
        a = Account.parseJson("{\"id\":3}");
        System.out.println("Account created from JSON string; id#" + a.getId());
        a.setUser(u);
        t = new Transaction(3, a, "INITIAL DEPOSIT", BigDecimal.valueOf(50), LocalDateTime.now());
        transactionDAO.addTransaction(t);
        accountDAO.addAccount(a);

        //display result in queue
        PriorityQueue<Account> accounts = accountDAO.getAllAccounts();
        System.out.println();
        System.out.println("Accounts according to order of priority:");

        while (!accounts.isEmpty()) {
            Account account = accounts.poll();
            System.out.println("\t" + account.display().replace("\n", "\n\t"));
            System.out.println();
            //can also print in JSON
            //System.out.println("JSON:"+account.toJson());
        }
    }
}