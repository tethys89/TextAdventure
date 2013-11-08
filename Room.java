import java.util.ArrayList;

public class Room implements Cloneable {
    
    private String roomName;
    private String roomDesc;
    private ArrayList<Item> roomInventory;
    private ArrayList<Exit> roomExits;
    
    //getters
    public String getName(){
        return roomName;
    }
    public String getDesc(){
        return roomDesc;
    }
    public ArrayList<Item> getInvent(){
        return (ArrayList<Item>) roomInventory.clone();
    }
    public ArrayList<Exit> getExits(){
        return (ArrayList<Exit>) roomExits.clone();
    }
    
    //setters
    public void setName(String nameRoom){
        roomName = nameRoom;
    }
    public void setDesc(String descRoom){
        roomDesc = descRoom;
    }
    public void setInvent(ArrayList<Item> inventoryRoom){
        roomInventory = inventoryRoom;
    }
    public void setExits(ArrayList<Exit> exitsRoom){
        roomExits = exitsRoom;
    }
    
    //constructor
    public Room(String name, String description, ArrayList<Item> inventory, ArrayList<Exit> exits){
        roomName = name;
        roomDesc = description;
        roomInventory = inventory;
        roomExits = exits;
    }
    
    public Room(Room room){
	Room newRoom = new Room();
	newRoom.setName(room.getName());
	newRoom.setDesc(room.getDesc());
	newRoom.setInvent(room.getInvent());
	newRoom.setExits(room.getExits());
	//return newRoom;
    }
    //empty constructor
    public Room(){
    }
    //methods
    
    public String toString() {
	return roomName;
    }
    
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    //add an exit to the room
    public void addExit (Exit exit){
        roomExits.add(exit);
    }
    //remove exit
    public void removeExit(Exit exit){
        if (roomExits.contains(exit)){
            roomExits.remove(exit);
        }
    }
    
    //add item to room
    public void giveItem (Item item){
        roomInventory.add(item);
    }
    //remove item frm room
    public void takeItem(Item item){
        if (roomInventory.contains(item)){
            roomInventory.remove(item);
        }
    }
    
    public void printInventory(){
	System.out.print("This room contains: ");
	for (int i=0; i<roomInventory.size();i++){
	    System.out.print(roomInventory.get(i).getName());
	    if (i<roomInventory.size()-1){
		System.out.print(", ");
	    }
    }
    System.out.print("\n");
    }
    
    public void printExits() {
	System.out.print("This room has exits to the: ");
	for (int i=0; i<roomExits.size();i++){
	    System.out.print(roomExits.get(i).getDirectionName() + " (" +roomExits.get(i).getLeadsTo()+ ")");
	    if (i<roomExits.size()-1){
		System.out.print(", ");
	    }
    }
    System.out.print("\n");
    }

    void roomDetails() {
	System.out.print("You are standing in the "+roomName+".  "+roomDesc+"\nIt contains "+roomInventory+"\n");
    }
}