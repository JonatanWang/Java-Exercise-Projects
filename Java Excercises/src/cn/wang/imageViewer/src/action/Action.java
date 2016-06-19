package cn.wang.imageViewer.src.action;

import cn.wang.imageViewer.src.ViewerFrame;
import cn.wang.imageViewer.src.ViewerService;

/**
 * Interface of Action for image viewer
 */
public interface Action {
	/**
	 * Method to execute
	 * @param service Business handler
	 * @param frame Object of the main interface
	 */
	void execute(ViewerService service, ViewerFrame frame);
}
