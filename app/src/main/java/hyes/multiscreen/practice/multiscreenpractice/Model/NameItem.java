package hyes.multiscreen.practice.multiscreenpractice.Model;

/**
 * Created by hyes on 2015. 9. 7..
 */
public class NameItem {

    private int id;
    private String Name;


    public NameItem(int id, String name) {
        this.id = id;
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public int getId() {
        return id;
    }


}
