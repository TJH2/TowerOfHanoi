#Programmers: Ben Diskin, David Rukashaza-Hancock, T. Jake Holmes, Jaspreet Khatkar
#CS 145: Face-To-Face
#Date: 2/15/2023
#Assignment: Tower Of Hanoi
#Reference: Chapter 12
#Purpose: Uses recursion to solve the Tower of Hanoi puzzle and print out towers for viewing
#Extra Credit: created tower art for displaying each movie. see view() method

#List = ArrayList 
t1 = [];
t2 = [];
t3 = [];

rings = 4;

#function to print towers
def view():
    for number in range(5):
            if number in t1:
               print(" ", end="");
               print(number, end="")
               print("     ", end="")
            else:
                print(" |     ", end="")

            if number in t2:
               print(" ", end="")
               print(number, end="")
               print("     ", end="")
            else:
                print(" |     ", end="")

            if number in t3:
               print(" ", end="")
               print(number)
            else:
                print(" | ")

    print(" |      |      | ");
    print("= =    = =    = =");
    print("\n");
# end  view function

#Recursive function
def moveRing(rings, start, temp, end, tower1, tower2, tower3):

        #BASE CASE
        if rings == 1:
            index = len(tower1) - 1;
            print(start + " TO " + end + ":");
            tower3.append(tower1[index]);
            tower1.pop(index);
            view();

        #RECURSIVE CASE
        else:
            index = len(tower1) - rings;
            moveRing(rings - 1, start, end, temp, tower1, tower3, tower2); # function call (n = 2 temp = c end = b) (n = 1 temp = b end = c)
            print(start + " TO " + end + ":");
            tower3.append(tower1[index]);
            tower1.pop(index);
            view();
            moveRing(rings - 1, temp, start, end, tower2, tower1, tower3);

#for loop to fill first tower
for numbers in reversed(range(rings + 1)):
    t1.append(numbers);

t1.remove(0); # removes extra 0 from list

#Main program
print("STARTING POSITION:");
view();
moveRing(rings, "TOWER 1", "TOWER 2", "TOWER 3", t1, t2, t3);


