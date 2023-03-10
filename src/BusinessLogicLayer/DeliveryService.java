package BusinessLogicLayer;

import DataAccess.FileWriter1;
import DataAccess.Serializator;
import GUI.AdminGUI;
import GUI.ClientGUI;
import Model.Client;
import Model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryService  extends Observable implements DeliveryServiceProcessing,Serializable{
    public List<MenuItem> menu=new ArrayList<>();
    Serializator serializator=new Serializator();
    Employee employee=new Employee(1,"1234","1234");
    public DeliveryService()
    {
        addObserver(employee);
    }

    public void createItem(String name, String rating, String calories, String protein, String fat, String sodium, String price)
    {
        BaseProduct baseProduct=new BaseProduct(name, Float.parseFloat(rating), Integer.parseInt(calories), Integer.parseInt(protein), Integer.parseInt(fat),Integer.parseInt(sodium),Integer.parseInt(price));
        if (isUnique(baseProduct))
        {
            menu.add(baseProduct);
            //System.out.println(name+" "+rating+" "+calories+" "+protein+" "+fat+" "+sodium+" "+price);
        }

    }
    public boolean isUnique(BaseProduct product)
    {
        for(MenuItem item: menu)
        {
            if (item.getTitle().equals(product.getTitle()))
                return false;
        }
        return true;
    }

    @Override
    public void importProducts() {
        try {
            Files.lines(Path.of("products.csv")).skip(1).map(line -> line.split(",")).forEach(row-> createItem(row[0],row[1],row[2],row[3],row[4],row[5],row[6]));
            serializator.serialize(menu,"menu.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertProduct(AdminGUI view, BaseProduct product, DefaultTableModel model) {
        ArrayList<MenuItem> menuItems;
        menuItems=view.getMenuItems();
        menuItems.add(product);
        String[] row=new String[7];
        row[0]=view.getTitleInput();
        row[1]=String.valueOf(view.getRatingInput());
        row[2]=String.valueOf(view.getCaloriesInput());
        row[3]=String.valueOf(view.getProteinInput());
        row[4]=String.valueOf(view.getFatInput());
        row[5]=String.valueOf(view.getSodiumInput());
        row[6]=String.valueOf(view.getPriceInput());
        model.addRow(row);
        serializator.serialize(menuItems,"menu.ser");
    }
    @Override
    public void deleteProducts(AdminGUI view, DefaultTableModel model)
    {
        int[] selectedRow;
        selectedRow=view.getSelectedRows();
        JTable table=view.getTable();
        ArrayList<MenuItem> menuItems;
        menuItems=view.getMenuItems();
        if (selectedRow.length>0)
        {
            int j=0;
            for(int i:selectedRow)
            {
                menuItems.remove(i-j);
                model.removeRow(i-j);
                j++;
            }
            serializator.serialize(menuItems,"menu.ser");

        }
    }
    @Override
    public void updateProduct(AdminGUI view, DefaultTableModel model, BaseProduct product)
    {
        ArrayList<MenuItem> menuItems;
        int row=view.getSelectedRow();
        menuItems=view.getMenuItems();
        menuItems.set(row,product);
        model.setValueAt(view.getTitleInput(), row, 0);
        model.setValueAt(view.getRatingInput(), row, 1);
        model.setValueAt(view.getCaloriesInput(), row, 2);
        model.setValueAt(view.getProteinInput(), row, 3);
        model.setValueAt(view.getFatInput(), row, 4);
        model.setValueAt(view.getSodiumInput(), row, 5);
        model.setValueAt(view.getPriceInput(), row, 6);
        serializator.serialize(menuItems,"menu.ser");
    }
    @Override
    public void importToGUI(AdminGUI view, DefaultTableModel model)
    {
        String[] columns=new String[7];
        int rows=model.getRowCount();
        for (int i=0; i<rows;i++)
        {
            model.removeRow(0);
        }
        columns[0]="title";
        columns[1]="rating";
        columns[2]="calories";
        columns[3]="protein";
        columns[4]="fat";
        columns[5]="sodium";
        columns[6]="price";
        ArrayList<MenuItem> menu= importProducts2();
        model.setColumnIdentifiers(columns);
        for (MenuItem o : menu) {
            String[] row=new String[7];
            row[0]=o.getTitle();
            row[1]= String.valueOf(o.getRating());
            row[2]= String.valueOf(o.getCalories());
            row[3]= String.valueOf(o.getProtein());
            row[4]= String.valueOf(o.getFat());
            row[5]= String.valueOf(o.getSodium());
            row[6]= String.valueOf(o.getPrice());
            model.addRow(row);
        }
    }
    @Override
    public void composeProduct(AdminGUI view, DefaultTableModel model)
    {
        int[] selectedRow;
        selectedRow=view.getSelectedRows();
        ArrayList<MenuItem> items=new ArrayList<>();
        ArrayList<MenuItem> menuItems;
        menuItems=view.getMenuItems();
        for(int i:selectedRow)
        {
            items.add(menuItems.get(i));
        }
        CompositeProduct compositeProduct=new CompositeProduct(view.getTitleInput(),items);
        menuItems.add(compositeProduct);
        String[] row=new String[7];
        row[0]=compositeProduct.getTitle();
        row[1]= String.valueOf(compositeProduct.getRating());
        row[2]= String.valueOf(compositeProduct.getCalories());
        row[3]= String.valueOf(compositeProduct.getProtein());
        row[4]= String.valueOf(compositeProduct.getFat());
        row[5]= String.valueOf(compositeProduct.getSodium());
        row[6]= String.valueOf(compositeProduct.getPrice());
        model.addRow(row);
        serializator.serialize(menuItems,"menu.ser");
    }
    public ArrayList<MenuItem> importProducts2() {
        try {
            Files.lines(Path.of("products.csv")).skip(1).map(line -> line.split(",")).forEach(row-> createItem(row[0],row[1],row[2],row[3],row[4],row[5],row[6]));
            serializator.serialize(menu,"menu.ser");
            return (ArrayList<MenuItem>) menu;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Order> filterHours(int start, int end)
    {
        ArrayList<Order> orders= (ArrayList<Order>) serializator.deserialize("orders.ser");
        return (ArrayList<Order>) orders.stream().filter(order -> order.getOrderDate().getHours()>=start).filter(order -> order.getOrderDate().getHours()<=end).collect(Collectors.toList());
    }
    public ArrayList<MenuItem> filterOrdered(int n)
    {
        ArrayList<MenuItem> items= (ArrayList<MenuItem>) serializator.deserialize("menu.ser");
        return (ArrayList<MenuItem>) items.stream().filter(item-> item.getNrTimesOrdered()>=n).collect(Collectors.toList());
    }

    @Override
    public void generateReport(String message, int n,int m) {
        if (message.equals("order"))
        {
            ArrayList<MenuItem> items=filterOrdered(n);
            FileWriter1.createReportOrder(items);
        }
        else if(message.equals("time"))
        {
            ArrayList<Order> orders=filterHours(n,m);
            FileWriter1.createReportTime(orders);
        }
    }

    @Override
    public void createNewOrder(ClientGUI view, String username) {
        Date date=new Date();
        int[] selectedRow;
        int price=0;
        selectedRow=view.getSelectedRows();
        ArrayList<MenuItem> items=new ArrayList<>();
        ArrayList<MenuItem> menuItems;
        int id=0;
        menuItems=view.getMenuItems();
        for(int i:selectedRow)
        {
            items.add(menuItems.get(i));
            menuItems.get(i).increment();
            price+=menuItems.get(i).getPrice();
        }
        ArrayList<Client> clients= (ArrayList<Client>) serializator.deserialize("clients.ser");
        for(Client client:clients)
        {
            if (client.getUsername().equals(username))
            {
                id=client.getId();
                client.incrementOrdered();
            }
        }
        serializator.serialize(clients,"clients.ser");
        ArrayList<Order> orders= (ArrayList<Order>) serializator.deserialize("orders.ser");
        Order order=new Order((int) date.getTime(),id,date,price);
        orders.add(order);
        serializator.serialize(orders,"orders.ser");
        setChanged();
        notifyObservers(order.getOrderID());
        FileWriter1.createBill(order,items);
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "Order has been successfully registered!");

    }
    public ArrayList<MenuItem> filterByTitle(String titleFilter) {
        ArrayList<MenuItem> menu;
        menu= (ArrayList<MenuItem>) serializator.deserialize("menu.ser");
        return (ArrayList<MenuItem>) menu.stream().filter(product -> product.getTitle().toLowerCase().contains(titleFilter.toLowerCase())).collect(Collectors.toList());
    }

    public ArrayList<MenuItem> filterByRating(float ratingFilterMin, float ratingFilterMax) {
        ArrayList<MenuItem> menu;
        menu= (ArrayList<MenuItem>) serializator.deserialize("menu.ser");
        return (ArrayList<MenuItem>) menu.stream().filter(product -> product.getRating() >= (ratingFilterMin)).filter(product -> product.getRating() <= (ratingFilterMax)).collect(Collectors.toList());
    }
    public ArrayList<MenuItem> filterByCalories(Integer caloriesFilterMin, Integer caloriesFilterMax) {
        ArrayList<MenuItem> menu;
        menu= (ArrayList<MenuItem>) serializator.deserialize("menu.ser");
        return (ArrayList<MenuItem>) menu.stream().filter(product -> product.getCalories() >= (caloriesFilterMin)).filter(product -> product.getCalories() <= (caloriesFilterMax)).collect(Collectors.toList());
    }
    public ArrayList<MenuItem> filterByProtein(Integer proteinFilterMin, Integer proteinFilterMax) {
        ArrayList<MenuItem> menu;
        menu= (ArrayList<MenuItem>) serializator.deserialize("menu.ser");
        return (ArrayList<MenuItem>) menu.stream().filter(product -> product.getProtein() >= (proteinFilterMin)).filter(product -> product.getProtein() <= (proteinFilterMax)).collect(Collectors.toList());
    }
    public ArrayList<MenuItem> filterByFat(Integer fatFilterMin, Integer fatFilterMax) {
        ArrayList<MenuItem> menu;
        menu= (ArrayList<MenuItem>) serializator.deserialize("menu.ser");
        return (ArrayList<MenuItem>) menu.stream().filter(product -> product.getFat() >= (fatFilterMin)).filter(product -> product.getFat() <= (fatFilterMax)).collect(Collectors.toList());
    }
    public ArrayList<MenuItem> filterBySodium(Integer sodiumFilterMin, Integer sodiumFilterMax) {
        ArrayList<MenuItem> menu;
        menu= (ArrayList<MenuItem>) serializator.deserialize("menu.ser");
        return (ArrayList<MenuItem>) menu.stream().filter(product -> product.getSodium() >= ( sodiumFilterMin)).filter(product -> product.getSodium() <= (sodiumFilterMax)).collect(Collectors.toList());
    }
    public ArrayList<MenuItem> filterByPrice(Integer priceFilterMin, Integer priceFilterMax) {
        ArrayList<MenuItem> menu;
        menu= (ArrayList<MenuItem>) serializator.deserialize("menu.ser");
        return (ArrayList<MenuItem>) menu.stream().filter(product -> product.getPrice() >= (priceFilterMin)).filter(product -> product.getPrice() <= (priceFilterMax)).collect(Collectors.toList());
    }
    @Override
    public void searchCriteria(ClientGUI view, DefaultTableModel model)
    {
        if (!view.getTitleInput().isEmpty())
        {
            ArrayList<MenuItem> menu;
            menu=filterByTitle(view.getTitleInput());
            modelChange(menu,model);
        }
        else if (view.getRatingInput()!=-1)
        {
            ArrayList<MenuItem> menu;
            menu=filterByRating(view.getRatingInput(),view.getRatingInput());
            modelChange(menu,model);
        }
        else if (view.getProteinInput()!=-1)
        {
            ArrayList<MenuItem> menu;
            menu=filterByProtein(view.getProteinInput(),view.getProteinInput());
            modelChange(menu,model);
        }
        else if (view.getFatInput()!=-1)
        {
            ArrayList<MenuItem> menu;
            menu=filterByFat(view.getFatInput(),view.getFatInput());
            modelChange(menu,model);
        }
        else if (view.getSodiumInput()!=-1)
        {
            ArrayList<MenuItem> menu;
            menu=filterBySodium(view.getSodiumInput(),view.getSodiumInput());
            modelChange(menu,model);
        }
        else if (view.getPriceInput()!=-1)
        {
            ArrayList<MenuItem> menu;
            menu=filterByPrice(view.getPriceInput(),view.getPriceInput());
            modelChange(menu,model);
        }
        else if (view.getCaloriesInput()!=-1)
        {
            ArrayList<MenuItem> menu;
            menu=filterByCalories(view.getCaloriesInput(),view.getCaloriesInput());
            modelChange(menu,model);
        }
    }
    public void modelChange(ArrayList<MenuItem> menuItems, DefaultTableModel model)
    {
        String[] columns=new String[7];
        int rows=model.getRowCount();
        for (int i=0; i<rows;i++)
        {
            model.removeRow(0);
        }
        columns[0]="title";
        columns[1]="rating";
        columns[2]="calories";
        columns[3]="protein";
        columns[4]="fat";
        columns[5]="sodium";
        columns[6]="price";
        model.setColumnIdentifiers(columns);
        for (MenuItem o : menuItems) {
            String[] row=new String[7];
            row[0]=o.getTitle();
            row[1]= String.valueOf(o.getRating());
            row[2]= String.valueOf(o.getCalories());
            row[3]= String.valueOf(o.getProtein());
            row[4]= String.valueOf(o.getFat());
            row[5]= String.valueOf(o.getSodium());
            row[6]= String.valueOf(o.getPrice());
            model.addRow(row);
        }
    }
    public ArrayList<MenuItem> getItems()
    {
        return (ArrayList<MenuItem>) menu;
    }
}
