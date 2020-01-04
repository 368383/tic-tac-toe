package tictaktoe;

import bankPart2.Utility;
import projectX.tickTacToe.Display;

public class TikTakToeLaunch {
	public static int round = 1;
	public static boolean breakCycle = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game play = new Game();
		play.set();
		if (1 == 1) {
			System.out.println("Human Input");
			humanPlay(play);
		} else {
			System.out.println("Computer Input");
			computerPlay(play);
		}

	}

	public static int random() {
		int alpha = (int) (Math.random() * 2 + 1);
		System.out.println(alpha);
		return alpha;
	}

	public static void humanPlay(Game play) {
		System.out.println(
				"To choose slot, insert value from 1 to 9 when prompted | To input, insert either X or an O (not case sensitive)");
		String input = null;
		int slot = 0;
		while (breakCycle) {
			// Display.print();
			if (humanInput(play, round) == true) {
				System.out.println("Program Completion");
				break;
			}

		}
	}

	public static void computerPlay(Game play) {
		System.out.println(
				"To choose slot, insert value from 1 to 9 when prompted | To input, insert either X or an O (not case sensitive)");

		String input = null;
		int slot = 0;
		while (breakCycle) {
			System.out.println("=========NEXT ROUND=======");
			// Display.print();
			if (computerInput(play, round) == true) {
				System.out.println("Program Completion");
				break;
			}
		}

	}

	private static boolean humanInput(Game play, int round) {
		Display.print();
		String input = play.setInput();
		int slot = play.setSlot();
		play.input(slot, input);
		Display.print();

		System.out.println("COMPUTERS TURN");
		if (play.determineSlots()) {
			return true;
		}
		play.calculation();
		return false;
	}

	private static boolean computerInput(Game play, int round) {
		System.out.println("COMPUTERS TURN");
		play.computerInput = "X";
		if (play.determineSlots()) {
			return true;
		}
		play.calculation();
		Display.print();
		String input = play.setInput();
		int slot = play.setSlot();
		play.input(slot, input);
		Display.print();
		return false;
	}

}
