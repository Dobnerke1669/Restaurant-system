package GUI;

import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.MenuItem;
import BusinessLogicLayer.Order;
import DataAccess.FileWriter1;
import DataAccess.Serializator;
import Model.Client;
import Model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class ClientController {
    private ClientGUI view;
    private DeliveryService deliveryService;
    private Serializator serializator=new Serializator();
    DefaultTableModel model;
    FileWriter1 fileWriter;
    String username;
    public ClientController(ClientGUI view,String username)
    {
        this.view=view;
        deliveryService=new DeliveryService();
        model=view.getModel();
        view.addSearchListener(new SearchListener());
        view.addOrderListener(new OrderListener());
        fileWriter=new FileWriter1();
        this.username=username;
    }
    public class SearchListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.searchCriteria(view,model);
        }
    }


    public class OrderListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.createNewOrder(view,username);
        }
    }

}
