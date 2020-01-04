package projectX.tickTacToe;

import java.util.Scanner;

public class Game {
	public static int[] cpuInputsArray = new int[9];
	public static int[] userInputsArray = new int[9];
	public static String[] marks = new String[9];
	public static String cpuInput = "";
	public static String userInput = "";

	public static boolean calculation() {
		// readAllSlots();

		if (isOccupied(4) == false) {
			marks[4] = cpuInput;
			debug("DEFAULT");
			return false;
		}
		System.out.println("MOVING ON TO ASSAULT");
		if (generalAlg(cpuInputsArray) == false) {
			debug("ASSAULT");
			return false;
		}
		System.out.println("MOVING ON TO DEFEND");
		if (generalAlg(userInputsArray) == false) {
			debug("DEFEND");
			return false;
		}
		System.out.println("MOVING ON TO CORNERS");
		if (corners()) {
			return false;
		}
		System.out.println("MOVING ON TO FILL IN");
		fillIn();
		return true;
	}

	private static void fillIn() {

		for (int i = 0; i < 9; i++) {
			if (isOccupied(i) == false) {
				marks[i] = cpuInput;
				break;
			}
		}
	}

	private static boolean corners() {
		for (int i = 0; i < 3; i++) {
			// VERTICAL
			if (isOccupied(i) == false && isOccupied(i + 6) == false && marks[i + 3].equals(cpuInput)) {
				debug("VERTICAL");
				marks[i] = cpuInput;
				return true;
			}
			// HORRIZONTAL
			if (isOccupied(i * 3 + 1) == true && isOccupied(i * 3 + 2) == false && isOccupied(i * 3) == false
					&& marks[i * 3 + 1].equals(cpuInput)) {
				debug("HORRIZONTAL");
				marks[i * 3] = cpuInput;
				return true;
			}
		}
		int[] cornerList = { 0, 2, 4, 8 };
		for (int corner : cornerList) {
			if (isOccupied(corner) == false) {
				marks[corner] = cpuInput;
				return true;
			}
		}
		return false;
	}

