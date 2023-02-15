// Programmers: Ben Diskin, David Rukashaza-Hancock, T. Jake Holmes, Jaspreet Khatkar
// CS 145: Face-To-Face
// Date: 2/15/2023
// Assignment: Tower Of Hanoi
// Reference: N/A
// Purpose: recreated the game of Tower Of Hanoi for fun.
// Extra Credit: created tower art for displaying each movie. see viewTower() method

import java.util.*;

public class Lab5TowerOfHanoiBonus {

    //Tower Objects
    private static Tower towerOne;
    private static Tower towerTwo = new Tower("Tower Two", 0);
    private static Tower towerThree = new Tower("Tower Three", 0);

    public static void main(String[] args) {
    
        
        //VARIABLES
        Scanner input = new Scanner(System.in);
        Tower firstTower = null;
        Tower secondTower = null;
        int playAgain = 1;
        int bestScoreE = 1000;
        int bestScoreM = 1000;
        int bestScoreH = 1000;
        
        instructions();
        
        while(playAgain == 1) {
        
         int difficulty = 0;
         while(difficulty < 1 || difficulty > 3) {
            try {
                  System.out.print("What difficulty would you like to play on? 1 = easy (three rings), 2 = medium (four rings),  3 = hard (five rings)\t");
                  difficulty = input.nextInt() + 2;
            }
            catch(InputMismatchException e)  { String issue = input.nextLine();}
            if(difficulty < 3 || difficulty > 5) {
            System.out.println("Please choose a valid difficulty.");
            }
         } // end while
        
         towerOne = new Tower("Tower One", difficulty);
        
         int score = 0; // score for current round

         towerView(difficulty);

         while(!towerOne.isEmpty() || !towerTwo.isEmpty()) {
            int prompt1 = 0;
            while(prompt1 < 1 || prompt1 > 3) {
               try {
                     System.out.print("Which Tower Would You Like To Remove A Ring From? 1 = Tower One, 2 = Tower Two, 3 = Tower Three\t");
                     prompt1 = input.nextInt();
               }
               catch(InputMismatchException e)  { String issue = input.nextLine();}
               if(prompt1 < 1 || prompt1 > 3) {
                  System.out.println("Please choose a valid tower.");
               }
            }

            int prompt2 = 0;
            
            while(prompt2 < 1 || prompt2 > 3) {
               try {
                     System.out.print("Which Tower Would You Like To Move The Ring To? 1 = Tower One, 2 = Tower Two, 3 = Tower Three\t");
                     prompt2 = input.nextInt();
               }
               catch(InputMismatchException e)  { String issue = input.nextLine();}
                if(prompt2 < 1 || prompt2 > 3) {
                  System.out.println("Please choose a valid tower.");
               }
            }

            System.out.println();

            if(prompt1 == prompt2) {
            System.out.println("Adding and removing rings from the same tower does nothing, choose again.");    
            }

            else {
                switch(prompt1) {
                    case 1:
                    firstTower = towerOne;
                    break;
                    case 2:
                    firstTower = towerTwo;
                    break;
                    case 3:
                    firstTower = towerThree;
                    break;
                }
                switch(prompt2) {
                    case 1:
                    secondTower = towerOne;
                    break;
                    case 2:
                    secondTower = towerTwo;
                    break;
                    case 3:
                    secondTower = towerThree;
                    break;
                }
                if(firstTower.isEmpty()) {
                    System.out.println("There are no rings to remove from " + firstTower.name());
                }
                else if(secondTower.isEmpty() || firstTower.topRing() < secondTower.topRing()) {
                    System.out.println("Ring " + firstTower.topRing() + " from " + firstTower.name() + " has been moved to " + secondTower.name());
                    secondTower.addRing(firstTower.removeRing());
                    score++;
                }
                else {
                    System.out.println("Error: Not a valid move.\nRing " + firstTower.topRing() + " is larger than ring " + secondTower.topRing());
                }
            } // end else
            towerView(difficulty);
        } // end while
        // clears tower 3
        for(int i = 0; i < difficulty; i++) {
        towerThree.removeRing();
        }
        // best score best on difficulty
        if(difficulty == 3) {
            if(score < bestScoreE) {
                  bestScoreE = score;
            }
        }
        else if(difficulty == 4) {
            if(score < bestScoreM) {
                  bestScoreM = score;
            }
        }
        else { 
            if(score < bestScoreH) {
                  bestScoreM = score;
            }
        }
        results(difficulty, score, bestScoreE, bestScoreM, bestScoreH);
        playAgain = 0;
        while(playAgain < 1 || playAgain > 2) {
         try {
               System.out.print("Would you like to play again? 1 = yes, 2 = no.\t");
               playAgain = input.nextInt();
        }
        catch(InputMismatchException e)  { String issue = input.nextLine();}
               if(playAgain < 1 || playAgain > 2) {
                  System.out.println("Please choose a valid response.");
               }
        } // end while play again
        } // end while
        System.out.println("\nThanks for playing!");
    } // end of main   

