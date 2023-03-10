package GUI;

import BusinessLogicLayer.MenuItem;
import BusinessLogicLayer.Order;
import DataAccess.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class EmployeeGUI extends JFrame{
    JTable table;
    DefaultTableModel myModel;
    private Serializator serializator;

    public EmployeeGUI()
    {
        serializator=new Serializator();
        myModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scrollPane=new JScrollPane();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        scrollPane.setBounds(700,50,500,500);
        table=createTable(myModel);
        table.setEnabled(true);
        table.setVisible(true);
        scrollPane.setViewportView(table);
        getContentPane().add(scrollPane);
    }
    public JTable createTable(DefaultTableModel myModel)
    {
        String[] columns=new String[3];
        columns[0]="order id";
        columns[1]="client id";
        columns[2]="total price";
        ArrayList<Order> orders= (ArrayList<Order>) serializator.deserialize("orders.ser");
        myModel.setColumnIdentifiers(columns);
        for (int i=0;i<orders.size();i++) {
            String[] row=new String[3];
            row[0]= String.valueOf(orders.get(i).getOrderID());
            row[1]= String.valueOf(orders.get(i).getClientID());
            row[2]= String.valueOf(orders.get(i).getTotalPrice());
            myModel.addRow(row);
        }
        JTable myTable = new JTable(myModel);
        return myTable;
    }
}
