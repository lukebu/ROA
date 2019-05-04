package com.lukebu.workmt.contact;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ModifyContactController {

    @FXML
    private JFXTextField contactNameTF;
    @FXML
    private JFXTextField contactSurnameTF;
    @FXML
    private JFXTextField mobileNumberTF;
    @FXML
    private JFXTextField desktopNumberTF;
    @FXML
    private JFXTextField contactEmailTF;
    @FXML
    private JFXComboBox contactTypeTF;

    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXButton modifyContactkButton;

    @FXML
    private void cancel() {
        Stage stage = (Stage) contactNameTF.getScene().getWindow();
        stage.close();
    }
}
