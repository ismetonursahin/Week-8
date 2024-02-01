package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout {
    private JPanel container;
    private JTextField fld_username;
    private JTextField fld_password;
    private JButton btn_user_login;
    private JPanel wrapper;
    private final UserManager userManager;


    public LoginView(){
        this.userManager = new UserManager();
        add(container);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Tourism Agency");
        this.setSize(400,450);
        this.setVisible(true);
        this.setLocation(Helper.getLocationPoint("x",this.getSize()),Helper.getLocationPoint("y",this.getSize()));
        btn_user_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username , this.fld_password};
            if((Helper.isFieldListEmpty(checkFieldList))){
                Helper.showMsg("fill");
            }else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(),this.fld_password.getText());
                if (loginUser == null){
                    Helper.showMsg("notFound");
                }
                else {
                    if(loginUser.getRole().equals("admin")){
                        AdminView adminView = new AdminView(loginUser);
                        dispose();
                    }else {
                        EmployeeView employeeView = new EmployeeView(loginUser);
                        dispose();
                    }
                }
            }
        });
    }
}
