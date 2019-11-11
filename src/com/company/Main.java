package com.company;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*
    Created by Margie Ruffin
    Due Sunday, September 15, 2019
    Turtle Graphics Code

 */


public class Main {

    public enum Directions{
        East,
        West,
        North,
        South

    }

    public static void main(String[] args) {
	    /*
	    Create a 20 x 20 array floor initialized all to 0
	    Keep track of the current position of the turtle at all times and whether the pen is currently
	    up or down.
	    Assume that the turtle always starts at position (0, 0) of the floor with its pen up.
	     */


	    /* Extra functions that the turtle can do.
	        1. make the turtle turn around and go the opposite way


	     */

	    /* Try these combinations for a fun drawing!
	        1. 1 5,5 2 5,10 3 5,2 3 5,10 4 5,2 4 5,10 3 5,2 3 5,10 4 5,2 4 5,10 3 5,2 3 5,10 4 5,2 4 5,10 3 5,2 6 9
	        2. 1 5,5 3 5,5 4 2 5,10 3 5,10 3 5,10 3 5,10 1 6 9
	        3. 1 5,5 2 3 5,10 11 5,5 3 5,6 4 5,5 11 5,11 6 9
	        4. 2 3 5,4 11 5,4 3 5,7 3 5,16 3 5,7 11 5,14 6 9
	     */



	    //initialize the board
        char [][] board = new char [20][20];
        restartBoard(board);


        //create the list and scanner for user input
        List userInput = new ArrayList();
        Scanner scanUser = new Scanner(System.in);



        //set variables here
        String input = "";

        int positionX = 0;
        int positionY = 0;


        boolean penUp = true;
        boolean penDown = false;

        int spacesMoved = 0;
        Directions dir = Directions.East;





        //program should prompt the user for what it is they want to do.
        System.out.println();
        System.out.println("Welcome to Turtle World, let's make a drawing together!");
        System.out.println();
        System.out.println("This is your canvas below. Enter numbers followed by spaces to " +
                        "create and display your graphic");
        System.out.println();





        //display the blank board
        displayZeroBoard(board);
        System.out.println();


        //display the menu
        displayMenu();

        //take in the string of user input and then split by the spaces
        input = scanUser.nextLine();
        System.out.println();
        System.out.println();



        //split the string by spaces and add them to the userInput list
        String [] split = input.split(" ");
        for(int i = 0; i < split.length; i++){
            userInput.add(split[i]);
        }


        //iterate through the user list
       for(int i = 0; i < userInput.size(); i++) {


           String choice = (String) userInput.get(i);
               //go through the list if it starts with 5 split it take the second half for number of spaces to move

               if (choice.startsWith("5")) {
                   String[] split2 = choice.split(",");
                   String temp = split2[1];
                   spacesMoved = Integer.parseInt(temp);

                   choice = "5";

               }


               switch (choice) {


                   //pen up
                   case "1":
                       //System.out.println("penUp");
                       penUp = true;
                       penDown = false;
                       break;

                   //pen down
                   case "2":
                       //System.out.println("penDown");
                       penDown = true;
                       penUp = false;
                       break;

                   //turn right
                   case "3":
                       //System.out.println("turn right");
                       if (dir == Directions.North) {
                           dir = Directions.East;
                       } else if (dir == Directions.South) {
                           dir = Directions.West;
                       } else if (dir == Directions.East) {
                           dir = Directions.South;
                       } else
                           dir = Directions.North;

                       break;


                   //turn left
                   case "4":
                       //System.out.println("turn left");
                       if (dir == Directions.North) {
                           dir = Directions.West;
                       } else if (dir == Directions.South) {
                           dir = Directions.East;
                       } else if (dir == Directions.East) {
                           dir = Directions.North;
                       } else
                           dir = Directions.South;

                       break;

                   //make the turtle move
                   case "5":
                       //System.out.println("Go X steps forward");
                       Pair<Integer, Integer> pair = moveForward(spacesMoved, penUp, penDown, board, positionX, positionY, dir);
                       positionX = pair.getKey();
                       positionY = pair.getValue();
                       break;


                   //These are our special cases for extra creativity
                   case "11":

                       //System.out.println("Turn around, About Face!");
                       if (dir == Directions.North) {
                           dir = Directions.South;
                       } else if (dir == Directions.South) {
                           dir = Directions.North;
                       } else if (dir == Directions.East) {
                           dir = Directions.West;
                       } else
                           dir = Directions.East;

                    break;



                   //Print the board
                   case "6":
                       displayFinalBoard(board);
                       break;


                   //exit the program
                   case "9":
                       System.exit(0);

               }


        }

    }


