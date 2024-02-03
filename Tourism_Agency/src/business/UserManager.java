package business;

import core.Helper;
import dao.UserDao;
import entity.Rezervation;
import entity.User;

import java.util.ArrayList;

public class UserManager {

    private final UserDao userDao;



    public UserManager() {
        this.userDao = new UserDao();
    }

    public ArrayList<User> findAll(){
        return this.userDao.findAll();
    }

    public User findByLogin(String username,String password){
        return this.userDao.findByLogin(username , password);
    }
    public User getById(int id) {
        return this.userDao.getById(id);
    }

    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for(User user : this.findAll()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getName();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getRole();
            userRowList.add(rowObject);
        }
        return userRowList;
    }

    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMsg(user.getId() + "ID Kayıtlı Kullanıcı Bulunamadı.");
            return false;
        }
        return this.userDao.update(user);
    }
    public ArrayList<Object[]> getForTableFilter(String selectedRole , int size){
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for(User user : this.userDao.filter(selectedRole)){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getName();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getRole();
            userRowList.add(rowObject);
        }
        return userRowList;
    }

    public boolean save(User user) {
        if (this.getById(user.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(user);
    }
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID Kayıtlı Kullanıcı Bulunamadı.");
            return false;
        }
        return this.userDao.delete(id);
    }


}
