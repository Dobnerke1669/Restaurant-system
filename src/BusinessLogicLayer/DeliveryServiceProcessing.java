package BusinessLogicLayer;

import GUI.AdminGUI;
import GUI.ClientGUI;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public interface DeliveryServiceProcessing {
    public void importProducts();
    public void insertProduct(AdminGUI view, BaseProduct product, DefaultTableModel model);
    public void deleteProducts(AdminGUI view, DefaultTableModel model);
    public void updateProduct(AdminGUI view, DefaultTableModel model, BaseProduct product);
    public void importToGUI(AdminGUI view, DefaultTableModel model);
    public void composeProduct(AdminGUI view, DefaultTableModel model);
    public void generateReport(String message, int n, int m);
    public void createNewOrder(ClientGUI view, String username);

    void searchCriteria(ClientGUI view, DefaultTableModel model);
}
