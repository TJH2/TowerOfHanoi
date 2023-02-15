// Programmers: Ben Diskin, David Rukashaza-Hancock, T. Jake Holmes, Jaspreet Khatkar
// CS 145: Face-To-Face
// Date: 2/15/2023
// Assignment: Tower Of Hanoi
// Reference: Chapter 12
// Purpose: Uses recursion to solve the Tower of Hanoi puzzle and print out towers for viewing
// Extra Credit: created tower art for displaying each movie. see viewTower() method

import java.util.*;

public class Lab5TowerOfHanoi {

    private static ArrayList<Integer> towerOne = new ArrayList<Integer>();
    private static ArrayList<Integer> towerTwo = new ArrayList<Integer>();
    private static ArrayList<Integer> towerThree = new ArrayList<Integer>();

    public static void main(String[] args) {

        int rings = 4; // amount of rings

        for(int i = rings; i > 0; i--) { // fills initial tower
            towerOne.add(i);
        }

        System.out.println("STARTING POSITION:");
        towerView();

        moveRing(rings, "TOWER 1", "TOWER 2", "TOWER 3", towerOne, towerTwo, towerThree);
        
    } // end of main

    // move method
    public static void moveRing(int rings, String start, String temp, String end, ArrayList tower1, ArrayList tower2, ArrayList tower3) {

        // BASE CASE
        if(rings == 1) {
            int index = tower1.size() - 1;
            System.out.println(start + " TO " + end + ":");
            tower3.add(tower1.get(index));
            tower1.remove(index);
            towerView();
        }

        // RECURSIVE CASE
        else {
                int index = tower1.size() - rings;
                moveRing(rings - 1, start, end, temp, tower1, tower3, tower2); // function call (n = 2 temp = c end = b) (n = 1 temp = b end = c)
                System.out.println(start + " TO " + end + ":");
                tower3.add(tower1.get(index)); // issue
                tower1.remove(index);
                towerView();
                moveRing(rings - 1, temp, start, end, tower2, tower1, tower3);
        }
    } // end of move

    //Method for creating tower visuals
    public static void towerView() {
        for(int i = 0; i < (5); i++) {
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
    }
} // end of class