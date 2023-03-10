package DataAccess;

import BusinessLogicLayer.MenuItem;
import BusinessLogicLayer.Order;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriter1 {

    public static void createBill(Order order, ArrayList<MenuItem> item) {
        File file = new File( "bill1" + ".txt");
        int increase = 1;
        while (file.exists()) {
            increase++;
            file = new File(  "bill" + increase + ".txt");
        }
        if (!file.exists()) {
            try {

                StringBuilder content=new StringBuilder();
                content.append("The order was succesfully made at ").append(order.getOrderDate()).append(" With id ").append(order.getOrderID());
                content.append("\n");
                content.append("Products ordered: ");
                int priceTotal=0;
                for (MenuItem menu: item)
                {
                    content.append(menu.getTitle()).append(" ");
                    priceTotal+=menu.getPrice();
                }
                content.append("\n");
                content.append("Total price:").append(priceTotal);
                file.createNewFile();

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content.toString());
                bw.close();

            } catch (IOException e) {
            }
        }
    }
    public static void createReportOrder(ArrayList<MenuItem> item) {
        File file = new File( "ReportOrder1" + ".txt");
        int increase = 1;
        while (file.exists()) {
            increase++;
            file = new File(  "ReportOrder" + increase + ".txt");
        }
        if (!file.exists()) {
            try {

                StringBuilder content=new StringBuilder();
                content.append("The report with the specific number ordered: ");
                content.append("\n");
                content.append("Products ordered more than the nr: ");
                int priceTotal=0;
                for (MenuItem menu: item)
                {
                    content.append(menu.getTitle()).append(" ");
                }
                content.append("\n");
                file.createNewFile();

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content.toString());
                bw.close();

            } catch (IOException e) {
            }
        }
    }
    public static void createReportTime(ArrayList<Order> orders)
    {
        File file = new File( "ReportTime1" + ".txt");
        int increase = 1;
        while (file.exists()) {
            increase++;
            file = new File(  "ReportTime" + increase + ".txt");
        }
        if (!file.exists()) {
            try {

                StringBuilder content=new StringBuilder();
                content.append("Report succesfully made: ");
                content.append("\n");
                content.append("Orders with the Time interval: ");
                int priceTotal=0;
                for (Order order: orders)
                {
                    content.append(order.getOrderID()).append(" ");
                }
                content.append("\n");
                file.createNewFile();

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content.toString());
                bw.close();

            } catch (IOException e) {
            }
        }
    }
}
