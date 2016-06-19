package cn.wang.imageViewer.src;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import java.io.File;

/**
 * File dialog class
 */
@SuppressWarnings("serial")
public class ViewerFileChooser extends JFileChooser {
	/**
	 * Create a ImageFileChoose by the default directory
	 * 
	 * @return void
	 */
	public ViewerFileChooser() {
		super();
		setAcceptAllFileFilterUsed(false);
		addFilter();
	}

	/**
	 * Create a ViewerFileChoose by current directory
	 * 
	 * @param currentDirectoryPath String Current Directory
	 * @return void
	 */
	public ViewerFileChooser(String currentDirectoryPath) {
		super(currentDirectoryPath);
		setAcceptAllFileFilterUsed(false);
		addFilter();
	}

	/**
	 * Add file filter
	 * 
	 * @return void
	 */
	private void addFilter() {
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".BMP" },
				"BMP (*.BMP)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".JPG",
						".JPEG", ".JPE", ".JFIF" },
						"JPEG (*.JPG;*.JPEG;*.JPE;*.JFIF)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".GIF" },
				"GIF (*.GIF)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".TIF",
				".TIFF" }, "TIFF (*.TIF;*.TIFF)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".PNG" },
				"PNG (*.PNG)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".ICO" },
				"ICO (*.ICO)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".BMP",
				".JPG", ".JPEG", ".JPE", ".JFIF", ".GIF", ".TIF", ".TIFF",
				".PNG", ".ICO" }, "All image files"));
	}

	class MyFileFilter extends FileFilter {
		// Array for suffix
		String[] suffarr;
		// Description
		String decription;

		public MyFileFilter() {
			super();
		}

		/**
		 * Create a MyFileFilter by suffix array and description
		 * 
		 * @param suffarr
		 *            String[]
		 * @param decription
		 *            String
		 * @return void
		 */
		public MyFileFilter(String[] suffarr, String decription) {
			super();
			this.suffarr = suffarr;
			this.decription = decription;
		}

		/**
		 * Rewrite boolean accept( File f ) method
		 * 
		 * @param f File
		 * @return boolean
		 */
		public boolean accept(File f) {
			// If suffix legal, return true
			for (String s : suffarr) {
				if (f.getName().toUpperCase().endsWith(s)) {
					return true;
				}
			}
			// If it is directory return ture, otherwise false
			return f.isDirectory();
		}

		/**
		 * Get description
		 * 
		 * @return String
		 */
		public String getDescription() {
			return this.decription;
		}
	}

}