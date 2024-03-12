/*************************************************
 File: Account.java
 By: Jeanine Nebrija
 Date: 3/12/24
 Compile: Open directory as IntelliJ project, compile and run
 System: Windows w/ Java
 Description: Implementation of user's account
 *************************************************/



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account implements Comparable<Account>{
    private final int id;
    @JsonIgnore
    private User user;
    private BigDecimal balance;
    private final LocalDateTime dateCreated;
    ObjectMapper objectMapper =new ObjectMapper();

    public Account(int id){
        this.id=id;
        this.balance=new BigDecimal(0);
        this.dateCreated= LocalDateTime.now();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static Account parseJson(String s) {
        ObjectMapper om=new ObjectMapper();
        JsonNode jn = null;
        try {jn = om.readTree(s);}
        catch (JsonProcessingException e) {throw new RuntimeException(e);}
        return new Account(jn.get("id").asInt());
    }

    public int getId(){
        return this.id;
    }
    public User getUser(){
        return this.user;
    }
    public BigDecimal getBalance(){return this.balance;}
    public void setBalance(BigDecimal balance){this.balance=balance;}

    public LocalDateTime getDateCreated(){return this.dateCreated;}

    public String display(){
        return "id : " + this.getId()
        + "\nowner : " + this.getUser().getUserName()
        + "\ndate created : " + this.getDateCreated()
        + "\nbalance : " + this.getBalance();
    }

    public String toJson(){
        String string = "";
        try {string = objectMapper.writeValueAsString(this);}
        catch (JsonProcessingException jpe) {System.out.println(jpe.getMessage());}
        return string;
    }

    @Override
    public int compareTo(Account o) {
        //compare dateCreated first
        int comparison=-Integer.compare(o.getDateCreated().compareTo(this.getDateCreated()), 0);

        //if equal, compare account balance
        if(comparison==0) return Integer.compare(o.getBalance().compareTo(this.getBalance()), 0);
        else return comparison;
    }

    public void setUser(User user) {
        System.out.println(user.getUserName() + " set as owner of Account#" + this.getId());
        this.user = user;
    }
}
