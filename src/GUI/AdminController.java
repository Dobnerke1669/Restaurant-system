package GUI;

import BusinessLogicLayer.BaseProduct;
import BusinessLogicLayer.CompositeProduct;
import BusinessLogicLayer.DeliveryService;
import BusinessLogicLayer.MenuItem;
import DataAccess.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class AdminController {
    private AdminGUI view;
    private DeliveryService deliveryService;
    private Serializator serializator=new Serializator();
    DefaultTableModel model;
    public AdminController(AdminGUI view)
    {
        this.view=view;
        view.addImportListener(new ImportListener());
        view.addDeleteListener(new DeleteListener());
        view.addInsertListener(new InsertListener());
        view.addUpdateListener(new UpdateListener());
        view.addComposeListener(new ComposeListener());
        view.addReportListener(new ReportListener());
        deliveryService=new DeliveryService();
        model=view.getModel();

    }
    public class InsertListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            BaseProduct product=new BaseProduct(view.getTitleInput(),view.getRatingInput(),view.getCaloriesInput(), view.getProteinInput(), view.getFatInput(), view.getSodiumInput(), view.getPriceInput());
            deliveryService.insertProduct(view,product,model);
        }
    }
    public class DeleteListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.deleteProducts(view,model);
        }
    }
    public class UpdateListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            BaseProduct product=new BaseProduct(view.getTitleInput(),view.getRatingInput(),view.getCaloriesInput(), view.getProteinInput(), view.getFatInput(), view.getSodiumInput(), view.getPriceInput());
            deliveryService.updateProduct(view,model,product);
        }
    }
    public class ImportListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
           deliveryService.importToGUI(view,model);
        }
    }
    public class ComposeListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.composeProduct(view,model);
        }
    }
    public class ReportListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame jframe=new JFrame();
            jframe.setSize(400,300);
            jframe.setLayout(null);
            jframe.setLocationRelativeTo(null);
            JRadioButton rb1,rb2;
            JTextField tf1,tf2;
            JButton button=new JButton("Generate");
            rb1=new JRadioButton("Time interval");
            rb1.setBounds(100,50,100,30);
            rb2=new JRadioButton("Orders");
            rb2.setBounds(100,100,100,30);
            button.setBounds(100,200,100,30);
            tf1=new JTextField(3);
            tf2=new JTextField(3);
            tf1.setBounds(100,150,50,25);
            tf2.setBounds(150,150,50,25);
            ButtonGroup bg=new ButtonGroup();
            bg.add(rb1);bg.add(rb2);
            jframe.getContentPane().add(rb1);
            jframe.getContentPane().add(rb2);
            jframe.getContentPane().add(tf1);
            jframe.getContentPane().add(tf2);
            jframe.getContentPane().add(button);
            jframe.setVisible(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(rb1.isSelected())
                    {
                        String string="time";
                        deliveryService.generateReport(string,Integer.parseInt(tf1.getText().toString()),Integer.parseInt(tf2.getText().toString()));
                    }
                    else if (rb2.isSelected())
                    {
                        String string="order";
                        deliveryService.generateReport(string,Integer.parseInt(tf1.getText().toString()),0);

                    }

                }
            });




        }
    }
}
