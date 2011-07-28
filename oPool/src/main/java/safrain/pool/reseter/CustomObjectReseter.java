package safrain.pool.reseter;

import safrain.pool.pool.OPool;

/**
 * User define object reseter,just implement reset method
 * 
 * @author safrain
 * 
 */
public abstract class CustomObjectReseter<T> extends AbstractObjectReseter {
	protected OPool oPool;

	public CustomObjectReseter(OPool oPool) {
		this.oPool = oPool;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void resetObject(Object object) {
		reset((T) object);
	}

	public abstract void reset(T t);

	public void setoPool(OPool oPool) {
		this.oPool = oPool;
	}

}
