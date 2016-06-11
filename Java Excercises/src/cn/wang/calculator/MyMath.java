package cn.wang.calculator;
import java.math.BigDecimal;

/**
 * Basic operations/Calculation module
 */
public class MyMath {
	// Digital decimals to be saved
	public static final int DEFAULT_SCALE = 20;

	/**
	 * Build object of BigDecimal
	 * @param number
	 * @return
	 */
	private static BigDecimal getBigDecimal(double number) {
		return new BigDecimal(number);
	}

	/**
	 * Addition
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double result a + b
	 */
	public static double add(double num1, double num2) {
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.add(second).doubleValue();
	}

	/**
	 * Substraction
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double result a - b
	 */
	public static double subtract(double num1, double num2) {
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.subtract(second).doubleValue();
	}

	/**
	 * Multiplication
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double result a x b
	 */
	public static double multiply(double num1, double num2) {
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.multiply(second).doubleValue();
	}

	/**
	 * Division
	 * 
	 * @param num1
	 *            double
	 * @param num2
	 *            double
	 * @param reutrn
	 *            double result a / b
	 */
	public static double divide(double num1, double num2) {
		BigDecimal first = getBigDecimal(num1);
		BigDecimal second = getBigDecimal(num2);
		return first.divide(second, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

}