package by.bsu.plesko.app;


import by.bsu.plesko.util.FileMetadata;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static Stage primarySt = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("by/bsu/plesko/forms/sample.fxml"));
        primaryStage.setTitle("Metadata");
        primaryStage.setScene(new Scene(root, 1024, 720));
//        Example.prob(new File("D:\\samp\\3888х2592х72.jpg"));
        primaryStage.setResizable(false);
        primarySt = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

