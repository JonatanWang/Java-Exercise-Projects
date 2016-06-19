package cn.wang.imageViewer.src.action;

import cn.wang.imageViewer.src.ViewerFrame;
import cn.wang.imageViewer.src.ViewerService;

/**
 * Action to open file
 */
public class OpenAction implements Action {

	public void execute(ViewerService service, ViewerFrame frame) {
		service.open(frame);
	}

}
