package com.lukebu.workmt.login;

import com.lukebu.workmt.Main;
import com.lukebu.workmt.conector.Connector;
import com.lukebu.workmt.context.ClientContext;
import com.lukebu.workmt.footer.FooterController;
import com.lukebu.workmt.query.task.LoginQuery;
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
    @FXML
    private FooterController footerController;

    private Connector connector = new Connector();
    private LoginQuery loginQuery = new LoginQuery();

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
                //System.out.print("USR_LOGIN = " + username);
                String password = rs.getString("USR_PASSWORD");
                //System.out.print("USR_PASSWORD = " + password);
                int userId = rs.getInt("USR_ID");
                ClientContext.getInstance().prepareClientContext(userId);
                letIn = true;
            } else {
                connector.closeConnectionWithCommit();
            }
        }
        connector.closeConnectionWithCommit();
        return letIn;
    }

    @FXML
    public void handleSubmitButtonAction(ActionEvent e) throws Exception{
        if (isLoginCorrect()){
            Main.getInstance().showDashboardScene();
        } else {
            loginTextField.clear();
            passwordPF.clear();
            errorMsgLabel.setText("Username or Password is not Correct");
        }
    }
}
