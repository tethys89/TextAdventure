
import java.util.ArrayList;
import java.io.*;

public class RPG {

    public static void main(String[] args) {

	//create rooms : Kitchen Lounge Dining Hall
	//<editor-fold defaultstate="collapsed" desc="room creation">
	//Entrance Hall
	ArrayList<Item> hallInventory = new ArrayList<>();
	ArrayList<Exit> hallExits = new ArrayList<>();
	Room Hall = new Room("Entrance Hall", "It is an entrance hall of a very grand house", hallInventory, hallExits);

	//kitchen
	ArrayList<Item> kitchenInventory = new ArrayList<>();
	ArrayList<Exit> kitchenExits = new ArrayList<>();
	Room Kitchen = new Room("Kitchen", "This is a very large kitchen.", kitchenInventory, kitchenExits);

	//dining room
	ArrayList<Item> diningInventory = new ArrayList<>();
	ArrayList<Exit> diningExits = new ArrayList<>();
	Room Dining = new Room("Dining Room", "This dining room is set for 12 people", diningInventory, diningExits);

	//lounge
	ArrayList<Item> loungeInventory = new ArrayList<>();
	ArrayList<Exit> loungeExits = new ArrayList<>();
	Room Lounge = new Room("Lounge", "The Lounge is a mess, and there are blood spatters on the wall", loungeInventory, loungeExits);

	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="Fill rooms with objects">
	hallInventory.add(new Item("a fruit bowl", "The fruit bowl contains some fruit"));
	hallInventory.add(new Item("a clock", "Tick Tock"));
	kitchenInventory.add(new Item("a stove", "The stove is very hot"));
	kitchenInventory.add(new Item("a knife", "The knife is blunt"));
	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="add exits to rooms">
	hallExits.add(new Exit(1, Lounge));
	hallExits.add(new Exit(4, Dining));
	loungeExits.add(new Exit(2, Hall));
	diningExits.add(new Exit(3, Hall));
	diningExits.add(new Exit(4, Kitchen));
	kitchenExits.add(new Exit(3, Dining));
	//</editor-fold>

	//create character : Avatar
	//<editor-fold defaultstate="collapsed" desc="character creation">
	ArrayList<Item> invent = new ArrayList<>();
	Character Avatar = new Character("Tethys", "A tall elf dressed in armour", 100, invent);
	invent.add(new Item("armour", "leather armour"));
	//</editor-fold>
	//begin
	Room currentLoc = Hall;
	System.out.print("You are standing in the " + currentLoc.getName() + "\n" + currentLoc.getDesc() + "\n");
	currentLoc.printInventory();
	currentLoc.printExits();

	BufferedReader command = new BufferedReader(new InputStreamReader(System.in));
	String orders = null;
	while (true) {
	    System.out.print("What do you want to do? ");
	    try {
		orders = command.readLine();
	    } catch (IOException ioe) {
		System.out.println("I'm sorry, I didn't understand that!");
		System.exit(1);
	    }

	    String[] orderWords = orders.split(" ");

//	    for (String s : orderWords){
//		System.out.print(s);
	    switch (orderWords[0].toLowerCase()) {
		case "go":
		    int count = 0;
		    for (Exit e : currentLoc.getExits()) {
			String direct = orderWords[1].toUpperCase();
			if (direct.equals(e.getDirectionName())) {
			    currentLoc = e.getLeadsTo();
			    count++;
			    System.out.print("You are standing in the " + currentLoc.getName() + "\n" + currentLoc.getDesc() + "\n");
			    currentLoc.printInventory();
			    currentLoc.printExits();
			    break;
			}
		    }
		    if (count == 0) {
			System.out.print("I'm sorry, I can't go that way.\n");
		    }
		    break;

		case "pick":


		case "put":


		case "exit":
		    System.exit(0);
		    break;
	    }

	}


    }
}
