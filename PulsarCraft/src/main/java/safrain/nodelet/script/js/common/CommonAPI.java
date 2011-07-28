package safrain.nodelet.script.js.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import safrain.nodelet.script.ScriptAPI;
import safrain.nodelet.util.ClassUtil;

public class CommonAPI extends ScriptAPI {

	@Override
	public String getName() {
		return "api_common";
	}

	@Override
	protected void afterRegister() {
		scriptService.runScript(ClassUtil.getAbsoluteClassPath(getClass())
				+ "/Common.js");
	}

	/**
	 * 输出到控制台
	 */
	public void out(String string) {
		System.out.println(string);
	}

	/**
	 * 运行js脚本
	 * 
	 * @param path
	 *            脚本的classpath路径
	 * @return 运行的脚本的返回值
	 */
	public Object runScript(String path) {
		return scriptService.runScript(path);
	}

	/**
	 * 创建指定类型的新实例
	 */
	public Object newObject(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取Class类型
	 */
	public Class<?> getClass(String className) {
		try {
			return Class.forName(className);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 检查给出的类名是不是合法
	 */
	public boolean checkClassName(String className) {
		try {
			if (Class.forName(className) != null) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public List newList() {
		return new ArrayList();
	}

	@SuppressWarnings("rawtypes")
	public Map newMap() {
		return new HashMap();
	}
}
