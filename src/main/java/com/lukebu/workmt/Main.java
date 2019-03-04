package com.lukebu.workmt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private static Main instance;

    private Stage primaryStage;
    private BorderPane borderPane;


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
        borderPane = loader.load();
        Scene scene = new Scene(borderPane, SCENE_WIDTH_SMALL, SCENE_HIGH_SMALL);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }

    public void showDashboardScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/dashboard/dashboard.fxml"));
        borderPane = loader.load();
        primaryStage.getScene().setRoot(borderPane);
        showMenu();
        showFooter();
        //primaryStage.setMaximized(true);
    }

    private void showMenu() throws IOException {
        FXMLLoader menuLoader = new FXMLLoader();
        menuLoader.setLocation(getClass().getResource("/scenes/menu/menu.fxml"));
        borderPane.setTop(menuLoader.load());
    }

    private void showFooter() throws IOException {
        FXMLLoader footerLoader = new FXMLLoader();
        footerLoader.setLocation(getClass().getResource("/scenes/footer/footer.fxml"));
        borderPane.setBottom(footerLoader.load());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
