import java.util.Hashtable;

/** A class that creates the player
 * @author Renee Owusu-Poku
 * @version 11/16/2022
 * References: 
 * https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html
*/

public class Player implements Contract {

    /** List of items in player inventory */
    private Hashtable<String, Boolean> inventory;
    /** List of items in the room */
    private Hashtable<String, String> storage;
    /** The time of day */
    private boolean time;
    /** The name of player */
    private String name;
    /** The size of player */
    private int size;



    /** Constructor that create the player object.
     *Initializes inventory hashtable, storage hashtable, time and player's size. 
     * @param name the name of the player
     */

    public Player(String userName) {
        this.name = userName;
        this.inventory = new Hashtable<>();
        this.storage = new Hashtable<>();
        this.time = true;
        this.size = 0;

        storage.put("wings", "Maybe you can use these to fly?");
        storage.put("cake", "EAT ME");
        storage.put("bottle", "DRINK ME");
        
        System.out.println("Welcome " + name +"!");
    }


     /** Method that allows player to grab item 
     * Adds the item to player's inventory
     * @param item the name of the item the player is grabbing */
    public void grab(String item){
        inventory.put(item, true);
        System.out.println("You have grabbed " + item + ".");
        
    }

     /** Method that allows player to drop item 
     * Removes the item from player's inventory if it is in inventory
     * @param item the name of the item the player is dropping 
     * @return the name of the item the player is dropping */    

    public String drop(String item){
        if(inventory.get(item) == true){
            inventory.remove(item);
            System.out.println("You have dropped " + item + ".");
            return item;
        }
        else{
            throw new RuntimeException("You do not have " + item + " in your inventory!");
        }
    }

     /** Method that allows player to examine item 
     * Checks if item is in inventory 
     * If it is item in the room storage it gets a special description 
     * Otherwise, it gets a placeholder message. 
     * @param item the name of the item the player is examining */     

    public void examine(String item){
        if (inventory.get(item) == true){
            if (storage.containsKey(item)){
                System.out.println(item + "-item decription: " + storage.get(item));
            }
            else{
                System.out.println(item + "Never seen these before(Placeholder)");
            }
        }
        if (inventory.get(item) == false){
            throw new RuntimeException("You do not have " + item + " in your inventory!");
        }
    }

     /** Method that allows player to use item if it is in inventory
     * @param item the name of the item the player is examining */     

    public void use(String item){
        if (inventory.get(item) == true){
            inventory.replace(item, false);
            System.out.println("You have used the " + item + ".");
        }
        else{
            throw new RuntimeException("You do not have " + item + " in your inventory!");
        }
    }

     /** Method that allows player to walk in specific direction  
     * @param direction the direction the player wants to walk in
     * @return a boolean that indicates that the user is walking (true) */    

    public boolean walk(String direction){
        if (direction == "north"){
            System.out.println("You are walking " + direction + "!");  
            return true;   
        }
        if (direction == "south"){
            System.out.println("You are walking " + direction + "!");  
            return true;    
        }
        if (direction == "east"){
            System.out.println("You are walking " + direction + "!");  
            return true;
        }
        if (direction == "west"){
            System.out.println("You are walking " + direction + "!");  
            return true;
        }
        else{
            throw new RuntimeException(direction + " is not a valid direction! Must walk: north, south, east or west");
        }
    }

     /** Method that allows player to fly from to given coordinate  
     * If the player has used the wings item they can fly
     * @param x the int value of the x coordinate
     * @param y the int value of the y coordinate
     * @return a boolean that indicates that the user is flying (true) */       

    public boolean fly(int x, int y){
        if (inventory.containsKey("wings")){
            if(inventory.get("wings") == false){
                System.out.println("The user has flown to ("+ x +", " + y + ")");
                return true; 
            }
            else{
                throw new RuntimeException("You do not have the wings equipped silly! You must use it first");
            }
        }
        else{
            System.out.println("You can't fly silly!");
            return false; 
        }
    }

     /** This method allows the player shrink themselves by -10
     * If the player has used the bottle item they can strink.
     * @return the player's new size */          

    public Number shrink(){
        if (inventory.containsKey("bottle")){
            if(inventory.get("bottle") == false){
                System.out.println("You have become larger!");
                this.size = this.size - 10;
                return this.size; 
            }
            else{
                throw new RuntimeException("You do not have the wand equipped silly! You must use it first");
            }
        }
        else{
            throw new RuntimeException("Why don't you try the bottle on the table?");
        }
    }


     /** This method allows the player grow themselves by +10
     * If the player has used the cake item they can grow.
     * @return the player's new size */               
    public Number grow(){
        if (inventory.containsKey("cake")){
            if(inventory.get("cake") == false){
                System.out.println("You have become smaller!");
                this.size = this.size + 10;
                return this.size; 
            }
            else{
                throw new RuntimeException("You do not have the wand equipped silly! You must use it first");
            }
        }
        else{
            throw new RuntimeException("Try the box, naturally");
        }
    }

     /** This method allows the player to rest 
     * resetting their size and changing the time of day.*/

    public void rest(){
        if (this.time == false){
            this.time = true;
            System.out.println("You slept the whole night, it is now day!");
        }
        else{
            this.time = false;
            System.out.println("You slept the whole day, it is now night!");
        }
        this.size = 0;
    }
        

     /** This method undos or resets all the player's previous actions */   
    public void undo(){
        this.inventory.clear();
        this.inventory = new Hashtable<>();
        this.size = 0;
        this.time = true;
        System.out.println("You have cleared all your previous actions");
    }
        

    /** Main method for testing
     * @param args the command line arguments (ignored)
     */
    public static void main(String[] args) {

        //Create instance of Player class
        Player myPlayer = new Player("Carl");
        myPlayer.grab("cat");
        myPlayer.grab("wings");

        myPlayer.drop("cat");
        myPlayer.examine("wings");

        myPlayer.use("wings");
        myPlayer.fly(10,50);
        myPlayer.walk("north");


        myPlayer.grab("bottle");
        myPlayer.use("bottle");
        myPlayer.shrink();

        myPlayer.grab("cake");
        myPlayer.use("cake");
        myPlayer.grow();

        myPlayer.rest();
        myPlayer.rest();
        myPlayer.undo();

    }
}


