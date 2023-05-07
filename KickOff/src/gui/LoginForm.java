/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author omare
 */
public class LoginForm extends Form {
    private final String MYLOGIN = "Admin";
    private final String MYPSD = "Admin";
    private final TextField tf_login = new TextField("", "Login");
    private final TextField tf_password = new TextField("", "Password");
    private final Button loginBtn = new Button("Valider");

    public LoginForm() {
        super("Sign in", BoxLayout.yCenter());
        tf_password.setConstraint(TextField.PASSWORD);
        addAll(tf_login, tf_password, loginBtn);
        loginBtn.addActionListener((evt) -> {
            if (tf_login.getText().equals(MYLOGIN) && tf_password.getText().equals(MYPSD)) {
                new HomeForm().show();
            } else {
                Dialog.show("Warning", "Invalid login or password", "Ok", null);
            }
        });
    }

}