    //this function moves the turtle forward
    private static Pair<Integer, Integer> moveForward(int spacesMoved, boolean penUp, boolean penDown, char[][] board, int positionX, int positionY, Directions dir){

        //move the turtle and draw if the pen is down
        if(penDown) {
            if (dir == Directions.North && positionX - spacesMoved >= 0) {
                //decrease the x values
                int i = 0;
                while (i < spacesMoved ) {
                    board[positionX][positionY] = '1';
                    positionX--;
                    i++;

                }

            }
            if (dir == Directions.South && spacesMoved + positionX < 19) {
                //increase the x values
                int i = 0;
                while (i < spacesMoved ) {
                    board[positionX][positionY] = '1';
                    positionX++;
                    i++;
                }


            }
            if (dir == Directions.East && spacesMoved + positionY < 20 ) {
                //increase the y values
                int i = 0;
                while (i < spacesMoved ) {
                    board[positionX][positionY] = '1';
                    positionY++;
                    i++;
                }

            }
            if (dir == Directions.West && positionY - spacesMoved >= 0) {
                //decrease the y values
                int i = 0;
                while (i < spacesMoved ) {
                    board[positionX][positionY] = '1';
                    positionY--;
                    i++;
                }

            }

        }
        //move the turtle if the pen is up
        else if(penUp){
            if (dir == Directions.North && positionX - spacesMoved >= 0) {
                //decrease the x values
                int i = 0;
                while (i < spacesMoved ) {
                    board[positionX][positionY] = '0';
                    positionX--;
                    i++;

                }

            }
            if (dir == Directions.South && spacesMoved + positionY < 20) {
                //increase the x values
                int i = 0;
                while (i < spacesMoved ) {
                    board[positionX][positionY] = '0';
                    positionX++;
                    i++;
                }


            }
            if (dir == Directions.East && spacesMoved + positionY < 20) {
                //increase the y values
                int i = 0;
                while (i < spacesMoved ) {
                    board[positionX][positionY] = '0';
                    positionY++;
                    i++;
                }

            }
            if (dir == Directions.West && positionY - spacesMoved >= 0) {
                //decrease the y values
                int i = 0;
                while (i < spacesMoved ) {
                    board[positionX][positionY] = '0';
                    positionY--;
                    i++;
                }

            }
        }
        return new Pair<Integer, Integer>(positionX, positionY);
    }


    //display the board
    private static void displayMenu(){
        System.out.println("Enter a combination of the following choices to craft a masterpiece ");
        System.out.println("1: Pen up");
        System.out.println("2: Pen down");
        System.out.println("3: Turn right");
        System.out.println("4: Turn left");
        System.out.println("5: Go X steps forward, Ex. 5,3");


        System.out.println("11: Turn around, about face!");

        System.out.println("6: Display the board");
        System.out.println("9: End");
        System.out.println();
        System.out.println("Here's an example program " +
                "1 5,5 3 5,5 4 2 5,10 3 5,10 3 5,10 3 5,10 1 6 9");
        System.out.println();


    }


    //fill the board with 0's
    private static void restartBoard(char [][] board){
        //fill the board with 0's
        for (int i = 0; i < 20; i++) {
            for(int m = 0; m < 20; m++){
                board[i][m] = '0';
            }
        }

    }
    //function to display the board
    private static void displayZeroBoard(char[][] board){

        //display the board
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();

        }

    }

    //display the final board with *'s
    private static void displayFinalBoard(char [][] board){
        //display the board
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++){

                if(board[i][j] == '0'){
                    board[i][j] = ' ';
                }
                else if(board[i][j] == '1') {
                    board[i][j] = '*';
                }

                System.out.print(board[i][j]);
            }
            System.out.println();

        }

    }


}
