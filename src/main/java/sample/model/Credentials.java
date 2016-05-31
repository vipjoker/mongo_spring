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

    @Override
    public String toString(){
        return String.format(
                "Credentials [id = %s \n firstName= %s \n , lastName= %s",
                id,firstName,lastName);
    }
}
