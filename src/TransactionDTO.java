/*************************************************
 File: TransactionDTO.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: Implementation of Transaction of Data Transfer Object (DTO)
 *************************************************/



import java.sql.SQLException;

public class TransactionDTO {

    static TransactionDAOConcrete tdc = new TransactionDAOConcrete();

    public static int performInsert(Transaction transaction) {
        int updateResult = -1;

        try {
            updateResult = tdc.insertTransaction(transaction);
        } catch(SQLException se) {
            System.out.println(se.getMessage());
        }

        if(updateResult != -1) System.out.println("Transaction database updated");
        return updateResult;
    }
}
