package sample;

import com.mongodb.DB;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import sample.model.Credentials;

import java.util.List;


public class Main extends Application {


    private List<Node> mElements;
    private MongoTemplate mTemplate;
    public static void main(String[] args) {
        launch(args);
//        https://spring.io/guides/gs/accessing-data-mongodb/
//        https://habrahabr.ru/company/jugru/blog/301042/
//        http://www.springbyexample.org/examples/
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        primaryStage.setTitle("Mongo db");
        Scene scene = new Scene(group, 1000, 600);
        mElements = group.getChildren();
        primaryStage.setScene(scene);
        setListView(mElements);
        setFormField(mElements);
        initSpring();

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initSpring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        mTemplate = applicationContext.getBean("mongoTemplate", MongoTemplate.class);

    }

    private void setListView(List<Node> nodeList) {
        ListView<String> listView = new ListView<>();
        listView.setId("list");
        listView.setLayoutX(500);
        listView.setPrefWidth(500);


        listView.getItems().addAll(
                "One",
                "Two",
                "Three");

        nodeList.add(listView);
    }

    private void setFormField(List<Node> nodeList) {
        int x  = 100;
       int y = 100;

        TextField field = new TextField();
        field.setId("tfName");

        field.setLayoutX(x);
        field.setLayoutY(y);

        TextField field1 = new TextField();
        field1.setId("tfSurname");
        field1.setLayoutX(x);
        field1.setLayoutY(y+50);


        Button button = new Button("Save");
        button.setId("btnSave");
        button.setLayoutX(x);
        button.setLayoutY(y +100);
        button.setOnAction(this::onSavePressed);

        Button button1 = new Button("Get");
        button1.setId("btnGet");
        button1.setLayoutX(x + 100);
        button1.setLayoutY(y+ 100);
        button1.setOnAction(this::onGetPressed);

        nodeList.add(button);
        nodeList.add(button1);
        nodeList.add(field);
        nodeList.add(field1);
    }


    private void onGetPressed(ActionEvent event){
        List<Credentials> credentialses = mTemplate.find(new Query(Criteria.where("firstName").is("HELLO")), Credentials.class);

        System.out.println(credentialses);
    }

    private void onSavePressed(ActionEvent event) {
        System.out.println("saved");
        ListView list = findById("list", ListView.class);


        String text =getTextById("tfName") + getTextById("tfSurname");

        list.getItems().add(text);

    }

    private String getTextById(String id){
        TextField textField = findById(id, TextField.class);
        if(textField != null)return textField.getText();
        else return "UNDEFINED";
    }

    public <T extends Node> T findById(String id , Class<T> type) {
        for (Node node : mElements)
            if (id.equals(node.getId())) return (T) node;
        return null;

    }
}
