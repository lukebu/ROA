package com.lukebu.workmt.login;

import com.lukebu.workmt.Main;
import com.lukebu.workmt.conector.Connector;
import com.lukebu.workmt.query.LoginQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    boolean letIn = false;

    @FXML
    private Label loginLabel;
    @FXML
    private TextField loginTextField;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private Button logInButton;
    @FXML
    private Label errorMsgLabel;

    Connector conector = new Connector();
    LoginQuery loginQuery = new LoginQuery();

    public boolean isLoginCorrect() throws SQLException {
        boolean letIn = false;
        ResultSet rs = null;

        loginQuery.setUserLogin(loginTextField.getText());
        loginQuery.setUserPassword(passwordPF.getText());

        String statementQuery =  loginQuery.prepareQuery();

        conector.createConnectionToDb();
        rs = conector.insertQueryStatement(statementQuery);

        while (rs.next()) {
            if(rs.getString("USR_LOGIN") != null && rs.getString("USR_PASSWORD")!= null ) {
                String username = rs.getString("USR_LOGIN");
                System.out.print("USR_LOGIN = " + username);
                String password = rs.getString("USR_PASSWORD");
                System.out.print("USR_PASSWORD = " + password);
                letIn = true;
            } else {
                conector.closeConnectionWithCommit();
            }
        }
        conector.closeConnectionWithCommit();
        return letIn;

    }

    @FXML
    public void handleSubmitButtonAction(ActionEvent e) throws Exception{
        if (isLoginCorrect()){
         //   Main.instance.showDashboardScene();
            System.out.println("\n");
            System.out.println("DANE OK, TRZEBA DOROBIĆ KOLEJNE EKRANY :)");
        } else {
            loginTextField.clear();
            passwordPF.clear();
            errorMsgLabel.setText("Username or Password is not Correct");
        }
    }


}
