package com.lukebu.workmt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private static final int SCENE_WIDTH_SMALL = 800;
    private static final int SCENE_HIGH_SMALL = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/login/login.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setMinHeight(SCENE_HIGH_SMALL);
        primaryStage.setMinWidth(SCENE_WIDTH_SMALL);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Work MT");
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        launch(args);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Long exitTime = System.currentTimeMillis();
                System.out.println(exitTime);}
        });
    }
}
