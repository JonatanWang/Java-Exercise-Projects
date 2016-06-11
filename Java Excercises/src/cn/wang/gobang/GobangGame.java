package cn.wang.gobang;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * Gobang game class
 * @author wang
 *
 */
public class GobangGame {
	// Define the term on which to win the game
	private final int WIN_COUNT = 5;
	// Define the x-coordinate input by user
	private int posX = 0;
	// Define the y-coordinate input by user
	private int posY = 0;
	// Define the chess board
	private ChessBoard chessboard;
	/**
	 * Default constructor
	 */
	public GobangGame() {
}
	/**
	 * Constructor
	 * Initialize chess board and chess
	 * @param chessboard
	 */
	public GobangGame(ChessBoard chessboard) {
		this.chessboard = chessboard;
	}
	
	/**
	 * Check if the input is valid
	 * @param inputStr The strings input via console
	 * @return true if strings are valid, otherwise false
	 */
	public boolean isValid(String inputStr) {
		// Split input string into 2 parts, separated by a blank space
		String[] posStrArr = inputStr.split(" ");
		try {
			// In the console, the first position is (1,1) according to human habit/tradition
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("Please input x, y-coordinaters in format(number number): ");
			return false;
		}
		// Check if the input is within the right range
		if (posX < 0 || posX >= ChessBoard.BOARD_SIZE || posY < 0 || posY >= ChessBoard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.printf("X, Y-coordinater must be within range [1, " + ChessBoard.BOARD_SIZE + "], please re-enter: ");
			System.out.println("There is already a chess here. Please input at another position: ");
			return false;
		}
		// Check if the input position is occupied
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "十") {
			chessboard.printBoard();
			System.out.println("The position is taken, please re-enter: ");
			return false;
		}
		return true;
	}
	/**
	 * Judge win-lose
	 * @param posX	x-coordinate of chess
	 * @param posY  y-coordinate of chess
	 * @param pattern the pattern of chess
	 * @return ture if there is a line of 5 chess, otherwise false
	 */
	public boolean isWon(int posX, int posY, String pattern) {
		// x, y-coordinate at the start point of the board
		int startX = 0;
		int startY = 0;
		// x, y-coordinate at the end point of the board
		int endX = ChessBoard.BOARD_SIZE -1;
		int endY = endX;
		// The number of adjacent chess on the same line
		int sameCount = 0;
		int temp = 0;
		
		// Calculate the minimal posX and minimal posY at the start point
		temp = posX - WIN_COUNT + 1;
		startX = temp < 0 ? 0 : temp;
		temp = posY - WIN_COUNT + 1;
		startY = temp < 0 ? 0 : temp;
		// Calculate the maximal posX and maximal posY at the end point
		temp = posX + WIN_COUNT - 1;
		endX = temp > ChessBoard.BOARD_SIZE - 1 ? ChessBoard.BOARD_SIZE - 1 : temp;
		temp = posY + WIN_COUNT - 1;
		endY = temp > ChessBoard.BOARD_SIZE - 1 ? ChessBoard.BOARD_SIZE - 1 : temp;
		// Calculate the numbers of adjacent chess from left to right
		String[][] board = chessboard.getBoard();
		for (int i = startY; i < endY; i ++) {
			if (board[posX][i] == pattern && board[posX][i + 1]== pattern) {
				sameCount ++;
			} else if (sameCount != WIN_COUNT - 1) {
				sameCount = 0;
			}
		}
		if (sameCount == 0) {
			// Calculate the adjacent chess from top to down
			for (int i = startX; i < endX; i ++) {
				if (board[i][posY] == pattern && board[i + 1][posY] == pattern) {
					sameCount ++;
				} else if (sameCount != WIN_COUNT - 1) {
					sameCount = 0;
				}
			}
		}
		if (sameCount == 0) {
			// Calculate the number of adjacent chess from left-up to right-down
			int j = startY;
			for (int i = startX; i < endX; i ++) {
				if (j < endY) {
					if (board[i][j] == pattern && board[i + 1][j + 1] == pattern) {
						sameCount ++;
					} else if (sameCount != WIN_COUNT - 1) {
						sameCount = 0;
					}
					j ++;
				}
			}
		}
		if(sameCount == 0) {
			// Calculate the number of adjacent chess pieces left-down to right-up
			int i = startX;
			for (int j = startY; j > endY; j --) {
				if (i < endX) {
					if (board[i][j] == pattern && board[i + 1][j - 1] == pattern) {
						sameCount ++;
					} else if (sameCount != WIN_COUNT - 1) {
						sameCount = 0;
					}
					i ++;
				}
			}
		}
		return sameCount == WIN_COUNT - 1 ? true : false;
	}
	
	/**
	 * Computer places chess randomly 
	 * @return	x, y-coordinators
	 */
	public int[] computerDo() {
		int posX = (int) (Math.random() * (ChessBoard.BOARD_SIZE - 1));
		int posY = (int) (Math.random() * (ChessBoard.BOARD_SIZE - 1));
		String[][] board = chessboard.getBoard();
		while (board[posX][posY] != "十") {
			posX = (int) (Math.random() * (ChessBoard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (ChessBoard.BOARD_SIZE - 1));
		}
		int[] result = {posX, posY};
		return result;
 	}
	
	/**
	 * Check if start a new round
	 * @param chessman, "●" is user，"○" is computer
	 * @return	ture if re-start, false if quit the program
	 * @throws Exception
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "Congratulations, you win!" : "Sorry, you lost the game!";
		System.out.println(message + "Next round?(y/n) ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().toLowerCase().equals("y")) {
			// Start a new round
			return true;
		}
		return false;
	}
	
	/**
	 * Start the game
	 * @throws Exception
	 */
	public void  start() throws Exception {
		// true means game over
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		System.out.println("Please input coordinaters in format(num, num): ");
		// Get keyboard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine: press ENTER after input a line and the input will be read in by br
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// If invalid input, require another input and go on
				continue;
			}
			// Define the corresponding element to "●"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// Check if the user wins
			if (isWon(posX, posY, chessman)) {
				isOver = true;
			} else {
				// Computer choose a position randomly
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1], chessman);
				// Check if the computer wins
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// If won, check if start next round
			if (isOver) {
				// If continue, initialize chess board and start a new round
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// If not continue, quit the program
				break;
			}
			chessboard.printBoard();
			System.out.println("Please input coordinaters in format(num, num): ");
		}
	}
	
	public static void main(String[] args) throws Exception {
		GobangGame gb = new GobangGame(new ChessBoard());
		gb.start();
	}
}