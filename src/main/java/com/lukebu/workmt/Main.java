package com.lukebu.workmt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Main instance;

    public static final int SCENE_WIDTH_SMALL = 800;
    public static final int SCENE_HIGH_SMALL = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/login/login.fxml"));
        primaryStage.setTitle("Work MT");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH_SMALL, SCENE_HIGH_SMALL));
        primaryStage.show();
    }


    public void showDashboardScene() throws IOException {
        FXMLLoader loader  = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/scenes/workmt.fxml"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
