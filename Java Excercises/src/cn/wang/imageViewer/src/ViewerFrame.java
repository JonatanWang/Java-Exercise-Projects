package cn.wang.imageViewer.src;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

/**
 * Main User Interface
 * @author wang
 *
 */
@SuppressWarnings("serial")
public class ViewerFrame extends JFrame {
	// Define the size of main window
	private int width = 800;
	private int height = 600;
	// Label to place image
	JLabel label = new JLabel();
	ViewerService service = ViewerService.getInstance();
	// ActionListener to the menu
	ActionListener menuListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			service.menuDo(ViewerFrame.this, e.getActionCommand());
		}
	};
	/**
	 * Default constructor
	 */
	public ViewerFrame() {
		super();
		// Init the frame
		init();
	}
	/**
	 * Init
	 * @return void
	 */
	public void init() {
		// Set title
		this.setTitle("Image Viewer");
		// Set size
		this.setPreferredSize(new Dimension(width, height));
		// Create menu
		createMenuBar();
		// Create tool bar
		JPanel toolBar = createToolPanel();
		// Add tool bar and view area into the frame
		this.add(toolBar, BorderLayout.NORTH);
		this.add(new JScrollPane(label), BorderLayout.CENTER);
		this.setVisible(true);
		this.pack();
	}
	/**
	 * Get JLabel
	 * @return JLabel
	 */
	public JLabel getLabel() {
		return this.label;
	}
	
	/**
	 * Create tool panel
	 * @return JPanel
	 */
	public JPanel createToolPanel() {
		// Create a new JPanel
		JPanel panel = new JPanel();
		// Create a tool bar with name "Tools"
		JToolBar toolBar = new JToolBar("Tools");
		// Set the tool bar immovable
		toolBar.setFloatable(false);
		// Deploy the panel
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		// Array for tools
		String[] toolarr = {"OpenAction", 
				"LastAction", 
				"NextAction", 
				"BigAction", 
				"SmallAction" };
		for (int i = 0; i < toolarr.length; i++) {
			ImageIcon icon = new ImageIcon("imageViewer/img/"
					+ toolarr[i] + ".gif");
			System.out.println(icon.toString());
			ViewerAction action = new ViewerAction(icon, toolarr[i], this);
			// Create a new button by icon
			JButton button = new JButton(action);
			// Add button to the tool bar
			toolBar.add(button);
		}
		panel.add(toolBar);
		// Return the panel
		return panel;
	}

	/**
	 * Create menu bar
	 * 
	 * @return void
	 */
	public void createMenuBar() {
		// Create a JMenuBar to contain menu
		JMenuBar menuBar = new JMenuBar();
		// Array for the menu texts, corresponding to the items under menueItemArr
		String[] menuArr = { "File(F)", "Tools(T)", "Help(H)" };
		// Text array
		String[][] menuItemArr = { { "Open(O)", "-", "Quit(X)" },
				{ "Scale up(M)", "Scale Down(O)", "-", "Previous(X)", "Next(P)" }, { "Help Theme", "About" } };
		// Create menu by traverse menuArr and menuItemArr
		for (int i = 0; i < menuArr.length; i++) {
			// Create a new JMenu
			JMenu menu = new JMenu(menuArr[i]);
			for (int j = 0; j < menuItemArr[i].length; j++) {
				// If menuItemArr[i][j] equals "-"
				if (menuItemArr[i][j].equals("-")) {
					// Set separator for menu
					menu.addSeparator();
				} else {
					// Create a new JMenuItem
					JMenuItem menuItem = new JMenuItem(menuItemArr[i][j]);
					menuItem.addActionListener(menuListener);
					// Add menu items to menu 
					menu.add(menuItem);
				}
			}
			// Add menu to menu bar
			menuBar.add(menu);
		}
		// Set JMenubar
		this.setJMenuBar(menuBar);
	}
	
	public void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}		
	
	
}
