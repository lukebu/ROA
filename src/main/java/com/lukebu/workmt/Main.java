package com.lukebu.workmt;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private static Main instance;

    private Stage primaryStage;
    @FXML
    public BorderPane mainBorderPane;

    private static final int SCENE_WIDTH_SMALL = 800;
    private static final int SCENE_HIGH_SMALL = 600;

    public static Main getInstance(){
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        instance = this;
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Work MT");
        showLoginScene();
    }

    private void showLoginScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/login/login.fxml"));
        mainBorderPane = loader.load();
        Scene scene = new Scene(mainBorderPane, SCENE_WIDTH_SMALL, SCENE_HIGH_SMALL);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }

    public void showDashboardScene() throws IOException {
        FXMLLoader dashboardLoader = new FXMLLoader();
        dashboardLoader.setLocation(getClass().getResource("/scenes/dashboard/dashboard.fxml"));
        mainBorderPane = dashboardLoader.load();
        primaryStage.getScene().setRoot(mainBorderPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
