
import java.util.ArrayList;

public class Character {

    private String name;
    private String description;
    private int health;
    private ArrayList<Item> inventory;

    //getters
    public String getName() {
	return name;
    }

    public String getDesc() {
	return description;
    }

    public int getHealth() {
	return health;
    }
    
    public ArrayList<Item> getInvent(){
        return (ArrayList<Item>) inventory.clone();
    }

    //setters
    public void setName(String nameItem) {
	name = nameItem;
    }

    public void setDesc(String descItem) {
	description = descItem;
    }

    public void setHealth(int hp) {
	health = hp;
    }

    //constructor
    public Character(String nym, String desc, int hp, ArrayList<Item> invent) {
	name = nym;
	description = desc;
	health = hp;
	inventory = invent;
    }

    //methods
    public void printInventory() {
	System.out.print("You are carrying: ");
	for (int i = 0; i < inventory.size(); i++) {
	    System.out.print(inventory.get(i).getName());
	    if (i < inventory.size() - 1) {
		System.out.print(", ");
	    }
	}
	System.out.print("\n");
    }
    //add item to room
	    
    public void pickUpItem(String item, Room room) {
	int pickedUp = 0;
	//get name of item
	ArrayList<Item> roomVent = room.getInvent();
	for (Item i : roomVent) {
	    String itNam = i.getName();
	    if (itNam.equals(item)) {
		inventory.add(i);
		room.takeItem(i);
		System.out.print("You have picked up a " + item + "\n");
		pickedUp++;
	    }
	}
	if (pickedUp == 0) {
	    System.out.print("I cannot pick up something that isn't there!" + "("+item +")" + "\n");
	}
    }
    //remove item from room
    public void putDownItem(String item, Room room) {
	int putDown = 0;
	//get name of item
	for (Item i : inventory) {
	    String itNam = i.getName();
	    if (itNam.equals(item)) {
		inventory.remove(i);
		room.giveItem(i);
		System.out.print("You have put down a " + item + "\n");
		putDown++;
		break;
	    }
	}
	if (putDown == 0) {
	    System.out.print("I cannot put down something I don't have!" + "("+item +")" + "\n");
	}
    }
}