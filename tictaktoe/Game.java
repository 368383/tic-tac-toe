package tictaktoe;

import java.util.Scanner;

public class Game {
	public static String[] marksPrint = new String[9];
	public static int[] userSlots = new int[9];
	public static int[] cpuSlots = new int[9];
	public static boolean userInputIsX = true;
	public static String userInput = "";
	public static String computerInput = "";
	public static boolean userStatus = true;

	public static void set() {
		// printing blanks
		for (int i = 0; i < marksPrint.length; i++) {
			marksPrint[i] = " ";

		}
	}

	public Game() {
		set();
	}

	public void input(int slot, String value) {
		System.out.println("INPUTTING SLOT " + slot + " VALUE " + value);
		marksPrint[slot] = value;
	}

	public int setSlot() {

		while (true) {
			try {
				System.out.println("SELECT SPOT");
				Scanner hs = new Scanner(System.in);
				int slot = hs.nextInt() - 1;
				if (slot <= 8 && slot >= 0) {
					for (int currentLoc : userSlots) {
						if (isOccupied(slot)) {
							break;
						} else {
							return slot;
						}
					}
				}
				System.out.println("Insert an integer from 1 to 9");
			} catch (Exception e) {
				System.out.println("Insert an integer from 1 to 9");
			}
		}

	}

	public static boolean isStringOnlyAlphabet(String str) {
		return ((str != null) && (!str.equals("")) && (str.matches("^[a-zA-Z]*$")));
	}

	public String setInput() {

		if (userStatus) {
			while (userStatus) {
				try {
					System.out.println("SELECT INPUT");
					Scanner sc = new Scanner(System.in);
					String input = sc.next().toUpperCase();
					if (isStringOnlyAlphabet(input) && (input.equals("X") || input.equals("O"))) {
						if (input.equals(computerInput)) {
							System.out.println("Computer has chosen the value x, select O");
						} else {
							userStatus = false;

							System.out.println("The user inputed " + input);
							if (input.equals("X")) {
								System.out.println("HA Computer will use O");
								computerInput = "O";
								userInput = "X";
								userInputIsX = true;
							} else {
								System.out.println("HA Computer will use X");
								computerInput = "X";
								userInput = "O";
								userInputIsX = false;
							}
							return input;
						}
					}
					System.out.println("X or O");
				} catch (Exception E) {
					E.printStackTrace();
				}
			}
		}
		return userInput;
	}

	public static String[] getInputs() {

		return marksPrint;
	}

	public void calculation() {
		int userIndex = 0;
		boolean alpha = true;
		while (alpha) {
			int baseComparision = userSlots[userIndex];

			for (int comparisonIndex = userIndex + 1; comparisonIndex < userSlots.length; comparisonIndex++) {

				if (userSlots[comparisonIndex] == 1000) {
					continue;
				}
				int difference = userSlots[comparisonIndex] - baseComparision;
				comparisonIndex = userSlots[comparisonIndex];
				System.out.println("index " + userIndex + "\tUser baseComparison Value " + baseComparision + "\t : "
						+ "\t difference: " + difference + "\t User ComparisonIndex Value "
						+ userSlots[comparisonIndex]);

				// OPTION ONE - NEXT TO EACH OTHER
				if (difference == 1) {
					System.out.println("OPTION ONE");
					if ((comparisonIndex == 2 || comparisonIndex == 5 || comparisonIndex == 8)
							&& (!isOccupied(comparisonIndex + 1))) {
						System.out.print(" A");
						marksPrint[comparisonIndex + 1] = computerInput;
					} else {
						if (!isOccupied(comparisonIndex - 1)) {
							System.out.print(" B");
							marksPrint[comparisonIndex - 1] = computerInput;
						} else {
							// remaining slot
						}
					}
					alpha = false;
					break;
				}
				// OPTION TWO A - SAME ROW
				if ((difference == 2) && (baseComparision) % 3 == 0 && (!isOccupied(comparisonIndex - 1))) {
					System.out.println("OPTION TWO A");
					marksPrint[comparisonIndex - 1] = computerInput;
					alpha = false;
					break;
				}

				// OPTION TWO B - DIAGONALS RIGHT BOUND
				if ((difference == 2)) {
					System.out.println("OPTION TWO ");
					System.out.println("COMPARISON INDEX " + comparisonIndex + "\t" + isOccupied(7));
					if (comparisonIndex == 4 && isOccupied(6) == false) {
						System.out.print(" A");
						marksPrint[comparisonIndex + 2] = computerInput;
						alpha = false;
						break;
					} else {
						if (isOccupied(4) == false) {
							System.out.print(" B");
							marksPrint[4] = computerInput;
							alpha = false;
							break;
						}
					}

				}

				// OPTION THREE VERTICAL NEXT
				if (difference == 3) {
					System.out.println("OPTION THREE");
					if ((comparisonIndex == 3 || comparisonIndex == 4 || comparisonIndex == 5)
							&& !isOccupied(comparisonIndex + 3)) {
						System.out.print(" A");
						marksPrint[comparisonIndex + 3] = computerInput;
					} else {
						if (!isOccupied(comparisonIndex - 6)) {
							System.out.print(" B");
							marksPrint[comparisonIndex - 6] = computerInput;
						}
					}
					alpha = false;
					break;
				}
				// OPTION FOUR LEFT BOUND DIAGONALS
				if ((difference == 4)) {
					System.out.println("OPTION 4");
					if (comparisonIndex == 5 && !isOccupied(comparisonIndex)) {
						System.out.print(" A");
						marksPrint[comparisonIndex + 4] = computerInput;
					} else {
						if (!isOccupied(comparisonIndex)) {
							System.out.print(" B");
							marksPrint[comparisonIndex - 8] = computerInput;
						}
					}
					alpha = false;
					break;
				}
				// OPTION SIX VERTICAL
				if (difference == 6 && !isOccupied(comparisonIndex - 3)) {
					System.out.println("OPTION SIX");
					marksPrint[comparisonIndex - 3] = computerInput;
					alpha = false;
					break;
				}

			}
			userIndex++;
			if (userIndex == marksPrint.length) {
				break;
			}
		}
		System.out.println(" \n UNABLE TO LOGICALLY PROCES " + alpha);
		// COMPUTER DEFAULT FIRST OPTION
		if (marksPrint[4].equals(" ")) {
			System.out.println("OPTION BEGIN");
			marksPrint[4] = computerInput;
			alpha = false;
		}
		while (alpha) {
			alpha = cornerMethod(alpha);
			if (alpha == true) {
				alpha = randomFill(alpha);
			}
		}

	}

