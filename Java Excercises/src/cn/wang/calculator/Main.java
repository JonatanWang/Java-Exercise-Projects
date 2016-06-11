package cn.wang.calculator;

import javax.swing.JFrame;

/**
 * Calculator entrance 
 */
public class Main {
	public static void main(String[] args) {
		CalFrame f = new CalFrame();
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}