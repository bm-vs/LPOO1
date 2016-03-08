package gamecli;

import java.util.Scanner;
import java.util.*;
import gamelogic.*;
import gamelogic.Game;

public class Ui {
	public static void main(String [] args) {
		Game game = new Game();
		Scanner sc = new Scanner(System.in);
		String key;
		String gameMode;
		
		while (true) {
			System.out.println("Insert game mode: ");
			System.out.println("move - 0; alternate sleep/move - 1; still - 2;");
			gameMode = sc.next();
			if (gameMode.equals("0") || gameMode.equals("1")  || gameMode.equals("2"))
				break;
		}
		
		
		printBoard(game);
		
		loop:
		while (true) {
			System.out.println("Move: ");
			
			do {
				key = sc.next();
			} while (!(key.equals("w") || key.equals("a") || key.equals("s") || key.equals("d")));
			
			switch(game.play(gameMode, key)) {
			case 1: printBoard(game);						// WIN
					System.out.println("The hero has won!");
					break loop;
			case 2: printBoard(game);						// LOSE
					System.out.println("The hero has died!");
					break loop;
			case 3: printBoard(game);						// DRAGON DIE
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