	private boolean cornerMethod(boolean alpha) {
		System.out.println("Corner selection");
		int[] corners = { 0, 2, 6, 8 };
		for (int random = 0; random < corners.length; random++) {
			if (isOccupied(corners[random]) == true) {
				System.out.println("OCCUPIED");
			} else {
				System.out.println(corners[random] + "\tINPUT");
				marksPrint[corners[random]] = computerInput;
				alpha = false;
				break;
			}
		}
		return alpha;
	}

	private boolean randomFill(boolean alpha) {
		System.out.println("UNABLE TO SYSTEMATICALLY FILL");
		for (int i = 0; i < 9; i++) {
			if (isOccupied(i) == false) {
				System.out.println("THIS INDEX " + i);
				marksPrint[i] = computerInput;
				alpha = false;
				break;
			}

		}
		return alpha;
	}

	public static boolean win() {
		for (int i = 0; i < 2; i++) {
			if (userSlots[8 - i] - userSlots[5 - i] == 3
					&& userSlots[8 - i] - userSlots[5 - i] == userSlots[5 - i] - userSlots[2 - i]) {
				System.out.println("HUMAN WON VERTICALLY");
				return true;
			}
			if (userSlots[8 - i * 3] - userSlots[7 - i * 3] == 1
					&& userSlots[8 - i * 3] - userSlots[7 - i * 3] == userSlots[7 - i * 3] - userSlots[6 - i * 3]) {
				System.out.println("HUMAN WON HORRIZONTALLY");
				return true;
			}
			if (userSlots[8] - userSlots[4] == 4 && userSlots[8] - userSlots[4] == userSlots[4] - userSlots[0]) {
				System.out.println("HUMAN WON DIAGONALLY LEFT BOUND");
				return true;
			}
			if (userSlots[6] - userSlots[4] == 2 && userSlots[6] - userSlots[4] == userSlots[4] - userSlots[2]) {
				System.out.println("HUMAN WON DIAGONALLY RIGHT BOUND");
				return true;
			}
			if (cpuSlots[8 - i] - cpuSlots[5 - i] == 3
					&& cpuSlots[8 - i] - cpuSlots[5 - i] == cpuSlots[5 - i] - cpuSlots[2 - i]) {
				System.out.println("COMPUTER WON VERTICALLY");
				return true;
			}
			if (cpuSlots[8 - i * 3] - cpuSlots[7 - i * 3] == 1
					&& cpuSlots[8 - i * 3] - cpuSlots[7 - i * 3] == cpuSlots[7 - i * 3] - cpuSlots[6 - i * 3]) {
				System.out.println("COMPUTER WON HORRIZONTALLY");
				return true;
			}
			if (cpuSlots[8] - cpuSlots[4] == 4 && cpuSlots[8] - cpuSlots[4] == cpuSlots[4] - cpuSlots[0]) {
				System.out.println("COMPUTER WON DIAGONALLY LEFT BOUND");
				return true;
			}
			if (cpuSlots[6] - cpuSlots[4] == 2 && cpuSlots[6] - cpuSlots[4] == cpuSlots[4] - cpuSlots[2]) {
				System.out.println("COMPUTER WON DIAGONALLY RIGHT BOUND");
				return true;
			}
		}
		return false;
	}

	public static boolean isOccupied(int testSlot) {

		if (marksPrint[testSlot].equals(" ")) {
			// System.out.println("NOT OCCUPIED");
			return false;
		}
		// System.out.println("OCCUPIED");
		return true;
	}

	public static double random() {
		return (Math.random() * 9);
	}

	public boolean determineSlots() {
		if (win() == true) {
			return true;
		} else {
			if (userInputIsX) {
				System.out.println("DEBUG | COMPUTER WILL CHOOSE O");
				int i = 0;
				for (String current : marksPrint) {
					if (current.equals("X")) {
						System.out.println("X DETECTED @ " + i);
						userSlots[i] = i;
						cpuSlots[i] = 1000;
						// computerInput = "O";
					} else {
						if (current.equals("O")) {
							cpuSlots[i] = i;
						} else {
							userSlots[i] = 1000;
						}
					}
					i++;
				}
			} else {
				System.out.println("DEBUG | COMPUTER WILL CHOOSE X");
				int i = 1;
				for (String current : marksPrint) {
					if (current.equals("O")) {
						System.out.println("O DETECTED @ " + i);
						userSlots[i - 1] = i;
					} else {
						if (current.equals("X")) {
							cpuSlots[i - 1] = i;
						}
					}
					i++;

				}
			}
			return false;
		}

	}

	private void readLocations() {
		System.out.println("LOCATIONS DISPLAY");
		for (int current : userSlots) {
			System.out.print("\t DEBUG slots" + current);
		}
	}

	private void readMarks() {
		for (String current : marksPrint) {
			System.out.print("\t DEBUG slots" + current);
		}
	}

}
