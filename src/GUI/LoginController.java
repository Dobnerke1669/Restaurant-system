package GUI;

import DataAccess.Serializator;
import Model.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginController {
    private LoginGUI view;
    private Serializator serializator;
    public LoginController(LoginGUI view) {
        this.view = view;
        serializator=new Serializator();
        view.addLoginListener(new LoginListener());
        view.addRegisterListener(new RegisterListener());
    }

    public String roleLogin()
    {
        String user=view.getUser();
        String pass=view.getPass();
        if (user.equals("root")&&pass.equals("root"))
            return "admin";
        if (user.equals("1234")&&pass.equals("1234"))
            return "employee";
        ArrayList<Client> clients;
        clients= (ArrayList<Client>) serializator.deserialize("clients.ser");
        for (Client client:clients)
        {
            if (client.getUsername().equals(user)&&client.getPassword().equals(pass))
                return "client";
        }
        return "not valid";
    }
    public class LoginListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (roleLogin().equals("admin"))
            {
                AdminGUI adminGUI=new AdminGUI();
                AdminController controller=new AdminController(adminGUI);
                adminGUI.setVisible(true);
            }
            else if (roleLogin().equals("employee"))
            {
                EmployeeGUI employeeGUI=new EmployeeGUI();
                employeeGUI.setVisible(true);
            }
            else if (roleLogin().equals("client"))
            {
                ClientGUI clientGUI=new ClientGUI();
                ClientController controller=new ClientController(clientGUI,view.getUser());
                clientGUI.setVisible(true);
            }
        }
    }
    public class RegisterListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            RegisterGUI registerGUI=new RegisterGUI();
            RegisterController controller=new RegisterController(registerGUI);
            registerGUI.setVisible(true);
            view.setVisible(false);
        }
    }
}
