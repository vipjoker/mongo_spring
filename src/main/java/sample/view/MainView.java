package sample.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.util.JSON;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.bson.Document;
import org.controlsfx.control.Notifications;
import sample.KpiProperties;
import sample.db.Database;
import sample.model.BaseMongoModel;
import sample.model.Credentials;
import sample.model.Person;

import javax.management.Notification;
import java.util.List;

/**
 * Created by oleh on 02.06.16.
 */
public class MainView {

    private List<Node> mViews;
    private TextField tfName;
    private TextField tfLastName;
    private TableView<BaseMongoModel> mTable;

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

        tfName= new TextField();
        tfName.setId("tfName");

        tfName.setLayoutX(x);
        tfName.setLayoutY(y);

        tfLastName = new TextField();
        tfLastName.setId("tfSurname");
        tfLastName.setLayoutX(x);
        tfLastName.setLayoutY(y + 50);


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
        nodes.add(tfName);
        nodes.add(tfLastName);
    }

    private void initTableView(List<Node> nodes) {
        mTable= new TableView<>();
        mTable.setId("table");
        mTable.setLayoutX(500);
        mTable.setPrefWidth(500);
        mTable.setPrefHeight(600);


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



        TableColumn<BaseMongoModel, String> firstNameCol = new TableColumn<>("__id__");
        firstNameCol.setPrefWidth(250);
        firstNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BaseMongoModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BaseMongoModel, String> param) {
                return param.getValue().mongoIdProperty();
            }
        });



        TableColumn<BaseMongoModel, String> lastNameCol = new TableColumn<>("Name");
        lastNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BaseMongoModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BaseMongoModel, String> param) {
                return param.getValue().getMongoName();
            }
        });
        lastNameCol.setPrefWidth(250);


        mTable.getColumns().setAll(firstNameCol,lastNameCol);


        nodes.add(mTable);

    }


    private void updateTable(List<BaseMongoModel> list ){

        mTable.setItems(FXCollections.observableArrayList(list));
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
        BaseMongoModel selectedItem = mTable.getSelectionModel().getSelectedItem();
        deleteItem(selectedItem);


//        Notifications.create().title("hello").show();


    }

    private void onSavePressed(ActionEvent event) {
        System.out.println("saved");

        Credentials credentials = new Credentials(tfName.getText(), tfLastName.getText());

        String format = String.format("{'name':'%s', 'age':'%s'}" ,tfName.getText(),tfLastName.getText());

        System.out.println("credentials " + credentials);

        Document document = Document.parse(format);

        Database.getInstance().save(document , KpiProperties.getCollection());


    }


    private void onGetPressed(ActionEvent event){


        String allAsJson = Database.getInstance().getAllAsJson(KpiProperties.getCollection());

//        System.out.println(allAsJson);
//
        List<BaseMongoModel> credentialses = new Gson().fromJson(allAsJson,new TypeToken<List<BaseMongoModel>>(){}.getType());
        updateTable(credentialses);
        System.out.println(credentialses);
    }



    private void deleteItem(BaseMongoModel model) {
        //  mTemplate.remove(credentials);

        Object id = model.get_id().get("$oid");

        String s =String.format("{\'_id\':{'$oid' : \'%s\' }}" ,id);
        Document document = Document.parse(s);
        Database.getInstance().deleteObject(KpiProperties.getCollection(),document);
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