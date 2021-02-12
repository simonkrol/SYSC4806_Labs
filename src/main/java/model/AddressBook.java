package model;
/**
 * @author Simon Krol
 * SYSC4806 Lab 4 - Feb 4th 2021
 */
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    public List<BuddyInfo> buddies;

    public AddressBook() {
        buddies = new ArrayList<BuddyInfo>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addBuddy(BuddyInfo buddy)
    {
        buddies.add(buddy);
    }

    public void removeBuddy(BuddyInfo buddy) { buddies.remove(buddy); }

    public void listBuddies(){
        for(BuddyInfo b : buddies){
            System.out.print(b.getName() + " ---- ");
            System.out.println(b.getPhoneNumber());
        }
    }

    public String toString()
    {
        String result = "";
        for(BuddyInfo b : buddies){
            result = result + b.getName() + " ---- ";
            result = result + b.getPhoneNumber() + "\n";
        }
        return result;
    }

    public int size(){
        return buddies.size();
    }

    public static void main(String args[]) {
        BuddyInfo buddy1 = new BuddyInfo("Peter", "(613)-123-1234");
        BuddyInfo buddy2 = new BuddyInfo("Frank", "(613)-321-3210");
        BuddyInfo buddy3 = new BuddyInfo("Guadeloop", "(613)-555-5555");

        AddressBook book = new AddressBook();

        book.addBuddy(buddy1);
        book.addBuddy(buddy2);
        book.addBuddy(buddy3);

        book.listBuddies();
    }


}