    // Game instructions
    public static void instructions() {
        System.out.println("HOW TO PLAY TOWERS OF HANOI:");
        System.out.println("You will start with three towers. The first tower has three rings on it, ordered 1, 2 3.");
        System.out.print("The goal of the game, in the fewest possible moves, is to move all of the rings from the first tower to the third tower without ");
        System.out.println("ever placing a larger ring ontop of a smaller ring (ex: placing ring 3 ontop of ring 2 in a tower).");
        System.out.println("Each successful move you make the game will keep score, so good luck!\n");
    } // end of instructions
    
    // current ring pacement
    public static void towerView(int difficulty) {
        System.out.println("\n TOWER OF HANOI:\n");
        
            for(int i = 0; i < (difficulty + 1); i++) {
            if(towerOne.contains(i)) {
               System.out.print(" " + i);
            }
            else { System.out.print(" |"); }
            
            if(towerTwo.contains(i)) {
               System.out.print("      " + i);
            }
            else { System.out.print("      |"); }
            
             if(towerThree.contains(i)) {
               System.out.println("      " + i + " ");
            }
            else { System.out.println("      | "); }
            }
        
            System.out.println(" |      |      | ");
            System.out.println("= =    = =    = =");
        
            System.out.println();
    } // end tower View

    // method for game results
    public static void results(int difficulty, int score, int bestScoreE, int bestScoreM, int bestScoreH) {
        if(difficulty == 3) { // results for easy
            System.out.println("It took you " + score + " moves to complete the puzzle this round on easy difficulty.");
            System.out.println("Your best score is " + bestScoreE + ". Good job!");
            System.out.println("The fewest possible moves on easy difficulty is 7.\n");
        }
        else if(difficulty == 4) { // results for medium
            System.out.println("It took you " + score + " moves to complete the puzzle this round on medium difficulty.");
            System.out.println("Your best score is " + bestScoreM + ". Good job!");
            System.out.println("The fewest possible moves for medium difficulty is 15.\n");
        }
        else { // results for hard
            System.out.println("It took you " + score + " moves to complete the puzzle this round on hard difficulty.");
            System.out.println("Your best score is " + bestScoreH + ". Good job!");
            System.out.println("The fewest possible moves for hard difficulty is 31.\n");
        }
    } // end results

} // end of tower class

//tower object
class Tower {
private String name;
private ArrayList<Integer> rings = new ArrayList<Integer>();

    // constructor for tower object
    public Tower(String name, int ringNumber) {
        this.name = name; // sets the name of the tower

        for(int i = ringNumber; i > 0; i--) { // fills the tower with starting rings (ex: 3, 2, 1)
            rings.add(i);
        }
    } // end of constructor

    // method to determine if there are any rings on this tower
    public boolean isEmpty() {
        if(rings.isEmpty()) { return true; }
        else {return false; }
    } // end of isempty

    // method to return tower name
    public String name() {
        return this.name;
    } // end tostring
    
    // method to determine if an index exists
    public boolean contains(int number) {
        if(rings.contains(number)) { return true; }
        else { return false; }
    } // end contains
    
    // method to print top ring of tower
    public int topRing() {
        return rings.get(rings.size() - 1);
    } // end topRing

    // method to add ring to tower
    public void addRing(int ring) {
        rings.add(ring);
    } // end addRing

    //method to remove ring from tower
    public int removeRing() {
        return rings.remove(rings.size() - 1);
    }
} // end tower object