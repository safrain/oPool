package safrain.nodelet.graphic.driver.awt;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import safrain.nodelet.graphic.BitmapResourceManager;

public class AWTBitmapResourceManager extends BitmapResourceManager {
	
	protected final List<Image> bitmaps = new ArrayList<Image>();

	@Override
	protected int doRegisterBitmapResource(byte[] bitmapData) {
		try {
			Image image = ImageIO.read(new ByteArrayInputStream(bitmapData));
			bitmaps.add(image);
			return bitmaps.size() - 1;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public double[] getBitmapSize(int id) {
		double[] size = new double[2];
		Image image = bitmaps.get(id);
		size[0] = image.getWidth(null);
		size[1] = image.getHeight(null);
		return size;
	}
}
