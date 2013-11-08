
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RPG {

    public static void main(String[] args) {
	ArrayList<Room> rooms = new ArrayList<>();
	try {
	    rooms = createWorld();
	    //	//create rooms : Kitchen Lounge Dining Hall
	    //	//<editor-fold defaultstate="collapsed" desc="room creation">
	    //	//Entrance Hall
	    //	ArrayList<Item> hallInventory = new ArrayList<>();
	    //	ArrayList<Exit> hallExits = new ArrayList<>();
	    //	Room Hall = new Room("Entrance Hall", "It is an entrance hall of a very grand house", hallInventory, hallExits);
	    //
	    //	//kitchen
	    //	ArrayList<Item> kitchenInventory = new ArrayList<>();
	    //	ArrayList<Exit> kitchenExits = new ArrayList<>();
	    //	Room Kitchen = new Room("Kitchen", "This is a very large kitchen.", kitchenInventory, kitchenExits);
	    //
	    //	//dining room
	    //	ArrayList<Item> diningInventory = new ArrayList<>();
	    //	ArrayList<Exit> diningExits = new ArrayList<>();
	    //	Room Dining = new Room("Dining Room", "This dining room is set for 12 people", diningInventory, diningExits);
	    //
	    //	//lounge
	    //	ArrayList<Item> loungeInventory = new ArrayList<>();
	    //	ArrayList<Exit> loungeExits = new ArrayList<>();
	    //	Room Lounge = new Room("Lounge", "The Lounge is a mess, and there are blood spatters on the wall", loungeInventory, loungeExits);
	    //
	    //	//</editor-fold>
	    //
	    //	//<editor-fold defaultstate="collapsed" desc="Fill rooms with objects">
	    //	hallInventory.add(new Item("a fruit bowl", "The fruit bowl contains some fruit"));
	    //	hallInventory.add(new Item("a clock", "Tick Tock"));
	    //	kitchenInventory.add(new Item("a stove", "The stove is very hot"));
	    //	kitchenInventory.add(new Item("a knife", "The knife is blunt"));
	    //	//</editor-fold>
	    //
	    //	//<editor-fold defaultstate="collapsed" desc="add exits to rooms">
	    //	hallExits.add(new Exit(1, "Lounge"));
	    //	hallExits.add(new Exit(4, "Dining Room"));
	    //	loungeExits.add(new Exit(2, "Entrance Hall"));
	    //	diningExits.add(new Exit(3, "Entrance Hall"));
	    //	diningExits.add(new Exit(4, "Kitchen"));
	    //	kitchenExits.add(new Exit(3, "Dining Room"));
	    //	//</editor-fold>
	    //
	    //	//put Rooms into ArrayList
	    //	rooms.add(Hall);
	    //	rooms.add(Lounge);
	    //	rooms.add(Kitchen);
	    //	rooms.add(Kitchen);
	} catch (CloneNotSupportedException ex) {
	    Logger.getLogger(RPG.class.getName()).log(Level.SEVERE, null, ex);
	}

//	System.out.print("printing" + "\n");

	System.out.print(rooms.get(0) + "\n\n");

	//create character : Avatar
	//<editor-fold defaultstate="collapsed" desc="character creation">
	ArrayList<Item> invent = new ArrayList<>();
	Character Avatar = new Character("Tethys", "A tall elf dressed in armour", 100, invent);
	invent.add(new Item("armour", "leather armour"));
	//</editor-fold>
	//begin
	String currentLocStr = "Hall";
	Room currentLoc = rooms.get(0);
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
		currentLoc = goPlaces(currentLoc, orderList, rooms);
	    } //</editor-fold>
	    //<editor-fold defaultstate="collapsed" desc="PICK UP">
	    else if (orderList.contains("TAKE") || orderList.contains("PICK")) {
		takeStuff(currentLoc, orderList, Avatar);
	    } //</editor-fold>
	    //<editor-fold defaultstate="collapsed" desc="PUT DOWN">
	    else if (orderList.contains("LEAVE") || orderList.contains("DROP") || orderList.contains("PUT")) {
		leaveStuff(Avatar, orderList, currentLoc);
	    } //</editor-fold>
	    //<editor-fold defaultstate="collapsed" desc="DESCRIBE">
	    else if (orderList.contains("EXAMINE") || orderList.contains("DESCRIBE")) {
		lookAt(currentLoc, orderList);
	    } //</editor-fold>
	    //<editor-fold defaultstate="collapsed" desc="QUIT">
	    else if (orderList.contains("QUIT") || orderList.contains("Q")) {
		System.exit(0);
	    } //</editor-fold>
	    else {
		System.out.print("I'm sorry, I didn't understand that");
	    }

	    //</editor-fold>
	}
    }

