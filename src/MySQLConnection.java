/*************************************************
 File: MySQLConnection.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: [short description on what program does, what user
 enters, what result(s) are displayed]
 *************************************************/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author karunmehta
 */
public class MySQLConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "m@nd@l@p@rk";

    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    // SQL queries to be used to persist business objects as needed by the DAO
    private static final String USER_DROP_SQL = "DROP TABLE IF EXISTS bank.users;";
    private static final String USER_CREATE_SQL = "CREATE TABLE bank.users (id int, userName VARCHAR(128), password VARCHAR(128), role VARCHAR(128));";
    private static final String USER_INSERT_SQL = "INSERT INTO bank.users (id, userName,password,role) VALUES (?, ?, ?, ?)";

    private static final String ACCOUNT_DROP_SQL = "DROP TABLE IF EXISTS bank.accounts;";
    private static final String ACCOUNT_CREATE_SQL = "CREATE TABLE bank.accounts (id int, userId int, balance DECIMAL(10,2));";
    private static final String ACCOUNT_INSERT_SQL = "INSERT INTO bank.accounts (id, userId, balance) VALUES (?, ?, ?)";

    private static final String TRANSACTION_DROP_SQL = "DROP TABLE IF EXISTS bank.transactions;";
    private static final String TRANSACTION_CREATE_SQL = "CREATE TABLE bank.transactions (id int, amount DECIMAL(10,2), transactionDate DATETIME);";
    private static final String TRANSACTION_INSERT_SQL = "INSERT INTO bank.transactions (id, amount, transactionDate) VALUES (?, ?, ?)";

    public MySQLConnection()  { }

    public static Connection getDBConnection() throws SQLException {

        // JDBC variables for opening and managing connection
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        return connection;
    }

    public static String getUserDrop() {return USER_DROP_SQL;}
    public static String getUserCreate() {return USER_CREATE_SQL;}
    public static String getUserInsert() {
        return USER_INSERT_SQL;
    }


    public static String getAccountDrop() {return ACCOUNT_DROP_SQL;}
    public static String getAccountCreate() {return ACCOUNT_CREATE_SQL;}
    public static String getAccountInsert() {return ACCOUNT_INSERT_SQL;}


    public static String getTransactionDrop() {return TRANSACTION_DROP_SQL;}
    public static String getTransactionCreate() {return TRANSACTION_CREATE_SQL;}
    public static String getTransactionInsert() {return TRANSACTION_INSERT_SQL;}
}
