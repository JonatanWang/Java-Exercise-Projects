package cn.wang.imageViewer.src.action;

import cn.wang.imageViewer.src.ViewerFrame;
import cn.wang.imageViewer.src.ViewerService;

/**
 * Action to scale up
 */

public class BigAction implements Action {

	@Override
	public void execute(ViewerService service, ViewerFrame frame) {
		service.zoom(frame, true);
	}

}