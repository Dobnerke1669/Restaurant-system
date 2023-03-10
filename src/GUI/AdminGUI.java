package GUI;

import BusinessLogicLayer.MenuItem;
import DataAccess.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminGUI extends JFrame {
    private JLabel titleLabel;
    private JTextField titleInput;

    private JLabel ratingLabel;
    private JTextField ratingInput;

    private JLabel proteinLabel;
    private JTextField proteinInput;

    private JLabel caloriesLabel;
    private JTextField caloriesInput;

    private JLabel fatLabel;
    private JTextField fatInput;

    private JLabel sodiumLabel;
    private JTextField sodiumInput;

    private JLabel priceLabel;
    private JTextField priceInput;

    private JButton insertButton;

    private JButton deleteButton;

    private JButton updateButton;

    private JButton importButton;

    private JButton compositeButton;

    private JButton createReportButton;

    private ArrayList<MenuItem> menuItems;
    private Serializator serializator;
    JTable table;
    DefaultTableModel myModel;

    public AdminGUI()
    {
        serializator=new Serializator();
        menuItems=new ArrayList<MenuItem>();
        menuItems= (ArrayList<MenuItem>) serializator.deserialize("menu.ser");
        myModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.setTitle("Admin window");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        titleLabel=new JLabel("Title");
        titleLabel.setBounds(100,100,100,50);
        getContentPane().add(titleLabel);

        titleInput=new JTextField(20);
        titleInput.setBounds(200,100,100,25);
        getContentPane().add(titleInput);

        ratingLabel=new JLabel("Rating");
        ratingLabel.setBounds(100,150,100,50);
        getContentPane().add(ratingLabel);

        ratingInput=new JTextField(20);
        ratingInput.setBounds(200,150,100,25);
        getContentPane().add(ratingInput);

        caloriesLabel=new JLabel("Calories");
        caloriesLabel.setBounds(100,200,100,50);
        getContentPane().add(caloriesLabel);

        caloriesInput=new JTextField(20);
        caloriesInput.setBounds(200,200,100,25);
        getContentPane().add(caloriesInput);

        proteinLabel=new JLabel("Protein");
        proteinLabel.setBounds(100,250,100,50);
        getContentPane().add(proteinLabel);

        proteinInput=new JTextField(20);
        proteinInput.setBounds(200,250,100,25);
        getContentPane().add(proteinInput);

        fatLabel=new JLabel("Fat");
        fatLabel.setBounds(100,300,100,50);
        getContentPane().add(fatLabel);

        fatInput=new JTextField(20);
        fatInput.setBounds(200,300,100,25);
        getContentPane().add(fatInput);

        sodiumLabel=new JLabel("Sodium");
        sodiumLabel.setBounds(100,350,100,50);
        getContentPane().add(sodiumLabel);

        sodiumInput=new JTextField(20);
        sodiumInput.setBounds(200,350,100,25);
        getContentPane().add(sodiumInput);

        priceLabel=new JLabel("Price");
        priceLabel.setBounds(100,400,100,50);
        getContentPane().add(priceLabel);

        priceInput=new JTextField(20);
        priceInput.setBounds(200,400,100,25);
        getContentPane().add(priceInput);

        insertButton=new JButton("Insert");
        insertButton.setBounds(100,500,100,25);
        getContentPane().add(insertButton);

        deleteButton=new JButton("Delete");
        deleteButton.setBounds(250,500,100,25);
        getContentPane().add(deleteButton);

        updateButton=new JButton("Update");
        updateButton.setBounds(400,500,100,25);
        getContentPane().add(updateButton);

        importButton=new JButton("Import");
        importButton.setBounds(550,500,100,25);
        getContentPane().add(importButton);

        compositeButton=new JButton("Compose");
        compositeButton.setBounds(250,550,100,25);
        getContentPane().add(compositeButton);

        createReportButton=new JButton("Report");
        createReportButton.setBounds(400,550,100,25);
        getContentPane().add(createReportButton);

        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setBounds(700,50,500,500);

        table=createTable(myModel);
        table.setEnabled(true);
        table.setVisible(true);
        scrollPane.setViewportView(table);
        getContentPane().add(scrollPane);

    }
    public JTable createTable(DefaultTableModel myModel)
    {
        String[] columns=new String[7];
        columns[0]="title";
        columns[1]="rating";
        columns[2]="calories";
        columns[3]="protein";
        columns[4]="fat";
        columns[5]="sodium";
        columns[6]="price";
        ArrayList<MenuItem> menu= (ArrayList<MenuItem>) serializator.deserialize("menu.ser");
        myModel.setColumnIdentifiers(columns);
        for (MenuItem o : menu) {
            String[] row=new String[7];
            row[0]=o.getTitle();
            row[1]= String.valueOf(o.getRating());
            row[2]= String.valueOf(o.getCalories());
            row[3]= String.valueOf(o.getProtein());
            row[4]= String.valueOf(o.getFat());
            row[5]= String.valueOf(o.getSodium());
            row[6]= String.valueOf(o.getPrice());
            myModel.addRow(row);
        }
        JTable myTable = new JTable(myModel);
        return myTable;
    }
    public int[] getSelectedRows()
    {
        return table.getSelectedRows();
    }
    public int getSelectedRow()
    {
        return table.getSelectedRow();
    }
    public JTable getTable()
    {
        return table;
    }
    public ArrayList<MenuItem> getMenuItems()
    {
        return menuItems;
    }

    public String getTitleInput() {
        return titleInput.getText();
    }

    public float getRatingInput() {
        if (ratingInput.getText().isEmpty())
        {
            return 0;
        }
        return Float.parseFloat(ratingInput.getText());
    }

    public int getProteinInput() {
        if (proteinInput.getText().isEmpty())
        {
            return 0;
        }
        return Integer.parseInt(proteinInput.getText());
    }

    public int getCaloriesInput() {
        if (caloriesInput.getText().isEmpty())
        {
            return 0;
        }
        return Integer.parseInt(caloriesInput.getText());
    }

    public int getFatInput() {
        if (fatInput.getText().isEmpty())
        {
            return 0;
        }
        return Integer.parseInt(fatInput.getText());
    }

    public int getSodiumInput() {
        if (sodiumInput.getText().isEmpty())
        {
            return 0;
        }
        return Integer.parseInt(sodiumInput.getText());
    }

    public int getPriceInput() {
        if (priceInput.getText().isEmpty())
        {
            return 0;
        }
        return Integer.parseInt(priceInput.getText());
    }

    public void addUpdateListener(ActionListener mal) {
        updateButton.addActionListener(mal);
    }

    public void addDeleteListener(ActionListener mal) {
        deleteButton.addActionListener(mal);
    }

    public void addInsertListener(ActionListener mal) {
        insertButton.addActionListener(mal);
    }
    public void addComposeListener(ActionListener mal) {
        compositeButton.addActionListener(mal);
    }

    public void addImportListener(ActionListener mal) {
        importButton.addActionListener(mal);
    }
    public void addReportListener(ActionListener mal) {
        createReportButton.addActionListener(mal);
    }
    public DefaultTableModel getModel()
    {
        return myModel;
    }

}
