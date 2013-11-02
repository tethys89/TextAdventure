public class Item {
    
    private String name;
    private String description;
        
    //getters
    public String getName(){
        return name;
    }
    public String getDesc(){
        return description;
    }
        
    //setters
    public void setName(String nameItem){
        name = nameItem;
    }
    public void setDesc(String descItem){
        description = descItem;
    }
    
    //constructor
    public Item(String nym, String desc){
        name = nym;
        description = desc;
    }
    
    public String toString(){
	return name;
    }
    
    public void itemDetails(){
	System.out.print("This is "+name+".  "+description+"\n");
    }
}
