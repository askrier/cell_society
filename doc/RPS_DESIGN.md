
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
   - keep track of score for each round
   
   
   public void ReadFile(String fileName){}
   public Weapon PromptUser(){}
   private void updateScores(int whoWon){}
   private void resetGame(){}
   public static Main(String args[]){}
   
## USE CASES

1) Main class calls resetGame()
2) In Main class, the user is prompted for their choice of weapon
3) After users choose their weapons, their weapons are compared and updateScore is called
4) A new ReadFile is called and weapons are updated appropriately
5) call resetGame() and call ReadFile()