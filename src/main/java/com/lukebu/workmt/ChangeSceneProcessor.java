package com.lukebu.workmt;

import com.lukebu.workmt.events.EventProcessor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class ChangeSceneProcessor {

    private static final String ICON_IMAGE_LOC = "./common/images/wmt.png";

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
    }

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
                stage = new Stage(StageStyle.DECORATED);
            }

            stage.setTitle(title);
            setStageIcon(stage);
            stage.setScene(new Scene(parent));
            //EventProcessor.getInstance().handleEvent(stage);

            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return controller;
    }

    public static void loadScene(String fxmlFile, Scene scene) {
        try {
            URL location = ChangeSceneProcessor.class.getResource("/scenes/" + fxmlFile);
            FXMLLoader loader = new FXMLLoader(location);
            Parent parent = loader.load();
            Stage stage = null;
            stage.setScene(new Scene(parent));
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
