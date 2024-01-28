package dao;

import core.Db;
import entity.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private final Connection con ;

    public UserDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<User> filter(String selectedRole){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.users WHERE user_role = '" + selectedRole + "'";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                userList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;

    }

    public ArrayList<User> findAll(){
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.users";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                userList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public User getById(int id) {
        User obj = null;
        String query = "SELECT * FROM public.users WHERE user_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }

    public boolean save(User user){
        String query = "INSERT INTO public.users " +
                "(" +
                "name, " +
                "username , " +
                "password, " +
                "user_role" +
                ")" +
                " VALUES(?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getName());
            pr.setString(2, user.getUsername());
            pr.setString(3, user.getPassword());
            pr.setString(4 ,user.getRole());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(User user){
        String query = "UPDATE public.users SET " +
                "user_id = ? , " +
                "name = ? , " +
                "username = ? , " +
                "password = ? , " +
                "user_role = ?  " +
                "WHERE user_id = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, user.getId());
            pr.setString(2, user.getName());
            pr.setString(3, user.getUsername());
            pr.setString(4, user.getPassword());
            pr.setString(5, user.getRole());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.users WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public User findByLogin(String username,String password){
        User obj = null;
        String query = "SELECT * FROM public.users WHERE username = ? AND password = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;

    }

    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setName(rs.getString("name"));
        obj.setUsername(rs.getString("username"));
        obj.setPassword(rs.getString("password"));
        obj.setRole(rs.getString("user_role"));
        return obj;
    }
}
