package safrain.nodelet.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResourceUtil {
	public enum ResourceType {
		CLASS_PATH
	}

	public static byte[] loadResource(ResourceType type, String address) {
		switch (type) {
		case CLASS_PATH:
			return loadResourceFromClassPath(address);
		default:
			throw new IllegalArgumentException("Unknown load type : " + type);
		}
	}

	public static byte[] loadResourceFromClassPath(String path) {
		InputStream is;
		is = ResourceUtil.class.getResourceAsStream(path);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int readed;
		try {
			while ((readed = is.read(buffer)) != -1) {
				bos.write(buffer, 0, readed);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return bos.toByteArray();

	}
}
