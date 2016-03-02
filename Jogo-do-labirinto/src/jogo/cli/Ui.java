package jogo.cli;

import java.util.Scanner;
import java.util.*;
import jogo.logic.*;

public class Ui {
	public static void main(String [] args) {
		Jogo jogo = new Jogo();
		Scanner sc = new Scanner(System.in);
		String tecla;
		String modoJogo;
		
		while (true) {
			System.out.println("insira o modo de jogo");
			System.out.println("movimento-0; alternado dormir/andar-1; parado-2;");
			modoJogo = sc.next();
			if (modoJogo.equals("0") || modoJogo.equals("1")  || modoJogo.equals("2"))
				break;
		}
		
		
		printBoard(jogo);
		
		loop:
		while (true) {
			System.out.println("Move: ");
			
			do {
				tecla = sc.next();
			} while (!(tecla.equals("w") || tecla.equals("a") || tecla.equals("s") || tecla.equals("d")));
			
			switch(jogo.play(modoJogo, tecla)) {
			case 1: printBoard(jogo);						// WIN
					System.out.println("O heroi venceu!");
					break loop;
			case 2: printBoard(jogo);						// LOSE
					System.out.println("O heroi morreu!");
					break loop;
			case 3: printBoard(jogo);						// DRAGON DIE
					System.out.println("O dragao morreu!");
					break;
			}
			
			printBoard(jogo);
		}
		
		sc.close();
	}
	
	public static void printBoard(Jogo jogo) {
		System.out.print(jogo.return_board());
	}
}
