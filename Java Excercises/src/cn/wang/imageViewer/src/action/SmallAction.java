package cn.wang.imageViewer.src.action;

import cn.wang.imageViewer.src.ViewerFrame;
import cn.wang.imageViewer.src.ViewerService;

public class SmallAction implements Action {

	public void execute(ViewerService service, ViewerFrame frame) {
		service.zoom(frame, false);
	}

}