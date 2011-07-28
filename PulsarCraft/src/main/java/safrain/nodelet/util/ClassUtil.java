package safrain.nodelet.util;

public class ClassUtil {
	/**
	 * 获取给定类型的路径，例如：java.lang.String => /java/lang
	 */
	public static String getAbsoluteClassPath(Class<?> clazz) {
		return "/" + clazz.getPackage().getName().replace('.', '/');
	}

	public static boolean canAssign(Class<?> classToAssgin, Class<?> clazz) {
		return classToAssgin.isAssignableFrom(clazz);

	}
}
