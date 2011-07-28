package safrain.nodelet.graphic;

import java.util.ArrayList;
import java.util.List;

import safrain.nodelet.util.ResourceUtil;
import safrain.nodelet.util.ResourceUtil.ResourceType;

public abstract class BitmapResourceManager {

	protected List<String> addresses = new ArrayList<String>();

	public int registerBitmapResource(String address) {
		int size = addresses.size();
		for (int i = 0; i < size; i++) {
			if (addresses.get(i).equals(address)) {
				return i;
			}
		}
		int id = addresses.size();
		addresses.add(address);
		if (id != doRegisterBitmapResource(ResourceUtil.loadResource(
				ResourceType.CLASS_PATH, address))) {
			throw new IllegalStateException();
		}
		return id;
	}

	public abstract double[] getBitmapSize(int id);

	protected abstract int doRegisterBitmapResource(byte[] bitmapData);

}
