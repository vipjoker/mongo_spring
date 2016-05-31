package sample.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by oleh on 31.05.16.
 */

@Document(collection = "newCollection")
public class Credentials {
    @Id
    private String id;

    private String firstName;
    private String lastName;

    public Credentials(){}

    public Credentials(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return String.format("FirstName= %s , LastName= %s", firstName,lastName);
    }
}
