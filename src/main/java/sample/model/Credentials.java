package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by oleh on 31.05.16.
 */

public class Credentials {

    private String id;

    private StringProperty firstName;
    private StringProperty lastName;

    public Credentials(){}

    public Credentials(String firstName, String lastName){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }

    public StringProperty firstNameProperty(){
        return firstName;
    }

    public StringProperty lastNameProperty(){
        return lastName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {

        this.firstName.setValue(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.setValue(lastName);
    }

    @Override
    public String toString(){
        return String.format("FirstName= %s , LastName= %s", firstName,lastName);
    }
}
