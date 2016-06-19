package cn.wang.imageViewer.src.action;

import cn.wang.imageViewer.src.ViewerFrame;
import cn.wang.imageViewer.src.ViewerService;

public class NextAction implements Action {

	@Override
	public void execute(ViewerService service, ViewerFrame frame) {
		service.next(frame);
	}

}