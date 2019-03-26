package com.lukebu.workmt;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class ChangeSceneProcessor {

    public static Object changeScene(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.UTILITY);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return controller;
    }

    public static <T> T loadScene(String fxmlFile) {
        try {
            URL location = ChangeSceneProcessor.class.getResource("/scenes/" + fxmlFile);
            FXMLLoader loader = new FXMLLoader(location);
            loader.load();
            return loader.getController();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
