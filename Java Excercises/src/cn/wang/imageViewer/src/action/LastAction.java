package cn.wang.imageViewer.src.action;

import cn.wang.imageViewer.src.ViewerFrame;
import cn.wang.imageViewer.src.ViewerService;

/**
 * Action to view previous image
 * 
 */
public class LastAction implements Action {

	public void execute(ViewerService service, ViewerFrame frame) {
		service.last(frame);
	}

}

