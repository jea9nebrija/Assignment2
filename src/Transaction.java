/*************************************************
 File: Transaction.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: [short description on what program does, what user
 enters, what result(s) are displayed]
 *************************************************/



import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final int id;
    private final Account account;
    private final String type;
    private final BigDecimal amount;
    private final LocalDateTime transactionDate;

    public Transaction(int id, Account account, String type, BigDecimal amount, LocalDateTime transactionDate){
        this.id=id;
        this.account=account;
        this.type=type;
        this.amount=amount;
        this.transactionDate=transactionDate;
        this.execute();
    }

    public int getId() {return id;}


    public Account getAccount() {return account;}

    public BigDecimal getAmount() {return amount;}

    public LocalDateTime getTransactionDate() {return transactionDate;}

    public String display(){
        return "Transaction: " + getType() + " to Account#"+ getAccount().getId() +" (" + getAccount().getUser().getUserName() + "); " + getAmount();
    }

    private void execute(){
        getAccount().setBalance(getAccount().getBalance().add(getAmount()));
        System.out.println(display());
    }

    public String getType() {
        return type;
    }
}
