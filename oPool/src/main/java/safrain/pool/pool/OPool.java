package safrain.pool.pool;

import java.util.HashMap;
import java.util.Map;

import safrain.pool.cache.ClassCacheContext;
import safrain.pool.reseter.AnnotationReseter;
import safrain.pool.reseter.IObjectReseter;

public class OPool {

	private Map<Class<?>, Pool> pools = new HashMap<Class<?>, Pool>();
	private static final int DEFAULT_POOL_SIZE = 1000;

	private final ClassCacheContext classCacheContext = new ClassCacheContext();

	public ClassCacheContext getClassCacheContext() {
		return classCacheContext;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> type) {
		Pool pool = getPool(type);
		return (T) pool.get();
	}

	@SuppressWarnings("unchecked")
	public <T> T getSync(Class<T> type) {
		Pool pool = getPool(type);
		return (T) pool.getSync();
	}

	public void recycle(Object object) {
		Pool pool = getPool(object.getClass());
		pool.recycle(object);
	}

	public void recycleSync(Object object) {
		Pool pool = getPool(object.getClass());
		pool.recycleSync(object);
	}

	public void reset(Object object) {
		Pool pool = getPool(object.getClass());
		pool.resetObject(object);
	}

	public Pool getPool(Class<?> type) {
		Pool pool = pools.get(type);
		if (pool == null) {
			synchronized (this) {
				if (pool == null) {
					register(type, DEFAULT_POOL_SIZE);
					pool = pools.get(type);
				}
			}
		}
		return pool;
	}

	public void register(Class<?> type, int poolSize) {
		register(type, poolSize, new AnnotationReseter(type, this));
	}

	public void register(Class<?> type, int size, IObjectReseter reseter) {
		if (pools.containsKey(type)) {
			throw new RuntimeException("Already registed type "
					+ type.getName());// TODO:message
		}
		Pool pool = new Pool();
		pool.type = type;
		pool.size = size;
		pool.objectReseter = reseter;
		pools.put(type, pool);
	}

}
