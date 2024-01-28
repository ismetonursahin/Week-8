package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;

import javax.swing.*;

public class UserView extends Layout {
    private JPanel container;
    private JTextField fld_name;
    private JTextField fld_username;
    private JTextField fld_password;
    private JButton btn_user_save;
    private JComboBox<ComboItem> cmb_role;
    private User user ;
    private UserManager userManager;


    public UserView(User user) {
        this.userManager = new UserManager();
        this.user = user;
        this.add(container);
        this.guiInitilaze(400, 500);
        for (User u : this.userManager.findAll()){
            this.cmb_role.addItem(new ComboItem(u.getId(), u.getRole()));
        }

        if (user.getId() != 0) {
            this.fld_name.setText(user.getName());
            this.fld_username.setText(user.getUsername());
            this.fld_password.setText(user.getPassword());
            ComboItem defaultUser = new ComboItem(this.user.getId(),this.user.getRole());
            this.cmb_role.getModel().setSelectedItem(defaultUser);

        }
        btn_user_save.addActionListener(e -> {
            JTextField[] checkFieldList = {
                    this.fld_name,
                    this.fld_username,
                    this.fld_password,

            };
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");

            } else {

                ComboItem selectedItem = (ComboItem) cmb_role.getSelectedItem();
                this.user.setName(this.fld_name.getText());
                this.user.setUsername(this.fld_username.getText());
                this.user.setPassword(this.fld_password.getText());
                this.user.setRole(selectedItem.getValue());

                if (this.userManager.save(this.user)) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}
