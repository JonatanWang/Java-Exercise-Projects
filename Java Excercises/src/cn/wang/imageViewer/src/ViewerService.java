package cn.wang.imageViewer.src;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.util.List;
import java.util.ArrayList;

/**
 * Business class for image viewer
 */
public class ViewerService {
	private static ViewerService service = null;
	// Create a new ViewerFileChooser
	private ViewerFileChooser fileChooser = new ViewerFileChooser();
	// Set Proportion for scaling up and down
	private double range = 0.2;
	// Current folder
	private File currentDirectory = null;
	// All images under current folder
	private List<File> currentFiles = null;
	// Current image file
	private File currentFile = null;

	/**
	 * Private constructor
	 */
	private ViewerService() {
	}

	/**
	 * Get instance
	 * 
	 * @return ViewerService
	 */
	public static ViewerService getInstance() {
		if (service == null) {
			service = new ViewerService();
		}
		return service;
	}

	/**
	 * Open image
	 * 
	 * @param frame
	 *            ViewerFrame
	 * @return void
	 */
	public void open(ViewerFrame frame) {
		// If choose open
		if (fileChooser.showOpenDialog(frame) == ViewerFileChooser.APPROVE_OPTION) {
			// Set value to the current opened file
			this.currentFile = fileChooser.getSelectedFile();
			// Get directory of the current file
			String name = this.currentFile.getPath();
			// Get current folder 
			File cd = fileChooser.getCurrentDirectory();
			// If there is change in the folder
			if (cd != this.currentDirectory || this.currentDirectory == null) {
				FileFilter[] fileFilters = fileChooser
						.getChoosableFileFilters();
				File files[] = cd.listFiles();
				this.currentFiles = new ArrayList<File>();
				for (File file : files) {
					for (FileFilter filter : fileFilters) {
						// If the file is image
						if (filter.accept(file)) {
							// Add the file to currentFiles
							this.currentFiles.add(file);
						}
					}
				}
			}
			ImageIcon icon = new ImageIcon(name);
			frame.getLabel().setIcon(icon);
		}
	}

	/**
	 * Scale up and down
	 * 
	 * @param frame
	 *            ViewerFrame
	 * @return void
	 */
	public void zoom(ViewerFrame frame, boolean isEnlarge) {
		// Get the proportion
		double enLargeRange = isEnlarge ? 1 + range : 1 - range;
		// Get the current image
		ImageIcon icon = (ImageIcon) frame.getLabel().getIcon();
		if (icon != null) {
			int width = (int) (icon.getIconWidth() * enLargeRange);
			// Get the image after scaling
			ImageIcon newIcon = new ImageIcon(icon.getImage()
					.getScaledInstance(width, -1, Image.SCALE_DEFAULT));
			frame.getLabel().setIcon(newIcon);
		}
	}

	/**
	 * Choose previous image
	 * 
	 * @param frame
	 *            ViewerFrame
	 * @return void
	 */
	public void last(ViewerFrame frame) {
		// If there is image in  the folder
		if (this.currentFiles != null && !this.currentFiles.isEmpty()) {
			int index = this.currentFiles.indexOf(this.currentFile);
			// Open previous one
			if (index > 0) {
				File file = (File) this.currentFiles.get(index - 1);
				ImageIcon icon = new ImageIcon(file.getPath());
				frame.getLabel().setIcon(icon);
				this.currentFile = file;
			}
		}
	}

	/**
	 * Choose next image
	 * 
	 * @param frame
	 *            ViewerFrame
	 * @return void
	 */
	public void next(ViewerFrame frame) {
		if (this.currentFiles != null && !this.currentFiles.isEmpty()) {
			int index = this.currentFiles.indexOf(this.currentFile) + 1;
			if (index + 1 < this.currentFiles.size()) {
				File file = (File) this.currentFiles.get(index + 1);
				ImageIcon icon = new ImageIcon(file.getPath());
				frame.getLabel().setIcon(icon);
				this.currentFile = file;
			}
		}
	}

	/**
	 * Respond to the actions at menu
	 * 
	 * @param frame
	 *            ViewerFrame
	 * @param cmd
	 *            String
	 * @return void
	 */
	public void menuDo(ViewerFrame frame, String cmd) {
		// Open
		if (cmd.equals("Open(O)")) {
			open(frame);
		}
		// Scale up
		if (cmd.equals("Scale up(M)")) {
			zoom(frame, true);
		}
		// Scale down
		if (cmd.equals("Scale down(O)")) {
			zoom(frame, false);
		}
		// previous
		if (cmd.equals("Previous(X)")) {
			last(frame);
		}
		// Next
		if (cmd.equals("Next(P)")) {
			next(frame);
		}
		// Quit
		if (cmd.equals("Quit(X)")) {
			System.exit(0);
		}
	}
}