package Model;

import java.util.Observable;
import java.util.Observer;

public class Employee extends User implements Observer {
    public Employee(int id, String username, String password) {
        super(id, username, password);
    }


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("New order:"+arg.toString());
    }
}
