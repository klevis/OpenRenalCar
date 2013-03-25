package ramo.klevis.openrental.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CarImageHolder extends SWTImageCanvas {

	private static final int IMG_WIDTH = 195;
	private static final int IMG_HEIGHT = 100;
	private BufferedImage resizeImagePng;

	private String extendsion;

	private static BufferedImage resizeImage(BufferedImage originalImage,
			int type) {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
				type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();

		return resizedImage;
	}

	
	
	public void removeImage() {

		extendsion = null;
		this.setImageData(null);
		this.layout();

	}

	
	public void addImage(String url) {

		extendsion = null;
		BufferedImage originalImage;
		try {
			File file = new File(url);
			originalImage = ImageIO.read(file);
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
					: originalImage.getType();
			setResizeImagePng(resizeImage(originalImage, type));

			int indexOf = 1000;
			String substring = url;
			while (indexOf > 0) {

				if (indexOf != 1000)
					substring = substring.substring(indexOf + 1,
							substring.length());
				indexOf = substring.indexOf(".");

			}

			System.out.println("sub" + substring);
			extendsion = substring;
			ImageIO.write(getResizeImagePng(), substring, file);

			ImageData imgData = new ImageData(url);

			Image image = new Image(this.getDisplay(), imgData);

			this.setImageData(imgData);

			this.layout();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public CarImageHolder(Composite parent, int style) {
		super(parent, SWT.BORDER);

		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);

		layoutData.horizontalSpan = 2;

		layoutData.heightHint = 400;

		this.setLayoutData(layoutData);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public BufferedImage getResizeImagePng() {
		return resizeImagePng;
	}

	public void setResizeImagePng(BufferedImage resizeImagePng) {
		this.resizeImagePng = resizeImagePng;
	}

	public String getExtendsion() {
		return extendsion;
	}

	public void setExtendsion(String extendsion) {
		this.extendsion = extendsion;
	}

}
