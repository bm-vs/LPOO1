package gamecli;

import java.util.Scanner;
import java.util.*;
import gamelogic.*;
import gamelogic.Game;

public class Ui {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		String key;
		String dragon_type;
		int maze_size;
		int number_dragons;
		
		// Get maze size
		// Min: 5
		// Max: 128
		while (true) {
			System.out.print("Maze size (5-128): ");
			maze_size = sc.nextInt();
			if (maze_size >= 5 && maze_size <= 128) {
				break;
			}
			else {
				System.out.println("Invalid size!");
			}
		}
		
		// Get number of dragons
		// Min: 1
		// Max: 2 for every 10x10 square
		while (true) {
			System.out.print("Number of dragons: ");
			number_dragons = sc.nextInt();
			if (maze_size * maze_size < 50.0 && number_dragons == 1) {
				break;
			}
			else if (number_dragons <= maze_size * maze_size / 50.0 && number_dragons > 0) {
				break;
			}
			else {
				System.out.println("Invalid number of dragons!");
			}
		}
		
		
		// Get dragon behavior
		while (true) {
			System.out.println("Dragon behavior: ");
			System.out.println("0 - Dragon moves");
			System.out.println("1 - Dragon moves & sleeps");
			System.out.println("2 - Dragon doesn't move");
			dragon_type = sc.next();
			if (dragon_type.equals("0") || dragon_type.equals("1")  || dragon_type.equals("2"))
				break;
		}
		
		Game game = new Game(maze_size, number_dragons);
		printBoard(game);
		
		loop:
		while (true) {
			System.out.println("Move: ");
			
			do {
				key = sc.next();
			} while (!(key.equals("w") || key.equals("a") || key.equals("s") || key.equals("d")));
			
			switch(game.play(dragon_type, key)) {
			case 1: printBoard(game);						// WIN
					System.out.println("The hero has won!");
					break loop;
			case 2: printBoard(game);						// LOSE
					System.out.println("The hero has died!");
					break loop;
			case 3: printBoard(game);						// DRAGON DIES
					System.out.println("The dragon has died!");
					break;
			}
			
			printBoard(game);
		}
		
		sc.close();
	}
	
	public static void printBoard(Game game) {
		System.out.print(game.return_board());
	}
}
