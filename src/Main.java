import BusinessLogicLayer.DeliveryService;
import DataAccess.Serializator;
import GUI.LoginController;
import GUI.LoginGUI;
import Model.Employee;

public class Main {

    public static void main(String[] args) {

        LoginGUI view=new LoginGUI();
        DeliveryService deliveryService=new DeliveryService();
        LoginController loginController=new LoginController(view);
        Serializator serializator=new Serializator();
        view.setVisible(true);
    }
}
