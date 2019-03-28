package com.lukebu.workmt.login;

import com.lukebu.workmt.conector.Connector;
import com.lukebu.workmt.context.ClientContext;
import com.lukebu.workmt.query.task.LoginQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    boolean letIn = false;

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private Label errorMsgLabel;

    private Connector connector = new Connector();
    private LoginQuery loginQuery = new LoginQuery();

    private static final int SCENE_WIDTH_SMALL = 1280;
    private static final int SCENE_HIGH_SMALL = 720;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(":)");
    }

    private boolean isLoginCorrect() throws SQLException {
        boolean letIn = false;
        ResultSet rs = null;

        loginQuery.setUserLogin(loginTextField.getText());
        loginQuery.setUserPassword(passwordPF.getText());

        String statementQuery =  loginQuery.prepareQuery();

        connector.createConnectionToDb();
        rs = connector.insertQueryStatement(statementQuery);

        while (rs.next()) {
            if(rs.getString("USR_LOGIN") != null && rs.getString("USR_PASSWORD")!= null && rs.getInt("USR_ID") != 0) {
                String username = rs.getString("USR_LOGIN");
                String password = rs.getString("USR_PASSWORD");
                int userId = rs.getInt("USR_ID");
                ClientContext.getInstance().prepareClientContext(userId);
                letIn = true;
            } else {
                connector.closeConnectionWithCommit();
                letIn = false;
            }
        }
        connector.closeConnectionWithCommit();
        return letIn;
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void handleSubmitButtonAction(ActionEvent e) throws Exception{
        if (isLoginCorrect()){
            closeStage();
            loadMain();
        } else {
            loginTextField.clear();
            passwordPF.clear();
            errorMsgLabel.setText("Username or Password is not correct");
        }
    }

    private void closeStage() {
        ((Stage) loginTextField.getScene().getWindow()).close();
    }

    private void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/scenes/dashboard/dashboard.fxml"));
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setTitle("Work MT");
            stage.setMinHeight(SCENE_HIGH_SMALL);
            stage.setMinWidth(SCENE_WIDTH_SMALL);
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
