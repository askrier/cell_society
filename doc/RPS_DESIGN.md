
##Specification

- Weapons class:  
  - takes in a file (how many users/ how many weapons)
   - returns the weapon and its association 
    - compare method comparing the weapons to see who wins  (0,1,2 depending on tie) user1.compareTo(user2)

 public Weapon(String name, String[] losers){}
 public int compareTo (Weapon opponent){}
 
- Game: 
  - processes the weapon file -> constructs all the weapons 
  - runs the simulation - automates a response based off of the weapon class chosen 
   - users choose the weapon they want to play in the game
   
   
   public void ReadFile(String fileName){}
   public Weapon PromptUser(){}
   public static Main(String args[]){}