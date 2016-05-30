package sample.model;

import com.sun.javafx.beans.IDProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by oleh on 30.05.16.
 */

@Document(collection = Contact.COLLECTION_NAME)
public class Contact implements Serializable {
    public static final String COLLECTION_NAME = "contacts";

    @Id
    private Long id;

    private String name;
    private String number;
    private String email;
    public Contact(){

    }

    public Contact(String name, String number, String email){
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
