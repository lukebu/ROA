package com.lukebu.workmt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int SCENE_WIDTH_SMALL = 600;
    public static final int SCENE_HIGH_SMALL = 400;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/workmt.fxml"));
        primaryStage.setTitle("Work MT");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH_SMALL, SCENE_HIGH_SMALL));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
