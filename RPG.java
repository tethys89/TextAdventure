
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

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
	String orders = "";


	while (true) {
	    //<editor-fold defaultstate="collapsed" desc="ask for command">	    
	    System.out.print("What do you want to do? ");
	    try {
		orders = command.readLine();
	    } catch (IOException ioe) {
		System.out.println("I'm sorry, I didn't understand that!");
		//System.exit(1);
	    }
	    //split string into array
	    String[] orderWords = orders.toUpperCase().split(" ");
	    //turn string[] into arraylist
	    ArrayList<String> orderList = new ArrayList<>(Arrays.asList(orderWords));

	    //</editor-fold>

	    //<editor-fold defaultstate="collapsed" desc="switchable using string[]">
	     /* switch (orderWords[0].toLowerCase()) {
	     * case "go":
	     * int count = 0;
	     * for (Exit e : currentLoc.getExits()) {
	     * String direct = orderWords[1].toUpperCase();
	     * if (direct.equals(e.getDirectionName())) {
	     * currentLoc = e.getLeadsTo();
	     * count++;
	     * System.out.print("You are standing in the " + currentLoc.getName() + "\n" + currentLoc.getDesc() + "\n");
	     * currentLoc.printInventory();
	     * currentLoc.printExits();
	     * break;
	     * }
	     * }
	     * if (count == 0) {
	     * System.out.print("I'm sorry, I can't go that way.\n");
	     * }
	     * break;
	     * 
	     * case "pick":
	     * 
	     * 
	     * case "put":
	     * 
	     * 
	     * case "exit":
	     * System.exit(0);
	     * break;
	     * } 
	     */
	    //</editor-fold>

	    //<editor-fold defaultstate="collapsed" desc="arrayList if">
	    //<editor-fold defaultstate="collapsed" desc="GO">
	    if (orderList.contains("GO")) {
		int count = 0;
		for (Exit e : currentLoc.getExits()) {
		    if (orderList.contains(e.getDirectionName())) {
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
	    } //</editor-fold>
	    //<editor-fold defaultstate="collapsed" desc="PICK UP">
	    else if (orderList.contains("TAKE") || orderList.contains("PICK")) {
		//counter for picked up items
		int count = 0;
		//loop over items in the room
		for (Item i : currentLoc.getInvent()) {
		    //split the item name into string[]
		    String[] itemName = i.getName().toUpperCase().split(" ");
		    //loop over the words in the item name
		    for (String s : itemName) {
			//test if the word in the item name is contained in the command
			//(ignore 'a', 'the' etc?)
			if (orderList.contains(s) && !s.equals("A") && !s.equals("THE")) {
			    Avatar.pickUpItem(i.getName(), currentLoc);
			    Avatar.printInventory();
			    count++;
			    break;
			}
		    }
		}
		if (count == 0) {
		    System.out.print("I'm sorry I can't pick that up\n");
		}
	    } //</editor-fold>
	    //<editor-fold defaultstate="collapsed" desc="PUT DOWN">
	    else if (orderList.contains("LEAVE") || orderList.contains("DROP") || orderList.contains("PUT")) {
		//counter for put down items
		int count = 0;
		//loop over items in character's inventory
		for (Item i : Avatar.getInvent()) {
		    //split the item name into string[]
		    String[] itemName = i.getName().toUpperCase().split(" ");
		    //loop over the words in the item name
		    for (String s : itemName) {
			//test if the word in the item name is contained in the command
			//(ignore 'a', 'the' etc?)
			if (orderList.contains(s) && !s.equals("A") && !s.equals("THE")) {
			    Avatar.putDownItem(i.getName(), currentLoc);
			    Avatar.printInventory();
			    count++;
			    break;
			}
		    }
		}
		if (count == 0) {
		    System.out.print("I'm sorry I can't put that down\n");
		}
	    } //</editor-fold>
	    //<editor-fold defaultstate="collapsed" desc="DESCRIBE">
	    else if (orderList.contains("EXAMINE") || orderList.contains("DESCRIBE")) {
		String[] roomArr = currentLoc.getName().split(" ");
		if (orderList.contains(roomArr[0]) || orderList.contains("ROOM")) {
		    currentLoc.roomDetails();
		} else {
		    //		    int count = 0;
		    for (Item i : currentLoc.getInvent()) {
			//split the item name into string[]
			String[] itemName = i.getName().toUpperCase().split(" ");
			//loop over the words in the item name
			for (String s : itemName) {
			    //test if the word in the item name is contained in the command
			    //(ignore 'a', 'the' etc?)
			    if (orderList.contains(s) && !s.equals("A") && !s.equals("THE")) {
				i.itemDetails();
				//				count++;
				break;
			    }
			}
		    }
		}
	    }
	    //</editor-fold>
	    //<editor-fold defaultstate="collapsed" desc="QUIT">
	    if (orderList.contains("QUIT")) {
		System.exit(0);
	    }
	    //</editor-fold>

	    //</editor-fold>
	}
    }

    public ArrayList<Room> createWorld() {
	ArrayList<Room> world = new ArrayList<>();
	BufferedReader br = null;
 		try {
 			String sCurrentLine;
 			br = new BufferedReader(new FileReader("C:\\Users\\Madaxe\\Documents\\NetBeansProjects\\TextAdventure\\src"));
 			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			} 
		} catch (IOException e) {
			e.printStackTrace();} 
		
	return world;
    }
}