//    public static void createWorld() {
    public static ArrayList<Room> createWorld() throws CloneNotSupportedException {
	ArrayList<Room> world = new ArrayList<>();
	Scanner br;
	int roomCount = 0; //counter to count the rooms as they are added
//	System.out.print("building world\n");	

	//temp variables to store room and parameters
	String currentNym = "";
	String currentDesc = "";
	ArrayList<Item> currentInvent = new ArrayList<>();
	ArrayList<Exit> currentExits = new ArrayList<>();
	Room currentRoom = new Room();


//Scanner in = new Scanner(new FileReader("filename.txt"));
	try {
	    br = new Scanner(new FileReader("C:\\Users\\Madaxe\\Documents\\NetBeansProjects\\TextAdventure\\src\\roomData.txt"));
	    //to read in all the lines of the file
	    while ((br.hasNextLine())) {
//		System.out.print(roomCount+"\n");
		//to break the reading up into rooms using the "eoroom" divider
		    // first two lines are always the name and description of the room
//		    if(br.nextLine().isEmpty()){
//			System.out.print("skipping"+"\n");
//		    }
//		    System.out.print("doing\n");
		    
		    currentNym = br.nextLine();
		    currentDesc = br.nextLine();
		    String tempLine = br.nextLine();
		    
		    		    
		    System.out.print("Nym "+currentNym+"\n");
		    System.out.print("desc "+currentDesc+"\n");
		    System.out.print("templine "+tempLine+"\n");
		//    System.out.print(tempLineArr[0]+" , "+tempLineArr[1]+"\n");
		    
		do {
		    String[] tempLineArr = tempLine.split(" \\| ");
		    
		    System.out.print("tempLineArr[0] "+tempLineArr[0]+"\n");
		    
		    if (isInt(tempLineArr[0])) {
//			System.out.print(tempLine+" Exit\n");
			Exit newExit = new Exit(Integer.parseInt(tempLineArr[0]), tempLineArr[1]);
			currentExits.add(newExit);
		    } else /*if (tempLineArr[0].length() != 0)*/{
//			System.out.print(tempLine+" Item\n");
			Item newItem = new Item(tempLineArr[0], tempLineArr[1]);
			currentInvent.add(newItem);
		    }
		    tempLine = br.nextLine();
		    System.out.print("templine "+tempLine+"\n");
//		    if(br.nextLine().isEmpty()){
//			br.nextLine();
//			System.out.print("skipping"+"\n");
//		    }
		    
		} while ( br.hasNextLine() && !br.nextLine().isEmpty() ) ;
		
		currentRoom.setName(currentNym);
		currentRoom.setDesc(currentDesc);
		currentRoom.setInvent((ArrayList<Item>) currentInvent.clone());
		currentRoom.setExits((ArrayList<Exit>) currentExits.clone());
//		System.out.print("room "+currentRoom + "\n");
		Room tempRoom = (Room) currentRoom.clone();
//		System.out.print("temp name "+tempRoom+"\n");
//		System.out.print("temp desc "+tempRoom.getDesc()+"\n");
//		System.out.print("temp invent "+tempRoom.getInvent()+"\n");
//		System.out.print("temp exit "+tempRoom.getExits()+"\n");
//		
		world.add(tempRoom);
		roomCount++;
		currentRoom.setName("");
		currentRoom.setDesc("");
		currentInvent.clear();
		currentExits.clear();
		System.out.print("current name "+currentRoom+"\n");
//		System.out.print("current desc "+currentRoom.getDesc()+"\n");
//		System.out.print("current invent "+currentRoom.getInvent()+"\n");
//		System.out.print("current exit "+currentRoom.getExits()+"\n");
	    }
	} catch (IOException e) {
//	    e.printStackTrace();
	}

	return world;
    }

    public static boolean isInt(String input) {
	try {
	    String intTest = input.trim();
	    Integer.parseInt(intTest);
	    System.out.print("exit\n");
	    return true;
	} catch (NumberFormatException er) {
//	    System.out.print("item\n");
	    return false;
	}
    }

    public static Room goPlaces(Room currentLoc, ArrayList<String> orderList, ArrayList<Room> rooms) {
	String currentLocStr;
	int count = 0;
	//loop over exits in the current room
	for (Exit e : currentLoc.getExits()) {
	    //if the direction in the order is an exit of the room
	    if (orderList.contains(e.getDirectionName())) {
		//update the current location string to the new room
		currentLocStr = e.getLeadsTo();
		//loop over the rooms in the world
		for (Room r : rooms) {
		    //find the Room that has the same name as the string
		    if ((r.getName()).equals(currentLocStr)) {
			//set the currentLoc Room to the new room
			currentLoc = r;
			//don't need to loop over any more rooms now
			break;
		    }
		}
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
	return currentLoc;
    }

    public static void takeStuff(Room currentLoc, ArrayList<String> orderList, Character Avatar) {
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
    }

    public static void leaveStuff(Character Avatar, ArrayList<String> orderList, Room currentLoc) {
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
    }

    public static void lookAt(Room currentLoc, ArrayList<String> orderList) {
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
}