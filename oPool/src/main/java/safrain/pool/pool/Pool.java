package safrain.pool.pool;

import java.util.ArrayList;
import java.util.List;

import safrain.pool.reseter.IObjectReseter;

/**
 * 某一类型的对象池
 * 
 * @author safrain
 * 
 */
public class Pool {

	final List<Object> objects = new ArrayList<Object>();

	Class<?> type;

	int size;

	IObjectReseter objectReseter;

	public Object get() {
		if (!objects.isEmpty()) {
			Object poolObject = objects.remove(objects.size() - 1);
			return poolObject;
		}
		try {
			return type.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public synchronized Object getSync() {
		return get();
	}

	public void recycle(Object poolObject) {
		if (objects.size() < size) {
			resetObject(poolObject);
			objects.add(poolObject);
		}
	}

	public synchronized void recycleSync(Object poolObject) {
		recycle(poolObject);
	}

	public void resetObject(Object object) {
		objectReseter.resetObject(object);
	}

}