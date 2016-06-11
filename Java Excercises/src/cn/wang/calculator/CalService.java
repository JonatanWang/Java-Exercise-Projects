package cn.wang.calculator;

import java.math.BigDecimal;


/**
 * Calculation class, business logic
 * 
 */
public class CalService {
	// Temp memory
	private double store = 0;
	// First number to operate
	private String firstNum = null;
	// Last operation
	private String lastOp = null;
	// Second number to operate
	private String secondNum = null;
	// Check if it is the second number to be operated
	// If yes, input in the text field when click
	private boolean isSecondNum = false;
	// Numbers
	private String numString = "0123456789.";
	// Basic operations
	private String opString = "+-*/";

	/**
	 * Default constructor
	 */
	public CalService() {
		super();
	}

	/**
	 * Call methods and return result
	 * 
	 * @return String
	 */
	public String callMethod(String cmd, String text) throws Exception {
		if (cmd.equals("C")) {
			return clearAll();
		} else if (cmd.equals("CE")) {
			return clear(text);
		} else if (cmd.equals("Back")) {
			return backSpace(text);
		} else if (numString.indexOf(cmd) != -1) {
			return catNum(cmd, text);
		} else if (opString.indexOf(cmd) != -1) {
			return setOp(cmd, text);
		} else if (cmd.equals("=")) {
			return cal(text, false);
		} else if (cmd.equals("+/-")) {
			return setNegative(text);
		} else if (cmd.equals("1/x")) {
			return setReciprocal(text);
		} else if (cmd.equals("sqrt")) {
			return sqrt(text);
		} else if (cmd.equals("%")) {
			return cal(text, true);
		} else {
			return mCmd(cmd, text);
		}
	}

	/**
	 * Basic operations
	 * 
	 * @param text
	 *            String Value in the text area
	 * @param isPercent
	 *            boolean Check if it is "%" operation
	 * @return String Result in a string
	 */
	public String cal(String text, boolean isPercent) throws Exception {
		// Init the second number
		double secondResult = secondNum == null ? Double.valueOf(text)
				.doubleValue() : Double.valueOf(secondNum).doubleValue();
		// If divisor is 0, do nothing, except show 0 in the text area
		if (secondResult == 0 && this.lastOp.equals("/")) {
			return "0";
		}
		// If "%" operation 
		if (isPercent) {
			secondResult = MyMath.multiply(Double.valueOf(firstNum), MyMath
					.divide(secondResult, 100));
		}
		// Basic opeartions
		if (this.lastOp.equals("+")) {
			firstNum = String.valueOf(MyMath.add(Double.valueOf(firstNum),
					secondResult));
		} else if (this.lastOp.equals("-")) {
			firstNum = String.valueOf(MyMath.subtract(Double.valueOf(firstNum),
					secondResult));
		} else if (this.lastOp.equals("*")) {
			firstNum = String.valueOf(MyMath.multiply(Double.valueOf(firstNum),
					secondResult));
		} else if (this.lastOp.equals("/")) {
			firstNum = String.valueOf(MyMath.divide(Double.valueOf(firstNum),
					secondResult));
		}
		// Redefine the second number
		secondNum = secondNum == null ? text : secondNum;
		// Set isSecondNum as true
		this.isSecondNum = true;
		return firstNum;
	}

	/**
	 * Calculate reciprocal
	 * 
	 * @param text
	 *            String value in the text area
	 * @return String result
	 */
	public String setReciprocal(String text) {
		// If text is 0, do not calculate reciprocal
		if (text.equals("0")) {
			return text;
		} else {
			
			this.isSecondNum = true;
			return String.valueOf(MyMath.divide(1, Double.valueOf(text)));
		}
	}

	/**
	 * Calculate root
	 * 
	 * @param text
	 *            String value in the text area
	 * @return String result
	 */
	public String sqrt(String text) {
	
		this.isSecondNum = true;
		return String.valueOf(Math.sqrt(Double.valueOf(text)));
	}

	/**
	 * Set operation notations
	 * 
	 * @param cmd
	 *            String operation notation
	 * @param text
	 *            String Input value
	 * @return String Result in string
	 */
	public String setOp(String cmd, String text) {
		// Set the operation notation as last operation
		this.lastOp = cmd;
		// Set value for first number
		this.firstNum = text;
		// Set null for the second number	
		this.secondNum = null;
		this.isSecondNum = true;
		return null;
	}

	/**
	 * Set negative/positive numbers
	 * 
	 * @param text
	 *            String Input value
	 * @return String result in string
	 */
	public String setNegative(String text) {
		// Toggle
		if (text.indexOf("-") == 0) {
			return text.substring(1, text.length());
		}
		return text.equals("0") ? text : "-" + text;
	}

	/**
	 * Add the new clicked number to the string end
	 * 
	 * @param cmd
	 *            String Operation notation
	 * @param text
	 *            String Input value
	 * @return String result in string
	 */
	public String catNum(String cmd, String text) {
		String result = cmd;
		if (!text.equals("0")) {
			if (isSecondNum) {
				isSecondNum = false;
			} else {
				result = text + cmd;
			}
		}
		if (result.indexOf(".") == 0) {
			result = "0" + result;
		}
		return result;
	}

	/**
	 * Implement backspace function
	 * 
	 * @param text
	 *            String current result in the text area
	 * @return String
	 */
	public String backSpace(String text) {
		return text.equals("0") || text.equals("") ? "0" : text.substring(0,
				text.length() - 1);
	}

	/**
	 * Implement saving operation
	 * 
	 * @param cmd
	 *            String Operation notation
	 * @param text
	 *            String Current result in the text area
	 * @return String
	 */
	public String mCmd(String cmd, String text) {
		if (cmd.equals("M+")) {
			// If "M+", accumulate/add result to store
			store = MyMath.add(store, Double.valueOf(text));
		} else if (cmd.equals("MC")) {
			// If "MC"，clear store
			store = 0;
		} else if (cmd.equals("MR")) {
			// If "MR"，read the value of store
			isSecondNum = true;
			return String.valueOf(store);
		} else if (cmd.equals("MS")) {
			// If "MS"，save the result to store
			store = Double.valueOf(text).doubleValue();
		}
		return null;
	}

	/**
	 * Clear all
	 * @return String
	 */
	public String clearAll() {
		// Set first,second numbers to be default values
		this.firstNum = "0";
		this.secondNum = null;
		return this.firstNum;
	}

	/**
	 * Clear result of last operation
	 * 
	 * @param text
	 *            String Current result in the text area
	 * @return String
	 */
	public String clear(String text) {
		return "0";
	}

	/**
	 * Get the result in store
	 * 
	 * @return double
	 */
	public double getStore() {
		return this.store;
	}

}