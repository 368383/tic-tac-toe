package projectX.tickTacToe;

import java.util.Scanner;

public class TicTacToeLauncher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (random() == 1) {
			System.out.println("HUMAN TURN");
			while (true) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Would you like an X or O - not case sensitive");
				String input = sc.next().toUpperCase();
				if (input.equals("X") || input.equals("O")) {
					System.out.println("Chose " + input);
					Game.userInput = input;
					break;
				} else {
					System.out.println("Bad Input");
				}
			}

			Game.setCpuInput();
			Game alpha = new Game();
			Display.print();
			humanProcess(alpha);
		} else

		{

			System.out.println("COMPUTER TURN");
			Game alpha = new Game();
			cpuProcess(alpha);

		}
	}

	public static int random() {
		return (int) Math.random() * 2;
	}

	public static void humanProcess(Game play) {
		while (true) {
			Display.print();
			play.setSlot();
			play.determineSlots();
			if (play.ifStop()) {
				System.out.println("PROGRAM COMPLETION");
				break;
			}
			play.calculation();
			play.determineSlots();
			if (play.ifStop()) {
				Display.print();
				System.out.println("PROGRAM COMPLETION");
				break;
			}
		}
	}

	public static void cpuProcess(Game play) {
		while (true) {

			play.determineSlots();
			play.calculation();
			Display.print();
			play.determineSlots();
			if (play.ifStop()) {
				System.out.println("PROGRAM COMPLETION");
				break;
			}
			play.setSlot();
			if (play.ifStop()) {
				System.out.println("PROGRAM COMPLETION");
				break;
			}
		}
	}
}