	private static boolean generalAlg(int[] array) {
		for (int baseIndex = 0; baseIndex < 9; baseIndex++) {
			int base = array[baseIndex];
			if (base == 1000) {
				continue;
			} else {
				for (int testIndex = baseIndex + 1; testIndex < 9; testIndex++) {
					int test = array[testIndex];
					int difference = test - base;
					System.out.println("BASE VALUE " + base + "\t TEST VALUE " + test + "\t DIFFERENCE " + difference);

					// OPTION ONE - SLOTS ARE NEXT TO EACH OTHER (HORRIZONTAL)
					if (test % 3 != 0 && difference == 1) {
						debug("OPTION ONE");
						if (base % 3 == 0 && isOccupied(test + 1) == false) {
							marks[test + 1] = cpuInput;
							debug("OPTION A");
							return false;
						}
						if (base > 0 && isOccupied(base - 1) == false) {
							// System.out.println("OCCUPATION " + "\ttest value " + test + "\t" + marks[test
							// + 1] + " ||");
							marks[base - 1] = cpuInput;
							debug("OPTION B");
							return false;
						}
					}

					// OPTION TWO A - SAME ROW (HORRIZONTAL)
					if (difference == 2 && base % 3 == 0) {
						if (isOccupied(test) == true && isOccupied(base) == true && isOccupied(test - 1) == false) {
							debug("OPTION TWO A - SAME ROW");
							marks[test - 1] = cpuInput;
							return false;
						}
					}

					// OPTION THREE (VERTICAL)
					if (difference == 3) {
						if (base >= 3 && isOccupied(base - 3) == false) {
							marks[base - 3] = cpuInput;
							debug("OPTION THREE A");
							return false;
						}
						if (test >= 3 && isOccupied(test + 3) == false) {
							marks[test + 3] = cpuInput;
							debug("OPTION THREE B");
							return false;
						}
					}

					// OPTION 6 (VERTICAL SAME ROW)
					if (difference == 6) {
						if (isOccupied(test) == true && isOccupied(base) == true && isOccupied(test - 3) == false) {
							debug("OPTION TWO A - SAME ROW");
							marks[test - 3] = cpuInput;
							return false;
						}
					}

					// OPTION 4 DIAGONAL (LEFT BOUND)
					if (difference == 4 && test % 4 == 0) {
						debug("OPTION FOUR");
						if (isOccupied(8) == false) {
							debug(" A");
							marks[8] = cpuInput;
							return false;
						}
						if (isOccupied(0) == false) {
							marks[0] = cpuInput;
							debug("B");
							return false;
						}
					}

					// OPTION 8 DIAGONAL (LEFT BOUND)
					if (difference == 8) {
						if (isOccupied(4) == false) {
							debug("OPTION 8");
							marks[4] = cpuInput;
							return false;
						}
					}
					// OPTION 2 B DIAGONAL (RIGHT BOUND)
					if (difference == 2 && test % 2 == 0) {
						debug("OPTION TWO");
						if (isOccupied(2) == false) {
							debug(" A");
							marks[2] = cpuInput;
							return false;
						}
						if (isOccupied(6) == false) {
							debug(" B");
							marks[6] = cpuInput;
							return false;
						}
					}

					// OPTION 4 DIAGONAL (RIGHT BOUND)
					if (difference == 4) {
						if (isOccupied(4) == false) {
							debug("OPTION 8");
							marks[4] = cpuInput;
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private static boolean isOccupied(int spot) {
		if (marks[spot].equals(" ")) {
			return false;
		} else {
			return true;
		}
	}

	public static void setCpuInput() {

		if (userInput.equals("O")) {
			cpuInput = "X";
		}
		cpuInput = "O";
		userInput = "X";
	}

	public static void intializeGrid() {
		for (int i = 0; i < marks.length; i++) {
			marks[i] = " ";
		}
	}

	public static String[] getMarks() {
		return marks;
	}

	public Game() {
		intializeGrid();
		setCpuInput();
	}

	public static void setSlot() {

		System.out.println("Insert location. Range of 1 to 9");
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				int slot = sc.nextInt() - 1;
				if (slot == 99) {
					System.out.println("DEBUG");
					readAllSlots();
					break;
				}
				if (slot >= 0 && slot <= 8 && isOccupied(slot) == false) {
					marks[slot] = userInput;
					break;
				} else {
					System.out.println("Range of input must be from 1 to 9 and cannot be occupied");
				}
			} catch (Exception e) {
				System.out.println("INVALID INPUT");
			}
		}
	}

	public static boolean ifStop() {

		if ((userInputsArray[8] - userInputsArray[0] == 8) && (marks[4].equals(userInput))) {
			System.out.println("HUMAN WON");
			return true;
		}
		if ((userInputsArray[6] - userInputsArray[2] == 4) && (marks[4].equals(userInput))) {
			System.out.println("HUMAN WON");
			return true;
		}
		if ((cpuInputsArray[8] - cpuInputsArray[0] == 8) && (marks[4].equals(cpuInput))) {
			System.out.println("CPU WON");
			return true;
		}
		if ((cpuInputsArray[6] - cpuInputsArray[2] == 4) && (marks[4].equals(cpuInput))) {
			System.out.println("CPU WON");
			return true;
		}
		boolean alpha = false;
		for (String currentMark : marks) {
			if (currentMark.equals(" ")) {
				alpha = false;
				break;
			} else {
				alpha = true;
			}

		}
		if (alpha == true) {
			System.out.println("ALL SLOTS FILLED DRAW");
			return true;
		}
		for (int index = 0; index < 3; index++) {
			if ((userInputsArray[6 + index] - userInputsArray[index] == 6) && (marks[3 + index].equals(userInput))) {
				System.out.println("HUMAN WON");
				return true;
			}
			if ((userInputsArray[8 - index * 3] - userInputsArray[6 - index * 3] == 2
					&& (marks[7 - index * 3].equals(userInput))) && (marks[7 - index * 3].equals(userInput))) {
				System.out.println("HUMAN WON");
				return true;
			}
			if ((cpuInputsArray[6 + index] - cpuInputsArray[index] == 6) && (marks[3 + index].equals(cpuInput))) {
				System.out.println("CPU WON");
				return true;
			}
			if ((cpuInputsArray[8 - index * 3] - cpuInputsArray[6 - index * 3] == 2
					&& (marks[7 - index * 3].equals(cpuInput)))) {
				System.out.println("CPU WON");
				return true;
			}
		}
		System.out.println("NO WIN");
		return false;
	}

	public static void determineSlots() {
		for (int index = 0; index < 9; index++) {

			if (marks[index].equals(cpuInput)) {
				cpuInputsArray[index] = index;
			} else {
				cpuInputsArray[index] = 1000;
			}
		}
		for (int index = 0; index < 9; index++) {
			if (marks[index].equals(userInput)) {
				userInputsArray[index] = index;
			} else {
				userInputsArray[index] = 1000;
			}
		}
		// readAllSlots();

	}

	private static void readAllSlots() {
		System.out.println("ARRAYS _____________________________________________________________________________");

		for (int i = 0; i < 9; i++) {
			System.out.println("INDEX " + i + "\tUSER INPUTS ARRAY " + userInputsArray[i] + "\t CPU INPUTS ARRAY "
					+ cpuInputsArray[i]);
		}
		System.out.println("____________________________________________________________________________________");
	}

	public static void debug(String message) {
		System.out.println("DEBUG MESSAGE: " + message);
	}
}
