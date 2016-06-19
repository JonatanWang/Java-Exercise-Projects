package cn.wang.imageViewer.src;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import cn.wang.imageViewer.src.action.Action;;;

/**
 * Class Action to the tool bar
 */
@SuppressWarnings("serial")
public class ViewerAction extends AbstractAction {
	private String actionName = "";
	private ViewerFrame frame = null;
	private Action action = null;

	/**
	 * Constructor
	 * 
	 */
	public ViewerAction() {
		// Call parent constructor
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param icon
	 *            ImageIcon Icone
	 * @param name
	 *            String
	 */
	public ViewerAction(ImageIcon icon, String actionName, ViewerFrame frame) {
		// Call parent constructor
		super("", icon);
		this.actionName = actionName;
		this.frame = frame;
	}

	/**
	 * Rewrite void actionPerformed( ActionEvent e ) method
	 * 
	 * @param e
	 *            ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		ViewerService service = ViewerService.getInstance();
		Action action = getAction(this.actionName);
		//Call execute method in Action
		action.execute(service, frame);
	}
	
	/**
	 * Get instance by actionName
	 * @param actionName
	 * @return
	 */
	private Action getAction(String actionName) {
		try {
			if (this.action == null) {
				// Create instance of Action
				Action action = (Action)Class.forName(actionName).newInstance();
				this.action = action;
			}
			return this.action;
		} catch (Exception e) {
			return null;
		}
	}
	
	
}