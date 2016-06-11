package cn.wang.gobang;

/**
 * @author wang
 *Object of the chess board
 */
public class ChessBoard {
	// Define a 2 dimensional array as the chess board
	private String[][]  board;
	// Define the size of the chess board
	public static final int BOARD_SIZE = 15;
	
	/**
	 * Initialize the chess board
	 * @return void
	 */
	public void initBoard() {
		// Initialize the chess board array
		board = new String[BOARD_SIZE][BOARD_SIZE];
		// Define every element to be "", to output the chess board in console
		for (int i = 0; i < BOARD_SIZE; i ++) {
			for(int j = 0; j < BOARD_SIZE; j ++) {
				board[i][j] = "十";
			}
		}
	}
	
	/**
	 * Print the chess board in console
	 */
	public void printBoard() {
		for(int i = 0; i < BOARD_SIZE; i ++) {
			for (int j = 0; j < BOARD_SIZE; j ++) {
				System.out.print(board[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Set the values to the positions in the chess board
	 * By this method to change the "十" into BLACK("●") or WHITE("○");
	 * @param posX x-coordinate
	 * @param posY y-coordinate
	 * @param chessman chess
	 */
	public void setBoard(int posX, int posY, String chessman) {
		this.board[posX][posY] = chessman;
	}
	
	/**
	 * Return chess board
	 * @return board
	 */
	public String[][] getBoard() {
		return this.board;
	}
	
}