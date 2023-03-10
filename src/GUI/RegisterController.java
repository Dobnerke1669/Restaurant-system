package GUI;

import DataAccess.Serializator;
import Model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class RegisterController {
    private RegisterGUI view;
    private Serializator serializator;

    public RegisterController(RegisterGUI view) {
        this.view = view;
        serializator=new Serializator();
        view.addRegisterListener(new RegisterListener());
    }
    public class RegisterListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginGUI loginGUI=new LoginGUI();
            LoginController controller=new LoginController(loginGUI);
            JFrame jFrame = new JFrame();
            Date date=new Date();
            ArrayList<Client> clientArrayList;
            clientArrayList= (ArrayList<Client>) serializator.deserialize("clients.ser");
            Client client=new Client((int) date.getTime(),view.getUser(),view.getPass());
            clientArrayList.add(client);
            serializator.serialize(clientArrayList,"clients.ser");
            JOptionPane.showMessageDialog(jFrame, "Account has been successfully registered!");
            loginGUI.setVisible(true);
            view.setVisible(false);
        }
    }
}
