package view;

import business.UserManager;
import core.Helper;
import dao.UserDao;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JTabbedPane tab_menu;
    private JScrollPane scrl_user;
    private JTable tbl_user;
    private JComboBox cmb_user_filter;
    private JButton btn_user_search;
    private JButton btn_logout;
    private JButton btn_user_add;
    private User user;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private UserManager userManager;
    private JPopupMenu user_menu;
    private UserDao userDao;


    public AdminView(User user) {
        this.userDao = new UserDao();
        this.userManager = new UserManager();
        add(container);
        guiInitilaze(600, 450);
        this.setTitle("Tourism Agency - Admin Screen");
        this.user = user;
        if (this.user == null) {
            dispose();
        }

        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getName());
        loadComponent();

        loadUserTable();
        loadUserComponent();
        loadFilterUser();


    }

    public void loadFilterUser(){
       this.cmb_user_filter.addItem("");
       this.cmb_user_filter.addItem("admin");
       this.cmb_user_filter.addItem("employee");

        btn_user_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) cmb_user_filter.getSelectedItem();
                if (selectedRole.equals("") ){
                    loadUserTable();
                }
                else{
                    loadUserTableFilter(selectedRole);
                }
            }
        });

    }

    public void loadComponent() {
        this.btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView();
            }
        });
    }

    public void loadUserComponent() {
        tableRowSelect(this.tbl_user);
        this.user_menu = new JPopupMenu();
        this.user_menu.add("Güncelle").addActionListener(e -> {
            int selectModelId = this.getTableSelectedRow(tbl_user, 0);
            UserView userView = new UserView(this.userManager.getById(selectModelId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        btn_user_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserView userView = new UserView(new User());
                userView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadUserTable();
                    }
                });
            }
        });
        this.user_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectModelId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selectModelId)) {
                    Helper.showMsg("done");
                    loadUserTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_user.setComponentPopupMenu(user_menu);

    }

    public void loadUserTable() {
        Object[] col_user = {"Kullanıcı ID", "İsim Soyisim", "Kullanıcı Adı", "Şifre", "Yetkisi"};
        ArrayList<Object[]> userList = userManager.getForTable(col_user.length);
        this.createTable(this.tmdl_user, this.tbl_user, col_user, userList);
    }

    public void loadUserTableFilter(String selectedRole) {
        Object[] col_user = {"Kullanıcı ID", "İsim Soyisim", "Kullanıcı Adı", "Şifre", "Yetkisi"};
        ArrayList<Object[]> userList = userManager.getForTableFilter(selectedRole,col_user.length);
        this.createTable(this.tmdl_user, this.tbl_user, col_user, userList);
    }



}
