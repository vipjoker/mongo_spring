package sample.view;

import com.mongodb.util.JSON;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.bson.Document;
import org.controlsfx.control.Notifications;
import sample.KpiProperties;
import sample.db.Database;
import sample.model.Credentials;
import sample.model.Person;

import javax.management.Notification;
import java.util.List;

/**
 * Created by oleh on 02.06.16.
 */
public class MainView {

    private List<Node> mViews;

    public MainView(List<Node> nodes) {
        mViews = nodes;

        initTabPane(nodes);

        initTableView(nodes);

    }

    private void initTabPane(List<Node> nodes) {
        TabPane pane = new TabPane();

        Tab addTab = new Tab("add tab");
        addTab.setClosable(false);
        AnchorPane group = new AnchorPane();
        group.setPrefWidth(500);
        group.setPrefHeight(400);


        addTab.setContent(group);

        Tab getTab = new Tab("Get tab");
        getTab.setClosable(false);
        AnchorPane group1 = new AnchorPane();
        group1.prefWidth(400);
        group1.prefHeight(400);
        getTab.setContent(group1);

        initGetFields(group1.getChildren());




        pane.getTabs().addAll(addTab,getTab);

        initButtons(group.getChildren());
        initChooser(group.getChildren());
        initCollectionChooser(group.getChildren());

        nodes.add(pane);
    }



    private void initGetFields(List<Node> nodes){
        Button getBtn = new Button("Get");
        getBtn.setLayoutY(200);
        getBtn.setLayoutX(100);
        getBtn.setOnAction(this::onGetPressed);
        nodes.add(getBtn);

    }






    private void initButtons(List<Node> nodes) {
        int x = 100;
        int y = 120;

        TextField field = new TextField();
        field.setId("tfName");

        field.setLayoutX(x);
        field.setLayoutY(y);

        TextField field1 = new TextField();
        field1.setId("tfSurname");
        field1.setLayoutX(x);
        field1.setLayoutY(y + 50);


        Button button = new Button("Save");
        button.setId("btnSave");
        button.setLayoutX(x);
        button.setLayoutY(y + 100);
        button.setOnAction(this::onSavePressed);

        Button button1 = new Button("Delete");
        button1.setId("btnDelete");
        button1.setLayoutX(x + 100);
        button1.setLayoutY(y + 100);
        button1.setOnAction(this::onDeletePressed);

        nodes.add(button);
        nodes.add(button1);
        nodes.add(field);
        nodes.add(field1);
    }

    private void initTableView(List<Node> nodes) {
        TableView<Person> table = new TableView<>();
        table.setId("table");
        table.setLayoutX(500);
        table.setPrefWidth(500);
        table.setPrefHeight(600);


        ObservableList<Person> persons = FXCollections.observableArrayList();

        Person person1 = new Person();
        person1.setName("Oleh");
        person1.setSurname("Makhobei");

        Person person2 = new Person();
        person2.setName("Ivan");
        person2.setSurname("Petrenko");

        Person person3 = new Person();
        person3.setName("Ihor");
        person3.setSurname("Hun");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);


//
//        firstNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
//                // p.getValue() returns the Person instance for a particular TableView row
//                return p.getValue().firstNameProperty();
//            }
//        });


        table.setItems(persons);

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setPrefWidth(250);
        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        lastNameCol.setPrefWidth(250);
        table.getColumns().setAll(firstNameCol, lastNameCol);


        nodes.add(table);

    }

    private void initChooser(List<Node> nodes) {

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setLayoutY(50);
        choiceBox.setLayoutX(100);
        choiceBox.setPrefWidth(150);


        choiceBox.setTooltip(new Tooltip("Select database"));

        String db = KpiProperties.getDb();

        choiceBox.setValue(db);
        choiceBox
                .getSelectionModel()
                .selectedIndexProperty()
                .addListener((observable, num, num2) ->
                {
                    String s = choiceBox.getItems().get((Integer) observable.getValue());
                  KpiProperties.saveDb(s);
                });
        List<String> databases = Database.getInstance().getDatabases();
        choiceBox.getItems().addAll(databases);
        nodes.add(choiceBox);
    }

    private void initCollectionChooser(List<Node> nodes){
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setLayoutY(80);
        choiceBox.setLayoutX(100);
        choiceBox.setPrefWidth(130);


        choiceBox.setTooltip(new Tooltip("Select collection"));

        String db = KpiProperties.getCollection();

        choiceBox.setValue(db);

        List<String> databases = Database.getInstance().getCollections();
        choiceBox.getItems().addAll(databases);
        nodes.add(choiceBox);

        choiceBox
                .getSelectionModel()
                .selectedIndexProperty()
                .addListener((observable, num, num2) ->
                {
                    String s = choiceBox.getItems().get((Integer) observable.getValue());
                    KpiProperties.saveDb(s);
                });
    }

    private void onDeletePressed(ActionEvent event) {
//        List<Credentials> credentialses = mTemplate.find(new Query(Criteria.where("").is("")), Credentials.class);
//        ListView<Credentials> list = findById("table", ListView.class);
//        Credentials credentials = list.getSelectionModel().getSelectedItem();
//        deleteItem(credentials);
//
//
        Notifications.create().title("hello").show();


    }

    private void onSavePressed(ActionEvent event) {
        System.out.println("saved");

        Credentials credentials = new Credentials(getTextById("tfName"), getTextById("tfSurname"));

        System.out.println("credentials " + credentials);

//        mTemplate.save(credentials);
      //  updateList();

    }


    private void onGetPressed(ActionEvent event){
        for(Document item :Database.getInstance().getAll()){
            String s = item.toJson();
            Object parse = JSON.parse(s);
            System.out.println(parse);
        }
    }

    private void updateList() {

//        List<Credentials> credentialses = mTemplate.findAll(Credentials.class);


        TableView<Person> table = findById(mViews,"table", TableView.class);

    }

    private void deleteItem(Credentials credentials) {
        //  mTemplate.remove(credentials);
        updateList();
    }

    private String getTextById(String id) {
        TextField textField = findById(mViews,id, TextField.class);
        if (textField != null) return textField.getText();
        else return "UNDEFINED";
    }

    public <T extends Node> T findById(List<Node> views, String id, Class<T> type) {
        for (Node node : views) {
            if(node instanceof Parent)findById(((Parent)node).getChildrenUnmodifiable(),id,type);
            if (id.equals(node.getId())) return (T) node;
        }
        return null;

    }
}