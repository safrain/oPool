package safrain.nodelet.pool;

import java.util.HashMap;
import java.util.Map;

import safrain.nodelet.game.Game;

public class PoolService {

	private Map<Class<?>, Pool> pools = new HashMap<Class<?>, Pool>();
	private static final int DEFAULT_POOL_SIZE = 1000;

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

	public void recycle(Object poolObject) {
		Pool pool = getPool(poolObject.getClass());
		pool.recycle(poolObject);
	}

	public void recycleSync(Object poolObject) {
		Pool pool = getPool(poolObject.getClass());
		pool.recycleSync(poolObject);
	}

	private Pool getPool(Class<?> type) {
		Pool pool = pools.get(type);
		if (pool == null) {
			synchronized (this) {
				if (pool == null) {
					pool = register(type, DEFAULT_POOL_SIZE);
				}
			}
		}
		return pool;
	}

	public Pool register(Class<?> type, int poolSize) {
		Pool pool = new Pool(Game.instance.structureService.getStructure(type),
				poolSize);
		pools.put(type, pool);
		return pool;
	}

}
