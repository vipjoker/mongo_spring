package sample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Created by oleh on 06.06.16.
 */
public class BaseMongoModel {

    @Expose(serialize = false, deserialize = false)
    private StringProperty mongoId;



    @Expose(serialize = false, deserialize = false)
    private StringProperty mongoName;
    private LinkedTreeMap _id;
    private String name;


    public StringProperty getMongoName(){
        if(mongoName == null) mongoName = new SimpleStringProperty(name);
        return mongoName;
    }


    public StringProperty mongoIdProperty() {
        if(mongoId == null) mongoId = new SimpleStringProperty(_id.toString());
        return mongoId;
    }

    public LinkedTreeMap get_id() {
        return _id;
    }

    public void set_id(LinkedTreeMap _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "BaseMongoModel{" +
                "_id=" + _id +
                '}';
    }
}


//
//    private String id;
//
//    private StringProperty firstName;
//    private StringProperty lastName;
//
//    public Credentials(){}
//
//    public Credentials(String firstName, String lastName){
//        this.firstName = new SimpleStringProperty(firstName);
//        this.lastName = new SimpleStringProperty(lastName);
//    }
//
//    public StringProperty firstNameProperty(){
//        return firstName;
//    }
//
//    public StringProperty lastNameProperty(){
//        return lastName;
//    }
//
//    public String getFirstName() {
//        return firstName.get();
//    }
//
//    public void setFirstName(String firstName) {
//
//        this.firstName.setValue(firstName);
//    }
//
//    public String getLastName() {
//        return lastName.get();
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName.setValue(lastName);
//    }
