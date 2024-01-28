import business.UserManager;
import core.Helper;
import entity.User;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
      // LoginView loginView = new LoginView();
       UserManager userManager = new UserManager();
       EmployeeView emp = new EmployeeView(userManager.findByLogin("onur","12345"));
    }
}