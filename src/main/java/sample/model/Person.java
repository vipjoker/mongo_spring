package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by oleh on 02.06.16.
 */
public class Person {

        private StringProperty firstName;
        private StringProperty lastName;

    public Person() {

    }

    public String getFirstName(){
        return firstName.get();
    }

    public String getLastName(){
        return lastName.get();
    }

    public StringProperty getNameProperty() {
        return firstName;
    }


    public void setName(String name) {
        if(firstName == null) firstName = new SimpleStringProperty(this,"firstName");
        this.firstName.setValue(name);
    }

    public StringProperty getSurnameProperty() {
        return lastName;
    }

    public void setSurname(String surname) {
        if(lastName == null) lastName = new SimpleStringProperty(this,"lastName");
        this.lastName.setValue(surname);
    }
}

