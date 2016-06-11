package cn.wang.calculator;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

/**
 * The view
 * 
 */
public class CalFrame extends JFrame {
	// Text Field to display result
	private JTextField textField = null;
	// Array to save MC, MR, MS, M+
	private String[] mOp = { "MC", "MR", "MS", "M+" };
	// Array to save result operations
	private String[] rOp = { "Back", "CE", "C" };
	// Array to save numbers and operations
	private String[] nOp = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*",
			"%", "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "=" };
	// Button for Memory buttons
	private JButton button = null;
	// Business logic class
	private CalService service = new CalService();
	// Define listener
	private ActionListener actionListener = null;
	// Panel width
	private final int PRE_WIDTH = 360;
	// Panel Height
	private final int PRE_HEIGHT = 250;

	/**
	 * Default constructor
	 */
	public CalFrame() {
		super();
		initialize();
	}

	/**
	 * Init GUI
	 * 
	 * @return void
	 */
	private void initialize() {
		// Set the title of the window
		this.setTitle("Calculator Standard");
		// Set layout management
		 this.setLayout( new BorderLayout(10,1) );
		// Set window unresizable
		this.setResizable(false);
		// Set the input area
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 1));
		panel.add(getTextField(), BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(PRE_WIDTH, PRE_HEIGHT));
		// Set M keys to the left
		JButton[] mButton = getMButton();
		// Panel，to place memory buttons	
		JPanel panel1 = new JPanel();
		// Set layout
		panel1.setLayout(new GridLayout(5, 1, 0, 5));
		// Add the keys into panel
		for (JButton b : mButton) {
			panel1.add(b);
		}
		// Add result operation keys
		JButton[] rButton = getRButton();
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(1, 5));
		JPanel panel21 = new JPanel();
		panel21.setLayout(new GridLayout(1, 3, 3, 3));
		for (JButton b : rButton) {
			panel21.add(b);
		}
		// Add numbers and other keys
		JButton[] nButton = getNButton();
		JPanel panel22 = new JPanel();
		panel22.setLayout(new GridLayout(4, 5, 3, 5));
		for (JButton b : nButton) {
			panel22.add(b);
		}
		// Add panels to frame
		panel2.add(panel21, BorderLayout.NORTH);
		panel2.add(panel22, BorderLayout.CENTER);
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.CENTER);
		this.add(panel);
	}

	/**
	 * Get the listener
	 * 
	 * @return ActionListener
	 */
	public ActionListener getActionListener() {
		if (actionListener == null) {
			actionListener = new ActionListener() {
				/**
				 * Implement method actionPerformed from interface
				 * 
				 * @param e
				 *            ActionEvent
				 * @return void
				 */
				public void actionPerformed(ActionEvent e) {
					String cmd = e.getActionCommand();
					String result = null;
					try {
						// Calculate
						result = service.callMethod(cmd, textField.getText());
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
					// Deal M keys
					if (cmd.indexOf("MC") == 0) {
						button.setText("");
					} else if (cmd.indexOf("M") == 0 && service.getStore() > 0) {
						button.setText("M");
					}
					// Set result
					if (result != null) {
						textField.setText(result);
					}
				}
			};
		}
		return actionListener;
	}

	/**
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getButton() {
		if (button == null) {
			// Build a new button		
			button = new JButton();
		}
		return button;
	}

	/**
	 * Init input area
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTextField() {
		if (textField == null) {
			// Set default 0
			textField = new JTextField("0");
			// Set uneditable
			textField.setEditable(false);
			// 设置背景为白色
			textField.setBackground(Color.white);
		}
		return textField;
	}

	/**
	 * Get M keys
	 * 
	 * @return Array to save JButtons
	 */
	private JButton[] getMButton() {
		JButton[] result = new JButton[mOp.length + 1];
		result[0] = getButton();
		for (int i = 0; i < this.mOp.length; i++) {
			
			JButton b = new JButton(this.mOp[i]);
			b.addActionListener(getActionListener());
			b.setForeground(Color.red);
			result[i + 1] = b;
		}
		return result;
	}

	/**
	 * Get result operation keys
	 * 
	 * @return Array to save buttons
	 */
	private JButton[] getRButton() {
		JButton[] result = new JButton[rOp.length];
		for (int i = 0; i < this.rOp.length; i++) {

			JButton b = new JButton(this.rOp[i]);
			b.addActionListener(getActionListener());
			b.setForeground(Color.red);
			result[i] = b;
		}
		return result;
	}

	/**
	 * Get other keys
	 * 
	 * @return Array to save buttons
	 */
	private JButton[] getNButton() {
		// Save the keys with red color
		String[] redButton = { "/", "*", "-", "+", "=" };
		JButton[] result = new JButton[nOp.length];
		for (int i = 0; i < this.nOp.length; i++) {
	
			JButton b = new JButton(this.nOp[i]);
			b.addActionListener(getActionListener());
			// Sort red keys in order to use method binarySearch
			Arrays.sort(redButton);
			// If operation is within red buttons
			if (Arrays.binarySearch(redButton, nOp[i]) >= 0) {
				b.setForeground(Color.red);
			} else {
				b.setForeground(Color.blue);
			}
			result[i] = b;
		}
		return result;
	}
}