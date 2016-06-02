package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.view.MainView;


public class Main extends Application {

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

        primaryStage.setScene(scene);
        new MainView(group.getChildren());
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
