package Model;

public class Client implements java.io.Serializable{
    private int id;
    private String username;
    private String password;

    private int nrOfTimesOrdered;

    public Client(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        nrOfTimesOrdered=0;
    }
    public void incrementOrdered()
    {
        nrOfTimesOrdered++;
    }

    public void setNrOfTimesOrdered(int nrOfTimesOrdered) {
        this.nrOfTimesOrdered = nrOfTimesOrdered;
    }

    public int getNrOfTimesOrdered() {
        return nrOfTimesOrdered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
